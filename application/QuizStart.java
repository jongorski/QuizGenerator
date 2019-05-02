package application;

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
	public AddQuestionFormNode add_pop;
	
	/**
	 * constructor method
	 * @param stage: current stage of GUI
	 */
	public QuizStart(Stage stage) {
		this.qb = new QuizBank();
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
		this.qb.addQuestionToQuiz(q1);
		this.qb.addQuestionToQuiz(q2);
		this.curr_stage = stage;
		this.add_pop = new AddQuestionFormNode(this.qb);
	}
	
	/**
	 * handler method for start quiz button
	 * @param topic: topic of quiz
	 * @param n: number of questions to administer
	 */
	private void startHandler(String topic, int n) {
		AskQuestion asker = new AskQuestion(this.curr_stage,
			                                this.qb,
			                                topic, 
			                                n);
		asker.beginQuiz();
	}
	
	/**
	 * move to next scene
	 * @param next: next scene
	 */
	private void moveOnHandler(Scene next) {
		this.curr_stage.setScene(next);
	}

	/**
	 * handler method for add question button
	 */
	private int addQuestionHandler() {
		this.add_pop.AddQuestion().show();
		return 0;
	}
	
	private void swap() {
		this.qb = this.add_pop.qb;
		System.out.println(this.qb.topics);
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

		// Add question button
		Button add_q = new Button("Add question");
		add_q.setStyle("-fx-font-size:15");
		add_q.setMinSize(300, 125);
		add_q.setPrefSize(300, 125);
		add_q.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				int j = addQuestionHandler();
				swap();
			}
		});
		VBox.setMargin(add_q, new Insets(0, 0, 30, 0));

		// Move on button
		Button next = new Button("Move on!");
		next.setStyle("-fx-font-size:15");
		next.setMinSize(300, 125);
		next.setPrefSize(300, 125);
		Scene next_scene = this.AddQuestionOrStart();
		next.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				moveOnHandler(next_scene);
			}
		});
		VBox.setMargin(next, new Insets(0, 0, 30, 0));

		vbox.getChildren().add(input_json_path);
		vbox.getChildren().add(add_q);
		vbox.getChildren().add(next);

		return scene;
	}

	/**
	 * method to generate scene for add question or
	 * start menu
	 * @return scene to show 
	 */
	private Scene AddQuestionOrStart() {
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
		String[] topics = new String[this.qb.topics.size()];
		for (String top : this.qb.topics) {
			topics[k] = top;
			k = k + 1;
		}
		ComboBox<String> topic_box = new ComboBox<String>(FXCollections.observableArrayList(topics));
		topic_box.setVisibleRowCount(10);
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
				startHandler((String)topic_box.getValue(),
		                    Integer.parseInt((String)num_q.getText()));
			}
		});

		vbox.getChildren().add(title);
		vbox.getChildren().add(topic_box);
		vbox.getChildren().add(num_q);
		vbox.getChildren().add(start_button);

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
