package application;
	
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
			QuizStart qs = new QuizStart();
			
			Scene add_or_start = qs.AddQuestionOrStart();
			Scene welcome_scene = qs.WelcomeScene(qb, primaryStage, add_or_start);
			
			welcome_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(welcome_scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
