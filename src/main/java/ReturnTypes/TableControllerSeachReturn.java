package ReturnTypes;

import controller.*;

public class TableControllerSeachReturn {
    
    Bucket keyBucket;
    int verticalBucketIndex, horizontalBucketIndex, pageIndex, innerPageIndex;
    Page keyPage;

    public TableControllerSeachReturn (Bucket b, Page p, int verticalBucket, int horizontalBucket, int pageIndex, int innerIndex) {
        keyBucket = b;
        keyPage = p;
        verticalBucketIndex = verticalBucket;
        horizontalBucketIndex = horizontalBucket;
        this.pageIndex = pageIndex;
        innerPageIndex = innerIndex;
    }
}
