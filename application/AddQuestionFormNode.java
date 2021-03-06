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

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Class for allowing user to enter new question(s)
 * into the quizbank, and proceed to quiz
 * 
 * @author jwolf22
 *
 */
public class AddQuestionFormNode {

	public QuizBank qb;
	public Stage stage;

	/*
	 * constructor method for AddQuestionFormNode
	 */
	public AddQuestionFormNode(QuizBank quizbank, Stage curr_stage) {
		this.qb = quizbank;
		this.stage = curr_stage;
		this.stage.setTitle("Add questions");

	}

	/*
	 * Helper function to submit form
	 * 
	 * topic: javafx TextField where user enters question topic text: javafx
	 * TextArea where user enters question text ans: array of javafx TextField where
	 * user enters question answer options choice: javafx TextArea where user enters
	 * correct answer choice img_path: javafx TextArea where user enters path to
	 * assoc image
	 */
	public void getSubmittedQuestion(TextField[] choices, TextField answer, TextField meta, TextField topic,
			TextArea text, TextField img_path) {
		String q_meta = meta.getText(); // string metadata
		String q_topic = topic.getText(); // string topic
		String q_text = text.getText(); // string text
		String q_img = img_path.getText(); // string image path
		String q_answer = answer.getText(); // string answer idx
		// Take only non-null answer choices
		List<Choice> q_choices = new ArrayList<Choice>();
		int i = 0;
		for (TextField c : choices) {
			if (!c.getText().contentEquals("")) {
				if (i+1 == Integer.parseInt(q_answer)) {
					q_choices.add(new Choice("T", choices[i].getText())); // mark correct choice as T
				} else {
					q_choices.add(new Choice("F", choices[i].getText()));
				}
			}
			i = i + 1;
		}
		// create new question to add to quizbank
		Question question = new Question(q_topic, q_text, q_choices, q_meta, q_img);
		this.qb.addQuestionToQuiz(question);
	}

	/**
	 * handler method for start button
	 * 
	 * passes control of stage and users quizbank to AskQuestion
	 */
	private void startHandler() {
		AskQuestion asker = new AskQuestion(this.qb, this.stage);
		this.stage.setScene(asker.ChooseTopicStart());
	}
	
	/*
	 * Set up main stage for add question page return stage to be displayed when
	 * adding question
	 */
	public Scene AddQuestion() {
		HBox hbox = new HBox();
		Scene add_q_scene = new Scene(hbox, 800, 600);

		VBox leftPane = new VBox();
		leftPane.setAlignment(Pos.CENTER);

		//Required Label
		Label req = new Label("[Fields marked with a (*) are required.]");
		req.setFont(Font.font("Verdana", 15));
		VBox.setMargin(req, new Insets(15, 0, 15, 0));
		
		//Topic label
		Label topicLabel = new Label("*Enter topic:");
		topicLabel.setFont(Font.font("Verdana", 14));
		VBox.setMargin(topicLabel, new Insets(5,0,5,0));
		
		// Enter topic of new question
		TextField topic = new TextField();
		topic.setPromptText("Topic of new question");
		topic.setStyle("-fx-font-size:15");
		topic.setMinSize(200, 100);
		topic.setMaxSize(200, 100);
		VBox.setMargin(topic, new Insets(0, 50, 40, 50));
		
		//Enter question text label
		Label questionLabel = new Label("*Enter the question:");
		questionLabel.setFont(Font.font("Verdana", 14));
		VBox.setMargin(questionLabel, new Insets(5,0,5,0));

		// Enter text of new question
		TextArea text = new TextArea();
		text.setPromptText("Text of new question");
		text.setStyle("-fx-font-size:15");
		text.setMinSize(250, 200);
		text.setMaxSize(250, 200);
		VBox.setMargin(text, new Insets(0, 50, 40, 50));

		leftPane.getChildren().add(req);
		leftPane.getChildren().add(topicLabel);
		leftPane.getChildren().add(topic);
		leftPane.getChildren().add(questionLabel);
		leftPane.getChildren().add(text);

		VBox midPane = new VBox();
		midPane.setAlignment(Pos.CENTER);
		
		//Choices Label
		Label choicesLabel = new Label("*Enter answer options (up to 5):");
		choicesLabel.setFont(Font.font("Verdana",14));
		VBox.setMargin(choicesLabel, new Insets(5,0,5,0));

		// Answer choice 1
		TextField ans1 = new TextField();
		ans1.setPromptText("Answer choice #1:");
		ans1.setStyle("-fx-font-size:15");
		ans1.setMinSize(200, 50);
		ans1.setMaxSize(200, 50);
		VBox.setMargin(ans1, new Insets(0, 0, 30, 0));
		// Answer choice 2
		TextField ans2 = new TextField();
		ans2.setPromptText("Answer choice #2:");
		ans2.setStyle("-fx-font-size:15");
		ans2.setMinSize(200, 50);
		ans2.setMaxSize(200, 50);
		VBox.setMargin(ans2, new Insets(0, 0, 30, 0));
		// Answer choice 3
		TextField ans3 = new TextField();
		ans3.setPromptText("Answer choice #3:");
		ans3.setStyle("-fx-font-size:15");
		ans3.setMinSize(200, 50);
		ans3.setMaxSize(200, 50);
		VBox.setMargin(ans3, new Insets(0, 0, 30, 0));
		// Answer choice 4
		TextField ans4 = new TextField();
		ans4.setPromptText("Answer choice #4:");
		ans4.setStyle("-fx-font-size:15");
		ans4.setMinSize(200, 50);
		ans4.setMaxSize(200, 50);
		VBox.setMargin(ans4, new Insets(0, 0, 30, 0));
		// Answer choice 5
		TextField ans5 = new TextField();
		ans5.setPromptText("Answer choice #5:");
		ans5.setStyle("-fx-font-size:15");
		ans5.setMinSize(200, 50);
		ans5.setMaxSize(200, 50);
		VBox.setMargin(ans5, new Insets(0, 0, 30, 0));

		TextField[] choices = new TextField[] { ans1, ans2, ans3, ans4, ans5 };

		midPane.getChildren().add(choicesLabel);
		midPane.getChildren().add(ans1);
		midPane.getChildren().add(ans2);
		midPane.getChildren().add(ans3);
		midPane.getChildren().add(ans4);
		midPane.getChildren().add(ans5);

		VBox rightPane = new VBox();
		rightPane.setAlignment(Pos.CENTER);
		
		//Correct answer label
		Label corrAnsLabel = new Label("*Enter correct choice #:");
		corrAnsLabel.setFont(Font.font("Verdana", 14));
		VBox.setMargin(corrAnsLabel, new Insets(20, 0, 5, 40));

		// Enter answer choice of new question
		TextField ans_choice = new TextField();
		ans_choice.setPromptText("Correct answer (1-5)");
		ans_choice.setStyle("-fx-font-size:15");
		ans_choice.setMinSize(130, 80);
		ans_choice.setMaxSize(130, 80);
		VBox.setMargin(ans_choice, new Insets(10, 40, 10, 40));
		
		//Image file label
		Label imageLabel = new Label("Enter Image File:");
		imageLabel.setFont(Font.font("Verdana", 14));
		VBox.setMargin(imageLabel, new Insets(5, 40, 10, 40));

		// Enter image file of new question
		TextField img_file = new TextField();
		img_file.setPromptText("Image file");
		img_file.setStyle("-fx-font-size:15");
		img_file.setMinSize(130, 80);
		img_file.setMaxSize(130, 80);
		VBox.setMargin(img_file, new Insets(10, 40, 10, 40));
		
		//Metadata label
		Label metaLabel = new Label("Enter Metadata:");
		metaLabel.setFont(Font.font("Verdana", 14));
		VBox.setMargin(metaLabel, new Insets(5, 15, 5, 40));

		// Enter metadata of new question
		TextField meta = new TextField();
		meta.setPromptText("Metadata");
		meta.setStyle("-fx-font-size:15");
		meta.setMinSize(130, 80);
		meta.setMaxSize(130, 80);
		VBox.setMargin(meta, new Insets(10, 40, 10, 40));

		/*
		 * //Add another question button Button another = new
		 * Button("Submit & Add another"); another.setStyle("-fx-font-size:15");
		 * another.setMinSize(150, 60); another.setMaxSize(150, 60);
		 * another.setOnAction(new EventHandler<ActionEvent>() {
		 * 
		 * @Override public void handle(ActionEvent e) { getSubmittedQuestion(choices,
		 * ans_choice, meta, topic, text, img_file); topic.clear(); text.clear(); for
		 * (int i=0; i<choices.length; i++) { choices[i].clear(); } ans_choice.clear();
		 * img_file.clear(); meta.clear(); currentStage.close(); currentStage.show(); }
		 * }); VBox.setMargin(another, new Insets(30, 25, 10, 25));
		 * another.setStyle("-fx-font-size:15"); another.setMinSize(150, 60);
		 * another.setMaxSize(150, 60);
		 */

		Button submit = new Button("Submtit");
		submit.setStyle("-fx-font-size:15");
		submit.setMinSize(150, 60);
		submit.setMaxSize(150, 60);
		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				getSubmittedQuestion(choices, ans_choice, meta, topic, text, img_file);
				topic.clear();
				text.clear();
				for (int i = 0; i < choices.length; i++) {
					choices[i].clear();
				}
				ans_choice.clear();
				img_file.clear();
				meta.clear();
			}
		});
		VBox.setMargin(submit, new Insets(5, 25, 10, 25));
	
		Button start = new Button("Start Quiz");
		start.setStyle("-fx-font-size:15");
		start.setMinSize(150, 60);
		start.setMaxSize(150, 60);
		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				//getSubmittedQuestion(choices, ans_choice, meta, topic, text, img_file);
				startHandler();
			}
		});
		VBox.setMargin(start, new Insets(10, 25, 90, 25));
		
		rightPane.getChildren().add(corrAnsLabel);
		rightPane.getChildren().add(ans_choice);
		rightPane.getChildren().add(imageLabel);
		rightPane.getChildren().add(img_file);
		rightPane.getChildren().add(metaLabel);
		rightPane.getChildren().add(meta);
		// rightPane.getChildren().add(another);
		rightPane.getChildren().add(submit);
		rightPane.getChildren().add(start);
		hbox.getChildren().add(leftPane);
		hbox.getChildren().add(midPane);
		hbox.getChildren().add(rightPane);

		return add_q_scene;
	}

	/**
	 * main method of this class, called from QuizStart when add question
	 * button is clicked, passed control of qb and stage
	 * 
	 * @param quizbank: users quizbank to manipulate
	 * @param curr_stage: current stage, to change scene
	 */
	public static void start(QuizBank quizbank, Stage curr_stage) {
		AddQuestionFormNode addQ = new AddQuestionFormNode(quizbank, curr_stage);
		Scene addQScene = addQ.AddQuestion();
		addQ.stage.setScene(addQScene);
	}
}