package ReturnTypes;

import controller.*;

public class TableControllerSeachReturn extends HashTableSeachReturn {

    public int pageIndex;

    public TableControllerSeachReturn(Page p, Bucket b, int vBucketIndex, int hBucketIndex, int contentIndex, int pageIndex, int acessCount) {
        super(p, b, vBucketIndex, hBucketIndex, contentIndex, acessCount);
        this.pageIndex = pageIndex;
    }

    @Override
    public String toString() {
        return String.format("(vBucket: %d, hBucket: %d, contentIndex: %d, pageIndex: %d, acessCount: %d)", this.verticalBucketIndex, this.horizontalBucketIndex, this.contentIndex, this.pageIndex,this.acessCount);
    }
    
}