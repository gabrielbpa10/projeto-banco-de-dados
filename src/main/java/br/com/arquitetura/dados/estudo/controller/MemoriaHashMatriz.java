package br.com.arquitetura.dados.estudo.controller;

import java.util.Arrays;

public class MemoriaHashMatriz {

	private Object tabelaHash [][]= null;
	private int chave;

	public MemoriaHashMatriz(int tamanho) {
		this.tabelaHash = new Object[tamanho][4];

		for (int linha = 0; linha < tabelaHash.length; linha++) {
			for(int coluna = 0; coluna < tabelaHash[linha].length; coluna++) {
				Arrays.fill((Object[]) tabelaHash[linha], "-1");
		}}
	}

	private void gerarChave(String valor) {
		//		for(int index = 0; index < valor.length(); index++) {
		//			chave += valor.charAt(index);
		//			System.out.println(valor.charAt(index) + " " + ((int) valor.charAt(index)));
		//		}
		chave = valor.length() % tabelaHash.length;
	}

	public void adicionarDado(String valor) {
		gerarChave(valor);
		int index = 0;
		while(tabelaHash[chave][index]!= "-1") {
			
			if(index == 3) {
				chave++;
				index = -1;
			}
			index++;
		}

		tabelaHash[chave][index] = valor;
	}

	public void imprimirDados() {
		for (int linha = 0; linha < tabelaHash.length; linha++) {
			for(int coluna = 0; coluna < tabelaHash[linha].length; coluna++)
				System.out.println("[" + linha + "]" + "[" + coluna + "]" + tabelaHash[linha][coluna]);
		}
	}
}
