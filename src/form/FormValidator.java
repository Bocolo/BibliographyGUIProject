package form;

import javax.swing.JTextField;

public class FormValidator {
	
	public static String validateTechReport(JTextField institution) {
		int institutionLength = institution.getText().trim().length();
		String errorMsg = "";
		if (institutionLength == 0) {
			errorMsg += "\nPlease input an institution";
		}
		return errorMsg;
	}

	public static String validateBook(JTextField publisher) {
		String errorMsg = "";
		int publisherLength = publisher.getText().trim().length();
		if (publisherLength == 0) {
			errorMsg += "\nPlease input a Publisher";
		}
		return errorMsg;
	}

	
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
