package powerballlottogenerator;

/*
 * To Do List
 * 1. Make sure that there are no duplicate numbers (put code in ActionLisenter
 *    getNumbersButton section)
 * 
 * This is a standard lottery generator based on the Powerball game that generates
 * random numbers. The first 5 numbers generates numbers between 1-59, and the last
 * number (the powerball number) generates numbers between 1-35. The 3 buttons that
 * are featured are the generate button (which generates random numbers each time
 * the button is selected), the restart button, which resets all the numbers to 0,
 * and the exit button, which closes the GUI window. There is also a file menu, which
 * basically does the same thing as the buttons.
 */

import javax.swing.JFrame;

public class PowerballLottoGenerator {

    
    public static void main(String[] args) {
        
        /*Main GUI window*/
        PowerballGUI frame = new PowerballGUI();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
