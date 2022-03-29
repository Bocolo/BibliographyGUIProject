package strategies;

import form.TechReportForm;
import publications.BibItem;
/**
 * class that extends the GUIItemInputStrategy
 * creates tech reports from gui input
 * @author Bronagh
 *
 */
public class TechReportGUIInputStrategy extends GUIItemInputStrategy {
	/**
	 * overriding the required method
	 * creates tech reports from tech report form gui input
	 * @return an object representing an tech report
	 */
	@Override
	public BibItem createBibItemFromInput() {
		TechReportForm trf = new TechReportForm();
		return trf.showTechReportForm();
	} 
}
