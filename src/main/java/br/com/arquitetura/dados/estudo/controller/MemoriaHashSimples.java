package br.com.arquitetura.dados.estudo.controller;

import java.util.Arrays;

public class MemoriaHashSimples {

	private String memoria []= null;
	private int chave;

	public MemoriaHashSimples(int tamanho) {
		memoria = new String[tamanho];
		Arrays.fill(memoria, "-1");
	}

//	public void adicionarHashSimples(String [] dados) {
//		for(int index = 0; index < dados.length; index ++) {
//			String novoElemento = dados[index];
//			memoria[Integer.parseInt(novoElemento)] = novoElemento;
//		}
//	}

	public void adicionaHashEncadeado(String [] dados) {
		for(int index = 0; index < dados.length; index++) {
			String novoElemento = dados[index];
			int indiceArray = dados[index].length()%memoria.length;
			while(memoria[indiceArray] != "-1") {
				indiceArray+=1;
			}
			memoria[indiceArray] = novoElemento;
		}
	}

	private void gerarChaveDeBusca(String dado) {
		chave = dado.length() % memoria.length;
	}

	public String filtrarDado(String dado) {
		gerarChaveDeBusca(dado);
		while(memoria[chave]!="-1") {
			if(memoria[chave].equals(dado)) {
				return  memoria[chave] + " encontrado no indice " + chave;
			}
			chave+=1;
		}

		return "Valor nao encontrado";
	}

	public void imprime() {
		for(int index = 0; index < memoria.length; index++) {
			System.out.println("[" + index +"] " + memoria[index]);
		}
	}
}
