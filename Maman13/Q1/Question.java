/*
 * This class represents the constructor of a question set 
 */

import java.util.ArrayList;
import java.util.Collections;

public class Question {

	private String question; // A string that represents the question
	private ArrayList<String> choices;  // array list of the choices to answer
	private String answer; // the answer
	
	/*
	 * Constructor of a new question
	 * @Param question string of the question
	 * @Param choices array string of the choices to answer
	 * @Param answer string of the answer
	 */
	public Question(String question, String[] choices, String answer) {
		this.question = question;
		this.choices = new ArrayList<String>();
		for (int i = 0; i< choices.length; i++)
			this.choices.add(choices[i]);
		Collections.shuffle(this.choices); // make sure each game shows questions in different order
		this.answer = answer;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public ArrayList<String> getChoices() {
		return choices;
	}
	public String getAnswer() {
		return answer;
	}
}
