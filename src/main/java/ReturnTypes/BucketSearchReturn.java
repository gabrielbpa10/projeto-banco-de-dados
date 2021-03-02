package ReturnTypes;

import controller.*;

public class BucketSearchReturn {
    
    public Page foundPage;
    public Bucket foundBucket;
    public int horizontalBucketIndex, contentIndex;

    public BucketSearchReturn (Page p, Bucket b, int bucketIndex, int contentIndex) {
        foundPage = p;
        foundBucket = b;
        horizontalBucketIndex = bucketIndex;
        this.contentIndex = contentIndex;
    }

}
