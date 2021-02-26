package controller;

import Interfaces.IhashFunction;

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

    public void addRow (String item) throws Exception {
        Page newPage = lastPage.add(item);
        if (newPage == null) newPage = lastPage;
        hashTable.addKey(item, newPage);
    }

    public boolean hasItem (String item) throws Exception {
        Page itemPage = hashTable.search(item);
        if (itemPage == null) return false;
        return itemPage.search(item, false);
    }
}
