/**
 * 
 */
package application;

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
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
class FileParserTest {

  //fields that will be used by multiple tests
  protected QuizBank qb;
  protected FileParser fp;
  protected String qText1 = "If a good hash function is found and a reasonable table size is used for a hash table, then the operations of put, remove, and get should achieve an average time complexity of _____ where N is the number of items and TS is the size of the table.";
  protected String qText2 = "Which command will change the current working directory to a sub-directory named p3?";
 
  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    qb = new QuizBank();
    fp = new FileParser("C:\\Users\\mterr\\Documents\\CS400\\test_input.json");
//    fp = new FileParser("C:\\Users\\mterr\\Documents\\CS400\\test2.json");
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {
    qb = null;
    fp = null;
  }

  /**
   * Test method for {@link application.FileParser#Parse()}.
   */
  @Test
  void test000_Parse() {
    try {
      List<Question> listQuestions = fp.Parse();
      if (listQuestions.size() != 2) {
        fail("Expected list size not returned, instead returned: " + listQuestions.size());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Test method for {@link application.FileParser#addQuestionsToQuizBank(java.util.List)}.
   */
  @Test
  void test001_AddQuestionsToQuizBank_1() {
    try {
      List<Question> listQuestions = fp.Parse();
      if (listQuestions.size() != 2) {
        fail("Expected list size not returned, instead returned: " + listQuestions.size());
      }
      
      // add the Questions to the QuizBank
      for (Question q : listQuestions) {
        qb.addQuestionToQuiz(q);
      }
      
      // get all questions from the QuizBank 
      HashMap<String, topicBucket> qTable;
      qTable = qb.table;
      
      // get List of Questions for Topic 1 - hash table //
      topicBucket tB = qTable.get("hash table");     
      List<Question> qList = tB.bucket;
      
      for (Question q : qList) {
        if (q.getQuestion().equals(qText1)) {
          if (!q.getAnswer().equals("O(1)")) {
            fail("Expected answer to question 1 not returned, instead answer was: " + q.getAnswer());
          }
          if (!q.getMetadata().equals("unused")) {
            fail("Expected metadata for question 1 not returned, instead metadata = " + q.getMetadata());
          }
          if (!q.getTopic().equals("hash table")) {
            fail("Expected topic for question 1 not returned, instead topic = " + q.getTopic());
          }
          if (!q.getImage().equals("goodhash2_AK.jpg")) {
            fail("Expected image for question 1 not returned, instead image = " + q.getTopic());
          }
        }
      }
      
      // get List of Questions for Topic 2 - linux //
      tB = null;
      tB = qTable.get("linux");    
      qList = null;
      qList = tB.bucket;
      
      for (Question q : qList) {
        if (q.getQuestion().equals(qText2)) {
          if (!q.getAnswer().equals("cd")) {
            fail("Expected answer to question 2 not returned, instead answer was: " + q.getAnswer());
          }
          if (!q.getMetadata().equals("unused")) {
            fail("Expected metadata for question 2 not returned, instead metadata = " + q.getMetadata());
          }
          if (!q.getTopic().equals("linux")) {
            fail("Expected topic for question 2 not returned, instead topic = " + q.getTopic());
          }
          if (!q.getImage().equals("none")) {
            fail("Expected image for question 2 not returned, instead image = " + q.getTopic());
          }
        }
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  @Test
  void test002_Parse_Throws_FileNotFoundException() {
    FileParser fp = new FileParser("C:\\Users\\mterr\\Documents\\CS400\\test_input.txt");
    
    try {
      fp.Parse();
      fail("Parse() method did not throw FileNotFoundException when an invalid file was passed");
      
    } catch (FileNotFoundException e) {
      // test passed
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }

}
