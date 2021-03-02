package ReturnTypes;

import controller.*;

public class TableControllerSeachReturn extends HashTableSeachReturn {

    public int pageIndex;

    public TableControllerSeachReturn(Page p, Bucket b, int vBucketIndex, int hBucketIndex, int contentIndex, int pageIndex) {
        super(p, b, vBucketIndex, hBucketIndex, contentIndex);
        this.pageIndex = pageIndex;
    }
}