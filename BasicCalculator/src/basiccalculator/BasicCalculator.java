package basiccalculator;

/*TO DO LIST
 * 1. Create more buttons (exponents, trig functions, etc), Handle those Exceptions
 * 2. Add a Menu
 * 3. Fix a couple of small bug issues
 * 
 * Standard calculator that is typically seen in Microsoft Windows programs.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BasicCalculator extends JPanel implements ActionListener{

    private static final long serialVersionUID = 1L;
    
    public static final int WIDTH = 320; //width of the GUI
    public static final int HEIGHT = 480; //height of the GUI
    
    private GridBagLayout layout; //how the buttons will be laid out in the GUI
    private GridBagConstraints gbc; //defining the location and size of the buttons
    
    private JButton[] numButtons; //the buttons from 0-9
    private JButton[] operationButtons; //the non-number buttons
    
    private JTextField textField; //the textfield where the output is stored
    
    private double num1; //the first number input by the user
    private double num2; //the second number input by the user
    private double answer; //answer derived from whatever operation is used with the two numbers
    private int operation; //+, -, *, or /
    
    //[0] = gridx, [1] = gridy, [2] = gridwidth, [3] = gridheight, how the buttons are laid out
    private int[][] numConstraints = new int[][]{
        
        {0, 5, 2, 1}, //button 0
        {0, 4, 1, 1}, //button 1
        {1, 4, 1, 1}, //button 2
        {2, 4, 1, 1}, //button 3
        {0, 3, 1, 1}, //button 4
        {1, 3, 1, 1}, //button 5
        {2, 3, 1, 1}, //button 6
        {0, 2, 1, 1}, //button 7
        {1, 2, 1, 1}, //button 8
        {2, 2, 1, 1}  //button 9   
    };
    
    private int[][] operationConstraints = new int[][]{
    
        {2, 5, 1, 1}, //button decimal
        {3, 4, 1, 2}, //button equals
        {3, 3, 1, 1}, //button +
        {3, 2, 1, 1}, //button -
        {3, 1, 1, 1}, //button *
        {2, 1, 1, 1}, //button /
        {1, 1, 1, 1}, //button +/-
        {0, 1, 1, 1}  //button clear
    };
    
    public static void main(String[] args) { //main method
        
        JFrame frame = new JFrame("Calculator"); //initialize the JFrame and set the title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close the program safely
        frame.setResizable(false); //user is not allowed to resize the GUI window
        frame.setLayout(new BorderLayout()); //layout of GUI
        frame.add(new BasicCalculator(), BorderLayout.CENTER); //Make the components toward the center
        frame.pack(); //pack in the components efficiently
        frame.setLocationRelativeTo(null); //when program starts, it will display in middle of screen
        frame.setVisible(true); //make the program visible when executed
    }
    
    public BasicCalculator(){ //constructor for the BasicCalculator class
        setPreferredSize(new Dimension(WIDTH, HEIGHT)); //set the size of the calculaor
        
        layout = new GridBagLayout(); //initialize the GridBagLayout constructor
        layout.columnWidths = new int[]{80, 80, 80, 80}; //the width of the column for buttons
        layout.rowHeights = new int[]{80, 80, 80, 80, 80, 80}; //the height of the row for buttons
        setLayout(layout); //setting the layout for the GUI window using GridBagLayout
        
        gbc = new GridBagConstraints(); //initialize the GrdBagConstraints constructor
        
        numButtons = new JButton[10]; //initialize the number buttons as an array
        
        for(int i = 0; i < numButtons.length; i++){
            numButtons[i] = new JButton("" + i); //put in the numbers for each  number button
            numButtons[i].addActionListener(this); //used to handle teh events of number buttons
            
            gbc.gridx = numConstraints[i][0]; //set the x value of location of number button
            gbc.gridy = numConstraints[i][1]; //set the y value of location of number button
            gbc.gridwidth = numConstraints[i][2]; //set the width value of location of number button
            gbc.gridheight = numConstraints[i][3]; //set the height value of location of number button
            gbc.fill = GridBagConstraints.BOTH; //fill the display area if necessary
            gbc.insets =  new Insets(2, 2, 2, 2); //the space between each button (up, down, left, right)
            
            add(numButtons[i], gbc); //add the number buttons to the GUI window
        }
        
        operationButtons = new JButton[8]; //initualize the non-number buttons as an array
        
        operationButtons[0] = new JButton("."); //the decimal button
        operationButtons[1] = new JButton("="); //the equals button
        operationButtons[2] = new JButton("+"); //the addition button
        operationButtons[3] = new JButton("-"); //the subtract button
        operationButtons[4] = new JButton("*"); //the multiply button
        operationButtons[5] = new JButton("/"); //the division button
        operationButtons[6] = new JButton("+/-"); //the plus-minus button
        operationButtons[7] = new JButton("C"); //the clear button
        
        for(int i = 0; i < operationButtons.length; i++){
            operationButtons[i].addActionListener(this); //use to handle the events of non-number buttons
            
            gbc.gridx = operationConstraints[i][0]; //set the x value of location of non-number button
            gbc.gridy = operationConstraints[i][1]; //set the y value of location of non-number button
            gbc.gridwidth = operationConstraints[i][2]; //set the width of location of non-number button
            gbc.gridheight = operationConstraints[i][3]; //set the height of location of non-number button
            
            add(operationButtons[i], gbc); //add the non-number buttons to the GUI window
        }
        
        textField = new JTextField(); //initialize the textfield
        textField.setEditable(false); //user is not allowed to edit the textfield
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //create border with textfield
        textField.setFont(new Font("Helvetica", Font.PLAIN, 24)); //Fonts that are displayed in the output
        
        gbc.gridx = 0; //set the x value of the textfield
        gbc.gridy = 0; //set the y value of the textfield
        gbc.gridwidth = 4; //set the width of the textfield
        gbc.gridheight = 1; //set the height of the textfield
        
        add(textField, gbc); //add the textfield to the GUI window
        
    }
    
    public void actionPerformed(ActionEvent e){ //using event handling when button is pressed
        
        for(int i = 0; i < numButtons.length; i++){ //if a number button is pressed
            if(e.getSource() == numButtons[i]){     //input that number into the textfield
                textField.setText(textField.getText() + i);
            }
        }
        
        if(e.getSource() == operationButtons[0] && !textField.getText().contains(".")){ //one decimal only
            textField.setText(textField.getText() + ".");
        }
        
        if(e.getSource() == operationButtons[6]){ //plusMinus button is used to multiply by -1
            textField.setText("" + (-1 * Double.parseDouble(textField.getText())));
        }
        
        if(e.getSource() == operationButtons[7]){ //clear button clears what ever is in textfield
            textField.setText("");
        }
        
        if(e.getSource() == operationButtons[2]){ //add the numbers
            num1 = Double.parseDouble(textField.getText());
            operation = 1;
            textField.setText("");
        }
        
        if(e.getSource() == operationButtons[3]){ //subtract the numbers
            num1 = Double.parseDouble(textField.getText());
            operation = 2;
            textField.setText("");
        }
        
        if(e.getSource() == operationButtons[4]){ //multiply the numbers
            num1 = Double.parseDouble(textField.getText());
            operation = 3;
            textField.setText("");
        }
        
        if(e.getSource() == operationButtons[5]){ //divide the numbers
            num1 = Double.parseDouble(textField.getText());
            operation = 4;
            textField.setText("");
        }
        
        if(e.getSource() == operationButtons[1]){ //get the answer of the two numbers and operator used
            num2 = Double.parseDouble(textField.getText());
            
            if(operation == 1){ //if plus button is used, add the two numbers
                answer = num1 + num2;
                textField.setText("" + answer); //display answer in the textfield
                
            } else if(operation == 2){ //if the minus button is used, subtract the two numbers
                answer = num1 - num2;
                textField.setText("" + answer); //display answer in the textfield
                
            } else if(operation == 3){ //if the multiply button is used, multiply the two numbers
                answer = num1 * num2;
                textField.setText("" + answer); //display answer in the textfield
                
            } else if(operation == 4){ //if the division button is used, divide the two numbers
                if(num2 == 0){         //if division by zero, display "Can't Divide By Zero!"
                    textField.setText("Can't Divide By Zero!");
                } else{
                    answer = num1 / num2;
                    textField.setText("" + answer); //display answer in the textfield
                }
                
            }
            
            operation = 0;
        }
    }
    
}
