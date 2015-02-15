package rockpaperscissors;

/*
 This is a basic game of rock-paper-scissors
 The program uses a GUI window where the user
 can choose between 3 action buttons (rock,
 paper, and scissors), as well as 2 other buttons
 restart (which restarts the scores between the user
 and) the computer, and exit, which closes the game window.
 
 The first player to score 10 points is the winner.*/

import javax.swing.JFrame;

public class RockPaperScissors {

    
    public static void main(String[] args) {
        
        /*Setting up the main GUI window*/
        RpsGUI frame = new RpsGUI();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
}
