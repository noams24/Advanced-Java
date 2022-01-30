/*
 * This class represents the constructor of a new game 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import javafx.scene.control.Button;

public class Game extends QuizController{
	
	private static ArrayList<Question> questionSet; // set that contains: array list of the questions + choices + answer
	private static int numOfQuestions;
	private static final int BUTTONHEIGHT = 30;
	private static final int BUTTONWEIGHT = 2000;
	
	/*
	 * Constructor of a new game
	 */
		public Game() {
			
			numOfQuestions = 0;
			questionSet = new ArrayList<Question>();
			String q;
			String[] a = new String[4];
			try { // opening the file "exam.txt"
			Scanner input = new Scanner(new File("exam.txt"));
			while(input.hasNext()) {
				q = input.nextLine();
				a[0] = input.nextLine();
				a[1] = input.nextLine();
				a[2] = input.nextLine();
				a[3] = input.nextLine();
				questionSet.add(new Question(q,a,a[0]));
				Collections.shuffle(questionSet,new Random()); 
				numOfQuestions++;
			} 
			input.close();	
			}
			catch(FileNotFoundException e){
				System.out.println("Error, file not found");
			}
	}
		
		public ArrayList<Question> getquestionSet() {
			return questionSet;
		}
		
		public static int getHowManyQustions() {
			return numOfQuestions - 1;
		}
		
		public static Button[] initializeButtons() {
			Button[] btns = new Button[4]; // set the buttons
			for (int i = 0; i < 4; i++) {
				btns[i] = new Button("");
				btns[i].setPrefSize(BUTTONWEIGHT, BUTTONHEIGHT);
			}
			return btns;
		}
}
