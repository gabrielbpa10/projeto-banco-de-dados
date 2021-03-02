package Main;

import ReturnTypes.*;
import UI.MainWindow;
import controller.*;

public class IndiceHash {

    public static void main(String[] args) throws Exception {
        TableController tc = new TableController((k) -> {
            return k.length() % 6;
        }, 6, 2, 3);
        MainWindow mw = new MainWindow();
        mw.gui();
    }

    public static void log(Object obj) {
        System.out.println(obj.toString());
    }
}