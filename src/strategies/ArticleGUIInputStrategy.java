package strategies;

import form.ArticleForm;
import publications.Article;
import publications.BibItem;

public class ArticleGUIInputStrategy extends GUIItemInputStrategy {
	
	@Override
	public BibItem createBibItemFromInput() {
		ArticleForm af = new ArticleForm();
		Article article = new Article();
		article = af.addArticle();

		return article;
	}
}
