package br.com.arquitetura.dados.estudo.controller;

import java.util.LinkedList;
import java.util.Random;

import br.com.arquitetura.dados.estudo.model.Pessoa;

public class MemoriaHash {

	private Pessoa [] hash = null;
	private Pessoa pessoa;
	private int indice;

	public MemoriaHash(int tamanhoMemoria) {
		if(hash == null) {
			this.hash = new Pessoa [tamanhoMemoria];
		}
	}

	public void adicionarValor(Pessoa pessoa) {
		this.pessoa = pessoa;
		verificarIndiceDisponivel();
		hash[indice] = pessoa;
	}

	public int gerarIndiceCadastro() {
		return pessoa.getNome().length()%this.hash.length;
	}

	public void verificarIndiceDisponivel() {
		indice = gerarIndiceCadastro();
		while(hash[indice] != null) {
			indice = gerarIndiceCadastro();
		}		
	}

	public Pessoa filtrarValor(String nome) {
		Pessoa valorRetornar = null;
		int index = 0;
		while(index < hash.length) {
			if(hash[index] != null) {
				if(hash[index].getNome().equals(nome)) {
					valorRetornar = hash[index];
				}
			}
			index ++;
		}
		return valorRetornar;
	}

	public void imprimir() {
		for (int i = 0; i < hash.length; i++) {
			System.out.println("["+i+"]" + " " + hash[i]);
		}
	}
}
