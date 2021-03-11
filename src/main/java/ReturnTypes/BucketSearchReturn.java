package ReturnTypes;

import controller.*;

public class BucketSearchReturn {
    
    public Page foundPage;
    public Bucket foundBucket;
    public int horizontalBucketIndex, contentIndex;
    public int acessCount;

    public BucketSearchReturn (Page p, Bucket b, int bucketIndex, int contentIndex, int acessCount) {
        foundPage = p;
        foundBucket = b;
        horizontalBucketIndex = bucketIndex;
        this.contentIndex = contentIndex;
        this.acessCount = acessCount;
    }

}
