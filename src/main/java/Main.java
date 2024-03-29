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

//        guiHandler.setTrackSectionUsed(ETrackSection.I, true, "ICE");
//        guiHandler.setTrackSectionUsed(ETrackSection.C, true, "Güterzug A");
//        guiHandler.setTrackSectionUsed(ETrackSection.G, true, "Güterzug B");

//        guiHandler.setSwitchDirection(ETrackSwitch.ONE, ETrackSection.G);
//        guiHandler.setSwitchDirection(ETrackSwitch.TWO, ETrackSection.G);
//        guiHandler.setSwitchDirection(ETrackSwitch.THREE, ETrackSection.D);
//        guiHandler.setSwitchDirection(ETrackSwitch.FOUR, ETrackSection.D);
//        guiHandler.setSwitchDirection(ETrackSwitch.FIVE, ETrackSection.I);
//        guiHandler.setSwitchDirection(ETrackSwitch.SIX, ETrackSection.I);

        //endregion GUI

        //region INIT Track System
        new TrackSection(ETrackSection.A, 0.3);
        new TrackSection(ETrackSection.B, 0);
        new TrackSection(ETrackSection.C, 0.5);
        new TrackSection(ETrackSection.D, 0.2);
        new TrackSection(ETrackSection.E, 0);
        new TrackSection(ETrackSection.F, 0.2);
        new TrackSection(ETrackSection.G, 13.5);
        new TrackSection(ETrackSection.H, 0.2);
        new TrackSection(ETrackSection.I, 0.2);

        new TrackSwitch(ETrackSwitch.ONE,
                TrackSection.get(ETrackSection.B),
                TrackSection.get(ETrackSection.A),
                TrackSection.get(ETrackSection.G)
        );
        new TrackSwitch(ETrackSwitch.TWO,
                TrackSection.get(ETrackSection.B),
                TrackSection.get(ETrackSection.G),
                TrackSection.get(ETrackSection.C)
        );
        new TrackSwitch(ETrackSwitch.THREE,
                TrackSection.get(ETrackSection.C),
                TrackSection.get(ETrackSection.H),
                TrackSection.get(ETrackSection.D)
        );
        new TrackSwitch(ETrackSwitch.FOUR,
                TrackSection.get(ETrackSection.E),
                TrackSection.get(ETrackSection.D),
                TrackSection.get(ETrackSection.H)
        );
        new TrackSwitch(ETrackSwitch.FIVE,
                TrackSection.get(ETrackSection.E),
                TrackSection.get(ETrackSection.I),
                TrackSection.get(ETrackSection.F)
        );
        new TrackSwitch(ETrackSwitch.SIX,
                TrackSection.get(ETrackSection.A),
                TrackSection.get(ETrackSection.F),
                TrackSection.get(ETrackSection.I)
        );
        //endregion INIT Track System

        MqttClient.getInstance().initializeBus();

        //region INIT Trains
        Train ice = new Train("ICE", 1, 3, 20, Train.TrainDirection.COUNTERCLOCKWISE, 1);
        TrackSection.get(ETrackSection.A).block(ice);
        new TrackSectionEnterRequest(ice, TrackSection.get(ETrackSection.A), TrackSection.get(ETrackSection.B));

        //endregion INIT Trains
        //  "BR215" ID=4 // "BR142" ID=5 | 28   // "ICE alt" ID=10

        Train cargo = new Train("BR110", 3, 2, 12, Train.TrainDirection.COUNTERCLOCKWISE, 8);
        TrackSection.get(ETrackSection.D).block(cargo);
        new TrackSectionEnterRequest(cargo, TrackSection.get(ETrackSection.D), TrackSection.get(ETrackSection.E));

        Train br142 = new Train("BR142", 5, 1, 12, Train.TrainDirection.CLOCKWISE, 1);
        TrackSection.get(ETrackSection.G).block(br142);
        new TrackSectionEnterRequest(br142, TrackSection.get(ETrackSection.G), TrackSection.get(ETrackSection.B));

        Train br215 = new Train("BR215", 4, 1, 16, Train.TrainDirection.COUNTERCLOCKWISE, 8);
        TrackSection.get(ETrackSection.H).block(br215);
        new TrackSectionEnterRequest(br215, TrackSection.get(ETrackSection.H), TrackSection.get(ETrackSection.E));
        //region GUI Data Faker


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
