package br.com.arquitetura.dados.estudo.controller;

public class Bucket {
	
	BucketUnit[] content;
	Bucket bucketOverflow;
	//Bucket next;
	
	public Bucket (int size) {
		content = new BucketUnit[size];
	}
	
	public void addKey (String key, Page pointer) {
		
	}
	
	public Page searchKey (String key) {
		
	}
	
}