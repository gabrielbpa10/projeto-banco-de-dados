package br.com.arquitetura.dados.estudo.projeto_arquitetura_dados;

import org.junit.Test;

import br.com.arquitetura.dados.estudo.controller.MemoriaHashMatriz;
import br.com.arquitetura.dados.estudo.controller.MemoriaHashSimples;

public class ManipularDadosTest {

	@Test
	public void tarefaAdicionarDadosNaMemoriaHashMatriz() {
		String dados [] = {"Jonathan","Jonathan","Jonathan","Jonathan","Jonathan"};
		MemoriaHashMatriz memoria = new MemoriaHashMatriz(10);
		
		for(int index = 0; index < dados.length;index++) {
			memoria.adicionarDado(dados[index]);
		}
		
		memoria.imprimirDados();
	}
	
	@Test
	public void tarefaAdicionarDadosNaMemoriaHashSimples() {
		MemoriaHashSimples memoria = new MemoriaHashSimples(300);
		String dados []= {"Alan","Gabriel","Gabriee"};
		memoria.adicionaHashEncadeado(dados);
		
		memoria.imprime();
		String resposta = memoria.filtrarDado("Gabriee");
		System.out.println(resposta);
	}
}
