package no.kleppa.Pictionary;


import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.control.Button;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GameMaster {
	private List<User> listOfUser =new ArrayList();
	private String guessedAnswer;
	private final String pictureAnswer="No answer";
	BorderPane rootPane=new BorderPane();
	private List<Label> listofUserLabels;
	private TextArea chatBox;

	public GameMaster() {
		setUpRootPane();
	}

	private void setUpRootPane() {
		BorderPane subPane= new BorderPane();
		Canvas canvas = new Canvas(600,600);
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
		HBox hboxBot=new HBox(900);
		Button resetButton= new Button();
		resetButton.setText("Reset Canvas");
		resetCanvas(resetButton,gc,canvas);
		//bottom part ////////
		TextField textfieldGuessDrawing = new TextField();
		textfieldGuessDrawing.setPromptText("Guess the Drawing!");
		textfieldGuessDrawingOnEnterSetup(textfieldGuessDrawing);
		hboxBot.getChildren().add(resetButton);
		hboxBot.getChildren().add(textfieldGuessDrawing);
		createSomeUsers();
		listofUserLabels= createLabelList();
////////left side part.......
		VBox userBox = new VBox(1);

		userBox.getChildren().add(new Label("Users : "));
		listofUserLabels.forEach(label -> userBox.getChildren().add(label));

		rootPane.setLeft(userBox);
		rootPane.setBottom(hboxBot);
		chatBox = new TextArea();
		chatBox.setDisable(true);
		chatBox.setText("TEST\n"+"test2");


		chatBox.setPrefHeight(20);
		chatBox.setPrefWidth(300);
		subPane.setRight(chatBox);
		subPane.setCenter(canvas);
		rootPane.setBackground(new Background(new BackgroundFill(Color.SLATEGREY, CornerRadii.EMPTY, Insets.EMPTY)));
		rootPane.setCenter(subPane);


	}

	private ArrayList<Label> createLabelList() {
		ArrayList<Label> tmpList= new ArrayList<>();
		for (User u :listOfUser) {
			tmpList.add(new Label(u.getName()+" score - "+ u.getScore()));
		}
		return tmpList;
	}

	private void textfieldGuessDrawingOnEnterSetup(TextField textField) {
		textField.setOnKeyPressed(event -> {
			if(event.getCode() == KeyCode.ENTER){
				guessedAnswer=textField.getText();
				System.out.println("guessedAnswer > " + guessedAnswer + " print in textfieldGuessDrawingOnenter method");
				textField.clear();

				if (checkIfGuessIsAnswer(guessedAnswer)){
					//Todo Present victory, change user to draw

				}else {
					String textToBeDisplayed = chatBox.getText() + "\n" + guessedAnswer;
					chatBox.setText(textToBeDisplayed);
				}
			}
		});
	}

	private boolean checkIfGuessIsAnswer(String guessedAnswer) {
		if (!guessedAnswer.equalsIgnoreCase(pictureAnswer)) return false;
		return true;
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
		resetButton.setOnAction(e-> gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight()));
	}

	public Parent getRootPane() {
		return rootPane;
	}
	public void correctGuess(){

	}
	public void scoreBoard(){

	}
	private void createSomeUsers(){
		for (int i = 0; i <6 ; i++) {
			listOfUser.add(new User("asdf"));
		}
	}

}
