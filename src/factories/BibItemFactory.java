package factories;

import publications.BibItem;


public interface BibItemFactory{
	
    public BibItem createBibItem(String bibItem);
  
}