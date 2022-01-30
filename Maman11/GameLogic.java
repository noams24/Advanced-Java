// this class represents the game logic
import java.util.Scanner;

/**
 * Game Logic
 */
public class GameLogic {
	public static void game() {
		String guess; // the letter that the player guesses
		Scanner sc = new Scanner(System.in); // scanner
		String lines; // represents the word letters
		int lettersCounter = 26; // there are 26 letters from a - z
		int count = 0; // counts how many guesses
		Words word = new Words(); // generates a new word from the words array
		lines = new String(new char[word.getWord().length()]).replace("\0", "_"); // string of lines and letters that
																					// the user guess
		char[] usedLettersArray = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
				'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' }; // letters

		System.out.println("\nGuess any letter in the word");
		while (lines.contains("_")) {
			while (true) // makes sure the input is valid and the letter hasn't used yet
			{
				printLetters(usedLettersArray, lettersCounter); // print unused letters
				for (int i = 0; i < lines.length(); i++) // print the lines and letters that are correct
					System.out.print(lines.charAt(i) + " ");
				guess = sc.next();
				if (GameLogic.isAlpha(guess) && guess.length() == 1
						&& usedWords(usedLettersArray, guess.charAt(0), lettersCounter))// makes sure the input is valid
					break;
				else
					System.out.println("\nWrong input, please try again");
			}

			guess = guess.toLowerCase(); // lowercase the given letter
			lines = newLines(lines, guess, word.getWord()); // update the new lines
			usedLettersArray = substringLetter(usedLettersArray, guess.charAt(0), lettersCounter); // sub the used letter from the used letters array
																					
			count++;
		}
		System.out.println("Correct! You win! The word was " + word.getWord());
		System.out.println("number of guesses:" + count);
	}

	/**
	 * this method receiving a letter and generate String that present the correct
	 * letters and line of the random word.
	 * 
	 * @param tmp   presents the lines of the word
	 * @param guess is the letter that the user has guessed
	 * @param word  is the random word picked by the system
	 * @return the new String of lines
	 */
	private static String newLines(String tmp, String guess, String word) {
		String newlines = "";
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == guess.charAt(0)) // correct char!
				newlines += guess.charAt(0);
			else if (tmp.charAt(i) != '_') // already exist letter
				newlines += tmp.charAt(i);
			else
				newlines += '_';
		}
		return newlines;
	}

	/**
	 * this method checks if a given String is alpha( a - z letters) and returns
	 * true or false
	 * 
	 * @param guess is the given char
	 * @return true if its a letter and false if it is not
	 */
	private static boolean isAlpha(String guess) {
		char[] chars = guess.toCharArray();

		for (char c : chars) {
			if (!Character.isLetter(c))
				return false;
		}
		return true;
	}

	/**
	 * this method checks the letter is already used by the user
	 * 
	 * @param usedLettersArray - the array of the unused letters
	 * @param guess            - a letter that the user guessed
	 * @param lettersCounter   - how many letters in the unused letters array
	 * @return false if the letter is already used, true if the letter hasn't used
	 *         yet
	 */
	private static boolean usedWords(char[] usedLettersArray, char guess, int lettersCounter) {
		for (int i = 0; i < lettersCounter; i++) {
			if (usedLettersArray[i] == guess) // if the letter appears in the letter array it means it has'nt used yet
				return true;
		}
		System.out.println("\nError, You have already entered this letter"); // else the letter is already used
		return false;
	}

	/**
	 * this method sub the used letter from the unused letters array
	 * 
	 * @param usedLettersArray - the used letters array
	 * @param guess            - the used letter
	 * @param lettersCounter   - how many letters in the letters array
	 * @return the letters array without the used letter
	 */
	private static char[] substringLetter(char[] usedLettersArray, char guess, int lettersCounter) {
		for (int i = 0; i < lettersCounter; i++) {
			if (usedLettersArray[i] == guess)
				usedLettersArray[i] = '\0';
		}
		return usedLettersArray;
	}

	/**
	 * this method prints the used letters array
	 * 
	 * @param usedLettersArray - the array to print
	 * @param lettersCounter   how many letters to print
	 */
	private static void printLetters(char[] usedLettersArray, int lettersCounter) {
		System.out.println("The remain letters are:");
		for (int i = 0; i < lettersCounter; i++)
			if (usedLettersArray[i] != '\0')
				System.out.print(usedLettersArray[i] + " ");
		System.out.println("");
		return;
	}

	/**
	 * this method checks if the user wants to play again
	 * 
	 * @return true if the user want to play again, false if he doesn't want
	 */
	public static boolean playAgain() {
		while (true) {
			Scanner sc = new Scanner(System.in); // scanner
			System.out.println("Do you want to play again?(y|n)");
			String again = sc.next();
			if (again.equals("y")) {
				System.out.println("Play again!\n-----------------------------------------\n");
				return true;
			}
			if (again.equals("n")) {
				System.out.println("Thank you for playing! :)");
				return false;
			} else
				System.out.println("Wrong input");
		}
	}

} // end of class
