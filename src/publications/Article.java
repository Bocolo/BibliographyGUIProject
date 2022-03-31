package publications;


public class Article extends BibItem {
//"https://doi.org/\\d{2}\\.\\d{4}/[-._;()/:A-Z0-9]+"
	private String journal;
	private String doi;

	
	public Article() {
		super();
	}

	
	public Article(String author, String title, int year, String journal, String doi) {
		super(author, title, year);
		this.journal = journal;
		setDoi(doi);
		setCiteKey();
	}

	
	public void setCiteKey() {
		if ((getAuthor() != null) && (getYear() != 0) && (journal != null)) {
			String arr[] = author.split(" ");
			String arr2[] = this.getJournal().split(" ", 2);
			System.out.println("setting cite ket");
			this.citeKey = (arr[0] + this.getYear() % 100 + arr2[0]);
		}
	}

	
	public void setDoi(String doi) {
		if (isValidDoi(doi)) {
			this.doi = doi;
		} else
			throw new IllegalArgumentException("Invalid DOI entered");
	}

	
	public String getDoi() {
		return this.doi;
	}

	
	public boolean isValidDoi(String doi) {
		String regex = "https://doi.org/\\d{2}\\.\\d{4}/[-._;()/:a-zA-Z0-9]+";
		return doi.matches(regex);
	}

	
	public String getJournal() {
		return this.journal;
	}

	
	public void setJournal(String journal) {
		this.journal = journal;
		setCiteKey();
	}

	
	@Override
	public String toHarvardString() {
		return getAuthor() + ". (" + getYear() + "). " + getTitle() + ". " + this.journal + ". " + this.doi + ".";
	}

	
	@Override
	public String toBibTexString() {
		return "@article{" + getCiteKey() + ",\nauthor = \"" + getAuthor() + "\",\ntitle = \"" + getTitle()
				+ "\",\nyear = \"" + getYear() + "\",\njournal= \"" + this.journal + "\",\ndoi= \"" + this.doi + "\"}";
	}

	
	@Override
	public String toString() {
		return toHarvardString();
	}
}
