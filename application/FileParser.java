///////////////////////////////////////////////////////////////////////////////
//
// Title:           FileParser
// Course:          CS400 Spring 2019
// Project:         Team Project 
//
// Authors:         Jack Wolf
//                  Mikel Terracina
//
// Email:           jwolf22@wisc.edu           
//                  mterracina@wisc.edu
//
// Lecturer's Name: Andrew Kuemmel
// Lecture Number:  004
//
// Due Date:        2019-05-02
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
// Online Sources: 
// https://www.javatpoint.com/java-string-replaceall
// https://stackoverflow.com/questions/34930249/replaceall-by-in-java
// 
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

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
  
  /**
   * Adds Question objects to the QuizBank
   * 
   * @param listQuestions - list of Question objects to add to the QuizBank
   * @return QuizBank with newly added Questions
   */
  public QuizBank addQuestionsToQuizBank(List<Question> listQuestions) {
    QuizBank qb = new QuizBank();
    for (Question question : listQuestions) {
      qb.addQuestionToQuiz(question);
    }
    return qb;
  }
}
