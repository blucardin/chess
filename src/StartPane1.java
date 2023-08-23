/*
 * Noah, Harjosh, Rahul, Peter
 * ICS4UE
 * August 22 2023
 * Mr Diakoloukas
 * Default start panel
 */

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

/**
 *
 * @author sam
 */
public class StartPane1 extends javax.swing.JPanel {

    /**
     * Creates new form startPane1
     */
    public StartPane1() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        btnStartGame = new javax.swing.JButton();
        btnHelp = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/chessMenu.png"))); // NOI18N
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, -1));

        btnStartGame.setText("Start Game");
        btnStartGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartGameActionPerformed(evt);
            }
        });
        add(btnStartGame, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, 160, 80));

        btnHelp.setText("jButton1");
        btnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpActionPerformed(evt);
            }
        });
        add(btnHelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 480, 120, 70));
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartGameActionPerformed
        // creates new game
        ChessGame.cardLayout.show(ChessGame.cardPanel, "newGame");
    }//GEN-LAST:event_btnStartGameActionPerformed

    private void btnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpActionPerformed
        // shows help screen panel
        ChessGame.cardLayout.show(ChessGame.cardPanel, "HelpScreenPanel");
    }//GEN-LAST:event_btnHelpActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHelp;
    private javax.swing.JButton btnStartGame;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
