package publications;

/**
 * class that extends the abstract class BibItem represents book objects
 * 
 * @author Bronagh
 *
 */
public class Book extends BibItem {
	private String publisher;

	/**
	 * default constructor that calls the super constructor
	 */
	public Book() {
		super();
	}

	/**
	 * book constructor that sets the fields and calles the super constructor
	 * 
	 * @param author    the new author
	 * @param title     the new title
	 * @param year      the new year
	 * @param publisher the new publisher
	 */
	public Book(String author, String title, int year, String publisher) {
		super(author, title, year);
		this.publisher = publisher;
		setCiteKey();
	}

	/**
	 * publisher getter
	 * 
	 * @return the publisher
	 */
	public String getPublisher() {
		return this.publisher;
	}

	/**
	 * publisher setter
	 * 
	 * @param publisher the new publisher
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * updates the cite key while author is not null and year is not 0
	 */
	@Override
	public void setCiteKey() {
		if ((getAuthor() != null) && (getYear() != 0)) {
			String arr[] = this.getAuthor().split(" ");
			this.citeKey = (arr[0] + this.getYear());
		}
	}

	/**
	 * returns a string representation of the book in Harvard format
	 * 
	 * @return a string representation of the book in Harvard format
	 */
	@Override
	public String toHarvardString() {
		return getAuthor() + ". (" + getYear() + "). " + getTitle() + ". " + this.publisher + ".";
	}

	/**
	 * returns a string representation of the book in bibtex format
	 * 
	 * @return a string representation of the book in bibtex format
	 */
	@Override
	public String toBibTexString() {
		return "@book{" + getCiteKey() + ",\nauthor \t\t= \"" + getAuthor() + "\",\ntitle \t\t= \"" + getTitle()
				+ "\",\nyear \t\t= " + getYear() + ",\npublisher\t= \"" + this.publisher + "\"}";
	}

	/**
	 * Overriding the to String methods returns the results of Harvard string
	 */
	@Override
	public String toString() {
		return toHarvardString();
	}
}
