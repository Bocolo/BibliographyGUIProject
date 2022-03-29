package form;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
/**
 * this class extend the plainDocument class
 * it is used to ensure year input in a JTextField only contains digits,
 * and sets the max number of digits the field will accept
 * @author Bronagh
 *
 */
public class YearJTextField extends PlainDocument {
	private static final long serialVersionUID = 1L;
	private int max;
/**
 * Constructor
 * @param max int representing the max no of digits to be entered in a Jtextfield
 */
	public YearJTextField(int max) {
		super();
		this.max = max;
	}
/**
 * Overridden method that ensures max no of characters to be entered in a Jtextfield 
 * is not exceeded
 */
	@Override
	public void insertString(int offset, String text, AttributeSet attr) throws BadLocationException {
		if (text == null)
			return;
		if ((getLength() + text.length()) <= max) {
			super.insertString(offset, text, attr);
		}
	}
	/**
	 * Overridden method that ensures only digits can be entered in a  Jtextfield 
	 */
	@Override
	public void replace(int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
		Pattern regEx = Pattern.compile("\\d*");
		Matcher matcher = regEx.matcher(text);
		if (!matcher.matches()) {
			return;
		}
		super.replace(offset, length, text, attrs);
	}
}