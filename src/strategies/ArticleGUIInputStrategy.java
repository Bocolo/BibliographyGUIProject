package strategies;

import form.ArticleForm;
import publications.Article;
import publications.BibItem;
/**
 * class that extends the GUIItemInputStrategy
 * creates articles from gui input
 * @author Bronagh
 *
 */
public class ArticleGUIInputStrategy extends GUIItemInputStrategy {
	/**
	 * overriding the required method
	 * creates articles from article form gui input
	 * @return an object representing an article
	 */
	@Override
	public BibItem createBibItemFromInput() {
		ArticleForm af = new ArticleForm();
		Article article = new Article();
		article = af.addArticle();

		return article;
	}
}
