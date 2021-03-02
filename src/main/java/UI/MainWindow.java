package UI;

import javax.swing.*;

public class MainWindow {

    public JFrame Frame = new JFrame();

    public MainWindow () {
        try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void run () {

    }
}
