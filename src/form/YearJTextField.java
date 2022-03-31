package form;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class YearJTextField extends PlainDocument {
	private static final long serialVersionUID = 1L;
	private int max;

	public YearJTextField(int max) {
		super();
		this.max = max;
	}

	@Override
	public void insertString(int offset, String text, AttributeSet attr) throws BadLocationException {
		if (text == null)
			return;
		if ((getLength() + text.length()) <= max) {
			super.insertString(offset, text, attr);
		}
	}
	
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