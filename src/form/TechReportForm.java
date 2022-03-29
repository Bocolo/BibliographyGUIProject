package form;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import publications.TechReport;
/**
 * class to display a form to create a tech report citation 
 * @author Bronagh
 *
 */
public class TechReportForm {
	/**
	 * this is a recursive method. It creates and displays a JPanel with input fields
	 * for a TechReport. If ok is selected, inputs are validated. If any input errors exist,
	 * an ErrorMsg is displayed, when acknowledge, the method calls itself again
	 * 
	 * If no errorMsgs are displayed a new TechReport is created with the form inputs as params.
	 * The techReport is returned.
	 * If cancel is select, null is returned
	 */
	public TechReport showTechReportForm() {
		JTextField author = new JTextField(5);
		JTextField title = new JTextField(5);
		JTextField institution = new JTextField(5);
		JTextField year = new JTextField(5);
		year.setDocument(new YearJTextField(4));
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		myPanel.add((new JLabel("Please Enter The Report Details")));
		myPanel.add((new JLabel("Author:")));
		myPanel.add(author);
		myPanel.add(new JLabel("Title:"));
		myPanel.add(title);
		myPanel.add(new JLabel("Year:"));
		myPanel.add(year);
		myPanel.add(new JLabel("Institution:"));
		myPanel.add(institution);
		TechReport techReport = null;
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Please Enter The Book Details",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			String errorMsg = "Error: ";
			errorMsg += FormValidator.validateBibItem(author, title, year);
			errorMsg += FormValidator.validateTechReport(institution);
			if (!errorMsg.equals("Error: ")) {
				JOptionPane.showMessageDialog(null, errorMsg);
				techReport = showTechReportForm();
			} else {
				techReport = new TechReport(author.getText(), title.getText(), Integer.parseInt(year.getText()),
						institution.getText());
			}
		}
		return techReport;
	}
}
