/**************************************************************************************************
 * This is the BMR class to the BMR project. This will perform the calculations in order to 
 * actually show the user their Basal Metabolic Rate.
 * 
 * @author Ronald Rounsifer
 * @version 0.0.1
 * @date 08-22-2017
 *************************************************************************************************/
package tdee;

import java.text.DecimalFormat;

public class BMR {
	
	/** BMR that will be calculated in this class */
	double bmr;
	
	/** Gender of the user */
	String gender;
	
	/** Age of the user */
	int age;
	
	/** Height of the user */
	int height;
	
	/** Weight of the user */
	int weight;

	/**************************************************************************************************
	 * Constructor that sets all of the variables to empty/null.
	 *************************************************************************************************/
	public BMR() {
		this.bmr = 0;
		this.gender = "";
		this.age = 0;
		this.height = 0;
		this.weight = 0;
	}

	/**************************************************************************************************
	 * Performs the calculation in order to return the BMR of the user.
	 * 
	 * @param gender - users gender
	 * @param age - users age
	 * @param height - users height
	 * @param weight - users weight
	 * @return
	 *************************************************************************************************/
	public String getBMR(String gender, int age, int height, int weight) {
		if(gender == "Male") {
			bmr = 66.5 + (13.75 * weight) + (5.003 * height) - (6.755 * age);
			return String.format("%.0f", bmr);
		}
		else if(gender == "Female") {
			bmr = 655.1 + (4.35 * weight) + (4.7 * height) - (4.7 * age);
			return String.format("%.0f", bmr);
		}
		else {
			throw new IllegalArgumentException("Did not pick a gender.");
		}
	}
}
