/*
 * This class represent a controller for the project.
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

public class GameOfLifeController {

	@FXML
	private Canvas canv;

	@FXML
	private Button btn;

	protected GraphicsContext gc;
	private boolean alreadyPressed = false; // for initializing the first matrix
	final int MATSIZE = 10; // mat 10x10
	final int SQUARESIZE = 50;
	protected Matrix mat; 

	/*
	 * This method initializing canvas
	 */
	public void initialize() {
		gc = canv.getGraphicsContext2D();
	}

	/*
	 * This method is the logics of the game, clicking on the button triggers this method.
	 */
	@FXML
	void btnPressed(ActionEvent event) {
		if (alreadyPressed == false) {
			mat = new Matrix(MATSIZE); // initialize the first matrix
			int x = 0, y = 0; // coordinates of the first square in the mat
			int[][] tmp = mat.getMatrix();
			
			for (int i = 0; i < MATSIZE; i++) {
				for (int j = 0; j < MATSIZE; j++) {
					if (tmp[i][j] == 0) //  stroke empty square - represent death
						gc.strokeRect(x, y, SQUARESIZE, SQUARESIZE);
					else // stroke full square - represent life 
						gc.fillRect(x, y, SQUARESIZE, SQUARESIZE);
					x += SQUARESIZE;
				}
				y += SQUARESIZE;
				x = 0;
				alreadyPressed = true;
			}
		}
		else // change the matrix according to the game rules 
		{
			gc.clearRect(0, 0, canv.getWidth(), canv.getHeight()); // clear the board
			mat.changeMatrix();
			int x = 0, y = 0; // coordinates of the first square in the mat
			int[][] tmp = mat.getMatrix();
			for (int i = 0; i < MATSIZE; i++) {
				for (int j = 0; j < MATSIZE; j++) {
					if (tmp[i][j] == 0)
						gc.strokeRect(x, y, 50, 50);
					else
						gc.fillRect(x, y, 50, 50);
					x += SQUARESIZE;
				}
				y += SQUARESIZE;
				x = 0;
				alreadyPressed = true;
			}	
		} 
	}
} // end of class
