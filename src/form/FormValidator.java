package form;

import javax.swing.JTextField;
/**
 * this class contains static methods that can be used to validate form inputs
 * for BibItems 
 * @author Bronagh
 *
 */
public class FormValidator {
	/**
	 * validates the JTextField passed parametrs to ensure they are not empty
	 * if empty, adds error details to string
	 * @param institution JTextField representing a tech reports institution
	 * @return a string that is empty or holds an error message
	 */
	public static String validateTechReport(JTextField institution) {
		int institutionLength = institution.getText().trim().length();
		String errorMsg = "";
		if (institutionLength == 0) {
			errorMsg += "\nPlease input an institution";
		}
		return errorMsg;
	}
/**
 * validates the JTextField passed parametrs to ensure they are not empty
 * if empty, adds error details to string
 * @param publisher JTextField representing a books publisher
 * @return a string that is empty or holds an error message
 */
	public static String validateBook(JTextField publisher) {
		String errorMsg = "";
		int publisherLength = publisher.getText().trim().length();
		if (publisherLength == 0) {
			errorMsg += "\nPlease input a Publisher";
		}
		return errorMsg;
	}

	/**
	 * validates the JTextField passed parametrs to ensure they are not empty
	 * if empty, adds error details to string
	 * @param journal this a text field representing the Article's journal
	 * @param doi     this a text field representing the Article's doi
	 * @return a String with details of any errors in the journal / doi
	 */
	public static String validateArticle(JTextField journal, JTextField doi) {
		int journalLength = journal.getText().trim().length();
		int doiLength = doi.getText().trim().length();
		String errorMsg = "";
		if (journalLength == 0) {
			errorMsg += "\nPlease input an Author";
		}
		if (doiLength == 0) {
			errorMsg += "\nPlease input a DOI";
		} 
		return errorMsg;
	}
/**
	 * validates the JTextField passed parametrs to ensure they are not empty
	 * if empty, adds error details to string
 * @param author this a text field representing the BibItems author
 * @param title this a text field representing the BibItems title
 * @param year this a text field representing the BibItems year
 * @return  a String with details of any errors in the bibitem
 */
	public static String validateBibItem(JTextField author, JTextField title, JTextField year) {
		int authorLength = author.getText().trim().length();
		int titleLength = title.getText().trim().length();
		int yearLength = year.getText().trim().length();
		String errorMsg = "";
		if (authorLength == 0) {
			errorMsg += "\nPlease input an Author";
		}
		if (yearLength == 0) {
			errorMsg += "\nPlease input a Year";
		} else {
			int yearNo = Integer.parseInt(year.getText());
			if (yearNo == 0) {
				errorMsg += "\nYear cannot be zero";
			}
		}
		if (titleLength == 0) {
			errorMsg += "\nPlease input a Title";
		}
		return errorMsg;
	}
}
