package publications;

/**
 * class that extends the abstract class BibItem represents articles
 * 
 * @author Bronagh
 *
 */
public class Article extends BibItem {
//"https://doi.org/\\d{2}\\.\\d{4}/[-._;()/:A-Z0-9]+"
	private String journal;
	private String doi;

	/**
	 * default constructor that calls the super constructor
	 */
	public Article() {
		super();
	}

	/**
	 * Article constructor that sets the fields and calls the super constructor
	 * 
	 * @param author  the new author
	 * @param title   the new title
	 * @param year    the new year
	 * @param journal the new journal
	 * @param doi     the new doi
	 */
	public Article(String author, String title, int year, String journal, String doi) {
		super(author, title, year);
		this.journal = journal;
		setDoi(doi);
		setCiteKey();
	}

	/**
	 * updates the cite key while author is not null and year is not 0 and journal
	 * is not null calls the super set cite key
	 */
	public void setCiteKey() {
		if ((getAuthor() != null) && (getYear() != 0) && (journal != null)) {
			String arr[] = author.split(" ");
			String arr2[] = this.getJournal().split(" ", 2);
			System.out.println("setting cite ket");
			this.citeKey = (arr[0] + this.getYear() % 100 + arr2[0]);
		}
	}

	/**
	 * checks if passed doi is valid sets the doi if valid else throws an exception
	 * 
	 * @param doi the new doi
	 */
	public void setDoi(String doi) {
		if (isValidDoi(doi)) {
			this.doi = doi;
		} else
			throw new IllegalArgumentException("Invalid DOI entered");
	}

	/**
	 * doi getter
	 * 
	 * @return the doi
	 */
	public String getDoi() {
		return this.doi;
	}

	/**
	 * checks if the doi matches the regex
	 * 
	 * @param doi a string representing the doi
	 * @return a boolean is the doi matches the regex
	 */
	public boolean isValidDoi(String doi) {
		String regex = "https://doi.org/\\d{2}\\.\\d{4}/[-._;()/:a-zA-Z0-9]+";
		return doi.matches(regex);
	}

	/**
	 * journal getter
	 * 
	 * @return the journal
	 */
	public String getJournal() {
		return this.journal;
	}

	/**
	 * journal setter
	 * 
	 * @param journal the new journal
	 */
	public void setJournal(String journal) {
		this.journal = journal;
		setCiteKey();
	}

	/**
	 * returns a string representation of the article in Harvard format
	 * 
	 * @return a string representation of the article in Harvard format
	 */
	@Override
	public String toHarvardString() {
		return getAuthor() + ". (" + getYear() + "). " + getTitle() + ". " + this.journal + ". " + this.doi + ".";
	}

	/**
	 * returns a string representation of the book in bibtex format
	 * 
	 * @return a string representation of the book in bibtex format
	 */
	@Override
	public String toBibTexString() {
		return "@article{" + getCiteKey() + ",\nauthor = \"" + getAuthor() + "\",\ntitle = \"" + getTitle()
				+ "\",\nyear = \"" + getYear() + "\",\njournal= \"" + this.journal + "\",\ndoi= \"" + this.doi + "\"}";
	}

	/**
	 * overridding the to String methods returns the results of Harvard string
	 */
	@Override
	public String toString() {
		return toHarvardString();
	}
}
