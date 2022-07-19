package gui;

import core.ETrackSection;
import core.ETrackSwitch;

public class GuiHandler {

    static GuiHandler instance = null;

    private GUI gui = null;

    public static GuiHandler getInstance() {
        if(instance == null) {
            instance = new GuiHandler();
        }
        return instance;
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public GUI getGui() {
        return gui;
    }

    public void setTrackSectionUsed(ETrackSection section, boolean setUsed) {
        setTrackSectionUsed(section, setUsed, "");
    }
    public void setTrackSectionUsed(ETrackSection section, boolean setUsed, String pBarString) {
        switch (section) {
            case A:
                gui.pbars.get("G_A_PB_top").setValue(setUsed ? 100 : 0);
                gui.pbars.get("G_A_PB_left").setValue(setUsed ? 100 : 0);
                gui.pbars.get("G_A_PB_top").setString(pBarString);
                break;
            case B:
                gui.pbars.get("G_B_PB").setValue(setUsed ? 100 : 0);
                gui.pbars.get("G_B_PB").setString(pBarString);
                break;
            case C:
                gui.pbars.get("G_C_PB_left").setValue(setUsed ? 100 : 0);
                gui.pbars.get("G_C_PB_top").setValue(setUsed ? 100 : 0);
                gui.pbars.get("G_C_PB_top").setString(pBarString);
                break;
            case D:
                gui.pbars.get("G_D_PB").setValue(setUsed ? 100 : 0);
                gui.pbars.get("G_D_PB").setString(pBarString);
                break;
            case E:
                gui.pbars.get("G_E_PB").setValue(setUsed ? 100 : 0);
                gui.pbars.get("G_E_PB").setString(pBarString);
                break;
            case F:
                gui.pbars.get("G_F_PB").setValue(setUsed ? 100 : 0);
                gui.pbars.get("G_F_PB").setString(pBarString);
                break;
            case G:
                gui.pbars.get("G_G_PB_left").setValue(setUsed ? 100 : 0);
                gui.pbars.get("G_G_PB_top").setValue(setUsed ? 100 : 0);
                gui.pbars.get("G_G_PB_right").setValue(setUsed ? 100 : 0);
                gui.pbars.get("G_G_PB_top").setString(pBarString);
                break;
            case H:
                gui.pbars.get("G_H_PB").setValue(setUsed ? 100 : 0);
                gui.pbars.get("G_H_PB").setString(pBarString);
                break;
            case I:
                gui.pbars.get("G_I_PB").setValue(setUsed ? 100 : 0);
                gui.pbars.get("G_I_PB").setString(pBarString);
                break;
        }
        gui.pack();
    }

    public void setSwitchDirection(ETrackSwitch tswitch, ETrackSection direction) {
        switch (tswitch) {
            case ONE:
                if(direction == ETrackSection.A) gui.rbuttons.get("W1_BA_RB").setSelected(true);
                else if(direction == ETrackSection.G) gui.rbuttons.get("W1_BG_RB").setSelected(true);
                break;
            case TWO:
                if(direction == ETrackSection.G) gui.rbuttons.get("W2_BG_RB").setSelected(true);
                else if(direction == ETrackSection.C) gui.rbuttons.get("W2_BC_RB").setSelected(true);
                break;
            case THREE:
                if(direction == ETrackSection.D) gui.rbuttons.get("W3_CD_RB").setSelected(true);
                else if(direction == ETrackSection.H) gui.rbuttons.get("W3_CH_RB").setSelected(true);
                break;
            case FOUR:
                if(direction == ETrackSection.D) gui.rbuttons.get("W4_ED_RB").setSelected(true);
                else if(direction == ETrackSection.H) gui.rbuttons.get("W4_EH_RB").setSelected(true);
                break;
            case FIVE:
                if(direction == ETrackSection.F) gui.rbuttons.get("W5_EF_RB").setSelected(true);
                else if(direction == ETrackSection.I) gui.rbuttons.get("W5_EI_RB").setSelected(true);
                break;
            case SIX:
                if(direction == ETrackSection.F) gui.rbuttons.get("W6_AF_RB").setSelected(true);
                else if(direction == ETrackSection.I) gui.rbuttons.get("W6_AI_RB").setSelected(true);
                break;
        }
    }
}
