package strategies;

import form.TechReportForm;
import publications.BibItem;

public class TechReportGUIInputStrategy extends GUIItemInputStrategy {
	
	@Override
	public BibItem createBibItemFromInput() {
		TechReportForm trf = new TechReportForm();
		return trf.showTechReportForm();
	} 
}
