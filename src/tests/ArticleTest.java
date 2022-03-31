package tests;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import publications.Article;

/**
 * This is a test class for testing a valid doi input Doi must begin with
 * "https://doi.org/" then be followed by 2 digits then a period (.) then be
 * followed by 4 digits then be followed by a / then be followed by non null
 * e.g. "https://doi.org/10.1007/3-540-47910-4_21"
 * 
 * @author Bronagh
 *
 */
class ArticleTest {
	Article article;

	@Before
	public void setup() {
		this.article = new Article();
	}

	@Test
	void testDoiValid() {
		setup();
		article.setDoi("https://doi.org/10.1007/3-540-47910-4_21");
	}


	@Test
	void testDoiNotValid_FirstNumber2Digits_1() {
		setup();
		IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			article.setDoi("https://doi.org/1.1000/3-540");
		});
		Assertions.assertEquals("Invalid DOI entered", thrown.getMessage());
	}

	@Test
	void testDoiNotValid_FirstNumber2Digits_3() {
		setup();
		IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			article.setDoi("https://doi.org/100.1000/3-540");
		});
		Assertions.assertEquals("Invalid DOI entered", thrown.getMessage());
	}

	@Test
	void testDoiNotValid_SecondNumber4Digits_3() {
		setup();
		IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			article.setDoi("https://doi.org/10.100/3-540");
		});
		Assertions.assertEquals("Invalid DOI entered", thrown.getMessage());
	}

	@Test
	void testDoiNotValid_SecondNumber4Digits_5() {
		setup();
		IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			article.setDoi("https://doi.org/10.10000/3-540");
		});
		Assertions.assertEquals("Invalid DOI entered", thrown.getMessage());
	}

	@Test
	void testDoiNotValid_NullAfterSlash() {
		setup();
		IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			article.setDoi("https://doi.org/10.1007/");
		});
		Assertions.assertEquals("Invalid DOI entered", thrown.getMessage());
	}

	@Test
	void testDoiValid_MixedAlphaNumericAfterSlash() {
		setup();
		article.setDoi("https://doi.org/10.1000/dada-sda-543-h");
	}

	@Test
	void testDoiNotValid_StartsWithHttpsPrefix() {
		setup();
		IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			article.setDoi("hllqs://doi.org/10.1007/1254-465");
		});
		Assertions.assertEquals("Invalid DOI entered", thrown.getMessage());
	}
	@Test
	void testDoiValid_BoundaryFirst_SecondNumbers() {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher match = pattern.matcher("https://doi.org/10.1000/3-540");
		ArrayList<String> matches = new ArrayList<String>();
		while (match.find()) {
			matches.add(match.group());
		}
		Assertions.assertTrue(Integer.parseInt(matches.get(0)) > (9));
		Assertions.assertTrue(Integer.parseInt(matches.get(0)) < (100));
		Assertions.assertTrue(Integer.parseInt(matches.get(1)) > (999));
		Assertions.assertTrue(Integer.parseInt(matches.get(1)) < (10000));
	}
	@Test
	void testDoiNotValid_BoundaryFirst_SecondNumbers() {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher match = pattern.matcher("https://doi.org/1.100/3-540");
		ArrayList<String> matches = new ArrayList<String>();
		while (match.find()) {
			matches.add(match.group());
		}
		Assertions.assertFalse(Integer.parseInt(matches.get(0)) > (9) );
		Assertions.assertFalse(Integer.parseInt(matches.get(1)) > (999) );
		
		Matcher match2 = pattern.matcher("https://doi.org/100.10000/3-540");
		ArrayList<String> matches2 = new ArrayList<String>();
		while (match2.find()) {
			matches2.add(match2.group());
		}
		Assertions.assertFalse(Integer.parseInt(matches2.get(0)) < (100));
		Assertions.assertFalse(Integer.parseInt(matches2.get(1)) < (10000));
	}

}
