package application;

import java.util.Arrays;
import java.util.Set;

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
	
	private Stage curr_stage;
	private QuizBank qb;
	
	public QuizStart(QuizBank qb, Stage curr_stage) {
		this.qb = qb;
		this.curr_stage = curr_stage;
	}
	
	private void startHandler(String topic, int n) {
		System.out.println("QuizStart startHandler 1");
		AskQuestion asker = new AskQuestion(this.curr_stage,
			                                this.qb,
			                                topic, 
			                                n);
		System.out.println("QuizStart startHandler 2");
		asker.beginQuiz();
	}

	/*
	 * Creates welcome scene for quiz users
	 * 
	 * qb: user's quizbank
	 * curr_stage: current stage of GUI to change scene
	 * next_scene: scene to move to once 'move on' button is pressed
	 * 
	 */
	public Scene WelcomeScene(Scene next_scene) {
		System.out.println("welcome scene");
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

		// Add question button
		Button add_q = new Button("Add question");
		add_q.setStyle("-fx-font-size:15");
		add_q.setMinSize(300, 125);
		add_q.setPrefSize(300, 125);
		AddQuestionFormNode addQ = new AddQuestionFormNode();
		Stage add_popup_stage = addQ.AddQuestion(qb);
		add_q.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				add_popup_stage.show();
			}
		});
		VBox.setMargin(add_q, new Insets(0, 0, 30, 0));

		// Move on button
		Button next = new Button("Move on!");
		next.setStyle("-fx-font-size:15");
		next.setMinSize(300, 125);
		next.setPrefSize(300, 125);
		next.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				curr_stage.setScene(next_scene);
			}
		});
		VBox.setMargin(next, new Insets(0, 0, 30, 0));

		vbox.getChildren().add(input_json_path);
		vbox.getChildren().add(add_q);
		vbox.getChildren().add(next);

		return scene;
	}

	public Scene AddQuestionOrStart() {
		System.out.println("add or start");
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vbox, 800, 700);

		// Title of main page
		Label title = new Label("QuizGenerator");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		VBox.setMargin(title, new Insets(20, 0, 70, 0));

		// Start button
		Button start_button = new Button("Start quiz!");
		start_button.setStyle("-fx-font-size:30");
		start_button.setMaxSize(300, 125);
		start_button.setMinSize(300, 125);
		VBox.setMargin(start_button, new Insets(70, 0, 40, 0));

		// Topic list
		int k = 0;
		String[] topics = new String[qb.topics.size()];
		for (String top : qb.topics) {
			topics[k++] = top;
		}
		ComboBox<String> topic_box = new ComboBox<String>(FXCollections.observableArrayList(topics));
		topic_box.setPromptText("Topics");
		topic_box.setStyle("-fx-font-size:15");
		topic_box.setMinSize(225, 75);
		topic_box.setMaxSize(225, 75);
		topic_box.setPrefSize(225, 75);
		VBox.setMargin(topic_box, new Insets(20, 0, 20, 0));

		// Choose number of questions
		TextField num_q = new TextField();
		num_q.setPromptText("Enter # questions");
		num_q.setStyle("-fx-font-size:15");
		num_q.setMinSize(225, 75);
		num_q.setMaxSize(225, 75);
		VBox.setMargin(num_q, new Insets(20, 0, 20, 0));
		
		start_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("start");
				startHandler((String)topic_box.getValue(), Integer.parseInt((String)num_q.getText()));
			}
		});

		vbox.getChildren().add(title);
		vbox.getChildren().add(topic_box);
		vbox.getChildren().add(num_q);
		vbox.getChildren().add(start_button);

		return scene;
	}
}
