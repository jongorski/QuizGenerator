
///////////////////////////////////////////////////////////////////////////////
//
// Title: Quiz Generator
// Course: CS400 Spring 2019
// Project: Team Project
//
// Author: Mikel Terracina, Danielle Hart, Jon Gorski, Jack Wolf
// Email: mterracina@wisc.edu, dahart2@wisc.edu, jongorski2@wisc.edu, jwolf22@wisc.edu
//
// Lecturer's Name: Andrew Kuemmel & Deb Deppler
// Lecture Number: 004 & 001
//
// Due Date: 2019-05-02
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
// Online Sources: none
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * The quizstart class is called when the user chooses to start the quiz
 * it passes control of the stage to QuizComplete when user is finished
 * 
 * @author jwolf
 *
 */
public class QuizStart {
	
	public QuizBank qb;
	public Stage curr_stage;
	
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
		if (!json_path.getText().contentEquals("")) {
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
		else {
			Alert empty_file = new Alert(AlertType.ERROR);
			empty_file.setContentText("Please choose json file.\n");
			empty_file.show();
		}
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

		//Welcome text
		Label welcomeLabel = new Label("Welcome to Quiz Generator!");
		welcomeLabel.setFont(Font.font("Verdana", 40));
		VBox.setMargin(welcomeLabel, new Insets(10, 0, 10, 0));
		
		//Instruction label
		Label instructionLabel = new Label("Please choose your quiz file:");
		instructionLabel.setFont(Font.font("Verdana", 16));
		VBox.setMargin(instructionLabel, new Insets(10,0,10,0));
		
		// input path to json file
		TextField input_json_path = new TextField();
		input_json_path.setVisible(false);
		input_json_path.setPromptText("Enter path to json");
		input_json_path.setStyle("-fx-font-size:15");
		input_json_path.setMinSize(300, 50);  /*********changed height ******/
		input_json_path.setMaxSize(300, 50);  /*********changed height ******/
		VBox.setMargin(input_json_path, new Insets(20, 0, 40, 0));

        // Mike code 
        final FileChooser fileChooser = new FileChooser();
        final Button openButton = new Button("Open File...");  // label at top
        openButton.setStyle("-fx-font-size:15");
        openButton.setMinSize(300, 90);
        openButton.setPrefSize(300, 90);
        openButton.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    File file = fileChooser.showOpenDialog(curr_stage);
                    if (file != null) {
                    	input_json_path.setVisible(true);
                        input_json_path.setText(file.getAbsolutePath());
                    }
                }
            });
        VBox.setMargin(openButton, new Insets(0, 0, 30, 0));
        /*******************************************************************/
		
		// Add Question button
		Button next = new Button("Add Question");
		next.setStyle("-fx-font-size:15");
		next.setMinSize(300, 90);  /*********changed height ******/
		next.setPrefSize(300, 90); /*********changed height ******/
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
		start.setMinSize(300, 90); /*********changed height ******/
		start.setPrefSize(300, 90); /*********changed height ******/
		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				startHandler(input_json_path);
			}
		});
		VBox.setMargin(start, new Insets(0, 0, 30, 0));

		vbox.getChildren().add(welcomeLabel);
		vbox.getChildren().add(instructionLabel);
	    vbox.getChildren().add(openButton); /**** mike added ****/
		vbox.getChildren().add(input_json_path);
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