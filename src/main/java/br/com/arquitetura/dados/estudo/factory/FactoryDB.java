package br.com.arquitetura.dados.estudo.factory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class FactoryDB {

	private static final String PATH = "";
	private FileReader fileReader = null;
	private BufferedReader bufferedReader = null;
	String valores = "";
	
	public void instanciarBase() {
		try {
			fileReader = new FileReader(PATH);
		} catch (FileNotFoundException e) {
			//e.printStackTrace(PrintWriter);
			e.printStackTrace();
		}
	}
}
