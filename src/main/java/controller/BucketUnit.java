package controller;

public class BucketUnit {
	
	String key;
	Page pagePointer;
	
	public BucketUnit (String key, Page page) throws Exception {
		if (page == null) throw new Exception("Bucket unit cannot have a null page pointer. Key ("+key+").");
		this.key = key;
		this.pagePointer = page;
	}

	@Override
	public String toString() {
		return String.format("BucketUnit([%s, %s])", key, (pagePointer == null ? "null" : "PagePointer"));
	}
	
}
