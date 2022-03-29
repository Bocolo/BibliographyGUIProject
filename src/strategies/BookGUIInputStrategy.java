package strategies;

import form.BookForm;
import publications.BibItem;
/**
 * class that extends the GUIItemInputStrategy
 * creates books from gui input
 * @author Bronagh
 *
 */
public class BookGUIInputStrategy extends GUIItemInputStrategy {
	/**
	 * overriding the required method
	 * creates books from book form gui input
	 * @return an object representing an book
	 */
	@Override
	public BibItem createBibItemFromInput() {
		BookForm bf = new BookForm();
		return bf.showBookForm();
	}
}