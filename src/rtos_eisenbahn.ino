

#include <Arduino.h>
#include <Arduino_FreeRTOS.h>
//#include <MQTT.h>

void ReadSensorInput_1( void *pvParameters );
void ReadSensorInput_2 ( void *pvParameters );
void TogglePoint_1 ( void *pvParameters );
void TogglePoint_2 ( void *pvParameters );

void OutgoingComm( void *pvParameters );
void IncommingComm( void *pvParamerters);

TaskHandle_t TogglePoint1_handle = NULL;
TaskHandle_t TogglePoint2_handle = NULL;

TaskHandle_t OutgoingComm_handle = NULL;

int axes_at_1;
int axes_at_2;

void setup() {
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
    , &OutgoingComm_handle );

  xTaskCreate(
    IncommingComm
    , "IncommingComm"
    , 128 // Stack size
    , NULL
    , 3 // priority
    , NULL );

  vTaskStartScheduler();

}

void loop() {
  // put your main code here, to run repeatedly:

}

void ReadSensorInput_1(void *pvParameters)
{
  (void) pvParameters;
  TickType_t xLastWakeTime;
  // const TickType_t xFrequency = 5; alternate implementation
  BaseType_t xWasDelayed;

  xLastWakeTime = xTaskGetTickCount();
  for (;;) // A Task shall never return or exit.
  {
    //check PIN
    xWasDelayed = xTaskDelayUntil(&xLastWakeTime, 5 / portTICK_PERIOD_MS);
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
  BaseType_t xWasDelayed;

  xLastWakeTime = xTaskGetTickCount();
  for (;;) // A Task shall never return or exit.
  {
    //check PIN
    xWasDelayed = xTaskDelayUntil(&xLastWakeTime, 5 / portTICK_PERIOD_MS);
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
    xWasDelayed = xTaskDelayUntil(&xLastWakeTime, 100 / portTICK_PERIOD_MS);
    // Test if we need to suspend everything else or the task blocks everything out
    //mqtt read into variables activate tasks
    //activate necessary tasks via
    vTaskResume(TogglePoint1_handle);
    vTaskResume(TogglePoint2_handle);
  }


}
