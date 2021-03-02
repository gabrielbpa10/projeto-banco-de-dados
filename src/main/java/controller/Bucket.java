package controller;

import ReturnTypes.BucketSearchReturn;

public class Bucket {
	
	BucketUnit[] content;
	Bucket bucketOverflow;
	
	public Bucket (int size) {
		if (size < 1) size = 100;
		content = new BucketUnit[size];
	}
	
	public void addKey (String key, Page pointer) {
		for (int i = 0; i < content.length; i++) {
			if (content[i] == null) {
				content[i] = new BucketUnit(key, pointer);
				return;
			}
		}
		bucketOverflow = new Bucket(content.length);
		bucketOverflow.addKey(key, pointer);
	}
	
	public BucketSearchReturn searchKey (String key) {
		return searchKey(key, 0);
	}

	BucketSearchReturn searchKey (String key, int currentBucketIndex) {
		for (int i = 0; i < content.length; i++) {
			BucketUnit unit = content[i];
			if (unit.key.equals(key)) return new BucketSearchReturn(unit.pagePointer, this, currentBucketIndex, i);
		}
		if (bucketOverflow == null) return null;
		return bucketOverflow.searchKey(key, currentBucketIndex++); 
	}
	
}