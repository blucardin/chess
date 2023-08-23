/*
 * Noah, Harjosh, Rahul, Peter
 * ICS4UE
 * August 22 2023
 * Mr Diakoloukas
 * Help panel for speicifc pieces 
 */
// help panel for individual pieces
public class PieceHelpPanel extends javax.swing.JPanel {

    public PieceHelpPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        btnBackToHelpScreen = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/woodPattern.png"))); // NOI18N
        jLabel8.setText("jLabel4");

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(227, 213, 184));
        jLabel4.setText("Knight");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, -1, -1));

        jTextArea4.setEditable(false);
        jTextArea4.setColumns(20);
        jTextArea4.setLineWrap(true);
        jTextArea4.setRows(5);
        jTextArea4.setText("Knights move in an 'L' shape: two squares in one direction and one square perpendicular. They can jump over other pieces.");
        jTextArea4.setWrapStyleWord(true);
        jScrollPane4.setViewportView(jTextArea4);

        add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(318, 146, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(227, 213, 184));
        jLabel5.setText("Rook");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, -1, -1));

        jTextArea5.setEditable(false);
        jTextArea5.setColumns(20);
        jTextArea5.setLineWrap(true);
        jTextArea5.setRows(5);
        jTextArea5.setText("Rooks move in straight lines, either horizontally or vertically. They're excellent for controlling columns and rows.");
        jTextArea5.setWrapStyleWord(true);
        jScrollPane5.setViewportView(jTextArea5);

        add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 310, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(227, 213, 184));
        jLabel6.setText("Pawn");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 450, -1, -1));

        jTextArea6.setEditable(false);
        jTextArea6.setColumns(20);
        jTextArea6.setLineWrap(true);
        jTextArea6.setRows(5);
        jTextArea6.setText("Pawns move forward one square but capture diagonally. They promote to stronger pieces if they reach the opposite end of the board.\n");
        jTextArea6.setWrapStyleWord(true);
        jScrollPane6.setViewportView(jTextArea6);

        add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 480, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(227, 213, 184));
        jLabel1.setText("King");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, -1, -1));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("The King is the most crucial piece. It moves one square in any direction. Protect it at all costs!");
        jTextArea1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextArea1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 146, 244, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(227, 213, 184));
        jLabel2.setText("Queen");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, -1, -1));

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setText("The Queen is your most powerful piece. It can move diagonally, horizontally, or vertically, covering lots of ground.");
        jTextArea2.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jTextArea2);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(227, 213, 184));
        jLabel3.setText("Bishop");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 450, -1, -1));

        jTextArea3.setEditable(false);
        jTextArea3.setColumns(20);
        jTextArea3.setLineWrap(true);
        jTextArea3.setRows(5);
        jTextArea3.setText("Bishops move diagonally, covering the long diagonal paths on the board. Each player has two of them.");
        jTextArea3.setWrapStyleWord(true);
        jScrollPane3.setViewportView(jTextArea3);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, -1, -1));

        btnBackToHelpScreen.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnBackToHelpScreen.setText("Back to the help screen");
        btnBackToHelpScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackToHelpScreenActionPerformed(evt);
            }
        });
        add(btnBackToHelpScreen, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 610, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(84, 150, 84));
        jLabel7.setText("Pieces");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/woodPattern.png"))); // NOI18N
        jLabel9.setText("jLabel4");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, 720));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/woodPattern.png"))); // NOI18N
        jLabel10.setText("jLabel4");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, -1, 720));
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackToHelpScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackToHelpScreenActionPerformed
        // shows the help screen panel 
        ChessGame.cardLayout.show(ChessGame.cardPanel, "HelpScreenPanel");
    }//GEN-LAST:event_btnBackToHelpScreenActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackToHelpScreen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextArea6;
    // End of variables declaration//GEN-END:variables
}
