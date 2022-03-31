package bibliography;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

import publications.BibItem;


public class Bibliography {
	private TreeMap<String, BibItem> bibItemsTree = new TreeMap<String, BibItem>();
	public static Bibliography instance = null;

	
	public static Bibliography getInstance() {
		if (instance == null) {
			instance = new Bibliography();
		}
		return instance;
	}

	
	public TreeMap<String, BibItem> getBibItemsTree() {
		return this.bibItemsTree;
	}

	
	public void addToTree(String citeKey, BibItem bibItem) {
		bibItemsTree.put(citeKey, bibItem);
	}

	
	public void removeFromTree(String citeKey) {
		bibItemsTree.remove(citeKey);
	}

	
	public void setBibItemsTree(TreeMap<String, BibItem> bibItemsTree) {
		this.bibItemsTree = bibItemsTree;
	}

	
	public List<BibItem> getSortedBibliography() {
		List<BibItem> bibItemByYear = new ArrayList<>(bibItemsTree.values());
		Collections.sort(bibItemByYear, Comparator.comparing(BibItem::getYear).reversed());
		return bibItemByYear;
	}

	
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