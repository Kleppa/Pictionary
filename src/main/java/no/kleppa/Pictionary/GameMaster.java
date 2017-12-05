package no.kleppa.Pictionary;


import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.control.Button;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class GameMaster {
	BorderPane rootPane=new BorderPane();
	public GameMaster() {
		setUpRootPane();
	}

	private void setUpRootPane() {
		BorderPane subPane= new BorderPane();
		Canvas canvas = new Canvas(400,500);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

		gc.setStroke(Color.BLACK);
		canvasFunctionality(canvas,gc);


		rootPane.setBackground(
				new Background(
						new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)
				)
		);
		Button resetButton= new Button();
		resetButton.setText("Reset Canvas");
		resetCanvas(resetButton,gc,canvas);
		subPane.setBottom(resetButton);
		subPane.setCenter(canvas);
		rootPane.setBackground(new Background(new BackgroundFill(Color.SLATEGREY, CornerRadii.EMPTY, Insets.EMPTY)));
		rootPane.setCenter(subPane);

	}

	private void canvasFunctionality(Canvas canvas, GraphicsContext gc) {
		canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
				event -> {
					gc.lineTo(event.getX(), event.getY());
					gc.stroke();
				});
		canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
				event -> {
					gc.beginPath();
					gc.moveTo(event.getX(), event.getY());
					gc.stroke();
				});
	}
	private void resetCanvas(Button resetButton,GraphicsContext gc, Canvas canvas){
		resetButton.setOnAction(e->{
			gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

		});
	}

	public Parent getRootPane() {
		return rootPane;
	}

}
