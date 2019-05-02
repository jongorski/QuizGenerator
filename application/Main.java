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

	@Override
	public void start(Stage primaryStage) {
		try {
			
			QuizBank qb = new QuizBank();
			List<Choice> choices1 = new ArrayList<Choice>();
			choices1.add(new Choice("T", "Harrier"));
			choices1.add(new Choice("F", "Lab"));
			Question q1 = new Question("dogs", "What breed is this?", 
					choices1, "such a cute dog", "../"
							+ "harrier-puppy.jpg");
			List<Choice> choices2 = new ArrayList<Choice>();
			choices2.add(new Choice("T", "yah"));
			Question q2 = new Question("sports", "sports?", 
					choices2, "sports", "");
			qb.addQuestionToQuiz(q1);
			qb.addQuestionToQuiz(q2);

			QuizStart.startQuiz(primaryStage, qb);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
