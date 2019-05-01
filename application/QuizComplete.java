package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class QuizComplete {

	public int numCorrect;
	public int numAnswered;
	public double percentCorrect;
	
	
	public QuizComplete() {
		this.numCorrect = 0;
		this.numAnswered = 0;
		this.percentCorrect = 0;
	}
	
	
	public Scene QuizCompleteScene(Stage curr_stage, int correct, int answered) {
		
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		Scene quiz_Complete_Scene = new Scene(vBox, 700, 500);
		
		//Title of quiz complete page
		Label title = new Label("Quiz Complete!");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		VBox.setMargin(title, new Insets(0, 0, 70, 0));
		
		//Number correct line
		Label numCorrectLabel = new Label("Total Number Correct: " + correct);
		numCorrectLabel.setFont(Font.font("Verdana", 20));
		VBox.setMargin(numCorrectLabel, new Insets(10, 0, 10, 0));
		
		//Number answered line
		Label numAnsweredLabel = new Label("Total Number Answered: " + answered);
		numAnsweredLabel.setFont(Font.font("Verdana", 20));
		VBox.setMargin(numAnsweredLabel, new Insets(10, 0, 10, 0));
		
		//Percent correct line
		double percCorrect = ((double)correct/(double)answered)*100;
		Label percCorrectLabel = new Label("Percent Correct: " + percCorrect + "%");
		percCorrectLabel.setFont(Font.font("Verdana", 20));
		VBox.setMargin(percCorrectLabel, new Insets(10, 0, 10, 0));
		
		//Exit Button
		Button exitButton = new Button("Exit");
		exitButton.setStyle("-fx-font-size:25");
		exitButton.setMaxSize(200, 50);
		exitButton.setMinSize(200, 50);
		exitButton.setPrefSize(200, 50);
		VBox.setMargin(exitButton, new Insets(10, 0, 10, 0));
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				curr_stage.setScene(ExitScene(curr_stage));
			}
		});
		
		//Quiz Again Button
		Button quizAgainButton = new Button("Take Another Quiz");
		quizAgainButton.setStyle("-fx-font-size:25");
		quizAgainButton.setMaxSize(250, 50);
		quizAgainButton.setMinSize(250, 50);
		quizAgainButton.setPrefSize(250, 50);
		VBox.setMargin(quizAgainButton, new Insets(10, 0, 10, 0));
		
		vBox.getChildren().add(title);
		vBox.getChildren().add(numCorrectLabel);
		vBox.getChildren().add(numAnsweredLabel);
		vBox.getChildren().add(percCorrectLabel);
		vBox.getChildren().add(quizAgainButton);
		vBox.getChildren().add(exitButton);
		
		return quiz_Complete_Scene;
	}
	
	public Scene ExitScene(Stage curr_stage) {
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		Scene exit_Scene = new Scene(vBox, 700, 500);
		
		//Save label
		Label saveLabel = new Label("Save all questions to new file:");
		saveLabel.setFont(Font.font("Verdana", 30));
		
		//file text box
		TextField fileText = new TextField();
		fileText.setMinSize(400, 25);
		fileText.setMaxSize(400, 25);
		VBox.setMargin(fileText, new Insets(10,0,10,0));
		
		
		//Save button
		Button saveButton = new Button("Save");
		saveButton.setFont(Font.font("Verdana", 20));
		saveButton.setMinSize(200,50);
		saveButton.setMaxSize(200, 50);
		saveButton.setPrefSize(200, 50);
		VBox.setMargin(saveButton, new Insets(10,0,10,0));
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (fileText.getText() != null && !fileText.getText().isEmpty()) {
					String filename = fileText.getText();
					boolean saved = saveFile(filename);
					curr_stage.setScene(goodbyeSavedScene());
				}
			}
		});
		
		//Exit without saving button
		Button exitW = new Button("Exit Without Saving");
		exitW.setFont(Font.font("Verdana", 20));
		exitW.setMinSize(400,50);
		exitW.setMaxSize(400, 50);
		exitW.setPrefSize(400, 50);
		VBox.setMargin(exitW, new Insets(100,0,10,0));
		exitW.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				curr_stage.setScene(goodbyeScene());
			}
		});
		
		vBox.getChildren().add(saveLabel);
		vBox.getChildren().add(fileText);
		vBox.getChildren().add(saveButton);
		vBox.getChildren().add(exitW);
		
		return exit_Scene;
	}
	
	public Scene goodbyeScene() {
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		Scene goodbye_Scene = new Scene(vBox, 700,500);
		
		//goodbye text
		Label goodbyeLabel = new Label("Goodbye!");
		goodbyeLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		
		vBox.getChildren().add(goodbyeLabel);
		return goodbye_Scene;
	}
	
	public Scene goodbyeSavedScene() {
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		Scene goodbye_Saved_Scene = new Scene(vBox, 700,500);
		
		//goodbye text
		Label goodbyeSavedLabel = new Label("File Saved, Goodbye!");
		goodbyeSavedLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		
		vBox.getChildren().add(goodbyeSavedLabel);
		return goodbye_Saved_Scene;
	}
	
	public static boolean saveFile(String filename) {
		
		//if file saved return true
		//else return false
		return true;
		
	}
}
