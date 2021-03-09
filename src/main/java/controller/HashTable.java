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
		for (int i = 0; i < bucketCount; i++) {
			this.buckets[i] = new Bucket(this.bucketSize);
		}
	}
	
	public int addKey (String item, Page pointer) throws Exception {
		int code = hashFunction.Run(item);
		buckets[code].addKey(item, pointer);
		return code;
	}

	public void clear () {
		for (int i = 0; i < buckets.length; i++) {
			this.buckets[i].clear();
		}
	}
	
	public HashTableSeachReturn search (String item) {
		int bucketIndex = hashFunction.Run(item);
		BucketSearchReturn r = buckets[bucketIndex].searchKey(item);
		if (r == null) return null;
		return new HashTableSeachReturn(r.foundPage, r.foundBucket, bucketIndex, r.horizontalBucketIndex, r.contentIndex);
	}
	
}
