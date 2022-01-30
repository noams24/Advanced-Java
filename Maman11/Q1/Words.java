// this class represents to random words, picking a random word
public class Words // words
{
	private static final String[] WORDS = { "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
			"class", "const", "continue", "default", "double", "do", "else", "enum", "extends", "false", "final",
			"finnaly", "float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long",
			"native", "new", "null", "package", "private", "public", "return", "short", "static", "super", "switch",
			"this", "throw", "true", "try", "terminator", "banana", "computer", "cow", "rain", "water" }; // random
																											// words

	private String word;

	/**
	 * structor for word
	 */
	public Words() {
		this.word = chooseRandomWord();
		// this.tmpLettersArray = lettersArray;
	}

	/**
	 * this method generates random word
	 * 
	 * @return the random word
	 */
	private static String chooseRandomWord() {
		return WORDS[(int) (Math.random() * WORDS.length)];
	}

	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}
} // end of class