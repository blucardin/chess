/*
 * Noah, Harjosh, Rahul, Peter
 * ICS4UE
 * August 22 2023
 * Mr Diakoloukas
 * New game which stores and loads games
 */

import java.io.File;

// new game screen which stores and loads games
public class newGame extends javax.swing.JPanel {

    public newGame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnNewGame = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnMainMenu = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(227, 213, 184));
        jLabel1.setText("Play Chess!");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, -1, -1));

        btnNewGame.setBackground(new java.awt.Color(84, 150, 84));
        btnNewGame.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnNewGame.setText("New Game");
        btnNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewGameActionPerformed(evt);
            }
        });
        add(btnNewGame, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, -1, -1));

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, 360, 188));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(227, 213, 184));
        jLabel2.setText("Click on the button to create a new game");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, -1, -1));

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(227, 213, 184));
        jLabel3.setText("Or double click on a saved game to resume");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, -1, -1));

        btnMainMenu.setBackground(new java.awt.Color(84, 150, 84));
        btnMainMenu.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnMainMenu.setText("Back to Main Menu");
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });
        add(btnMainMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 600, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/woodPattern.png"))); // NOI18N
        jLabel4.setText("jLabel4");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, -1, 720));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/woodPattern.png"))); // NOI18N
        jLabel5.setText("jLabel4");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, -1, 720));
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewGameActionPerformed
        // creates new board
        ChessGame.board = new Board("defaultBoard.txt");
        // shows board
        ChessGame.cardLayout.show(ChessGame.cardPanel, "ChessBoardPanel");
    }//GEN-LAST:event_btnNewGameActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // check if the mouse was double-clicked
        if (evt.getClickCount() == 2) {
            // get the name of the selected game from the GUI list
            String selectedGame = jList1.getSelectedValue();

            // if a game is selected
            if (selectedGame != null) {
                // create a new ChessGame board with the selected game's file
                ChessGame.board = new Board("src/savedGames/" + selectedGame);

                // create a File object for the selected game's file
                File file = new File("src/savedGames/" + selectedGame);

                // delete the selected game's file
                file.delete();

                // switch the card layout to the ChessBoardPanel
                ChessGame.cardLayout.show(ChessGame.cardPanel, "ChessBoardPanel");
            }
        }
    }//GEN-LAST:event_jList1MouseClicked

    private void btnMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenuActionPerformed
        // shows main menu
        ChessGame.cardLayout.show(ChessGame.cardPanel, "StartPanel");
    }//GEN-LAST:event_btnMainMenuActionPerformed

    public void refresh() {
        String savedGamesDirectory = "src/savedGames";

        // create a File object representing the directory
        File directory = new File(savedGamesDirectory);

        // check if the directory exists and is indeed a directory
        if (directory.exists() && directory.isDirectory()) {
            // get an array of File objects representing files in the directory
            File[] files = directory.listFiles();

            // check if files were found in the directory
            if (files != null && files.length > 0) {
                // create an array to store the names of the files
                String[] fileNames = new String[files.length];

                // iterate through the files and extract their names
                for (int i = 0; i < files.length; i++) {
                    fileNames[i] = files[i].getName();
                }

                // populate a GUI list with the file names
                jList1.setListData(fileNames);
            } else {
                // if no files were found, display a message in the GUI list
                jList1.setListData(new String[]{"No saved games found"});
            }
        } else {
            // if the directory doesn't exist, display an error message in the GUI list
            jList1.setListData(new String[]{"Saved games directory not found"});
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JButton btnNewGame;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
