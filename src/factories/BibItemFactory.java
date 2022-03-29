package factories;

import publications.BibItem;

/**
 * the factory interface
 * @author Bronagh
 *
 */
public interface BibItemFactory{
	/**
	 * interface method that returns a BibItem
	 * @param bibItem string representing the type of bibitem to return
	 * @return a BibItem object
	 */
    public BibItem createBibItem(String bibItem);
  
}