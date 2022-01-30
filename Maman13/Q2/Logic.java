/*
 * This class represent the logics of the calculator
 */

import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Logic {

	
	public static double evaluateString(String input) {
		LinkedList list = new LinkedList();
		list  = stringToList(list, input);
		if (list == null)
			return 0;
		return evaluateList(list);
	}

	
	/*
	 * This method transform a given string input to a linked list of numbers and operators
	 * @Param list is the given linked list
	 * @Param input is the given string input
	 * returns the new linked list 
	 */
	private static LinkedList stringToList(LinkedList list, String input)
	{
		int i= 0, j=0; // counters
		double num; // the number or the operator that added to the list each time
		int dotCounter = 0;

		if(input.charAt(0)=='+' || input.charAt(0)=='-') 
			i++;
		while(true) // loops all the characters in the string
		{
			if(i == input.length()) { // end of the string						
				num = Double.parseDouble(input.substring(j, i)); // transform the string number into double 
				list.add(num); // adds the last number to the list
				break; // end the loop
			}
			if( input.charAt(i) != '.' && !Character.isDigit(input.charAt(i))) {
				num = Double.parseDouble(input.substring(j, i));  // transform the string number into double 
				list.add(num);   // adds the number to the list
				list.add(input.charAt(i)); // adds the operator
				j = i+1;
				dotCounter = 0;
			}
			if (input.charAt(i) == '.')
				dotCounter++;
			if(dotCounter == 2) {
				JOptionPane.showMessageDialog(null, "Syntax error");
				return null;
			}
			i++;
		}
		return list;
	}
	
	/*
	 * This method evaluate a given linked list to a number
	 * @Param list is the given list
	 * returns number after evaluation
	 */
	private static double evaluateList(LinkedList list)
    {
    	double tmp; // temporary number of the result of two numbers (for the operators * and /)
    	double num = 0; // evaluate the number
    	for(int i=0; i< list.size() ;i++) // first loop for the operators * and /
    	{
    		if(list.get(i).equals('*'))  // the operator is *
    		{
    			tmp = (double)list.get(i-1) *(double)list.get(i+1);
    			list.set(i-1, tmp); // set the result
    			list.remove(i);
    			list.remove(i); // remove the operator and two numbers
    			i--; 
    		}
    		else if(list.get(i).equals('/')) // the operator is /
    		{
    			if( (double)list.get(i+1) == 0) // cannot divide by 0
    				return Integer.MAX_VALUE; 
    			tmp = (double)list.get(i-1) / (double)list.get(i+1); 
    			list.set(i-1, tmp); // set the result
    			list.remove(i);
    			list.remove(i); // remove the operator and two numbers
    			i--;
    		}
    	} // end of loop 
    	num += (double)list.get(0); // adds the first number

    	for(int i=2; i< list.size();i+=2) // second loop for the operators + and -
    	{
    		if(list.get(i-1).equals('-')) //  the operator is -
    			num -= (double)list.get(i);
    		else		// the operator is +
    			num += (double)list.get(i);
    	}
    	return num;
    }// end of method
} // end of class
