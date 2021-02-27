package ReturnTypes;

import controller.*;

public class HashTableSeachReturn extends BucketSearchReturn {

    int verticalBucketIndex;

    public HashTableSeachReturn(Page p, Bucket b, int vBucketIndex, int hBucketIndex, int contentIndex) {
        super(p, b, hBucketIndex, contentIndex);
        verticalBucketIndex = vBucketIndex;
    }
    
}
