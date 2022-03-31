package factories;

import publications.BibItem;
import strategies.ItemInputStrategy;


public class PublicationFactory implements BibItemFactory {
	private ItemInputStrategy bookInputStrategy;
	private ItemInputStrategy articleInputStrategy;
	private ItemInputStrategy techReportInputStrategy;

	
	public PublicationFactory(ItemInputStrategy bookInputStrategy,
			ItemInputStrategy articleInputStrategy, ItemInputStrategy techReportInputStrategy) {
		this.bookInputStrategy = bookInputStrategy;
		this.articleInputStrategy = articleInputStrategy;
		this.techReportInputStrategy = techReportInputStrategy;
	}

	
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

	
	private BibItem createBibItemUsingStrategy(ItemInputStrategy inputGUIStrategy) {
		return inputGUIStrategy.createBibItemFromInput();
	} 
}
