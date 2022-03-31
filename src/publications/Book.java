package publications;


public class Book extends BibItem {
	private String publisher;

	
	public Book() {
		super();
	}

	
	public Book(String author, String title, int year, String publisher) {
		super(author, title, year);
		this.publisher = publisher;
		setCiteKey();
	}

	
	public String getPublisher() {
		return this.publisher;
	}

	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	
	@Override
	public void setCiteKey() {
		if ((getAuthor() != null) && (getYear() != 0)) {
			String arr[] = this.getAuthor().split(" ");
			this.citeKey = (arr[0] + this.getYear());
		}
	}

	
	@Override
	public String toHarvardString() {
		return getAuthor() + ". (" + getYear() + "). " + getTitle() + ". " + this.publisher + ".";
	}

	
	@Override
	public String toBibTexString() {
		return "@book{" + getCiteKey() + ",\nauthor \t\t= \"" + getAuthor() + "\",\ntitle \t\t= \"" + getTitle()
				+ "\",\nyear \t\t= " + getYear() + ",\npublisher\t= \"" + this.publisher + "\"}";
	}

	
	@Override
	public String toString() {
		return toHarvardString();
	}
}
