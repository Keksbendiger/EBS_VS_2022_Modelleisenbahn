/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import mqtt.MqttClient;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author keksb
 */
public class GUI extends javax.swing.JFrame {

    public Map<String, JProgressBar> pbars = new HashMap<>();
    public Map<String, JRadioButton> rbuttons = new HashMap<>();
    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
    }

    private JProgressBar addProgressBar(JProgressBar pb, String name) {
        pbars.put(name, pb);
        return pb;
    }

    private JRadioButton addRadioButton(JRadioButton rb, String name) {
        rbuttons.put(name, rb);
        return rb;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        bg_W1 = new javax.swing.ButtonGroup();
        bg_W2 = new javax.swing.ButtonGroup();
        bg_W3 = new javax.swing.ButtonGroup();
        bg_W4 = new javax.swing.ButtonGroup();
        bg_W5 = new javax.swing.ButtonGroup();
        bg_W6 = new javax.swing.ButtonGroup();
        G_A_PB_top = addProgressBar(new javax.swing.JProgressBar(), "G_A_PB_top");
        G_A_PB_left = addProgressBar(new javax.swing.JProgressBar(), "G_A_PB_left");
        G_B_PB = addProgressBar(new javax.swing.JProgressBar(), "G_B_PB");
        G_C_PB_top = addProgressBar(new javax.swing.JProgressBar(), "G_C_PB_top");
        G_C_PB_left = addProgressBar(new javax.swing.JProgressBar(), "G_C_PB_left");
        G_D_PB = addProgressBar(new javax.swing.JProgressBar(), "G_D_PB");
        G_E_PB = addProgressBar(new javax.swing.JProgressBar(), "G_E_PB");
        G_F_PB = addProgressBar(new javax.swing.JProgressBar(), "G_F_PB");
        G_G_PB_top = addProgressBar(new javax.swing.JProgressBar(), "G_G_PB_top");
        G_G_PB_left = addProgressBar(new javax.swing.JProgressBar(), "G_G_PB_left");
        G_G_PB_right = addProgressBar(new javax.swing.JProgressBar(), "G_G_PB_right");
        G_H_PB = addProgressBar(new javax.swing.JProgressBar(), "G_H_PB");
        G_I_PB = addProgressBar(new javax.swing.JProgressBar(), "G_I_PB");

        W1_BA_RB = addRadioButton(new javax.swing.JRadioButton(), "W1_BA_RB");
        W1_BG_RB = addRadioButton(new javax.swing.JRadioButton(), "W1_BG_RB");
        W2_BG_RB = addRadioButton(new javax.swing.JRadioButton(), "W2_BG_RB");
        W2_BC_RB = addRadioButton(new javax.swing.JRadioButton(), "W2_BC_RB");
        W3_CH_RB = addRadioButton(new javax.swing.JRadioButton(), "W3_CH_RB");
        W3_CD_RB = addRadioButton(new javax.swing.JRadioButton(), "W3_CD_RB");
        W4_EH_RB = addRadioButton(new javax.swing.JRadioButton(), "W4_EH_RB");
        W4_ED_RB = addRadioButton(new javax.swing.JRadioButton(), "W4_ED_RB");
        W5_EF_RB = addRadioButton(new javax.swing.JRadioButton(), "W5_EF_RB");
        W5_EI_RB = addRadioButton(new javax.swing.JRadioButton(), "W5_EI_RB");
        W6_AF_RB = addRadioButton(new javax.swing.JRadioButton(), "W6_AF_RB");
        W6_AI_RB = addRadioButton(new javax.swing.JRadioButton(), "W6_AI_RB");

        Label_W_1A = new javax.swing.JLabel();
        Label_W_1G = new javax.swing.JLabel();
        Label_W_2G = new javax.swing.JLabel();
        Label_W_2C = new javax.swing.JLabel();

        Label_A = new javax.swing.JLabel();
        Label_B = new javax.swing.JLabel();
        Label_C = new javax.swing.JLabel();
        Label_D = new javax.swing.JLabel();
        Label_E = new javax.swing.JLabel();
        Label_F = new javax.swing.JLabel();
        Label_G = new javax.swing.JLabel();
        Label_H = new javax.swing.JLabel();
        Label_I = new javax.swing.JLabel();

        G_A_PB_top.setStringPainted(true);
        G_B_PB.setStringPainted(true);
        G_C_PB_top.setStringPainted(true);
        G_D_PB.setStringPainted(true);
        G_E_PB.setStringPainted(true);
        G_F_PB.setStringPainted(true);
        G_G_PB_top.setStringPainted(true);
        G_H_PB.setStringPainted(true);
        G_I_PB.setStringPainted(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        G_A_PB_left.setOrientation(1);

        bg_W6.add(W6_AF_RB);
        W6_AF_RB.setText("W6-AF");
        W6_AF_RB.setEnabled(false);
        W6_AF_RB.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        bg_W6.add(W6_AI_RB);
        W6_AI_RB.setText("W6-AI");
        W6_AI_RB.setEnabled(false);
        W6_AI_RB.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        bg_W5.add(W5_EF_RB);
        W5_EF_RB.setText("W5-EF");
        W5_EF_RB.setEnabled(false);

        bg_W5.add(W5_EI_RB);
        W5_EI_RB.setText("W5-EI");
        W5_EI_RB.setEnabled(false);

        G_C_PB_left.setOrientation(1);

        bg_W3.add(W3_CH_RB);
        W3_CH_RB.setText("W3-CH");
        W3_CH_RB.setEnabled(false);
        W3_CH_RB.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        bg_W3.add(W3_CD_RB);
        W3_CD_RB.setText("W3-CD");
        W3_CD_RB.setEnabled(false);
        W3_CD_RB.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        bg_W4.add(W4_EH_RB);
        W4_EH_RB.setText("W4-EH");
        W4_EH_RB.setEnabled(false);

        bg_W4.add(W4_ED_RB);
        W4_ED_RB.setText("W4-ED");
        W4_ED_RB.setEnabled(false);

        G_G_PB_right.setOrientation(1);

        G_G_PB_left.setOrientation(1);

        bg_W1.add(W1_BA_RB);
        W1_BA_RB.setEnabled(false);

        bg_W1.add(W1_BG_RB);
        W1_BG_RB.setEnabled(false);

        bg_W2.add(W2_BG_RB);
        W2_BG_RB.setEnabled(false);

        bg_W2.add(W2_BC_RB);
        W2_BC_RB.setEnabled(false);

        G_E_PB.setOrientation(1);

        Label_W_1A.setText("1A");
        Label_W_1A.setEnabled(false);

        Label_W_1G.setText("1G");
        Label_W_1G.setEnabled(false);

        Label_W_2G.setText("2G");
        Label_W_2G.setEnabled(false);

        Label_W_2C.setText("2C");
        Label_W_2C.setEnabled(false);

        Label_A.setText("A");

        Label_G.setText("G");

        Label_B.setText("B");

        Label_C.setText("C");

        Label_H.setText("H");

        Label_D.setText("D");

        Label_E.setText("E");

        Label_I.setText("I");

        Label_F.setText("F");

        bt_Bus = new JButton();
        bt_ICE = new JButton();
        bt_BR110 = new JButton();
        bt_BR142 = new JButton();
        bt_BR215 = new JButton();
        new BusButton("Bus", "*", bt_Bus);
        new BusButton("ICE", "1", bt_ICE);
        new BusButton("BR110", "3", bt_BR110);
        new BusButton("BR142", "5", bt_BR142);
        new BusButton("BR215", "4", bt_BR215);

        // Settings for all Progress Bars
        for (Map.Entry<String, JProgressBar> entry : pbars.entrySet()) {
            entry.getValue().setStringPainted(true);
            entry.getValue().setString("");
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(G_A_PB_left, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(W1_BA_RB))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(Label_I))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(44, 44, 44)
                                                                                .addComponent(G_G_PB_top, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(Label_H)))
                                                                .addGap(345, 345, 345))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(Label_E, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(W1_BG_RB)
                                                                                                .addComponent(Label_W_1G)
                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                        .addComponent(G_G_PB_left, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addGap(70, 70, 70)
                                                                                                        .addComponent(Label_G)))
                                                                                        .addGap(61, 61, 61)
                                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                        .addComponent(G_G_PB_right, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addComponent(W2_BG_RB))
                                                                                                .addComponent(Label_W_2G))
                                                                                        .addGap(18, 18, 18)
                                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                                                        .addComponent(G_C_PB_left, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                        .addGap(32, 32, 32)
                                                                                                                        .addComponent(Label_C)
                                                                                                                        .addGap(218, 218, 218))
                                                                                                                .addComponent(G_C_PB_top, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(Label_W_2C)
                                                                                                                .addComponent(W2_BC_RB))
                                                                                                        .addGap(0, 0, Short.MAX_VALUE)))
                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                        .addComponent(W3_CH_RB)
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                        .addComponent(G_H_PB, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                        .addComponent(W3_CD_RB)
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(G_D_PB, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                                                        .addComponent(Label_D)
                                                                                                                        .addGap(242, 242, 242)))))
                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(W4_EH_RB)
                                                                                                .addComponent(W4_ED_RB)))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                        .addComponent(Label_A)
                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                        .addComponent(W6_AF_RB)
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                        .addComponent(G_F_PB, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                        .addComponent(W6_AI_RB)
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                        .addComponent(G_I_PB, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(W5_EF_RB)
                                                                                                .addComponent(W5_EI_RB))))
                                                                        .addComponent(G_A_PB_top, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)))
                                                .addComponent(G_E_PB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(35, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(Label_W_1A)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(10, 10, 10)
                                                                .addComponent(G_B_PB, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(146, 146, 146)
                                .addComponent(Label_B)

                                // HAND MADE
                                .addGap(20, 20, 20)
                                .addComponent(bt_Bus, 120, 120, 120)
                                .addComponent(bt_ICE, 120, 120, 120)
                                .addComponent(bt_BR110, 120, 120, 120)
                                .addComponent(bt_BR142, 120, 120, 120)
                                .addComponent(bt_BR215, 120, 120, 120)

                                // END HAND MADE

                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Label_F)
                                .addGap(398, 398, 398))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(Label_F)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(G_G_PB_left, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(277, 277, 277)
                                                        .addComponent(G_C_PB_left, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(196, 196, 196)
                                                        .addComponent(G_G_PB_top, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(G_G_PB_right, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(Label_G)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(G_E_PB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                                .addComponent(W5_EF_RB)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(W5_EI_RB))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(W6_AF_RB)
                                                                                        .addComponent(G_F_PB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGap(2, 2, 2)
                                                                                .addComponent(G_A_PB_top, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(2, 2, 2)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(W6_AI_RB)
                                                                                                .addComponent(Label_A))
                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(G_I_PB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(Label_I)))))
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                .addComponent(Label_E)
                                                                                .addGap(44, 44, 44)
                                                                                .addComponent(Label_H)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(W4_EH_RB)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(W4_ED_RB))
                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                        .addComponent(W3_CH_RB)
                                                                                                        .addComponent(G_H_PB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(18, 18, 18)
                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                        .addComponent(W3_CD_RB)
                                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                                .addComponent(G_D_PB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                .addComponent(Label_D))))))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(186, 186, 186)
                                                                                .addComponent(G_C_PB_top, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(22, 22, 22)
                                                                                .addComponent(Label_C)
                                                                        ))))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(44, 44, 44)
                                                        .addComponent(G_A_PB_left, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(W2_BG_RB)
                                        .addComponent(W1_BA_RB)
                                        .addComponent(W1_BG_RB)
                                        .addComponent(W2_BC_RB))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(Label_W_1A)
                                                .addComponent(Label_W_1G))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(Label_W_2G)
                                                .addComponent(Label_W_2C)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(G_B_PB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(Label_B)

                                // HAND MADE
                                .addGap(20, 20, 20)
                                .addComponent(bt_Bus, 20, 20, 20)
                                .addComponent(bt_ICE, 20, 20, 20)
                                .addComponent(bt_BR110, 20, 20, 20)
                                .addComponent(bt_BR142, 20, 20, 20)
                                .addComponent(bt_BR215, 20, 20, 20)

                                // END HAND MADE

                                .addContainerGap(318, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    // Variables declaration - do not modify
    private javax.swing.JProgressBar G_A_PB_left;
    private javax.swing.JProgressBar G_A_PB_top;
    private javax.swing.JProgressBar G_B_PB;
    private javax.swing.JProgressBar G_C_PB_left;
    private javax.swing.JProgressBar G_C_PB_top;
    private javax.swing.JProgressBar G_D_PB;
    private javax.swing.JProgressBar G_E_PB;
    private javax.swing.JProgressBar G_F_PB;
    private javax.swing.JProgressBar G_G_PB_left;
    private javax.swing.JProgressBar G_G_PB_right;
    private javax.swing.JProgressBar G_G_PB_top;
    private javax.swing.JProgressBar G_H_PB;
    private javax.swing.JProgressBar G_I_PB;
    private javax.swing.JLabel Label_A;
    private javax.swing.JLabel Label_B;
    private javax.swing.JLabel Label_C;
    private javax.swing.JLabel Label_D;
    private javax.swing.JLabel Label_E;
    private javax.swing.JLabel Label_F;
    private javax.swing.JLabel Label_G;
    private javax.swing.JLabel Label_H;
    private javax.swing.JLabel Label_I;
    private javax.swing.JLabel Label_W_1A;
    private javax.swing.JLabel Label_W_1G;
    private javax.swing.JLabel Label_W_2C;
    private javax.swing.JLabel Label_W_2G;
    private javax.swing.JRadioButton W1_BA_RB;
    private javax.swing.JRadioButton W1_BG_RB;
    private javax.swing.JRadioButton W2_BC_RB;
    private javax.swing.JRadioButton W2_BG_RB;
    private javax.swing.JRadioButton W3_CD_RB;
    private javax.swing.JRadioButton W3_CH_RB;
    private javax.swing.JRadioButton W4_ED_RB;
    private javax.swing.JRadioButton W4_EH_RB;
    private javax.swing.JRadioButton W5_EF_RB;
    private javax.swing.JRadioButton W5_EI_RB;
    private javax.swing.JRadioButton W6_AF_RB;
    private javax.swing.JRadioButton W6_AI_RB;
    private javax.swing.ButtonGroup bg_W1;
    private javax.swing.ButtonGroup bg_W2;
    private javax.swing.ButtonGroup bg_W3;
    private javax.swing.ButtonGroup bg_W4;
    private javax.swing.ButtonGroup bg_W5;
    private javax.swing.ButtonGroup bg_W6;
    private javax.swing.JButton bt_Bus;
    private javax.swing.JButton bt_ICE;
    private javax.swing.JButton bt_BR110;
    private javax.swing.JButton bt_BR142;
    private javax.swing.JButton bt_BR215;
    // End of variables declaration
}
