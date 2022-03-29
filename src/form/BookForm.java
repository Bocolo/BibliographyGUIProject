package form;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import publications.Book;
/**
 * class to display a form to create a tech report citation 
 * @author Bronagh
 *
 */
public class BookForm {
	/**
	 * this is a recursive method. It creates and displays a JPanel with input fields
	 * for a Book. If ok is selected, inputs are validated. If any input errors exist,
	 * an ErrorMsg is displayed, when acknowledge, the method calls itself again
	 * 
	 * If no errorMsgs are displayed a new Book is created with the form inputs as params.
	 * The book is returned.
	 * If cancel is select, null is returned
	 */
	public Book showBookForm() {
		JTextField author = new JTextField(5);
		JTextField title = new JTextField(5);
		JTextField publisher = new JTextField(5);
		JTextField year = new JTextField(5);
		year.setDocument(new YearJTextField(4));
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		myPanel.add((new JLabel("Please Enter The Book Details")));
		myPanel.add((new JLabel("Author:")));
		myPanel.add(author);
		myPanel.add(new JLabel("Title:"));
		myPanel.add(title);
		myPanel.add(new JLabel("Year:"));
		myPanel.add(year);
		myPanel.add(new JLabel("Publisher:"));
		myPanel.add(publisher);
		Book book = null;
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Please Enter The Book Details",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			String errorMsg = "Error: ";
			errorMsg += FormValidator.validateBibItem(author, title, year);
			errorMsg += FormValidator.validateBook(publisher);
			if (!errorMsg.equals("Error: ")) {
				JOptionPane.showMessageDialog(null, errorMsg);
				book = showBookForm();
			} else {
				book = new Book(author.getText(), title.getText(), Integer.parseInt(year.getText()),
						publisher.getText());
			}
		}
		return book;
	}
}
