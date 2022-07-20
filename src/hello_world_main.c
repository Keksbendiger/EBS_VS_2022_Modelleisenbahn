#include <stdio.h>
#include "freertos/FreeRTOS.h"
#include "freertos/task.h"
#include "esp_system.h"
#include "esp_spi_flash.h"
#include "mqtt_client.h"
#include "esp_wifi.h"
#include "driver/gpio.h"

#define D10 1
#define RX 3
#define D8 15
#define D7 13
#define D6 12
#define D5 14
#define D4 2
#define D3 0
#define D2 4
#define D1 5
#define D0 16
#define analogInPin A0

// Refactor when building
#define sensor1 GPIO_NUM_5 // D1
#define sensor2 GPIO_NUM_4 // D2
#define sensor3 D3
#define sensor4 D4

#define point1_left GPIO_NUM_14 // D5
#define point1_right GPIO_NUM_12 // D6
#define point2_left GPIO_NUM_13 // D7
#define point2_right GPIO_NUM_15 // D8


TaskHandle_t TogglePoint1_handle = NULL;
TaskHandle_t TogglePoint2_handle = NULL;

TaskHandle_t OutgoingComm_handle = NULL;

volatile uint32_t ulIdleCycleCount = 0UL;

//MQTT Implementation
esp_mqtt_client_handle_t client_handle = NULL;

int counter1 = 0, counter2 = 0;
int noSightCount1 = 0, noSightCount2 = 0;
int lastEdge1 = 0, lastEdge2 = 0;

int NO_SIGHT_THRESHHOLD = 400;

char* point1_instruction;
char* point2_instruction;

void ReadSensorInput_1(void *pvParameters)
{
  (void) pvParameters;
  TickType_t xLastWakeTime;
  // const TickType_t xFrequency = 5; alternate implementation
  for (;;) // A Task shall never return or exit.
  {
    xLastWakeTime = xTaskGetTickCount();
    //check PIN
    vTaskDelayUntil(&xLastWakeTime, 5 / portTICK_PERIOD_MS);
    // wait for five Miliseconds
    // this is either 0 or 1
    int reading = gpio_get_level(sensor1);
    if (reading < 300) {
      // SIGHT, POSSIBLE END OR CLEAR
      if (counter1 != 0 && ++noSightCount1 == NO_SIGHT_THRESHHOLD) {
        noSightCount1 = 0;
        counter1 = 0;

      }
      lastEdge1 = 0;
    } else {
      // POSSIBLE BEGIN OR END

      if (lastEdge1 == 0) {
        counter1++;
        noSightCount1 = 0;
      }

      lastEdge1 = 1;


    //Call within an IF to send the axis data
    vTaskResume(OutgoingComm_handle);
  }
 
}
}
void ReadSensorInput_2(void *pvParameters)
{
  (void) pvParameters;
  TickType_t xLastWakeTime;
  // const TickType_t xFrequency = 5; alternate implementation

  for (;;) // A Task shall never return or exit.
  {
    xLastWakeTime = xTaskGetTickCount();
    //check PIN
    vTaskDelayUntil(&xLastWakeTime, 5 / portTICK_PERIOD_MS);
    int reading = gpio_get_level(sensor2);
    if (reading < 300) {
      // SIGHT, POSSIBLE END OR CLEAR
      if (counter1 != 0 && ++noSightCount2 == NO_SIGHT_THRESHHOLD) {
        noSightCount2 = 0;
        counter2 = 0;

      }
      lastEdge2 = 0;
    } else {
      // POSSIBLE BEGIN OR END

      if (lastEdge2 == 0) {
        counter2++;
        noSightCount1 = 0;
      }

      lastEdge2 = 1;


    //Call within an IF to send the axis data
    vTaskResume(OutgoingComm_handle);
  }
  }
}

void TogglePoint_1(void *pvParameters)
{
  (void) pvParameters;
  TickType_t xLastWakeTime;
  for (;;) {
    xLastWakeTime = xTaskGetTickCount();
    if (&point1_instruction == 'R') {
      gpio_set_level(point1_right, 1);
      vTaskDelayUntil(&xLastWakeTime, 100 / portTICK_PERIOD_MS);
      gpio_set_level(point1_right, 0);
    } else if (&point1_instruction == 'L') {
      gpio_set_level(point1_left, 1);
      vTaskDelayUntil(&xLastWakeTime, 100 / portTICK_PERIOD_MS);
      gpio_set_level(point1_left, 0);
    }
    vTaskSuspend(NULL);
  }
}

void TogglePoint_2(void *pvParameters)
{
  (void) pvParameters;

  // initialize PIN as Input
  TickType_t xLastWakeTime;
  for (;;) // A Task shall never return or exit.
  {

    xLastWakeTime = xTaskGetTickCount();
    if (point1_instruction == "R") {
      gpio_set_level(point2_right, 1);
      vTaskDelayUntil(&xLastWakeTime, 100 / portTICK_PERIOD_MS);
      gpio_set_level(point2_right, 0);
    } else if (point1_instruction == "L") {
      gpio_set_level(point2_left, 1);
      vTaskDelayUntil(&xLastWakeTime, 100 / portTICK_PERIOD_MS);
      gpio_set_level(point2_left, 0);
    }
    vTaskSuspend(NULL);
  }
}

void OutgoingComm(void *pvParameters)
{
  (void) pvParameters;
  for (;;)
  {
    //mqtt Communication
    if(false){
      esp_mqtt_client_publish(client_handle,"sensor/","",0,0,0);
    }
    //deactivate scheduling for this task until necessary
    vTaskSuspend(NULL);
  }
}

void IncommingComm(void *pvParameters)
{

  (void) pvParameters;
  TickType_t xLastWakeTime;
  // const TickType_t xFrequency = 100; alternate implementation 

  xLastWakeTime = xTaskGetTickCount();
  for (;;) // A Task shall never return or exit.
  {
    esp_mqtt_client_start(client_handle);

    vTaskDelayUntil(&xLastWakeTime, 100 / portTICK_PERIOD_MS);
  }
}

void vApplicationIdleHook(void)
{
	ulIdleCycleCount++;
}

static void mqtt_event_handler(void *handler_args, esp_event_base_t base, int32_t event_id, void *event_data){
    esp_mqtt_event_handle_t event = event_data;
    //esp_mqtt_client_handle_t client = event->client;
  
  switch ((esp_mqtt_event_id_t)event_id){
    case MQTT_EVENT_ANY:
      break;
    case MQTT_EVENT_ERROR:
      break;
    case MQTT_EVENT_CONNECTED:
      break;
    case MQTT_EVENT_DISCONNECTED:
      break;
    case MQTT_EVENT_SUBSCRIBED:
      break;
    case MQTT_EVENT_UNSUBSCRIBED:
      break;
    case MQTT_EVENT_PUBLISHED:
      break;
    case MQTT_EVENT_BEFORE_CONNECT:
      break;
    case MQTT_EVENT_DATA:
      if(event->topic == "POINTTOPIC")
      {
        point1_instruction = event->data;
        vTaskResume(TogglePoint1_handle);

      }
      if(event->topic == "otherPOint")
      {
        point2_instruction = event->data;
        vTaskResume(TogglePoint2_handle);
      }
      
  }

}

void app_main()
{   

  const esp_mqtt_client_config_t mqtt_config = {.uri="tcp://192.168.0.10:1883",.client_id="test"};
  client_handle = esp_mqtt_client_init(&mqtt_config);
  esp_mqtt_client_subscribe(client_handle, "topic", 0);
  esp_mqtt_client_register_event(client_handle, ESP_EVENT_ANY_ID, mqtt_event_handler, NULL);
    



  printf("Hello world!\n");

    /* Print chip information */
    esp_chip_info_t chip_info;
    esp_chip_info(&chip_info);
    printf("This is ESP8266 chip with %d CPU cores, WiFi, ",
            chip_info.cores);

    printf("silicon revision %d, ", chip_info.revision);

    printf("%dMB %s flash\n", spi_flash_get_chip_size() / (1024 * 1024),
            (chip_info.features & CHIP_FEATURE_EMB_FLASH) ? "embedded" : "external");

  xTaskCreate(
    ReadSensorInput_1
    , "ReadSensorInput_1"
    , 128 // Stack size
    , NULL
    , 2 // priority
    , NULL );

  xTaskCreate(
    ReadSensorInput_2
    , "ReadSensorInput_2"
    , 128 // Stack size
    , NULL
    , 2 // priority
    , NULL );

  xTaskCreate(
    TogglePoint_1
    , "TogglePoint_1"
    , 128 // Stack size
    , NULL
    , 3 // priority
    , &TogglePoint1_handle );

  xTaskCreate(
    TogglePoint_2
    , "TogglePoint_1"
    , 128 // Stack size
    , NULL
    , 3 // priority
    , &TogglePoint2_handle );

  xTaskCreate(
    OutgoingComm
    , "OutgoingComm"
    , 128 // Stack size
    , NULL
    , 1 // priority
    , &OutgoingComm_handle);

  xTaskCreate(
    IncommingComm
    , "IncommingComm"
    , 128 // Stack size
    , NULL
    , 3 // priority
    , NULL );

  vTaskStartScheduler();


    for (int i = 10; i >= 0; i--) {
        printf("Restarting in %d seconds...\n", i);
        vTaskDelay(1000 / portTICK_PERIOD_MS);
    }
    printf("Restarting now.\n");
    fflush(stdout);
    esp_restart();
}
