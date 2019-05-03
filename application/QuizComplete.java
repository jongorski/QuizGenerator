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

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * This class holds the scenes that all follow after the user has completed a quiz.
 * The methods QuizCompleteScene(), ExitScene(), goodbyeScene(), and goodbyeSavedScene()
 * all create/return scenes that will be viewed depending on certain buttons the user
 * decides to click.
 * 
 * @author Jon Gorski
 *
 */
public class QuizComplete {

	public int numCorrect;
	public int numAnswered;
	public double percentCorrect;
	
	public QuizComplete() {
		this.numCorrect = 0;
		this.numAnswered = 0;
		this.percentCorrect = 0;
	}
	
	/**
	 * The QuizCompleteScene() is called immediately after the user finishes answering the last
	 * question in their quiz. It displays the quiz results and gives the user the options
	 * 1.) Take another quiz by clicking the [Take another quiz] button. This returns them to 
	 *     the start up menu.
	 * 2.) Exit the quiz by clicking the [Exit] button. This takes them to the ExitScene() scene.
	 * 
	 * @param curr_stage is used to make sure the same stage is used to display
	 * the scenes throughout the program.
	 * @param correct, answered are both used in calculating and displaying the quiz results.
	 * @param qb is used to pass forward to the next scene in case the user wants to save the quiz.
	 */
	public static Scene QuizCompleteScene(Stage curr_stage, int correct, int answered, QuizBank qb) {
		//setting up the vbox and scene
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		Scene quiz_Complete_Scene = new Scene(vBox, 800, 700);
		
		//Creating the title of quiz complete page
		Label title = new Label("Quiz Complete!");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		VBox.setMargin(title, new Insets(0, 0, 70, 0));
		
		//creating the "number correct" line of text
		Label numCorrectLabel = new Label("Total Number Correct: " + correct);
		numCorrectLabel.setFont(Font.font("Verdana", 20));
		VBox.setMargin(numCorrectLabel, new Insets(10, 0, 10, 0));
		
		//creating the "number answered" line of text
		Label numAnsweredLabel = new Label("Total Number Answered: " + answered);
		numAnsweredLabel.setFont(Font.font("Verdana", 20));
		VBox.setMargin(numAnsweredLabel, new Insets(10, 0, 10, 0));
		
		//creating the "percent correct" line of text
		double percCorrect = ((double)correct/(double)answered)*100;
		BigDecimal bd = new BigDecimal(percCorrect);
		bd = bd.round(new MathContext(4));
		percCorrect = bd.doubleValue();
		Label percCorrectLabel = new Label("Percent Correct: " + percCorrect + "%");
		percCorrectLabel.setFont(Font.font("Verdana", 20));
		VBox.setMargin(percCorrectLabel, new Insets(10, 0, 10, 0));
		
		//creating the [Exit] Button
		Button exitButton = new Button("Exit");
		exitButton.setStyle("-fx-font-size:25");
		exitButton.setMaxSize(300, 125);
		exitButton.setMinSize(300, 125);
		VBox.setMargin(exitButton, new Insets(10, 0, 10, 0));
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				curr_stage.setScene(ExitScene(curr_stage, qb));
			}
		});
		
		//creating the [quiz again] Button
		Button quizAgainButton = new Button("Take Another Quiz");
		quizAgainButton.setStyle("-fx-font-size:25");
		quizAgainButton.setMaxSize(300, 125);
		quizAgainButton.setMinSize(300, 125);
		VBox.setMargin(quizAgainButton, new Insets(10, 0, 10, 0));
		quizAgainButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main newMain = new Main();
				newMain.start(curr_stage);
			}
		});
		
		//adding things to the scene
		vBox.getChildren().add(title);
		vBox.getChildren().add(numCorrectLabel);
		vBox.getChildren().add(numAnsweredLabel);
		vBox.getChildren().add(percCorrectLabel);
		vBox.getChildren().add(quizAgainButton);
		vBox.getChildren().add(exitButton);
		
		//returning the scene
		return quiz_Complete_Scene;
	}
	
	/**
	 * The ExitScene() method creates a new scene that is displayed after the 
	 * exit button is clicked from the QuizCompleteScene. It gives the user the
	 * option to 
	 * 1.) Type out a preferred filename and save the quiz bank to a json file
	 *     by clicking the [save] button.
	 * 2.) Exit the quiz without saving by clicking the [Exit without saving] button.
	 * 
	 * @param curr_stage is used to make sure the same stage is used to display
	 * the scenes throughout the program.
	 * @param qb is used if the user wants to save the quiz bank to a JSON
	 * file.
	 */
	public static Scene ExitScene(Stage curr_stage, QuizBank qb) {
		//setting up the vbox and scene
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		Scene exit_Scene = new Scene(vBox, 800, 700);
		
		//Creating the save instruction label
		Label saveLabel = new Label("Save all questions to new file (include \".json\" at the end of your file name):");
		saveLabel.setFont(Font.font("Verdana", 14));
		
		//creating the text box where the user types in the name of the
		//new file they want to create and save
		TextField fileText = new TextField();
		fileText.setMinSize(400, 25);
		fileText.setMaxSize(400, 25);
		VBox.setMargin(fileText, new Insets(10,0,10,0));
		
		//creating the [save] button
		Button saveButton = new Button("Save");
		saveButton.setFont(Font.font("Verdana", 20));
		saveButton.setMinSize(300, 125);
		saveButton.setMaxSize(300, 125);
		VBox.setMargin(saveButton, new Insets(10,0,10,0));
		//when button is pressed, it saves a file using the text typed in by the user
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (fileText.getText() != null && !fileText.getText().isEmpty()) {
					String filename = fileText.getText();
					boolean saved = saveFile(filename, qb);
					if (saved) {
						curr_stage.setScene(goodbyeSavedScene());
					}
					else {
						Alert a = new Alert(AlertType.ERROR);
						a.setContentText("Please enter valid file name & make sure to end with \".json\"");
						a.show();
					}
				}
			}
		});
		
		//creating the [exit without saving] button
		Button exitW = new Button("Exit Without Saving");
		exitW.setFont(Font.font("Verdana", 20));
		exitW.setMinSize(300, 125);
		exitW.setMaxSize(300, 125);
		VBox.setMargin(exitW, new Insets(100,0,10,0));
		exitW.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				curr_stage.setScene(goodbyeScene());
			}
		});
		
		//putting things in the scene
		vBox.getChildren().add(saveLabel);
		vBox.getChildren().add(fileText);
		vBox.getChildren().add(saveButton);
		vBox.getChildren().add(exitW);
		
		return exit_Scene;
	}
	
	/**
	 * The goodbyeScene() method creates and returns a new scene that only says "Goodbye!" This
	 * method is only called when pressing the "exit without saving" button from the ExitScene 
	 * class/scene.
	 */
	public static Scene goodbyeScene() {
		//setting up vbox and scene
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		Scene goodbye_Scene = new Scene(vBox,800,700);
		
		//creating goodbye text
		Label goodbyeLabel = new Label("Goodbye!");
		goodbyeLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		
		//putting things in the scene
		vBox.getChildren().add(goodbyeLabel);
		
		//returning the scene
		return goodbye_Scene;
	}
	
	/**
	 * The goodbyeSavedScene() method creates & returns a new scene that only says "File Saved, Goodbye!" It's
	 * only called by pressing the "Save" button in the ExitScene class/scene.
	 */
	public static Scene goodbyeSavedScene() {
		//setting up vbox and scene
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		Scene goodbye_Saved_Scene = new Scene(vBox, 700,500);
		
		//goodbye text
		Label goodbyeSavedLabel = new Label("File Saved, Goodbye!");
		goodbyeSavedLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		
		//putting things in the scene
		vBox.getChildren().add(goodbyeSavedLabel);
		
		//returning the scene
		return goodbye_Saved_Scene;
	}
	
	public static boolean saveFile(String filename, QuizBank qb) {
		WriteFileJSON filewriter = new WriteFileJSON();
		try {
			filewriter.writeFileFromQuizBank(filename, qb);
		} catch (IllegalNullFileException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
			
		} catch (IllegalJSONFileException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
}