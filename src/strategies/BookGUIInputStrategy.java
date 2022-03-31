package strategies;

import form.BookForm;
import publications.BibItem;

public class BookGUIInputStrategy extends GUIItemInputStrategy {
	
	@Override
	public BibItem createBibItemFromInput() {
		BookForm bf = new BookForm();
		return bf.showBookForm();
	}
}