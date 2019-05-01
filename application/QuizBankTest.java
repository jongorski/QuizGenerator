/**
 * 
 */
package application;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import application.QuizBank.topicBucket;

/**
 * @author mterracina
 *
 */
class QuizBankTest {

//fields that will be used by multiple tests
 protected QuizBank qb;
 
  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    qb = new QuizBank();
    
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {
    qb = null;
  }
  
  /***
   * Creates a series of question and answers
   * @return List of Question objects with newly created question/answers
   */
  List<Question> createQuestions() {
    List<Question> questionList = new ArrayList<Question>();
    List<Choice> choices = new ArrayList<Choice>();
    Choice[] ch = new Choice[5];
    
    // create topic 1, question 1 //
    String topic = "Topic 1";
    String question = "Question 1";
    ch[0] = new Choice("F", "choice 1");
    ch[1] = new Choice("F", "choice 2");
    ch[2] = new Choice("T", "choice 3");
    ch[3] = new Choice("F", "choice 4");
    ch[4] = new Choice("F", "choice 5");  
    
    // add each Choice to the choices List
    for (int i=0; i < ch.length; i++) {
      if (ch[i] != null) {
        choices.add(ch[i]);
      }
    }

    // create the Question object
    Question q1 = new Question(topic, question, choices);

    // create topic 1, question 2 //
    question = "Question 2";
    choices = null;
    choices = new ArrayList<Choice>();
    ch[0] = new Choice("F", "choice 1");
    ch[1] = new Choice("F", "choice 2");
    ch[2] = new Choice("F", "choice 3");
    ch[3] = new Choice("T", "choice 4");
    ch[4] = null;
    
    // add each Choice to the choices List
    for (int i=0; i < ch.length; i++) {
      if (ch[i] != null) {
        choices.add(ch[i]);
      }
    }
    
    // create the Question object
    Question q2 = new Question(topic, question, choices);

    // create topic 1, question 3 //
    question = "Question 3";
    choices = null;
    choices = new ArrayList<Choice>();  
    ch[0] = new Choice("T", "choice 1");
    ch[1] = new Choice("F", "choice 2");
    ch[2] = new Choice("F", "choice 3");
    ch[3] = new Choice("F", "choice 4");
    ch[4] = null;
    
    // add each Choice to the choices List
    for (int i=0; i < ch.length; i++) {
      if (ch[i] != null) {
        choices.add(ch[i]);
      }
    }
    
    // create the Question object
    Question q3 = new Question(topic, question, choices);

    // create topic 2, question 1 //
    topic = "Topic 2";
    question = "Question 1";
    choices = null;
    choices = new ArrayList<Choice>();
    ch[0] = new Choice("F", "choice 1");
    ch[1] = new Choice("T", "choice 2");
    ch[2] = new Choice("F", "choice 3");
    ch[3] = new Choice("F", "choice 4");
    ch[4] = null;

    // add each Choice to the choices List
    for (int i = 0; i < ch.length; i++) {
      if (ch[i] != null) {
        choices.add(ch[i]);
      }
    }

    // create the Question object
    Question q4 = new Question(topic, question, choices);
    
    // create topic 2, question 3 //
    topic = "Topic 2";
    question = "Question 2";
    choices = null;
    choices = new ArrayList<Choice>();
    ch[0] = new Choice("F", "choice 1");
    ch[1] = new Choice("F", "choice 2");
    ch[2] = new Choice("F", "choice 3");
    ch[3] = new Choice("F", "choice 4");
    ch[4] = new Choice("T", "choice 5");

    // add each Choice to the choices List
    for (int i=0; i < ch.length; i++) {
      if (ch[i] != null) {
        choices.add(ch[i]);
      }
    }

    // create the Question object
    Question q5 = new Question(topic, question, choices);

    // create topic 2, question 3 //
    topic = "Topic 3";
    question = "Question 1";
    choices = null;
    choices = new ArrayList<Choice>();
    ch[0] = new Choice("F", "choice 1");
    ch[1] = new Choice("F", "choice 2");
    ch[2] = new Choice("T", "choice 3");
    ch[3] = new Choice("F", "choice 4");
    ch[4] = new Choice("F", "choice 5");

    // add each Choice to the choices List
    for (int i=0; i < ch.length; i++) {
      if (ch[i] != null) {
        choices.add(ch[i]);
      }
    }

    // create the Question object
    Question q6 = new Question(topic, question, choices);
    
    questionList.add(q1);
    questionList.add(q2);
    questionList.add(q3);
    questionList.add(q4);
    questionList.add(q5);
    questionList.add(q6);

    return questionList;
  }

  /**
   * Creates a new QuizBank object and adds several topics and questions. Verifies that expected answers are returned
   */
  @Test
  void test000_AddQuestionToQuiz() {

    boolean q_a_found = false;
    String topic;
    String question;
    String answer;
    
    List<Question> questions = createQuestions();
    
    // add the Questions to the QuizBank
    for (Question q : questions) {
      qb.addQuestionToQuiz(q);
    }

    // get all questions from the QuizBank 
    HashMap<String, topicBucket> qTable;
    qTable = qb.table;

    // get List of Questions for Topic 1 //
    topicBucket tB = qTable.get("Topic 1");
    List<Question> qList = tB.bucket;

    // test - topic 1, question 1 //
    topic = "Topic 1";
    question = "Question 1";
    answer = "choice 3";
    q_a_found = test000_AddQuestionToQuizHelper("Question 1", "choice 3", qList);
    if (q_a_found == false) {
      fail(topic + ", " + question + ", " + answer + " not found");
    }

    // test - topic 1, question 2 //
    question = "Question 2";
    answer = "choice 4";
    q_a_found = test000_AddQuestionToQuizHelper("Question 2", answer, qList);
    if (q_a_found == false) {
      fail(topic + ", " + question + ", " + answer + " not found");
    }

    // test - topic 1, question 3 //
    question = "Question 3";
    answer = "choice 1";
    q_a_found = test000_AddQuestionToQuizHelper("Question 3", "choice 1", qList);
    if (q_a_found == false) {
      fail(topic + ", " + question + ", " + answer + " not found");
    }

    // get List of Questions for Topic 2 //
    topic = "Topic 2";
    tB = qTable.get(topic);
    qList = tB.bucket;
    
    // test - topic 2, question 1 //
    question = "Question 1";
    answer = "choice 2";
    q_a_found = test000_AddQuestionToQuizHelper("Question 1", "choice 2", qList);
    if (q_a_found == false) {
      fail(topic + ", " + question + ", " + answer + " not found");
    }
    
    // test - topic 2, question 2 //
    question = "Question 2";
    answer = "choice 5";
    q_a_found = test000_AddQuestionToQuizHelper("Question 2", "choice 5", qList);
    if (q_a_found == false) {
      fail(topic + ", " + question + ", " + answer + " not found");
    }
    
    // get List of Questions for Topic 2 //
    topic = "Topic 3";
    tB = qTable.get(topic);
    qList = tB.bucket;
    
    // test - topic 3, question 1 //
    question = "Question 1";
    answer = "choice 3";
    q_a_found = test000_AddQuestionToQuizHelper("Question 1", "choice 3", qList);
    if (q_a_found == false) {
      fail(topic + ", " + question + ", " + answer + " not found");
    }             
  }

  /**
   * Helper method that returns true/false if expected question/answer combination is found in the passed List of Question objects
   * @param question - question to search for within given List
   * @param answer - answer expected for the given question
   * @param qList - List of Question objects to search for the question/answer combination
   * @return "T" if the question/answer combo is found, otherwise false
   */
  boolean test000_AddQuestionToQuizHelper(String question, String answer, List<Question> qList) {
    for (Question q : qList) {
      if (q.getQuestion().equals(question)) {
        if (q.getAnswer().equals(answer)) {
          return true;
        }
      }
    }
    return false;
  }
  
  /**
   * Creates a new QuizBank object and adds several topics and questions. Verifies that expected number of Questions per topic is returned
   */
  @Test
  void test001_GetNumberQuestions() {
    
    String topic;
    
    List<Question> questions = createQuestions();
    
    // add the Questions to the QuizBank
    for (Question q : questions) {
      qb.addQuestionToQuiz(q);
    }

    // get all questions from the QuizBank 
    HashMap<String, topicBucket> qTable;
    qTable = qb.table;

    // get the number of Questions for Topic 1 //
    topic = "Topic 1";
    topicBucket tB = qTable.get(topic);
    if (tB.n != 3) {
      fail("length of topicBucket " + topic + " did not return the expected value: " + tB.n);
    }
    
    // get the number of Questions for Topic 2 //
    topic = "Topic 2";
    tB = qTable.get(topic);
    if (tB.n != 2) {
      fail("length of topicBucket " + topic + " did not return the expected value: " + tB.n);
    }
    
    // get the number of Questions for Topic 2 //
    topic = "Topic 3";
    tB = qTable.get(topic);
    if (tB.n != 1) {
      fail("length of topicBucket " + topic + " did not return the expected value: " + tB.n);
    }
  }

}
