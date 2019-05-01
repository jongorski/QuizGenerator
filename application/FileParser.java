// https://www.javatpoint.com/java-string-replaceall
// https://stackoverflow.com/questions/34930249/replaceall-by-in-java
package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
//import java.io.IOException;
//import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import application.QuizBank.topicBucket;

/*
 * Class to parse JSON and return objects of type Question
 */
public class FileParser {

  private String path; // path to JSON file

  /*
   * Constructor for FileParser class
   * 
   * path: String, path to parse
   */
  public FileParser(String path) {
    this.path = path;
  }

  /*
   * main method of this class parse this.path and set up Question objects
   */
  public List<Question> Parse() throws FileNotFoundException {
    File f = new File(this.path);
    if (f.isFile()) {
      // okay, continue
    } else {
      throw new FileNotFoundException();
    }

    List<Question> listQuestions = new ArrayList<Question>();
    
    try {
      // create a new JSON object and parse the JSON file
      JSONParser parser = new JSONParser();
      JSONObject json = (JSONObject) parser.parse(new FileReader(this.path));

      // get "questionArray" from json file
      JSONArray question_arr = (JSONArray) json.get("questionArray");    

      // iterate over the questionArray, getting the meta-data, questionText, topic, and choiceArray
      Iterator q_itr = question_arr.iterator();
      while (q_itr.hasNext()) { // iterate through each question
        Map next = (Map) q_itr.next();
        String meta = (String) next.get("meta-data"); // pull out metadata
        String qText = (String) next.get("questionText"); // pull out question text
        qText = qText.replaceAll("\\$", ""); // remove all $ chars
        String topic = (String) next.get("topic"); // pull out question topic
        String image = (String) next.get("image"); // pull out question image path
//        String correct_choice = "";
//        System.out.println("meta: " + meta);
//        System.out.println("text: " + qText);
//        System.out.println("topic: " + topic);
//        System.out.println("img: " + image);
        
        List<Choice> listChoices = new ArrayList<Choice>();
        
        // iterate over the choiceArray, getting choice and isCorrect fields
        Iterator choice_itr = ((JSONArray) next.get("choiceArray")).iterator();
        int count = 0;
        while (choice_itr.hasNext()) { // iterate through each choice
          Map next_choice = (Map) choice_itr.next();
          String chc = (String) next_choice.get("choice");
          String isCorrect = (String) next_choice.get("isCorrect");
          chc = chc.replaceAll("\\$", ""); // remove all $ chars
          Choice choice = new Choice(isCorrect, chc);
          listChoices.add(choice);
//          System.out.println(isCorrect + " -- " + chc);
//          if (isCorrect.toUpperCase().contentEquals("T")) {
//            correct_choice = Integer.toString(count); // mark correct answer
//          }
          count++;
        }
        Question q = new Question(topic, qText, listChoices, meta, image);
        listQuestions.add(q);
        
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return listQuestions;
  }
  
  public QuizBank addQuestionsToQuizBank(List<Question> listQuestions) {
    QuizBank qb = new QuizBank();
    for (Question question : listQuestions) {
      qb.addQuestionToQuiz(question);
    }
    return qb;
  }

  // for testing only
  public static void main(String[] args) {
    FileParser fp = new FileParser("C:\\Users\\mterr\\Documents\\CS400\\test_input.json");
    
    try {
      List<Question> listQuestions = fp.Parse();
      QuizBank qb = fp.addQuestionsToQuizBank(listQuestions);
      
//      // get quizBank
//      // get all questions from the QuizBank 
//      HashMap<String, topicBucket> qTable;
//      qTable = qb.getQuestions();
//
//      // get List of Questions for Topic 1 //
//      topicBucket tB = qTable.get("hash table");
//      List<Question> qList = tB.bucket;
//      
//      for (Question question : qList ) {
//        System.out.println("Question = " + question.getQuestion());
//        List<Choice> choices = question.getChoices();
//        for (Choice choice : choices) {
//          System.out.println("choice = " + choice.getChoice() + " -- " + choice.isCorrect);
//        }
//      }
//      
//      // get List of Questions for Topic 1 //
//      tB = qTable.get("linux");
//      qList = tB.bucket;
//      
//      for (Question question : qList ) {
//        System.out.println("Question = " + question.getQuestion());
//        List<Choice> choices = question.getChoices();
//        for (Choice choice : choices) {
//          System.out.println("choice = " + choice.getChoice() + " -- " + choice.isCorrect);
//        }
//      }
      
    } catch (Exception e) {
      e.getMessage();
    }
  }
}
