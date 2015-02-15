package tictactoegui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * This is a basic tic tac toe GUI. If a player gets 3 in a row (vertically,
 * horizontally, or diagonally, then that player is the winner. Otherwise, the
 * game ends in a draw.)
 */

public class TicTacToeGUI extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static JFrame frame; //Holds the window of the GUI
    public static final int WIDTH = 300; //the width of the window
    public static final int HEIGHT = 300; //the height of the window
    private GridBagLayout layout; //how the buttons will be laid out in the GUI
    private GridBagConstraints gbc; //defining the location and size of buttons
    private static JButton[] button; //the buttons that the user(s) will press
    
    private static int count = 0; //count how many moves there are, used mainly for draw games
    private static boolean winner; //determining if there is a winner
    private static String letter = ""; //X's and O's
    
    //[0] = gridx, [1] = gridy, [2] = gridwidth, [3] = gridheight, how the buttons are laid out
    private int[][] buttonConstraints = new int[][]{
        {0, 0, 1, 1}, //button 1
        {1, 0, 1, 1}, //button 2
        {2, 0, 1, 1}, //button 3
        {0, 1, 1, 1}, //button 4
        {1, 1, 1, 1}, //button 5
        {2, 1, 1, 1}, //button 6
        {0, 2, 1, 1}, //button 7
        {1, 2, 1, 1}, //button 8
        {2, 2, 1, 1}  //button 9
    };

    public static void main(String[] args) {

        frame = new JFrame("Tic Tac Toe"); //initialize the JFrame and set the title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close the program safely
        frame.setResizable(false); //user is not allowed to resize the window
        frame.setLayout(new BorderLayout()); //layout of the GUI
        frame.add(new TicTacToeGUI(), BorderLayout.CENTER); //Make the components toward the center
        frame.pack(); //pack in the components so it doesn't waste space inside the window
        frame.setLocationRelativeTo(null); //when program executes, open in middle of screen
        frame.setVisible(true); //make the program visible when executed
    }

    public TicTacToeGUI() { //constructor for the TicTacToeGUI class

        setPreferredSize(new Dimension(WIDTH, HEIGHT)); //set the size of the GUI

        layout = new GridBagLayout(); //initialize the GridBagLayout constructor
        layout.columnWidths = new int[]{100, 100, 100}; //The width of the column for the buttons
        layout.rowHeights = new int[]{100, 100, 100};   //The height of the row for the buttons
        setLayout(layout); //setting the layout for the GUI window using GridBagLayout

        gbc = new GridBagConstraints(); //initialize the GridBagConstraints constructor

        button = new JButton[9]; //initialize the 9 buttons as an array

        for (int i = 0; i < 9; i++) {
            button[i] = new JButton("" + (i + 1)); //put numbers on each button
            button[i].addActionListener(this); //used to handle user events when button is pressed

            gbc.gridx = buttonConstraints[i][0]; //set the x value of the location of the button
            gbc.gridy = buttonConstraints[i][1]; //set the y value of the location of the button
            gbc.gridwidth = buttonConstraints[i][2]; //set the width value of location of button
            gbc.gridheight = buttonConstraints[i][3]; //set the height value of location of button

            gbc.fill = GridBagConstraints.BOTH; //fill the display area if necessary
            gbc.insets = new Insets(2, 2, 2, 2); //the gaps between each button

            add(button[i], gbc); //add the buttons to the JFrame
        }
        
    }
    
    public void actionPerformed(ActionEvent e) {
    
        count++; //will be used to count the number of turns in the game
        
        for(int i = 0; i < 9; i++){
            if (count % 2 == 1) { //if the turn is odd, it is X's turn
                letter = "X";
            } else if (count % 2 == 0) { //if the turn is even, it is O's turn
                letter = "O";
            }
            
            if(e.getSource() == button[i]){ //if the button is clicked, put the letter
                button[i].setText(letter);  //of whoever's turn it is, and make sure
                button[i].setEnabled(false); //the panel can't be clicked
            }
        }
        
        //Horizontal Win
        if(button[0].getText() == button[1].getText()
                && button[0].getText() == button[2].getText() 
                && button[0].getText() != ""){
            winner = true;
            
        } else if(button[3].getText() == button[4].getText()
                && button[3].getText() == button[5].getText() 
                && button[3].getText() != ""){
            winner = true;
            
        } else if(button[6].getText() == button[7].getText()
                && button[6].getText() == button[8].getText() 
                && button[6].getText() != ""){
            winner = true;
            
        //Vertical Win
        } else if(button[0].getText() == button[3].getText()
                && button[0].getText() == button[6].getText() 
                && button[0].getText() != ""){
            winner = true;
            
        } else if(button[1].getText() == button[4].getText()
                && button[1].getText() == button[7].getText() 
                && button[1].getText() != ""){
            winner = true;
            
        } else if(button[2].getText() == button[5].getText()
                && button[2].getText() == button[8].getText() 
                && button[2].getText() != ""){
            winner = true;
            
        //Diagonal Win
        } else if(button[0].getText() == button[4].getText()
                && button[0].getText() == button[8].getText() 
                && button[0].getText() != ""){
            winner = true;
            
        } else if(button[6].getText() == button[4].getText()
                && button[6].getText() == button[2].getText() 
                && button[6].getText() != ""){
            winner = true;
            
        } else{
            winner = false; //keep the game going if there is no winner
            
        }
        
        /*
         * If there is a winner, display a message of who the winner is and disable
         * all the buttons after the game ends. If the game is a draw, display such message
         */
        if(winner == true){
            JOptionPane.showMessageDialog(null, "Player " + letter + " wins!");
            for(int i = 0; i < 9; i++){
                button[i].setEnabled(false);
            }
        } else if(count == 9 && winner == false){ //If there are no more possible moves and no winner
            JOptionPane.showMessageDialog(null, "Draw Game!");
        }
    }
    
}