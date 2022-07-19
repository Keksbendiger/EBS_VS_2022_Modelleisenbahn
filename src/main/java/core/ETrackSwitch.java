package core;

//--------------------------------------------------//
// author:   Keksbendiger <keksbendiger@gmail.com>
// project:  EBS_VS_2022_Modelleisenbahn
// created:  19.07.2022
//--------------------------------------------------//
public enum ETrackSwitch {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6);

    private final int id;

    ETrackSwitch(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
