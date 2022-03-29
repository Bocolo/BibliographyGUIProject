package strategies;

import publications.BibItem;
/**
 * interface for bibitem input strategies
 * @author Bronagh
 *
 */
public interface ItemInputStrategy {
	/**
	 * this method should create a bibItem and return it
	 * @return a bibITem
	 */
	   public BibItem createBibItemFromInput();
	}
