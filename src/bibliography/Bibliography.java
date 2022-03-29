package bibliography;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

import publications.BibItem;

/**
 * singleton class that represents a bibliography which contains bibItems
 * 
 * @author Bronagh
 *
 */
public class Bibliography {
	private TreeMap<String, BibItem> bibItemsTree = new TreeMap<String, BibItem>();
	public static Bibliography instance = null;

	/**
	 * gets the bibliography instance, if null, sets the bibliography instance
	 * 
	 * @return the bibliography instance
	 */
	public static Bibliography getInstance() {
		if (instance == null) {
			instance = new Bibliography();
		}
		return instance;
	}

	/**
	 * bibItemsTree getter
	 * 
	 * @return bibItemsTree tree map
	 */
	public TreeMap<String, BibItem> getBibItemsTree() {
		return this.bibItemsTree;
	}

	/**
	 * adds new item to the bibItemsTree tree map
	 * 
	 * @param citekey tree map key to set
	 * @param bibItem tree map value to set
	 */
	public void addToTree(String citeKey, BibItem bibItem) {
		bibItemsTree.put(citeKey, bibItem);
	}

	/**
	 * remove item from the bibItemsTree using the citekey
	 * 
	 * @param citeKey key to remove item
	 */
	public void removeFromTree(String citeKey) {
		bibItemsTree.remove(citeKey);
	}

	/**
	 * bibItemsTree setter
	 * 
	 * @param bibItemsTree the new bibItemsTree
	 */
	public void setBibItemsTree(TreeMap<String, BibItem> bibItemsTree) {
		this.bibItemsTree = bibItemsTree;
	}

	/**
	 * sets the bibItemsTree values to a lists and sorts them by year
	 * 
	 * @return the newly sorted list of bibitems
	 */
	public List<BibItem> getSortedBibliography() {
		List<BibItem> bibItemByYear = new ArrayList<>(bibItemsTree.values());
		Collections.sort(bibItemByYear, Comparator.comparing(BibItem::getYear).reversed());
		return bibItemByYear;
	}

	/**
	 * saves the results of getSortedBibliography method to a file
	 * 
	 * @param filename name of file to save to
	 */
	public void saveToFile(String filename) {
		try {
			FileWriter writer = new FileWriter(filename + ".bib");
			List<BibItem> bibItemByYear = getSortedBibliography();
			for (BibItem bibItem : bibItemByYear) {
				writer.write(bibItem.toBibTexString() + System.lineSeparator() + "\n");
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}