import java.util.Random;

/*
 * This class represents the matrix methods
 */
//public class Matrix extends GameOfLifeController {
public class Matrix  {
	private int[][] mat;
	public static final int MATSIZE = 10; // mat 10x10

	/*
	 * Constructor for the matrix
	 */
	public Matrix(int size) {
		mat = new int[size][size];
		fillRandMmatix(); // fill with random numbers
	}

	/*
	 * getter for the matrix
	 */
	public int[][] getMatrix() {
		return mat;
	}
	/*
 	*  This method fills the matrix in random values of 0 and 1.
 	*/
	public void fillRandMmatix() {
		Random r = new Random();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				mat[i][j] = r.nextInt(2);
			}
		}
	}// End of fillRandMmatix

	/*
	 * This method changing the matrix according to the game rules
	 */
	public void changeMatrix() {
		int aliveNeighbors = 0;
		int[][] newMat = new int[MATSIZE][MATSIZE];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (i != 0 && i != mat.length - 1 && j != 0 && j != mat.length - 1) // in the middle
					aliveNeighbors = mat[i - 1][j - 1] + mat[i][j - 1] + mat[i + 1][j - 1] + mat[i - 1][j]
							+ mat[i + 1][j] + mat[i - 1][j + 1] + mat[i][j + 1] + mat[i + 1][j + 1];
				else // edges
				{
					if (i == 0) { // left column
						if (j == 0) // top left corner
							aliveNeighbors = mat[i][j + 1] + mat[i + 1][j + 1] + mat[i + 1][j];
						else if (j == mat.length - 1) // bottom left corner
							aliveNeighbors = mat[i][j - 1] + mat[i + 1][j - 1] + mat[i + 1][j];
						else // middle of the left column
							aliveNeighbors = mat[i][j - 1] + mat[i][j + 1] + mat[i + 1][j - 1] + mat[i + 1][j]
									+ mat[i + 1][j + 1];
					} else if (i == mat.length - 1) // right column
					{
						if (j == 0) // top right corner
							aliveNeighbors = mat[i][j + 1] + mat[i - 1][j] + mat[i - 1][j + 1];
						else if (j == mat.length - 1) // bottom right corner
							aliveNeighbors = mat[i - 1][j] + mat[i - 1][j - 1] + mat[i][j - 1];
						else // middle of the right column
							aliveNeighbors = mat[i][j - 1] + mat[i][j + 1] + mat[i - 1][j - 1] + mat[i - 1][j]
									+ mat[i - 1][j + 1];
					} else // middle tile of the edge
					{
						if (j == 0) { // middle of the top row
							aliveNeighbors = mat[i - 1][j] + mat[i + 1][j] + mat[i][j + 1] + mat[i - 1][j + 1]
									+ mat[i + 1][j + 1];
						} else if (j == mat.length - 1) { // middle of bottom row
							aliveNeighbors = mat[i - 1][j] + mat[i + 1][j] + mat[i - 1][j - 1] + mat[i][j - 1]
									+ mat[i + 1][j - 1];
						}
					}
				}
				if (mat[i][j] == 0) {
					if (aliveNeighbors == 3)
						newMat[i][j] = 1;
					else
						newMat[i][j] = 0;
				} else {
					if (aliveNeighbors < 2 || aliveNeighbors > 3)
						newMat[i][j] = 0;
					else
						newMat[i][j] = 1;
				}
			} // end of first loop
		} // end of second loop
		mat = newMat;
		return;
	} // end of changeMatrix

} // end of class
