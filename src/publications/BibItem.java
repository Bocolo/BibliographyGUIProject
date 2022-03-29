package publications;

/**
 * abstract class that represnts bibliogrpahy items contains shared fields and
 * methods for subclasses
 * 
 * @author Bronagh
 *
 */
public abstract class BibItem {
	protected String author;
	protected String title;
	protected int year = 0;
	protected String citeKey = "";

	/**
	 * default BibItem Constructor
	 */
	public BibItem() {
	}

	/**
	 * BibItem Constructor
	 * 
	 * @param author the bibItem author
	 * @param title  the bibItem title
	 * @param year   the bibItem year
	 */
	public BibItem(String author, String title, int year) {
		this.author = author;
		this.title = title;
		this.year = year;
	}

	/**
	 * Should create a string representation of the BibItem in BibTex format
	 * 
	 * @return the newly created string
	 */
	public abstract String toBibTexString();

	/**
	 * Should create a string representation of the BibItem in Harvard format
	 * 
	 * @return the newly created string
	 */
	public abstract String toHarvardString();

	/**
	 * Should set the citeKey to the correct citeKey format for the BibItem
	 */
	public abstract void setCiteKey();

	public String getAuthor() {
		return this.author;
	}

	/**
	 * author setter updates cite key after author is set
	 * 
	 * @param author the new book author
	 */
	public void setAuthor(String author) {
		this.author = author;
		setCiteKey();
	}

	/**
	 * title getter
	 * 
	 * @return the title string
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * title setter
	 * 
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * year getter
	 * 
	 * @return returns the year
	 */
	public int getYear() {
		return this.year;
	}

	/**
	 * year setter updates cite key after year is set
	 * 
	 * @param year the new book year
	 */
	public void setYear(int year) {
		this.year = year;
		setCiteKey();
	}

	/**
	 * cite key getter
	 * 
	 * @return returns the citekey
	 */
	public String getCiteKey() {
		return this.citeKey;
	}
}
