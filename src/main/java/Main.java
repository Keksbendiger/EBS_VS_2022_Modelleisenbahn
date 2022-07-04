import gui.GUI;
import gui.GuiHandler;
import mqtt.MqttClient;

import java.util.Random;

public class Main {
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
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
        //</editor-fold>
        GuiHandler guiHandler = GuiHandler.getInstance();

        MqttClient.getInstance();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                guiHandler.setGui(new GUI());
                guiHandler.getGui().setVisible(true);

                guiHandler.setTrackSectionUsed(GuiHandler.ETrackSection.I, true, "ICE");
                guiHandler.setTrackSectionUsed(GuiHandler.ETrackSection.C, true, "Güterzug A");
                guiHandler.setTrackSectionUsed(GuiHandler.ETrackSection.G, true, "Güterzug B");

                guiHandler.setSwitchDirection(GuiHandler.ETrackSwitch.ONE, GuiHandler.ETrackSection.G);
                guiHandler.setSwitchDirection(GuiHandler.ETrackSwitch.TWO, GuiHandler.ETrackSection.G);
                guiHandler.setSwitchDirection(GuiHandler.ETrackSwitch.THREE, GuiHandler.ETrackSection.D);
                guiHandler.setSwitchDirection(GuiHandler.ETrackSwitch.FOUR, GuiHandler.ETrackSection.D);
                guiHandler.setSwitchDirection(GuiHandler.ETrackSwitch.FIVE, GuiHandler.ETrackSection.I);
                guiHandler.setSwitchDirection(GuiHandler.ETrackSwitch.SIX, GuiHandler.ETrackSection.I);
            }
        });

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            MqttClient.getInstance().testPublish("sensor/1", "bla");
            MqttClient.getInstance().testPublish("sensor/16", "blub");
            guiHandler.setSwitchDirection(GuiHandler.ETrackSwitch.FIVE, GuiHandler.ETrackSection.F);
            guiHandler.setSwitchDirection(GuiHandler.ETrackSwitch.SIX, GuiHandler.ETrackSection.F);
            guiHandler.setTrackSectionUsed(GuiHandler.ETrackSection.G, true, "Güterzug B");
            guiHandler.setTrackSectionUsed(GuiHandler.ETrackSection.B, false);

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            guiHandler.setSwitchDirection(GuiHandler.ETrackSwitch.FIVE, GuiHandler.ETrackSection.I);
            guiHandler.setSwitchDirection(GuiHandler.ETrackSwitch.SIX, GuiHandler.ETrackSection.I);
            guiHandler.setTrackSectionUsed(GuiHandler.ETrackSection.B, true, "Güterzug B");
            guiHandler.setTrackSectionUsed(GuiHandler.ETrackSection.G, false);
        }
    }
}
