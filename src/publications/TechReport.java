package publications;

public class TechReport extends BibItem {
	
	private String institution;

	
	public TechReport() {
		super();
	}

	
	public TechReport(String author, String title, int year, String institution) {
		super(author, title, year);
		this.institution = institution;
		setCiteKey();
	}

	
	public String getInstitution() {
		return this.institution;
	}

	
	public void setInstitution(String institution) {
		this.institution = institution;
	}

	
	@Override
	public void setCiteKey() {
		if ((getAuthor() != null) && (getYear() != 0)) {
			String arr[] = this.getAuthor().split(" ");
			this.citeKey = (arr[0] + ":" + this.getYear());
			;
		}
	}

	
	@Override
	public String toHarvardString() {
		return getAuthor() + ". (" + getYear() + "). " + getTitle() + ". " + this.institution + ".";
	}


	
	@Override
	public String toBibTexString() {
		return "@techreport{" + getCiteKey() + ",\nauthor = \"" + getAuthor() + "\",\ntitle = \"" + getTitle()
				+ "\",\nyear = \"" + getYear() + "\",\ninstitution = \"" + this.institution + "\"}";
	}

	
	@Override
	public String toString() {
		return toHarvardString();
	}
}