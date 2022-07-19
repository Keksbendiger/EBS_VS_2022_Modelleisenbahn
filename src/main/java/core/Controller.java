package core;

//--------------------------------------------------//
// author:   Keksbendiger <keksbendiger@gmail.com>
// project:  EBS_VS_2022_Modelleisenbahn
// created:  19.07.2022
//--------------------------------------------------//
public class Controller {
    private static Controller instance = null;

    public static Controller get() {
        if(instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void receivedCount(int sensorId, int count) {

    }
}
