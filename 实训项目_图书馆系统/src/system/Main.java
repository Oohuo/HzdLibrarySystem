package system;

import login.*;

public class Main {
	public static void main(String[] args) {
		try {
			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new LoginDialog();
			//new Login();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}