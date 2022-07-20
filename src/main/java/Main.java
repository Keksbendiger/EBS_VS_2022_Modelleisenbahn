import core.*;
import gui.GUI;
import gui.GuiHandler;
import mqtt.MqttClient;

public class Main {
    public static void main(String[] args) {
        GuiHandler guiHandler = GuiHandler.getInstance();

        //region GUI
        /* Set the Nimbus look and feel */
        //region Look and feel setting code
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //endregion Look and feel setting code

        /* Create and display the form */
        guiHandler.setGui(new GUI());
        guiHandler.getGui().setVisible(true);

        guiHandler.setTrackSectionUsed(ETrackSection.I, true, "ICE");
        guiHandler.setTrackSectionUsed(ETrackSection.C, true, "Güterzug A");
        guiHandler.setTrackSectionUsed(ETrackSection.G, true, "Güterzug B");

        guiHandler.setSwitchDirection(ETrackSwitch.ONE, ETrackSection.G);
        guiHandler.setSwitchDirection(ETrackSwitch.TWO, ETrackSection.G);
        guiHandler.setSwitchDirection(ETrackSwitch.THREE, ETrackSection.D);
        guiHandler.setSwitchDirection(ETrackSwitch.FOUR, ETrackSection.D);
        guiHandler.setSwitchDirection(ETrackSwitch.FIVE, ETrackSection.I);
        guiHandler.setSwitchDirection(ETrackSwitch.SIX, ETrackSection.I);

        //endregion GUI

        //region INIT Track System
        new TrackSection(ETrackSection.A, 3);
        new TrackSection(ETrackSection.B, 0);
        new TrackSection(ETrackSection.C, 3);
        new TrackSection(ETrackSection.D, 1);
        new TrackSection(ETrackSection.E, 1);
        new TrackSection(ETrackSection.F, 1);
        new TrackSection(ETrackSection.G, 3);
        new TrackSection(ETrackSection.H, 1);
        new TrackSection(ETrackSection.I, 1);

        new TrackSwitch(ETrackSwitch.ONE,
                TrackSection.get(ETrackSection.B),
                TrackSection.get(ETrackSection.A),
                TrackSection.get(ETrackSection.G)
        ).switchToSection(TrackSection.get(ETrackSection.G));
        new TrackSwitch(ETrackSwitch.TWO,
                TrackSection.get(ETrackSection.B),
                TrackSection.get(ETrackSection.G),
                TrackSection.get(ETrackSection.C)
        ).switchToSection(TrackSection.get(ETrackSection.C));
        new TrackSwitch(ETrackSwitch.THREE,
                TrackSection.get(ETrackSection.C),
                TrackSection.get(ETrackSection.H),
                TrackSection.get(ETrackSection.D)
        ).switchToSection(TrackSection.get(ETrackSection.D));
        new TrackSwitch(ETrackSwitch.FOUR,
                TrackSection.get(ETrackSection.E),
                TrackSection.get(ETrackSection.D),
                TrackSection.get(ETrackSection.H)
        ).switchToSection(TrackSection.get(ETrackSection.H));
        new TrackSwitch(ETrackSwitch.FIVE,
                TrackSection.get(ETrackSection.E),
                TrackSection.get(ETrackSection.I),
                TrackSection.get(ETrackSection.F)
        ).switchToSection(TrackSection.get(ETrackSection.F));
        new TrackSwitch(ETrackSwitch.SIX,
                TrackSection.get(ETrackSection.A),
                TrackSection.get(ETrackSection.F),
                TrackSection.get(ETrackSection.I)
        ).switchToSection(TrackSection.get(ETrackSection.I));
        //endregion INIT Track System

        //region INIT Trains
        new Train("ICE", 3, 12, Train.TrainDirection.CLOCKWISE);
        TrackSection.get(ETrackSection.A).block(Train.get("ICE"));
        new Train("Güterzug grün", 2, 8, Train.TrainDirection.CLOCKWISE);
        TrackSection.get(ETrackSection.C).block(Train.get("Güterzug grün"));
        new Train("Güterzug rot", 1, 6, Train.TrainDirection.COUNTERCLOCKWISE);
        TrackSection.get(ETrackSection.G).block(Train.get("Güterzug rot"));
        //endregion INIT Trains

        //region GUI Data Faker
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MqttClient.getInstance();

//        while(true) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            guiHandler.setSwitchDirection(ETrackSwitch.FIVE, ETrackSection.F);
//            guiHandler.setSwitchDirection(ETrackSwitch.SIX, ETrackSection.F);
//            guiHandler.setTrackSectionUsed(ETrackSection.G, true, "Güterzug B");
//            guiHandler.setTrackSectionUsed(ETrackSection.B, false);
//
//            try {
//                Thread.sleep(4000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            guiHandler.setSwitchDirection(ETrackSwitch.FIVE, ETrackSection.I);
//            guiHandler.setSwitchDirection(ETrackSwitch.SIX, ETrackSection.I);
//            guiHandler.setTrackSectionUsed(ETrackSection.B, true, "Güterzug B");
//            guiHandler.setTrackSectionUsed(ETrackSection.G, false);
//        }
        //endregion GUI Data Faker
    }
}
