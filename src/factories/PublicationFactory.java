package factories;

import publications.BibItem;
import strategies.ArticleGUIInputStrategy;
import strategies.BookGUIInputStrategy;
import strategies.ItemInputStrategy;
import strategies.TechReportGUIInputStrategy;

/**
 * this is a publication factory that implements BibItemFactory interface 
 * used to create BibItems with the relevant strategy classes
 * @author Bronagh
 *
 */
public class PublicationFactory implements BibItemFactory {
	private BookGUIInputStrategy bookGUIInputStrategy;
	private ArticleGUIInputStrategy articleGUIInputStrategy;
	private TechReportGUIInputStrategy techReportGUIInputStrategy;

	/**
	 * constructor that sets the field strategies
	 * @param bookGUIInputStrategy a BookGUIInputStrategy object
	 * @param articleGUIInputStrategy a ArticleGUIInputStrategy object
	 * @param techReportGUIInputStrategy a TechReportGUIInputStrategy object
	 */
	public PublicationFactory(BookGUIInputStrategy bookGUIInputStrategy,
			ArticleGUIInputStrategy articleGUIInputStrategy, TechReportGUIInputStrategy techReportGUIInputStrategy) {
		this.bookGUIInputStrategy = bookGUIInputStrategy;
		this.articleGUIInputStrategy = articleGUIInputStrategy;
		this.techReportGUIInputStrategy = techReportGUIInputStrategy;
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
			returnedBibItem = createBibItemUsingStrategy(bookGUIInputStrategy);
			break;
		case "Article":
			returnedBibItem = createBibItemUsingStrategy(articleGUIInputStrategy);
			break;
		case "Tech Report":
			returnedBibItem = createBibItemUsingStrategy(techReportGUIInputStrategy);
			break;
		}
		return returnedBibItem;
	}

	/**
	 * takes in an implentation of the ItemInputStrategy interface and
	 * calls its createBibItemFromInput method, return its BibItem value
	 * @param inputGUIStrategy the strategy to be used to call createBibItemFromInput()
	 * @return a BibItem gotten from the strategies createBibItemFromInput method
	 */
	private BibItem createBibItemUsingStrategy(ItemInputStrategy inputGUIStrategy) {
		return inputGUIStrategy.createBibItemFromInput();
	} 
}
