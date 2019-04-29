package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Main extends Application {
	
	/*
	 * Create main GUI page
	 */
	public Scene MainGUI(Stage stage) {
		
		VBox vbox = new VBox();
	    vbox.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(vbox, 900, 700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		//Title of main page
		Label title = new Label("QuizGenerator");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		VBox.setMargin(title, new Insets(20, 0, 70, 0));
		
		
		//Start button
		Button start_button = new Button("Start quiz!");
		start_button.setStyle("-fx-font-size:30");
		start_button.setMaxSize(200, 75);
		start_button.setMinSize(200, 75);
		start_button.setPrefSize(200, 75);
		VBox.setMargin(start_button, new Insets(70, 0, 40, 0));

		
		//Add question button
		Button add_q = new Button("Add question");
		add_q.setStyle("-fx-font-size:15");
		add_q.setMinSize(150, 60);
		add_q.setPrefSize(150, 60);
		Stage add_popup_stage = AddQuestionFormNode.AddQuestion();
	    add_q.setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	            	add_popup_stage.show();
	            }
	    	});
		VBox.setMargin(add_q, new Insets(0, 0, 30, 0));

		
		//Topic list
        String topics[] = {"Math", "Comp Sci", "English", 
        		"History", "PE", "Photo" };
        ComboBox<String> topic_box = new ComboBox<String>(FXCollections
        		                    .observableArrayList(topics));
        topic_box.setPromptText("Topics");
		topic_box.setStyle("-fx-font-size:15");
        topic_box.setPrefWidth(220);
		topic_box.setMinSize(150, 60);
		topic_box.setPrefSize(150, 60);
		VBox.setMargin(topic_box, new Insets(20, 0, 20, 0));

		
		//Choose number of questions
		TextField num_q = new TextField();
		num_q.setPromptText("Enter # questions");
		num_q.setStyle("-fx-font-size:15");
        num_q.setMaxWidth(150);
		num_q.setMinSize(150, 60);
		num_q.setPrefSize(150, 60);
		VBox.setMargin(num_q, new Insets(20, 0, 20, 0));

		vbox.getChildren().add(title);
		vbox.getChildren().add(add_q);
		vbox.getChildren().add(num_q);
		vbox.getChildren().add(topic_box);
		vbox.getChildren().add(start_button);
        
		return scene;
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			QuizBank qb = new QuizBank();
			
			Scene scene = MainGUI(primaryStage);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
