/*
 * This class represent the graphics of the calculator
 */

import javax.swing.JOptionPane;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class CalculatorController {

	@FXML
    private Canvas canv;

    @FXML
    private GridPane grid;
    
    private GraphicsContext gc;  // canvas
    private String input = ""; // string of the user input
    private final int TEXTWIDTH = 20;
    private final int TEXTHEIGHT = 40;
    private final int TEXTOUTPUTHEIGHT = 180;
    
    @FXML
    public void initialize() 
    {
    	gc = canv.getGraphicsContext2D();
    	gc.setFont(Font.font ("", 30));
    }
    
    @FXML
    void onMouseClicked(MouseEvent mouseEvent) {
    	Button button = (Button) mouseEvent.getSource();
    	String number = button.getText();
    	switch(number) {
    	case "1":
    	case "2":
    	case "3":
    	case "4":
    	case "5":
    	case "6":
    	case "7":
    	case "8":
    	case "9":
    	case "0":	
    		input += number; // adds the number to the input string
    		gc.clearRect(0, 0, canv.getWidth(), canv.getHeight()); // clear the board
        	gc.strokeText(input, TEXTWIDTH, TEXTHEIGHT); // prints the user input
        	break;
    	case "+":
    	case "-":
    	case "*":
    	case "/":
    	case ".":
    		if(input.equals(""))
    			input += number;  // adds the operator to the input string
    		else {
    			char ch = input.charAt(input.length()-1); 
    			if(!Character.isDigit(ch)) 
    				input = input.substring(0,input.length()-1); // if the char is an operator, add the previous number to the string input
    			input += number; // adds the operator
    		}
    		gc.clearRect(0, 0, canv.getWidth(), canv.getHeight()); // clear the board
        	gc.strokeText(input, TEXTWIDTH, TEXTHEIGHT); // prints the user input
        	break;
    	case "+/-":
    		if(input.equals("")) {
    			input += '+'; // doesn't matter
    			break;
    		}
    		char ch = input.charAt(input.length()-1); // char needs to be + or -
    		if(Character.isDigit(ch)) {
    			JOptionPane.showMessageDialog(null, "You can click '(+/-)' only at begging of a number, Bad input");
    		}
    			else if(ch == '-') {
    			input = input.substring(0,input.length()-1);
    			input += '+';
    		}
    		else if(ch == '+') {
    			input = input.substring(0,input.length()-1);
    			input += '-';
    		}
    		gc.clearRect(0, 0, canv.getWidth(), canv.getHeight()); // clear the board
        	gc.strokeText(input, TEXTWIDTH, TEXTHEIGHT);
    		break;
    	case "CLEAR":
    		gc.clearRect(0, 0, canv.getWidth(), canv.getHeight()); // clear the board
    		input = "";
    		break;
    	case "=": // calculate the result
    		if(input.equals("")) 
    			break;
    		if(!Character.isDigit(input.charAt(input.length()-1))) {
    			input = input.substring(0,input.length()-1);
    			gc.clearRect(0, 0, canv.getWidth(), canv.getHeight()); // clear the board
            	gc.strokeText(input, TEXTWIDTH, TEXTHEIGHT); // prints the user input
    		}
    		double result = Logic.evaluateString(input); // evaluate the input string
    		if (result == Integer.MAX_VALUE)
    			JOptionPane.showMessageDialog(null, "Cannot divide by zero");
    		else if (result%1==0) //prints the result without '.'
    			gc.strokeText((int)result+ "", TEXTWIDTH,TEXTOUTPUTHEIGHT);
    		else // prints the result with '.'
    			gc.strokeText(result+ "", TEXTWIDTH,TEXTOUTPUTHEIGHT);
        	//break;
    	}
    } //end of onMouseClicked
} // end of class
    

  