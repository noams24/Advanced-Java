/*
 * This class represent the quiz
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
public class QuizController {

	@FXML
	private GridPane grid;
	@FXML
	private TextField txt;
	@FXML
	private TextField outputTxt;
	
	private int numOfQuestions;
	private int num;
	private Button[] btns;
	
	public int correctAnswers = 0;
	@FXML
	public void initialize() {
		btns = Game.initializeButtons(); // initialize the buttons
		logic(btns); // start a new game
	} // end of initialize	

	    @FXML
	    private void logic(Button[] btns) {
	    	Game g = new Game(); // create new game object
	    	numOfQuestions = g.getHowManyQustions();	//counter of questions
	    	num = 1; // switch to case 1
	    	txt.appendText(g.getquestionSet().get(numOfQuestions).getQuestion()); //set the first question
	    	for (int i = 0; i < 4; i++) {
	    		btns[i].setText(g.getquestionSet().get(numOfQuestions).getChoices().get(i)); // set the first answers
	    		btns[i].setOnAction(new EventHandler<ActionEvent>() {  

	    			@Override
	    			public void handle(ActionEvent event) {
	    				Button b = (Button)event.getSource();
	    				switch(num) {
	    				case 1: // wait for the user to answer the question

	    					if(b.getText().equals(g.getquestionSet().get(numOfQuestions).getAnswer())) { // Right answer
	    						outputTxt.setText("Correct answer!");  					
	    						correctAnswers++;
	    					}
	    					else // Wrong answer
	    						outputTxt.setText("Wrong answer. The correct answer is: " + g.getquestionSet().get(numOfQuestions).getAnswer());

	    					if (numOfQuestions > 0) { // if there are still questions remains continue
	    						numOfQuestions--;
	    						btns[0].setText("Continue");
	    						for (int i= 1; i<4;i++) { // disable buttons
	    							btns[i].setDisable(true);
	    							btns[i].setText("");	    					
	    						}
	    						num = 2;	    // switch to case 2			
	    					}
	    					else  // game over!
	    					{ 
	    						num = 3;
	    						
	    						int finalScore = 100 * correctAnswers / g.getquestionSet().size()  ;
	    						txt.setText("You got " + correctAnswers + " correct answers ("+finalScore+"%) . Do you want to play again?");
	    						btns[0].setText("Yes");
	    						btns[1].setText("No");
	    						btns[2].setText("");
	    						btns[2].setDisable(true);
	    						btns[3].setText("");	
	    						btns[3].setDisable(true); 
	    					}
	    					break;
	    				case 2: // wait for the user to press continue
	    					if(b.getText().equals("Continue")){
	    						txt.setText(g.getquestionSet().get(numOfQuestions).getQuestion());
	    						for(int i = 0; i < 4; i++) {
	    							btns[i].setText(g.getquestionSet().get(numOfQuestions).getChoices().get(i));
	    							btns[i].setDisable(false);
	    						}
	    						num = 1;
	    					}
	    					break;
	    				case 3: // end of game, ask if the user want's to play again.
	    					if(b.getText().equals("Yes")) {
	    						num = 1;
	    						numOfQuestions = g.getHowManyQustions();
	    						correctAnswers = 0;
	    						txt.setText(g.getquestionSet().get(numOfQuestions).getQuestion());
	    						outputTxt.setText("");
	    						for(int i = 0; i < 4; i++) {
	    							btns[i].setText(g.getquestionSet().get(numOfQuestions).getChoices().get(i));
	    							btns[i].setDisable(false);
	    						}
	    					}
	    					if(b.getText().equals("No")) {
	    						outputTxt.setText("Thank you for playing :)");
	    					}
	    					break;					
	    				} // end of cases
	    			}
	    		});
	    		grid.add(btns[i], 0, i); 
	    	} // end of loop
	    }
} // end of class
