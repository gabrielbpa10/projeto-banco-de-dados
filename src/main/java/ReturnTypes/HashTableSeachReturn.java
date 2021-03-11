package ReturnTypes;

import controller.*;

public class HashTableSeachReturn extends BucketSearchReturn {

    public int verticalBucketIndex;

    public HashTableSeachReturn(Page p, Bucket b, int vBucketIndex, int hBucketIndex, int contentIndex, int acessCount) {
        super(p, b, hBucketIndex, contentIndex,acessCount);
        verticalBucketIndex = vBucketIndex;
    }
    
}
