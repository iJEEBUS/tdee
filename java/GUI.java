/**************************************************************************************************
 * This is the GUI class to the BMR project, which will allow users to calculate their
 * own Basal Metabolic Rate by inputting the information that is requested.
 * 
 * @author Ronald Rounsifer
 * @version 0.0.1
 * @date 08-22-2017
 *************************************************************************************************/

package tdee;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class GUI extends JPanel {

	/** Gender of the user */
	String gender;

	/** Age of the user */
	int age;

	/** Height of the user */
	int height;

	/** Weight of the user */
	int weight;

	/** Creates the new type of layout to be used*/
	GridBagLayout g = new GridBagLayout();

	/** Creates the constraints for the GUI layout */
	GridBagConstraints c = new GridBagConstraints();


	/** Combo boxes to be used for the GUI*/
	JComboBox<String> gender_combo_box;
	JComboBox<Integer> age_combo_box;
	JComboBox<Integer> height_combo_box;
	JComboBox<Integer> weight_combo_box;

	/** Labels for the GUI */
	JLabel gender_label;
	JLabel age_label;
	JLabel height_label;
	JLabel weight_label;
	JLabel bmr_label;
	JLabel bmr_output_label;

	/** Buttons for the GUI */
	JButton calculate_btn;
	JButton exit_btn;

	/**************************************************************************************************
	 * Serial Version UID
	 *************************************************************************************************/
	private static final long serialVersionUID = 1L;


	/**************************************************************************************************
	 * Initializes the GUI for use.
	 *************************************************************************************************/
	public void init() {
		// Set the layout of the panel
		setLayout(g);

		// Make sure that all of the grids are fully used
		c.fill = GridBagConstraints.BOTH;

		// Add the labels to the GUI
		addLabels();

		// Add the cb to the GUI
		addComboBoxes();

		// Add the buttons to the GUI
		addButtons();
	}

	/**************************************************************************************************
	 * Used to create the labels for the GUI.
	 * 
	 * @param s - the string to put into the label
	 * @return label - the JLabel object
	 *************************************************************************************************/
	private JLabel makeLabel(String s) {
		JLabel label = new JLabel(s);
		return label;
	}

	/**************************************************************************************************
	 * Create a combo box that contains numbers for the GUI.
	 * 
	 * @param numbers - the array of numbers to be put into the combo box
	 * @return box - the combo box filled with numbers
	 *************************************************************************************************/
	private JComboBox<Integer> makeNumberComboBox(int[] numbers) {
		JComboBox<Integer> box = new JComboBox<Integer>();

		for(int i = 0; i < numbers.length; i++) {
			box.addItem(numbers[i]);
		}
		return box;
	}

	/**************************************************************************************************
	 * Create a number array where the length is specified by the user.
	 * 
	 * @param len - the length of the array to be made
	 * @return number_array - the array filled with numbers starting at 0
	 *************************************************************************************************/
	private int[] makeNumberArray(int len) {
		int[] number_array = new int[len];

		for(int i = 0; i < number_array.length; i++) {
			number_array[i] = i;
		}
		return number_array;
	}

	/**************************************************************************************************
	 * Creates a combo box when passed an array that consists of strings.
	 * 
	 * @param str - array of strings to have in the combo box
	 * @return box - the combo box of strings
	 *************************************************************************************************/
	private JComboBox<String> makeStringComboBox(String[] str) {
		JComboBox<String> box = new JComboBox<String>();

		for(int i = 0; i < str.length; i++) {
			box.addItem(str[i]);
		}
		return box;
	}

	/**************************************************************************************************
	 * Helper method that creates a button. Used to keep
	 * the code clean.
	 * @param str - what is to be written on the button
	 * @return b - the JButton that is created
	 *************************************************************************************************/
	private JButton makeButton(String str) {
		JButton b = new JButton(str);
		return b;
	}

	/**************************************************************************************************
	 * Add the labels to the JPanel so they can be added to the frame.
	 *************************************************************************************************/
	private void addLabels() {
		// LABELS
		// Gender Label
		gender_label = makeLabel("Gender: ");
		c.gridx = 0;
		c.gridy = 0;
		g.setConstraints(gender_label, c);
		add(gender_label);

		// Age Label
		age_label = makeLabel("Age: ");
		c.gridx = 0;
		c.gridy = 1;
		g.setConstraints(age_label, c);
		add(age_label);

		// Height Label
		height_label = makeLabel("Height: ");
		c.gridx = 0;
		c.gridy = 2;
		g.setConstraints(height_label, c);
		add(height_label);

		// Weight Label
		weight_label = makeLabel("Weight: ");
		c.gridx = 0;
		c.gridy = 3;
		g.setConstraints(weight_label, c);
		add(weight_label);

		// BMR Label
		bmr_label = makeLabel("BMR: ");
		c.gridx = 0;
		c.gridy = 5;
		g.setConstraints(bmr_label, c);
		add(bmr_label);

		// BMR Output Label
		bmr_output_label = makeLabel("");
		c.gridx = 1;
		c.gridy = 5;
		g.setConstraints(bmr_output_label, c);
		add(bmr_output_label);
	}

	/**************************************************************************************************
	 * Add the combo boxes to the JPanel so they can be added to the frame.
	 *************************************************************************************************/
	public void addComboBoxes() {
		// COMBO BOXES
		// Gender Combo Box
		String[] gender_array = {"-------", "Male", "Female", ""};
		this.gender_combo_box = makeStringComboBox(gender_array);
		c.gridx = 1;
		c.gridy = 0;
		g.setConstraints(gender_combo_box, c);
		add(gender_combo_box);

		// Age Combo Box
		int[] age_array = makeNumberArray(101);
		this.age_combo_box = makeNumberComboBox(age_array);
		c.gridx = 1;
		c.gridy = 1;
		g.setConstraints(age_combo_box, c);
		add(age_combo_box);

		// Height Combo Box
		int[] height_array = makeNumberArray(201);
		this.height_combo_box = makeNumberComboBox(height_array);
		c.gridx = 1;
		c.gridy = 2;
		g.setConstraints(height_combo_box, c);
		add(height_combo_box);

		// Weight Combo Box
		int[] weight_array = makeNumberArray(201);
		this.weight_combo_box = makeNumberComboBox(weight_array);
		c.gridx = 1;
		c.gridy = 3;
		g.setConstraints(weight_combo_box, c);
		add(weight_combo_box);
	}

	/**************************************************************************************************
	 * Add the buttons to the JPanel so they can be added to the frame.
	 *************************************************************************************************/
	private void addButtons() {
		// BUTTONS
		// Calculate Button
		calculate_btn = makeButton("Calculate");
		c.gridx = 0;
		c.gridy = 4;
		g.setConstraints(calculate_btn, c);
		add(calculate_btn);

		// Exit Button
		exit_btn = makeButton("Exit");
		c.gridx = 1;
		c.gridy = 4;
		g.setConstraints(exit_btn, c);
		add(exit_btn);


		// ACTION LISTENERS
		// Exit button action listener
		exit_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});

		// Calculate button action listener
		calculate_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BMR bmr = new BMR();
				String final_bmr = bmr.getBMR(getUserGender(), getUserAge(), 
						getUserHeight(), getUserWeight());
				bmr_output_label.setText(final_bmr);
				repaint();
			}
		});
	}

	/**************************************************************************************************
	 * Returns the gender of the user.
	 * @return gender - gender of the user
	 *************************************************************************************************/
	private String getUserGender() {
		return String.valueOf(gender_combo_box.getSelectedItem());
	}

	/**************************************************************************************************
	 * Returns the age of the user.
	 * @return age - age of the user
	 *************************************************************************************************/
	private int getUserAge() {
		return Integer.parseInt(String.valueOf(age_combo_box.getSelectedItem()));
	}

	/**************************************************************************************************
	 * Returns the height of the user.
	 * @return height - height of the user
	 *************************************************************************************************/
	private int getUserHeight() {
		return Integer.parseInt(String.valueOf(height_combo_box.getSelectedItem()));
	}

	/**************************************************************************************************
	 * Returns the weight of the user.
	 * @return weight - the weight of the user
	 *************************************************************************************************/
	private int getUserWeight() {
		return Integer.parseInt(String.valueOf(weight_combo_box.getSelectedItem()));
	}

	/**************************************************************************************************
	 * Executes the code to make the GUI visible to the user.
	 * 
	 * @param args
	 *************************************************************************************************/
	public static void main(String args[]) {
		JFrame f = new JFrame("BMR Calculator");
		GUI gui = new GUI();
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		gui.init();
		f.add(gui);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation(dim.width/2-f.getSize().width/2, dim.height/2-f.getSize().height/2);
		f.setSize(new Dimension(225, 200));
		f.setVisible(true);
	}
}	