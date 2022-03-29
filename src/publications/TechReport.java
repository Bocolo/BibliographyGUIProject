package publications;

public class TechReport extends BibItem {
	/**
	 * class that extends the abstract class BibItem represents tech report objects
	 * 
	 * @author Bronagh
	 *
	 */
	private String institution;

	/**
	 * default constructor that calls the super constructor
	 */
	public TechReport() {
		super();
	}

	/**
	 * TechReport constructor that sets the fields and calls the super constructor
	 * 
	 * @param author      the new author
	 * @param title       the new title
	 * @param year        the new year
	 * @param institution the new institution
	 * 
	 */
	public TechReport(String author, String title, int year, String institution) {
		super(author, title, year);
		this.institution = institution;
		setCiteKey();
	}

	/**
	 * institution getter
	 * 
	 * @return the institution
	 */
	public String getInstitution() {
		return this.institution;
	}

	/**
	 * institution setter
	 * 
	 * @param institution the new institution
	 */
	public void setInstitution(String institution) {
		this.institution = institution;
	}

	/**
	 * updates the cite key while author is not null and year is not 0 sets the
	 * citekey
	 */
	@Override
	public void setCiteKey() {
		if ((getAuthor() != null) && (getYear() != 0)) {
			String arr[] = this.getAuthor().split(" ");
			this.citeKey = (arr[0] + ":" + this.getYear());
			;
		}
	}

	/**
	 * returns a string representation of the article in Harvard format
	 * 
	 * @return a string representation of the article in Harvard format
	 */
	@Override
	public String toHarvardString() {
		return getAuthor() + ". (" + getYear() + "). " + getTitle() + ". " + this.institution + ".";
	}


	/**
	 * returns a string representation of the book in bibtex format
	 * 
	 * @return a string representation of the book in bibtex format
	 */
	@Override
	public String toBibTexString() {
		return "@techreport{" + getCiteKey() + ",\nauthor = \"" + getAuthor() + "\",\ntitle = \"" + getTitle()
				+ "\",\nyear = \"" + getYear() + "\",\ninstitution = \"" + this.institution + "\"}";
	}

	/**
	 * overridding the to String methods returns the results of Harvard string
	 */
	@Override
	public String toString() {
		return toHarvardString();
	}
}