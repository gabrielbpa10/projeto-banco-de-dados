package controller;

public class Bucket {
	
	BucketUnit[] content;
	Bucket bucketOverflow;
	//Bucket next;
	
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
	
	public Page searchKey (String key) {
		for (BucketUnit unit : content) if (unit.key.equals(key)) return unit.pagePointer;
		if (bucketOverflow == null) return null;
		return bucketOverflow.searchKey(key); 
	}
	
}