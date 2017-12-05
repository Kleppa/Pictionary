package no.kleppa.Pictionary;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class GameMaster {
	BorderPane rootPane=new BorderPane();
	public GameMaster() {
		setUpRootPane();
	}

	private void setUpRootPane() {
		Canvas canvas = new Canvas(400,500);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(10, 75, canvas.getWidth(), canvas.getHeight());

		gc.setStroke(Color.BLACK);
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

		rootPane.setBackground(
				new Background(
						new BackgroundFill(Color.CRIMSON, CornerRadii.EMPTY, Insets.EMPTY)
				)
		);

		rootPane.setCenter(canvas);

	}


	public Parent getRootPane() {
		return rootPane;
	}

}
