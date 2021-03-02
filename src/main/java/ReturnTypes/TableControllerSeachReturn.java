package ReturnTypes;

import controller.*;

public class TableControllerSeachReturn extends HashTableSeachReturn {

    public int pageIndex;

    public TableControllerSeachReturn(Page p, Bucket b, int vBucketIndex, int hBucketIndex, int contentIndex, int pageIndex) {
        super(p, b, vBucketIndex, hBucketIndex, contentIndex);
        this.pageIndex = pageIndex;
    }

    @Override
    public String toString() {
        return String.format("(vBucket: %d, hBucket: %d, contentIndex: %d, pageIndex: %d)", this.verticalBucketIndex, this.horizontalBucketIndex, this.contentIndex, this.pageIndex);
    }
}