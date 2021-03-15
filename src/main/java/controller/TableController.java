package controller;

import Interfaces.IhashFunction;
import ReturnTypes.*;

public class TableController {

    public HashTable hashTable;
    public Page page, lastPage;
    int pageSize;
    public int countAcess = 0;

    public TableController (IhashFunction function, int bucketCount, int bucketSize, int pageSize) {
        hashTable = new HashTable(function, bucketSize, bucketCount);
        this.pageSize = pageSize;
        this.page = new Page(pageSize);
        this.lastPage = this.page;
    }

    public TableControllerInsertReturn insert (String item) throws Exception {
        if (select(item) != null) throw new Exception(String.format("Key (%s) already in hash table.", item));
        System.out.println(item);
        Page newPage = lastPage.add(item);
        if (newPage == null) {
            newPage = lastPage;
        } else {
            lastPage = newPage;
        }
        return new TableControllerInsertReturn(newPage, hashTable.addKey(item, newPage));
    }

    public int pageCount () {
		int count = 0;
		Page pointer = page;
		while (pointer != null) {
            pointer = pointer.next;
		}
		return count;
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
        int count = 0;
    	HashTableSeachReturn r = hashTable.search(item);
        if (r == null) return null;
        countAcess += 1;
        int pageIndex = getPageIndex(r.foundPage);
        return new TableControllerSeachReturn(r.foundPage, r.foundBucket, r.verticalBucketIndex, r.horizontalBucketIndex, r.contentIndex, pageIndex, countAcess);
    }
    
    public int pageCount () {
        int count = 0;
        Page pointer = page;
        while (pointer != null) {
            pointer = pointer.next;
            count++;
        }
        return count;
    }
}
