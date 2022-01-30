import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class RandomShapesController {
	@FXML
	private Button btn;

	private GraphicsContext gc;
	final int N = 10; // number of shapes

	@FXML
	public void initialize() {
		gc = canv.getGraphicsContext2D();
	}

	@FXML
	private Canvas canv;

	@FXML
	void btnPressed(ActionEvent event) // press the button
	{
		int CanvWidth = (int) canv.getWidth(); // gets the canvas width
		int CanvHeight = (int) canv.getHeight(); // gets the canvas height
		Random r = new Random(); // generates random numbers
		gc.clearRect(0, 0, canv.getWidth(), canv.getHeight()); // clear the board

		for (int i = 0; i < N; i++) {

			switch (r.nextInt(3)) // there are 3 random possibilities:
			// line,circle,rectangle
			{
			case 0: // case line
				int x1 = r.nextInt(CanvWidth);
				int y1 = r.nextInt(CanvHeight);
				int x2 = x1 - r.nextInt(CanvWidth / 4) + r.nextInt(CanvWidth / 4);
				int y2 = y1 - r.nextInt(CanvHeight / 4) + r.nextInt(CanvHeight / 4);
				gc.setStroke(new Color(r.nextFloat(), r.nextFloat(), r.nextFloat(), r.nextFloat()));
				gc.strokeLine(x1, y1, x2, y2);
				break;
			case 1: // case circle
				gc.setFill(new Color(r.nextFloat(), r.nextFloat(), r.nextFloat(), r.nextFloat()));
				gc.fillOval(r.nextInt(CanvWidth), r.nextInt(CanvHeight), r.nextInt(CanvWidth / 4),
						r.nextInt(CanvHeight / 4));
				break;
			case 2: // case rectangle
				gc.setFill(new Color(r.nextFloat(), r.nextFloat(), r.nextFloat(), r.nextFloat()));
				gc.fillRect(r.nextInt(CanvWidth), r.nextInt(CanvHeight), r.nextInt(CanvWidth / 4),
						r.nextInt(CanvHeight / 4));
				break;
			}
		}
	}
}
