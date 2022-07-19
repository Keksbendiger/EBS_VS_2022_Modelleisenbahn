/* Hello World Example

   This example code is in the Public Domain (or CC0 licensed, at your option.)

   Unless required by applicable law or agreed to in writing, this
   software is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
   CONDITIONS OF ANY KIND, either express or implied.
*/
#include <stdio.h>
#include "freertos/FreeRTOS.h"
#include "freertos/task.h"
#include "esp_system.h"
#include "esp_spi_flash.h"

TaskHandle_t TogglePoint1_handle = NULL;
TaskHandle_t TogglePoint2_handle = NULL;

TaskHandle_t OutgoingComm_handle = NULL;

volatile uint32_t ulIdleCycleCount = 0UL;

void ReadSensorInput_1(void *pvParameters)
{
  (void) pvParameters;
  TickType_t xLastWakeTime;
  // const TickType_t xFrequency = 5; alternate implementation

  xLastWakeTime = xTaskGetTickCount();
  for (;;) // A Task shall never return or exit.
  {
    //check PIN
    vTaskDelayUntil(&xLastWakeTime, 5 / portTICK_PERIOD_MS);
    // wait for five Miliseconds

    //Call within an IF to send the axis data
    vTaskResume(OutgoingComm_handle);
  }
}

void ReadSensorInput_2(void *pvParameters)
{
  (void) pvParameters;
  TickType_t xLastWakeTime;
  // const TickType_t xFrequency = 5; alternate implementation

  xLastWakeTime = xTaskGetTickCount();
  for (;;) // A Task shall never return or exit.
  {
    //check PIN
    vTaskDelayUntil(&xLastWakeTime, 5 / portTICK_PERIOD_MS);
    // wait for five Miliseconds

    //Call within an IF to send the axis data
    vTaskResume(OutgoingComm_handle);
  }
}

void TogglePoint_1(void *pvParameters)
{
  (void) pvParameters;
  for (;;) {
    //runs once
    //deletes Task from scheduler
    vTaskSuspend(NULL);
  }
}

void TogglePoint_2(void *pvParameters)
{
  (void) pvParameters;

  // initialize PIN as Input

  for (;;) // A Task shall never return or exit.
  {
    //runs once
    //deletes Task from scheduler
    vTaskSuspend(NULL);
  }
}

void OutgoingComm(void *pvParameters)
{
  (void) pvParameters;
  for (;;)
  {
    //mqtt Communication
    //deactivate scheduling for this task until necessary
    vTaskSuspend(NULL);
  }
}

void IncommingComm(void *pvParameters)
{

  (void) pvParameters;
  TickType_t xLastWakeTime;
  // const TickType_t xFrequency = 100; alternate implementation
  BaseType_t xWasDelayed;

  xLastWakeTime = xTaskGetTickCount();
  for (;;) // A Task shall never return or exit.
  {
    //check PIN
    vTaskDelayUntil(&xLastWakeTime, 100 / portTICK_PERIOD_MS);
    // Test if we need to suspend everything else or the task blocks everything out
    //mqtt read into variables activate tasks
    //activate necessary tasks via
    vTaskResume(TogglePoint1_handle);
    vTaskResume(TogglePoint2_handle);
  }
}

void vApplicationIdleHook(void)
{
	ulIdleCycleCount++;
}

void app_main()
{    
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
