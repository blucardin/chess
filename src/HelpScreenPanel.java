/*
 * Noah, Harjosh, Rahul, Peter
 * ICS4UE
 * August 22 2023
 * Mr Diakoloukas
 * Help screen
 */

// help screen panel which shows help screen
public class HelpScreenPanel extends javax.swing.JPanel {
    public HelpScreenPanel() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        btnMainMenu = new javax.swing.JButton();
        btnGoToHelpPieces = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(600, 600));
        setPreferredSize(new java.awt.Dimension(600, 600));
        setSize(new java.awt.Dimension(600, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Chess is a two-player strategy board game where each player commands an army of 16 pieces, including a king, queen, rooks, knights, bishops, and pawns, set on an 8x8 grid. The objective is to checkmate the opponent's king, putting it in a position where it cannot move without being captured. Chess is a game of skill, tactics, and foresight, where players must plan their moves carefully, anticipate their opponent's actions, and use their pieces' unique abilities to control the board and ultimately outmaneuver the adversary's king to secure victory.");
        jTextArea1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextArea1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 164, 502, 135));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(227, 213, 184));
        jLabel3.setText("Main Premise of the game:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 124, -1, -1));

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setText("In chess there are a total of 6 different types of piece: Pawn, Rook, Knight, Bishop, Queen and King. Each team has a total of 16 pieces, which include 8 Pawns, 2 Rooks, 2 Knights, 2 Bishops, 1 Queen, and 1 King. \n");
        jTextArea2.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jTextArea2);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 500, -1));

        btnMainMenu.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnMainMenu.setText("Back to Main Menu");
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });
        add(btnMainMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 550, -1, -1));

        btnGoToHelpPieces.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnGoToHelpPieces.setText("Click here to Know More About the Pieces!");
        btnGoToHelpPieces.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoToHelpPiecesActionPerformed(evt);
            }
        });
        add(btnGoToHelpPieces, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 480, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(84, 150, 84));
        jLabel1.setText("How to play CHESS!");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 45, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(227, 213, 184));
        jLabel2.setText("Pieces:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/woodPattern.png"))); // NOI18N
        jLabel5.setText("jLabel4");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, -1, 720));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/woodPattern.png"))); // NOI18N
        jLabel4.setText("jLabel4");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, -1, 720));
    }// </editor-fold>//GEN-END:initComponents

    private void btnMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenuActionPerformed
        ChessGame.cardLayout.show(ChessGame.cardPanel, "StartPanel");

    }//GEN-LAST:event_btnMainMenuActionPerformed

    private void btnGoToHelpPiecesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoToHelpPiecesActionPerformed
        ChessGame.cardLayout.show(ChessGame.cardPanel, "PieceHelpPanel");
    }//GEN-LAST:event_btnGoToHelpPiecesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGoToHelpPieces;
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
