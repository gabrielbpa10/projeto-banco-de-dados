package br.com.arquitetura.dados.estudo.view;

import br.com.arquitetura.dados.estudo.controller.MemoriaHashEncadeada;
import br.com.arquitetura.dados.estudo.model.Pessoa;

public class App {
    public static void main( String[] args ){
    	MemoriaHashEncadeada memoria = new MemoriaHashEncadeada(10);
    	Pessoa p1 = new Pessoa("Jorvaldo Da Silva");
    	Pessoa p2 = new Pessoa("Mariabald");
    	Pessoa p3 = new Pessoa("Josebaldo");
    	memoria.adicionarValorHash(p1);
    	memoria.adicionarValorHash(p2);
    	memoria.adicionarValorHash(p3);
    	memoria.imprimirHash();
    	System.out.println(memoria.buscarValor("Josebaldo"));
    	
    }
}
