package org.agetac.client.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class DoubleTextField extends JTextField {
	
	public DoubleTextField(String txt) {
		super(new CustomPlainDocument(), txt, 0);
	}

	static class CustomPlainDocument extends PlainDocument {

		public void insertString(int offs, String str, AttributeSet a)
				throws BadLocationException {
			String currentText = getText(0, getLength());
			String beforeOffset = currentText.substring(0, offs);
			String afterOffset = currentText.substring(offs,
					currentText.length());
			String proposedResult = beforeOffset + str + afterOffset;

			Pattern pattern = Pattern.compile("(\\d|\\.)*");
			Matcher matcher = pattern.matcher(proposedResult);

			if (matcher.matches()) {
				super.insertString(offs, str, a);
			}
		}
	}
}
