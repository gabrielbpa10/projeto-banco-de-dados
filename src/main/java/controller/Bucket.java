package controller;

import ReturnTypes.BucketSearchReturn;

public class Bucket {
	
	public BucketUnit[] content;
	public Bucket bucketOverflow;
	public static int acess = 0;
	
	public Bucket (int size) {
		if (size < 1) size = 100;
		content = new BucketUnit[size];
	}
	
	public void addKey (String key, Page pointer) throws Exception {
		for (int i = 0; i < content.length; i++) {
			if (content[i] == null) {
				content[i] = new BucketUnit(key, pointer);
				return;
			}
		}
		if (bucketOverflow == null) bucketOverflow = new Bucket(content.length);
		bucketOverflow.addKey(key, pointer);
	}
	
	public BucketSearchReturn searchKey (String key) {
		return searchKey(key, 0);
	}

	public int count (boolean local) {
		int counter = 0;
		for (int i = 0; i < content.length; i++) {
			if (content[i] == null) break;
			counter++; 
		}
		if (!local && bucketOverflow != null) counter += bucketOverflow.count(local);
		return counter;
	}

	BucketSearchReturn searchKey (String key, int currentBucketIndex) {
		int acessCount = 0;
		for (int i = 0; i < content.length; i++) {
			BucketUnit unit = content[i];
			acessCount +=1;
			if (unit != null && unit.key.equals(key))return new BucketSearchReturn(unit.pagePointer, this, currentBucketIndex, i,acessCount);
		}
		if (bucketOverflow == null) {
			return null;
		}
		return bucketOverflow.searchKey(key, currentBucketIndex+1); 
	}

	public void clear () {
		for (int i = 0; i < content.length; i++) {
			content[i] = null;
		}
		bucketOverflow = null;
	}

	public String print () {
		String a = "[";
		for (int i = 0; i < this.content.length; i++) {
			BucketUnit unit = this.content[i];
			a += (unit == null ? "null" : unit.key);
			if (i < this.content.length - 1) a +=  ", ";
		}
		a += "] -> " + (this.bucketOverflow == null? "null" : "overflow");

		return a;
	}

	@Override
	public String toString() {
		String a = "content:\n[";
		for (BucketUnit unit : this.content) {
			a += (unit == null ? "null" : unit) + ", ";
		}
		a += "]\n";
		a += "Overflow:\n" + (this.bucketOverflow == null ? "null" : this.bucketOverflow.toString());
		return a;
	}
	
}