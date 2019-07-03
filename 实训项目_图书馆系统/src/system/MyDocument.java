package system;

import javax.print.attribute.AttributeSet;
import javax.swing.text.*;

public class MyDocument extends PlainDocument {
	private static final long serialVersionUID = 1L;
	int maxLength = 15;

	public MyDocument(int len) {
		maxLength = len;
	}

	// ��֤����maxLength�ַ�����
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		if (getLength() + str.length() <= maxLength) {
			super.insertString(offs, str, (javax.swing.text.AttributeSet) a);
		}
	}
}
