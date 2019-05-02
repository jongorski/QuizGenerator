///////////////////////////////////////////////////////////////////////////////
//
// Title:           WriteFileJSON
// Course:          CS400 Spring 2019
// Project:         Team Project 
//
// Author:          Mikel Terracina
// Email:           mterracina@wisc.edu
//
// Lecturer's Name: Andrew Kuemmel
// Lecture Number:  004
//
// Due Date:        2019-05-02
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
// Online Sources: 
// https://javatutorial.net/java-iterate-hashmap-example
// https://crunchify.com/how-to-write-json-object-to-file-in-java/
// https://howtodoinjava.com/json/json-simple-read-write-json-examples/
// 
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
package application;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import application.QuizBank.topicBucket;

/**
 * This class writes to (or creates) a JSON file containing the JSON representation of the QuizBank
 * object passed as a parameter
 * 
 * @author mterracina
 */
public class WriteFileJSON {

  /**
   * Creates a JSON file based on QuizBank object passed
   * 
   * @param fileName - absolute file path of the JSON file to write to
   * @param qb - QuizBank object to gather all information to be formatted into a JSON file
   * @throws IllegalNullFileException if no fileName is passed
   * @throws IOException in the event an error occurs during write of the JSON file
   */
  public void writeFileFromQuizBank(String fileName, QuizBank qb)
      throws IllegalNullFileException, IOException {
    if (fileName == null) {
      throw new IllegalNullFileException();
    }

    JSONObject j_quiz = new JSONObject();
    JSONArray j_questionArray = new JSONArray();

    // get all questions from the QuizBank
    HashMap<String, topicBucket> qTable = qb.table;

    // traverse the QuizBank, adding each Question item to the JSON object
    for (Map.Entry<String, topicBucket> entry : qTable.entrySet()) {

      // get all Questions by topic and place in a List
      String topic = entry.getKey();
      topicBucket tB = qTable.get(topic);
      List<Question> qList = tB.bucket;

      // traverse each Question object, adding it to the JSON Question object
      for (Question q : qList) {
        JSONObject j_qObj = new JSONObject();
        j_qObj.put("meta-data", q.getMetadata());
        j_qObj.put("questionText", q.getQuestion());
        j_qObj.put("topic", q.getTopic());
        j_qObj.put("image", q.getImage());

        // traverse all Choice objects per question, adding them to a JSON Array
        JSONArray j_choiceArray = new JSONArray();

        for (Choice choice : q.getChoices()) {
          JSONObject j_chObj = new JSONObject();
          j_chObj.put("isCorrect", choice.getIsCorrect());
          j_chObj.put("choice", choice.getChoice());
          j_choiceArray.add(j_chObj);
        }
        // add the JSON Array of Choices to the JSON Question object
        j_qObj.put("choiceArray", j_choiceArray);

        // add the JSON Question object to the main questionArray
        j_questionArray.add(j_qObj);

      }
      // lastly, place all Questions in the JSON Quiz Object
      j_quiz.put("questionArray", j_questionArray);

    }
    writeFile(fileName, j_quiz.toJSONString());
  }

  /**
   * Helper method to write to the file
   * 
   * @param fileName - absolute file path of JSON file to be written to
   * @param fileContents - toString() representation of JSON Object to write
   * @throws IOException in the event an error occurs during write of the JSON file
   */
  private void writeFile(String fileName, String fileContents) throws IOException {
    FileWriter file = new FileWriter(fileName);
    file.write(fileContents);
    file.flush();
  }
}
