package controller;

import Interfaces.IhashFunction;
import ReturnTypes.*;

public class TableController {

    public HashTable hashTable;
    public Page page, lastPage;
    int pageSize;

    public TableController (IhashFunction function, int bucketCount, int bucketSize, int pageSize) {
        hashTable = new HashTable(function, bucketSize, bucketCount);
        this.pageSize = pageSize;
        this.page = new Page(pageSize);
        this.lastPage = this.page;
    }

    public Page insert (String item) throws Exception {
        if (select(item) != null) throw new Exception(String.format("Key (%s) already in hash table.", item));
        Page newPage = lastPage.add(item);
        if (newPage == null) {
            newPage = lastPage;
        } else {
            lastPage = newPage;
        }
        hashTable.addKey(item, newPage);
        return newPage;
    }

    public void clear () {
        page.clear();
        lastPage = page;
        hashTable.clear();
    }

    int getPageIndex (Page p) throws Exception {
        if (p == null) throw new Exception("Cannot find index of null page.");
        int pageIndex = 0;
        Page pagePointer = page;
        while (p != pagePointer) {
            if (pagePointer == null) return -1;
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
