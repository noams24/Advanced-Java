/**
 * @author Noam 
 * MMN:11 //Question:1 
 * 			Brief: The system choose a random word and
 *         the user need to guess letters. Each time the user guesses a right
 *         letter it's appears on the screen until all the word is revealed. the
 *         system presents how many guesses the user guessed and asks if the
 *         user want's to play again.
 */

public class Main {

	public static void main(String[] args) {
		System.out.println("Welcome to the game! Lets begin");
		while (true) {
			GameLogic.game(); // start a new game
			if (!GameLogic.playAgain()) // Check if the player wants to play another game
				break;
		}
	}
} // end of class
