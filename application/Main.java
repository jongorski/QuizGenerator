package application;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Main extends Application {
	
	
	/**
	 * Creates and runs ask Question scene with given question.
	 * 
	 * @param primaryStage
	 * @param q
	 * @param qNum
	 * @param totalQuesNum
	 
	public void displayQuestion(Stage primaryStage, Question q) {
		AskQuestion a = new AskQuestion();
		Scene q_scene = a.AskQuestionScene(primaryStage, q);
		q_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(q_scene);
		primaryStage.show();
	}*/

	@Override
	public void start(Stage primaryStage) {
		try {
			System.out.println("main 1");
			QuizBank qb = new QuizBank();
			
			List<Choice> choices = new ArrayList<Choice>();
			
			System.out.println("main 2");
			Question q = new Question("dogs", "What breed is this?", 
					choices, "such a cute dog", "harrier-puppy.jpg");
			qb.addQuestionToQuiz(q);
			System.out.println("main 3");
			QuizStart start = new QuizStart(qb, primaryStage);
			System.out.println("main 4");
			Scene AddQOrStart = start.AddQuestionOrStart();
			System.out.println("main 5");
			Scene WelcomeScene = start.WelcomeScene(AddQOrStart);
			System.out.println("main 6");
			WelcomeScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(WelcomeScene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
