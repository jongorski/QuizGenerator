package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 

/*
 * Class to parse JSON and return objects 
 * of type Question
 */
public class FileParser {

	private String path;  //path to JSON file
	
	/*
	 * Constructor for FileParser class
	 * 
	 * path: String, path to parse
	 */
	public FileParser(String path) {
		this.path = path;
	}
	
	/*
	 * main method of this class
	 * parse this.path and set up Question objects
	 */
	public void Parse() {
		try {
	    	JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(new FileReader(this.path));;
			JSONArray question_arr = (JSONArray) json.get("questionArray");
			Iterator q_itr = question_arr.iterator();
			while (q_itr.hasNext()) {  //iterate through each question
    			Map next = (Map) q_itr.next();
    			String meta = (String) next.get("meta-data");       //pull out metadata
    			String text = (String) next.get("questionText");    //pull out question text
    			String topic = (String) next.get("topic");          //pull out question topic
    			String img = (String) next.get("image");            //pull out question image path
    			String correct_choice = "";                         //String repr index of correct answer
      			Iterator choice_itr = ((JSONArray) next.get("choiceArray")).iterator();
      			int count = 0;
    			while (choice_itr.hasNext()) {  //iterate through each choice
    				Map next_choice = (Map) choice_itr.next();
    				String chc = (String) next_choice.get("choice");
    				String isCorrect = (String) next_choice.get("isCorrect");
    				if (isCorrect.toUpperCase().contentEquals("T")) {
    					correct_choice = Integer.toString(count);  //mark correct answer
    				}
    				count++;
    			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
