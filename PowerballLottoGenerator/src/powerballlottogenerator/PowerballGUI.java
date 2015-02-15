package powerballlottogenerator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PowerballGUI extends JFrame implements ActionListener{
    
    /*The main action buttons used to interact with the GUI window*/
    private static JButton getNumbersButton;
    private static JButton resetButton;
    private static JButton exitButton;
    
    /*
     * The file menu buttons, which have similar functionality as the main action 
     * buttons
     */
    private JMenuItem getNumbersMenu;
    private JMenuItem resetMenu;
    private JMenuItem exitMenu;
    
    /*
     * The textfields are used to display the numbers that are randomly generated
     */
    private JTextField tf1;
    private JTextField tf2;
    private JTextField tf3;
    private JTextField tf4;
    private JTextField tf5;
    private JTextField tf6;
    
    /*The first 5 numbers are put into an array that are sorted based on the order
     from least to greatest. The last number is the powerball number.*/
    private int[] num;
    private int numPB;
    
    /*These panels are used to add the JComponents to, the panelTextField adds the
     textfield components, and the panelButtons add the button components*/
    private JPanel panelTextField;
    private JPanel panelButtons;
    
    public PowerballGUI(){ //constructor
        
        setTitle("Powerball Generator"); //Title of the Program
        
        /*The Menu is used to select from 3 different drop down file menus,
         which are generate numbers, reset the numbers, and exit the program.
         This menu is located at the top of the program.*/
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        
        getNumbersMenu = new JMenuItem("Generate");
        getNumbersMenu.addActionListener(this);
        fileMenu.add(getNumbersMenu);
        
        resetMenu = new JMenuItem("Reset");
        resetMenu.addActionListener(this);
        fileMenu.add(resetMenu);
        
        exitMenu = new JMenuItem("Exit");
        exitMenu.addActionListener(this);
        fileMenu.add(exitMenu);
        
        menuBar.add(fileMenu);
        
        add(menuBar, BorderLayout.NORTH);
        
        /*This panel will add the button components to the GUI window. The panel
         will be located on the bottom of the GUI window.*/
        panelButtons = new JPanel();
        
        getNumbersButton = new JButton("GENERATE!");
        getNumbersButton.addActionListener(this);
        panelButtons.add(getNumbersButton);
        
        resetButton = new JButton("RESET");
        resetButton.addActionListener(this);
        panelButtons.add(resetButton);
        
        exitButton = new JButton("EXIT");
        exitButton.addActionListener(this);
        panelButtons.add(exitButton);
        
        add(panelButtons, BorderLayout.SOUTH);
        
        
        /*
         * The textfield components will be used to display the numbers that are
         * generated. This panel will be located at the center of the GUI window.
         * The last textfield has a magenta color to make it represent the powerball
         * number (The powerball is actually red, but making it red made it somewhat
         * hard to see the number).
         */
        panelTextField = new JPanel();
        
        tf1 = new JTextField("" + 0, 3);
        tf1.setEditable(false);
        tf1.setFont(new Font("Helvetica", Font.PLAIN, 24));
        panelTextField.add(tf1);
        
        tf2 = new JTextField("" + 0, 3);
        tf2.setEditable(false);
        tf2.setFont(new Font("Helvetica", Font.PLAIN, 24));
        panelTextField.add(tf2);
        
        tf3 = new JTextField("" + 0, 3);
        tf3.setEditable(false);
        tf3.setFont(new Font("Helvetica", Font.PLAIN, 24));
        panelTextField.add(tf3);
        
        tf4 = new JTextField("" + 0, 3);
        tf4.setEditable(false);
        tf4.setFont(new Font("Helvetica", Font.PLAIN, 24));
        panelTextField.add(tf4);
        
        tf5 = new JTextField("" + 0, 3);
        tf5.setEditable(false);
        tf5.setFont(new Font("Helvetica", Font.PLAIN, 24));
        panelTextField.add(tf5);
        
        tf6 = new JTextField("" + 0, 3);
        tf6.setBackground(Color.MAGENTA);
        tf6.setEditable(false);
        tf6.setFont(new Font("Helvetica", Font.PLAIN, 24));
        panelTextField.add(tf6);
        
        add(panelTextField, BorderLayout.CENTER);
        
        
        setSize(500, 300); //The size of the GUI window
        
        /*END OF CONSTRUCTOR*/
    } 
    
    public void actionPerformed(ActionEvent e){
        
        num = new int[5]; //The non-powerball numbers will contain 5 numbers.
        numPB = (int) (1 + Math.random() * 35); //Powerball is from 1-35.
        
        /*If the reset menu is selected, the all the numbers will be set to 0*/
        if(e.getSource() == resetMenu || e.getSource() == resetButton){
            tf1.setText("" + 0);
            tf2.setText("" + 0);
            tf3.setText("" + 0);
            tf4.setText("" + 0);
            tf5.setText("" + 0);
            tf6.setText("" + 0);
        }
        
        /*If the exit menu is selected, the GUI window closes and ends the program*/
        if(e.getSource() == exitMenu || e.getSource() == exitButton){
            System.exit(0);
        }
        
        /*If the generate button is selected, the first 5 numbers will make a
         * random selected between 1 to 59. These numbers will then be sorted based
         * on the lowest number to the highest number. The numbers will then be
         * displayed in the textfield. The powerball number is also displayed in
         * the textfield.
         */
        if(e.getSource() == getNumbersButton || e.getSource() == getNumbersMenu){
            
            for(int i = 0; i < num.length; i++){
                num[0] = (int)(1 + Math.random() * 59);
                num[1] = (int)(1 + Math.random() * 59);
                num[2] = (int)(1 + Math.random() * 59);
                num[3] = (int)(1 + Math.random() * 59);
                num[4] = (int)(1 + Math.random() * 59);
                
                Arrays.sort(num);
                
                tf1.setText("" + num[0]);
                tf2.setText("" + num[1]);
                tf3.setText("" + num[2]);
                tf4.setText("" + num[3]);
                tf5.setText("" + num[4]);
            }
            
            tf6.setText("" + numPB);
        }
    }
}
