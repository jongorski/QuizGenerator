/**
 * 
 */
package application;

import java.util.List;

/**
 * @author mterracina
 *
 */
public class Question implements QuestionADT {

  // ************************** FIELDS ******************************************//
  private String metadata; // miscellaneous information about the question
  private String question;
  private String topic;
  private String image;
  private List<Choice> choices;
//  private String answer;
  private String answer;
  public boolean asked;


  // ************************** CONSTRUCTORS **************************************//
//  Question(String topic, String question, String answer, List<Choice> choices)  {
  Question(String topic, String question, List<Choice> choices)  {
    this.question = question;
    this.topic = topic;
//    this.answer = answer;
    this.choices = choices; // max number of choices = 5
    this.asked = false;
  }
  
  Question(String topic, String question, List<Choice> choices, String metadata)  {
    this.metadata = metadata;
    this.question = question;
    this.topic = topic;
    this.choices = choices; // max number of choices = 5
    this.asked = false;

  }
 
  Question(String topic, String question, List<Choice> choices, String metadata, String image) {
    this.metadata = metadata;
    this.question = question;
    this.topic = topic;
    this.image = image;
    this.choices = choices; // max number of choices = 5
    this.asked = false;
  }

  // ************************** METHODS ********************************************//
  public String getQuestion() {
    return question;
  }

  public List<Choice> getChoices() {
    return choices;
  }

  public String getAnswer() {
    for (Choice choice : choices) {
      System.out.println("choices " + choice.getChoice() + "--" + choice.getIsCorrect());
      if (choice.getIsCorrect().equals("T")) {
        return choice.getChoice();
      }
    }
    return null;
  }

  public String getMetadata() {
    return metadata;
  }

  public String getTopic() {
    return topic;
  }

  public String getImage() {
    return image;
  }
} // END CLASS
