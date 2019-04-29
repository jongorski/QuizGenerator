package application;

/**
 * Defines the operations required of the Choice class.
 * @author Jon Gorski (jgorski2@wisc.edu)
 */
public interface ChoiceADT {

	/**
	 * This method takes no parameters, and returns the String 
	 * associated with the text of the Choice. Choice will be
	 * a field in the Choice class.
	 * @return String of the choice
	 */
	String getChoice();
	
	/**
	 * This method takes no parameters, and returns the text 
	 * "Correct" if the boolean value isCorrect is true. This
	 * method returns "Incorrect" if isCorrect is false.
	 * @return String "Correct" or "Incorrect" based on the 
	 * isCorrect boolean field.
	 */
	String getIsCorrect();
	
	
}