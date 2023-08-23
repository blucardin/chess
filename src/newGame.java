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

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("Click to play a game!");

        btnNewGame.setText("New Game");
        btnNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewGameActionPerformed(evt);
            }
        });

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel2.setText("Create a new game");

        jLabel3.setText("Double click on saved game to resume");

        btnMainMenu.setText("Back to Main Menu");
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(49, 196, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnMainMenu)
                                .addGap(219, 219, 219))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnNewGame)
                                .addGap(254, 254, 254))))
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jLabel1)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(103, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(btnNewGame)
                .addGap(74, 74, 74)
                .addComponent(jLabel3)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(btnMainMenu)
                .addGap(92, 92, 92))
        );
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
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
