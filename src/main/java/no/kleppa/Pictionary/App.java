package no.kleppa.Pictionary;

import com.sun.xml.internal.bind.v2.TODO;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.util.EventListener;


/**
 * -Jarand Waage Kleppa , December 2017
 */
public class App extends Application implements EventHandler<ActionEvent> {
	private int id = 0;
	Button playBtn;

	public static void main(String[] args) {

		launch(args);

	}

	public void start(final Stage primaryStage) throws Exception {

		primaryStage.setTitle("Pictionary");
		final Scene[] scene = new Scene[1];
		playBtn = new Button();
		playBtn.setText("Play!");
		playBtn.setDisable(true);

		playBtn.setOnAction(e -> {
			GameMaster gm = new GameMaster();

			scene[0] = new Scene(gm.getRootPane(), 600, 900);
			primaryStage.setScene(scene[0]);
			primaryStage.show();
		});

		StackPane layout = new StackPane();
		TextField usernameField = new TextField();
		Label usernameLabel =new Label("Username: ");
		usernameLabel.setTranslateY(-75);
		usernameField.setMaxWidth(250);
		usernameField.setTranslateY(-50);
		usernameFieldPrepareForInput(usernameField);
		layout.getChildren().add(usernameLabel);
		layout.getChildren().add(usernameField);
		layout.getChildren().add(playBtn);
		scene[0] = new Scene(layout, 800, 800);

		primaryStage.setScene(scene[0]);
		primaryStage.show();
	}

	private void usernameFieldPrepareForInput(TextField usernameField) {
		usernameField.setOnKeyReleased(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				if (!(usernameField.getText().equalsIgnoreCase("Username here"))) {
					//create thread
					new ClientThread(new User(usernameField.getText()), id++);
					playBtn.setDisable(false);
					usernameField.setDisable(true);
				}
			}
		});
	}

	// TODO: 05/12/2017 Trenger jeg flere roomthreads eller noe?!?!?!?!

	public void handle(ActionEvent event) {

	}

}
