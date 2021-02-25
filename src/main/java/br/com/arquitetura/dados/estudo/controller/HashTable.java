package br.com.arquitetura.dados.estudo.controller;

import java.util.ArrayList;

import Interfaces.IhashFunction;

public class HashTable {
	
	public Bucket[] buckets;
	public IhashFunction hashFunction;
	public int bucketSize;
	
	public HashTable (int bucketSize, int bucketCount) {
		this.bucketSize = bucketSize;
		this.buckets = new Bucket[bucketCount];
	}
	
	public void addKey (String item) {
		int code = hashFunction.Run(item);
		buckets[code].add(item); 
	}
	
}
