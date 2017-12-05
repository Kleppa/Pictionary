package no.kleppa.Pictionary;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.util.EventListener;


/**
 * -Jarand Waage Kleppa , December 2017
 */
public class App extends Application implements EventHandler<ActionEvent> {
	Button playBtn;
	public static void main(String[] args) {

		launch(args);
	}

	public void start(final Stage primaryStage) throws Exception {

		primaryStage.setTitle("Pictionary");
		final Scene[] scene = new Scene[1];
		playBtn=new Button();
		playBtn.setText("Play!");

		playBtn.setOnAction(e->{
			GameMaster gm = new GameMaster();

			 scene[0] =new Scene(gm.getRootPane(),600,900);
			primaryStage.setScene(scene[0]);
			primaryStage.show();
		});

		StackPane layout =new StackPane();
		layout.getChildren().add(playBtn);
		scene[0] = new Scene(layout,400,500);

		primaryStage.setScene(scene[0]);
		primaryStage.show();
	}

	public void handle(ActionEvent event) {

	}
}
