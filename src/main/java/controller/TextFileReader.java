package controller;

import java.io.BufferedReader;
import java.io.FileReader;

import Interfaces.IAction;
import Interfaces.IonEachString;

public class TextFileReader {
    
    public static void ReadFile(String path, IonEachString onEach, IAction onFinish) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            for (String line = br.readLine(); line != null; line = br.readLine()) onEach.Do(line);
            onFinish.Do();
        } finally {
            br.close();
        }
    }
}
