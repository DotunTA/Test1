package rockpaperscissors;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class RpsGUI extends JFrame implements ActionListener{
    
    /*The main buttons of the GUI window*/
    private static JButton rockButton;
    private static JButton paperButton;
    private static JButton scissorsButton;
    private static JButton exitButton;
    private static JButton restartButton;
    
    /*The scores for the player, computer, and number of draw games*/
    private static int playerScore;
    private static int computerScore;
    private static int numberOfDraws;
    
    private int computerMove; //0 = rock, 1 = paper, 2 = scissors
    
    /*File Menu selection*/
    private JMenuItem restartMenu;
    private JMenuItem exitMenu;
    
    /*The player label and the computer label*/
    private JLabel labelSpace;
    private JLabel player;
    private JLabel computer;
    
    /*The textfields that represent the player's score, computer's score, number
     of draws, as well as the player's move and computer's move*/
    private JTextField playertf;
    private JTextField computertf;
    private JTextField drawtf;
    private JTextField playersMove;
    private JTextField computersMove;

    /*The panels that hold up the other JComponents*/
    private JPanel panel;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    
    /*Constructor*/
    public RpsGUI(){
        
        setTitle("Rock-Paper-Scissors"); //Title of the game
        
        /*This menu bar implements the restart menu, which restarts the game, and
         the exit menu, which exits the game. All this comes from the File Menu,
         which is put at the top of the main GUI window (but below the title)*/
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        
        restartMenu = new JMenuItem("Restart");
        restartMenu.addActionListener(this);
        fileMenu.add(restartMenu);
        
        exitMenu = new JMenuItem("Exit");
        exitMenu.addActionListener(this);
        fileMenu.add(exitMenu);
        
        menuBar.add(fileMenu);
        
        add(menuBar, BorderLayout.NORTH);
        
        /*This panel adds the buttons that are used to either make a move, exit
         the game, or restart the game. The components are put on the south area
         of the GUI window*/
        panel = new JPanel();
        
        rockButton = new JButton("ROCK");
        rockButton.addActionListener(this);
        panel.add(rockButton);
        
        paperButton = new JButton("PAPER");
        paperButton.addActionListener(this);
        panel.add(paperButton);
        
        scissorsButton = new JButton("SCISSORS");
        scissorsButton.addActionListener(this);
        panel.add(scissorsButton);
        
        labelSpace = new JLabel("                    ");
        panel.add(labelSpace);
        
        restartButton = new JButton("RESTART");
        restartButton.addActionListener(this);
        panel.add(restartButton);
        
        exitButton = new JButton("EXIT");
        exitButton.addActionListener(this);
        panel.add(exitButton);
        
        add(panel, BorderLayout.SOUTH);
        
        /*This panel adds the textfield components that make up the player and
         computer's move, as well the score. These components are placed in the
         center of the GUI window.*/
        panel1 = new JPanel();
        
        playertf = new JTextField("PLAYER: " + playerScore, 9);
        playertf.setEditable(false);
        playertf.setFont(new Font("Helvetica", Font.PLAIN, 24));
        panel1.add(playertf);
        
        computertf = new JTextField("COMPUTER: " + computerScore, 9);
        computertf.setEditable(false);
        computertf.setFont(new Font("Helvetica", Font.PLAIN, 24));
        panel1.add(computertf);
        
        drawtf = new JTextField("DRAW: " + numberOfDraws, 9);
        drawtf.setEditable(false);
        drawtf.setFont(new Font("Helvetica", Font.PLAIN, 24));
        panel1.add(drawtf);
        
        add(panel1, BorderLayout.CENTER);
        
        /*This panel is where the player label as well as what type of move the
         player made, is located. This is placed on the left side of the screen.*/
        panel2 = new JPanel();
        
        player = new JLabel("PLAYER");
        player.setFont(new Font("Helvetica", Font.PLAIN, 24));
        
        playersMove = new JTextField("", 9);
        playersMove.setEditable(false);
        playersMove.setFont(new Font("Helvetica", Font.PLAIN, 16));
        
        panel2.add(player);
        panel2.add(playersMove);
        
        add(panel2, BorderLayout.WEST);
        
        /*This panel is where the computer label as well as what type of move the
         computer made, is located. This is placed on the right side of the screen.*/
        panel3 = new JPanel();
        
        computer = new JLabel("COMPUTER");
        computer.setFont(new Font("Helvetica", Font.PLAIN, 24));
        
        computersMove = new JTextField("", 9);
        computersMove.setEditable(false);
        computersMove.setFont(new Font("Helvetica", Font.PLAIN, 16));
        
        panel3.add(computer);
        panel3.add(computersMove);
        
        add(panel3, BorderLayout.EAST);
        
        setSize(800, 400); //The size of the GUI window
        
    }
    
    public void actionPerformed(ActionEvent e){
        
        /*This algorithm will determine what move the computer makes, chosen
         just about exactly when the player makes their move.*/
        computerMove = (int) (1 + Math.random() * 3);
        
        /*If the exit button or exit menu is clicked, close the game*/
        if(e.getSource() == exitButton || e.getSource() == exitMenu){
            System.exit(0);
        }
        
        /*If the restart button or restart menu is clicked, make all the scores
         reset to 0, and enable the buttons if there was a previous winner.*/
        if(e.getSource() == restartButton || e.getSource() == restartMenu){
            playerScore = 0;
            computerScore = 0;
            numberOfDraws = 0;
            
            playertf.setText("PLAYER: " + playerScore);
            computertf.setText("COMPUTER: " + computerScore);
            drawtf.setText("DRAW: " + numberOfDraws);
            
            playersMove.setText("");
            computersMove.setText("");
            
            rockButton.setEnabled(true);
            paperButton.setEnabled(true);
            scissorsButton.setEnabled(true);
        }
        
        /*If the player selects the rock button, the computer will make a random
         move between rock, paper, and scissors. There will be a score by the player,
         computer, or a draw based on the moves between the player and the computer.*/
        if(e.getSource() == rockButton){
            
            playersMove.setText("ROCK");
            
            if(computerMove == 1){
                drawtf.setText("DRAW: " + ++numberOfDraws);
                computersMove.setText("ROCK");
            }
            else if(computerMove == 2){
                computertf.setText("COMPUTER: " + ++computerScore);
                computersMove.setText("PAPER");
            }
            else if(computerMove == 3){
                playertf.setText("PLAYER: " + ++playerScore);
                computersMove.setText("SCISSORS");
            }
            
        }
        
        /*If the player selects the paper button, the computer will make a random
         move between rock, paper, and scissors. There will be a score by the player,
         computer, or a draw based on the moves between the player and the computer.*/
        if(e.getSource() == paperButton){
            
            playersMove.setText("PAPER");
            
            if(computerMove == 1){
                playertf.setText("PLAYER: " + ++playerScore);
                computersMove.setText("ROCK");
            }
            else if(computerMove == 2){
                drawtf.setText("DRAW: " + ++numberOfDraws);
                computersMove.setText("PAPER");
            }
            else if(computerMove == 3){
                computertf.setText("COMPUTER: " + ++computerScore);
                computersMove.setText("SCISSORS");
            }
            
        }
        
        /*If the player selects the scissors button, the computer will make a random
         move between rock, paper, and scissors. There will be a score by the player,
         computer, or a draw based on the moves between the player and the computer.*/
        if(e.getSource() == scissorsButton){
            
            playersMove.setText("SCISSORS");
            
            if(computerMove == 1){
                computertf.setText("COMPUTER: " + ++computerScore);
                computersMove.setText("ROCK");
            }
            else if(computerMove == 2){
                playertf.setText("PLAYER: " + ++playerScore);
                computersMove.setText("PAPER");
            }
            else if(computerMove == 3){
                drawtf.setText("DRAW: " + ++numberOfDraws);
                computersMove.setText("SCISSORS");
            }
            
        }
        
        /*If either the player or computer scores 10 before the other player, a
         message will pop up that states whether the player won or lost. After this,
         the rock/paper/scissors buttons are disabled, unless the player wants to
         restart the game again (in which case, they are re-enabled and the score
         * is reset to 0 on each side).*/
        if(playerScore == 10){
            JOptionPane.showMessageDialog(null, "YOU WIN!!!");
            rockButton.setEnabled(false);
            paperButton.setEnabled(false);
            scissorsButton.setEnabled(false);
        } else if(computerScore == 10){
            JOptionPane.showMessageDialog(null, "YOU LOSE!!!");
            rockButton.setEnabled(false);
            paperButton.setEnabled(false);
            scissorsButton.setEnabled(false);
        }
        
    }
}
