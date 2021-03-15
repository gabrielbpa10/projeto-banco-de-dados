package Main;

import UI.MainWindow;

public class IndiceHash {

    public static void main(String[] args) throws Exception {
        MainWindow mw = new MainWindow();
        mw.gui();
    }

    public static void log(Object obj) {
        System.out.println(obj.toString());
    }
}