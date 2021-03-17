package Main;

import UI.MainWindow;
import UI.NewMainWindow;

public class IndiceHash {

    public static void main(String[] args) throws Exception {
        // MainWindow mw = new MainWindow();
        // mw.gui();
        NewMainWindow mw = new NewMainWindow();
        mw.gui();
    }

    public static void log(Object obj) {
        System.out.println(obj.toString());
    }
}