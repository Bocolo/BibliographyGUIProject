package factories;

import publications.BibItem;
import strategies.ItemInputStrategy;

/**
 * this is a publication factory that implements BibItemFactory interface 
 * used to create BibItems with the relevant strategy classes
 * @author Bronagh
 *
 */
public class PublicationFactory implements BibItemFactory {
	private ItemInputStrategy bookInputStrategy;
	private ItemInputStrategy articleInputStrategy;
	private ItemInputStrategy techReportInputStrategy;

	/**
	 * constructor that sets the field strategies
	 * @param bookGUIInputStrategy a ItemInputStrategy object
	 * @param articleGUIInputStrategy a ItemInputStrategy object
	 * @param techReportGUIInputStrategy a ItemInputStrategy object
	 */
	public PublicationFactory(ItemInputStrategy bookInputStrategy,
			ItemInputStrategy articleInputStrategy, ItemInputStrategy techReportInputStrategy) {
		this.bookInputStrategy = bookInputStrategy;
		this.articleInputStrategy = articleInputStrategy;
		this.techReportInputStrategy = techReportInputStrategy;
	}

	/**
	 * returns a bibitem based on the passed string using a switch case
	 * each switch case calls the local method createBibItemUsingStrategy
	 * passing the relevant strategy
	 * if no case activated, returns null
	 * @param bibItem a string representing the name of the BibItem type to be returned
	 * @return a BibItem object
	 */
	@Override
	public BibItem createBibItem(String bibItem) {
		BibItem returnedBibItem = null;
		switch (bibItem) {
		case "Book":
			returnedBibItem = createBibItemUsingStrategy(bookInputStrategy);
			break;
		case "Article":
			returnedBibItem = createBibItemUsingStrategy(articleInputStrategy);
			break;
		case "Tech Report":
			returnedBibItem = createBibItemUsingStrategy(techReportInputStrategy);
			break;
		}
		return returnedBibItem;
	}

	/**
	 * takes in an implementation of the ItemInputStrategy interface and
	 * calls its createBibItemFromInput method, return its BibItem value
	 * @param inputGUIStrategy the strategy to be used to call createBibItemFromInput()
	 * @return a BibItem gotten from the strategies createBibItemFromInput method
	 */
	private BibItem createBibItemUsingStrategy(ItemInputStrategy inputGUIStrategy) {
		return inputGUIStrategy.createBibItemFromInput();
	} 
}
