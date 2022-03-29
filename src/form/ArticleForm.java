package form;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import publications.Article;
/**
 * class to display a form to create a tech report citation 
 * @author Bronagh
 *
 */
public class ArticleForm {
	/**
	 * this is a recursive method. It creates and displays a JPanel with input fields
	 * for a Article. If ok is selected, inputs are validated. If any input errors exist,
	 * an ErrorMsg is displayed, when acknowledge, the method calls itself again
	 * 
	 * If no errorMsgs are displayed a new Article is created with the form inputs as params.
	 * The article is returned.
	 * If cancel is select, null is returned
	 */
	public Article addArticle() {
		JTextField author = new JTextField(5);
		JTextField title = new JTextField(5);
		JTextField year = new JTextField(5);
		JTextField journal = new JTextField(5);
		JTextField doi = new JTextField(5);
		JPanel myPanel = new JPanel();
		year.setDocument(new YearJTextField(4));
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		myPanel.add((new JLabel("Please Enter The article Details")));
		myPanel.add((new JLabel("Author:")));
		myPanel.add(author);
		myPanel.add(new JLabel("Title:"));
		myPanel.add(title);
		myPanel.add(new JLabel("Year:"));
		myPanel.add(year);
		myPanel.add(new JLabel("Journal:"));
		myPanel.add(journal);
		myPanel.add(new JLabel("Doi:"));
		myPanel.add(doi);
		Article article = null;
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Please Enter The Book Details",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			String errorMsg = "Error: ";
			errorMsg += FormValidator.validateBibItem(author, title, year);
			errorMsg += FormValidator.validateArticle(journal, doi);
			if (doi.getText().length() != 0) {
				try {
					article = new Article(author.getText(), title.getText(), Integer.parseInt(year.getText()),
							journal.getText(), doi.getText());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					errorMsg += "\nPlease Enter a valid DOI";
				}
			}
			if (!errorMsg.equals("Error: ")) {
				JOptionPane.showMessageDialog(null, errorMsg);
				article = addArticle();
			} else {
				article = new Article(author.getText(), title.getText(), Integer.parseInt(year.getText()),
						journal.getText(), doi.getText());
				
			}
		}
		return article;
	}
}
