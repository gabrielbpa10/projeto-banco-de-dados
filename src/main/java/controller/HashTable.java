package controller;

import Interfaces.IhashFunction;
import ReturnTypes.*;

public class HashTable {
	
	public Bucket[] buckets;
	public IhashFunction hashFunction;
	public int bucketSize;
	
	public HashTable (IhashFunction function, int bucketSize, int bucketCount) {
		hashFunction = function;
		this.bucketSize = bucketSize;
		this.buckets = new Bucket[bucketCount];
	}
	
	public void addKey (String item, Page pointer) {
		int code = hashFunction.Run(item);
		buckets[code].addKey(item, pointer);
	}
	
	public HashTableSeachReturn search (String item) {
		int bucketIndex = hashFunction.Run(item);
		BucketSearchReturn r = buckets[bucketIndex].searchKey(item);
		return new HashTableSeachReturn(r.foundPage, r.foundBucket, bucketIndex, r.horizontalBucketIndex, r.contentIndex);
	}
	
}
