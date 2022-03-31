package publications;


public abstract class BibItem {
	protected String author;
	protected String title;
	protected int year = 0;
	protected String citeKey = "";

	
	public BibItem() {
	}

	
	public BibItem(String author, String title, int year) {
		this.author = author;
		this.title = title;
		this.year = year;
	}

	
	public abstract String toBibTexString();

	
	public abstract String toHarvardString();

	
	public abstract void setCiteKey();

	public String getAuthor() {
		return this.author;
	}

	
	public void setAuthor(String author) {
		this.author = author;
		setCiteKey();
	}

	
	public String getTitle() {
		return this.title;
	}

	
	public void setTitle(String title) {
		this.title = title;
	}

	
	public int getYear() {
		return this.year;
	}

	
	public void setYear(int year) {
		this.year = year;
		setCiteKey();
	}

	
	public String getCiteKey() {
		return this.citeKey;
	}
}
