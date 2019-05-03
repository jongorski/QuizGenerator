package application;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class QuizStart {
	
	public QuizBank qb;
	public Stage curr_stage;
	//public AddQuestionFormNode add_pop;
	
	/**
	 * constructor method
	 * @param stage: current stage of GUI
	 */
	public QuizStart(Stage stage) {
		this.qb = new QuizBank();
		this.curr_stage = stage;
	}

	/**
	 * handler method for add question button
	 * 
	 * passes control to AddQuestionFormNode
	 */
	private void addQuestionHandler(TextField json_path) {
		FileParser fp = new FileParser(json_path.getText());
		try {
			List<Question> questions_from_file = fp.Parse();
			this.qb = fp.addQuestionsToQuizBank(questions_from_file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		AddQuestionFormNode addQ = new AddQuestionFormNode(this.qb, this.curr_stage);
		Scene addQScene = addQ.AddQuestion();
		this.curr_stage.setScene(addQScene);
	}
	
	/**
	 * handler method for start quiz button
	 * 
	 * passes control to AskQuestion
	 */
	private void startHandler(TextField json_path) { 
		FileParser fp = new FileParser(json_path.getText());
		try {
			List<Question> questions_from_file = fp.Parse();
			this.qb = fp.addQuestionsToQuizBank(questions_from_file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		AskQuestion asker = new AskQuestion(this.qb, this.curr_stage);
		this.curr_stage.setScene(asker.ChooseTopicStart());
		
	}
	
	/*
	 * Creates welcome scene for quiz users
	 * 
	 * next_scene: scene to move to once 'move on' button is pressed
	 */
	private Scene WelcomeScene() {
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vbox, 800, 600);

		// input path to json file
		TextField input_json_path = new TextField();
		input_json_path.setPromptText("Enter path to json");
		input_json_path.setStyle("-fx-font-size:15");
		input_json_path.setMinSize(300, 75);
		input_json_path.setMaxSize(300, 75);
		VBox.setMargin(input_json_path, new Insets(20, 0, 40, 0));

		// Add Question button
		Button next = new Button("Add Question");
		next.setStyle("-fx-font-size:15");
		next.setMinSize(300, 125);
		next.setPrefSize(300, 125);
		next.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				addQuestionHandler(input_json_path);
			}
		});
		VBox.setMargin(next, new Insets(0, 0, 30, 0));
		
		// start quiz button
		Button start = new Button("Start quiz!");
		start.setStyle("-fx-font-size:15");
		start.setMinSize(300, 125);
		start.setPrefSize(300, 125);
		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				startHandler(input_json_path);
			}
		});
		VBox.setMargin(start, new Insets(0, 0, 30, 0));

		vbox.getChildren().add(input_json_path);
		//vbox.getChildren().add(add_q);
		vbox.getChildren().add(next);
		vbox.getChildren().add(start);

		return scene;
	}

	
	/**
	 * static method to start quiz and call methods in
	 * this class
	 * @param curr_stage: current GUI stage
	 */
	public static void startQuiz(Stage curr_stage) {
		QuizStart starter = new QuizStart(curr_stage);
		curr_stage.setScene(starter.WelcomeScene());
		curr_stage.show();
	}
	
}
