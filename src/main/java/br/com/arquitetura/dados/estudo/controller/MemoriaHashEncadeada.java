package br.com.arquitetura.dados.estudo.controller;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.arquitetura.dados.estudo.model.Pessoa;

public class MemoriaHashEncadeada {

	private HashMap<Integer,ArrayList> hash = null;
	private ArrayList<Pessoa> lista = null;
	private Integer tamanho;
	private Integer chave;

	public MemoriaHashEncadeada(int tamanho) {
		this.tamanho = tamanho;
		if(hash == null) {hash = new HashMap<>();}
	}

	private void gerarChave(Pessoa pessoa) {
		chave = pessoa.getNome().length()%tamanho;
	}

	public void adicionarValorHash(Pessoa pessoa) {
		gerarChave(pessoa);
		
		if(!verificarChaveListaVazia()) {
			lista = new ArrayList<>();
			adicionarValorLista(pessoa);
			hash.put(chave,lista);
		} else {
			hash.get(chave).add(pessoa);
		}
	}

	public boolean verificarChaveListaVazia() {
		return hash.containsKey(chave);
	}
	
	private void adicionarValorLista(Pessoa pessoa) {
		this.lista.add(pessoa);
	}
	
	public Object buscarValor(String valor) {
		chave = valor.length()%tamanho;
		ArrayList<Pessoa> lista = (ArrayList<Pessoa>) hash.get(chave);
		
		return lista.stream().filter(v->v.getNome().equals(valor)).findFirst().get();
	}
	
	public void imprimirHash() {
		hash.forEach((chave,lista)->{
			System.out.println("Chave{"+chave+"} = "+ lista.toString());
		});
	}
}
