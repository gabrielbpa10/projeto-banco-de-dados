package controller;

import Interfaces.IhashFunction;
import ReturnTypes.*;

public class TableController {

    HashTable hashTable;
    Page page, lastPage;
    int pageSize;

    public TableController (IhashFunction function, int bucketCount, int bucketSize, int pageSize) {
        hashTable = new HashTable(function, bucketSize, bucketCount);
        this.pageSize = pageSize;
        this.page = new Page(pageSize);
        this.lastPage = this.page;
    }

    public Page insert (String item) throws Exception {
        Page newPage = lastPage.add(item);
        if (newPage == null) newPage = lastPage;
        hashTable.addKey(item, newPage);
        return newPage;
    }

    int getPageIndex (Page p) {
        int pageIndex = -1;
        Page pagePointer = page;
        while (p != pagePointer) {
            pagePointer = pagePointer.next;
            pageIndex++;
        }
        return pageIndex;
    }

    public TableControllerSeachReturn select (String item) throws Exception {
        HashTableSeachReturn r = hashTable.search(item);
        if (r == null) return null;
        int pageIndex = getPageIndex(r.foundPage);
        return new TableControllerSeachReturn(r.foundPage, r.foundBucket, r.verticalBucketIndex, r.horizontalBucketIndex, r.contentIndex, pageIndex);
    }
}
