package controller;

import Enums.TableControllerMode;
import Interfaces.IhashFunction;
import ReturnTypes.*;

public class TableController {

    public HashTable hashTable;
    public Page page, lastPage;
    Integer pageAmount;
    Integer pageIndex = 0;
    public int accessCounter = 0;

    public TableController (IhashFunction function, int bucketCount, int bucketSize, TableControllerMode mode, int pageValue) throws Exception {
        hashTable = new HashTable(function, bucketSize, bucketCount);
        if (mode == TableControllerMode.FixedPageAmount) {
            this.pageAmount = pageValue;
        } else if (mode == TableControllerMode.FixedPageSize) {
            this.page = new Page(pageValue);
        } else {
            throw new Exception("Invalid mode.");
        }
        this.lastPage = this.page;
    }

    public TableControllerInsertReturn insert (String item) throws Exception {
        if (select(item) != null) throw new Exception(String.format("Key (%s) already in hash table.", item));
        if (pageAmount == null) {
            Page newPage = lastPage.add(item);
            if (newPage == null) {
                newPage = lastPage;
            } else {
                lastPage = newPage;
            }
            return new TableControllerInsertReturn(newPage, hashTable.addKey(item, newPage));
        } else {
            if (pageIndex > pageAmount) {
                pageIndex = 0;
            }
            Page page = getNthPage(pageIndex++);
            page = page.add(item);
            return new TableControllerInsertReturn(page, hashTable.addKey(item, page));
        }
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
        accessCounter += 1;
        int pageIndex = getPageIndex(r.foundPage);
        return new TableControllerSeachReturn(r.foundPage, r.foundBucket, r.verticalBucketIndex, r.horizontalBucketIndex, r.contentIndex, pageIndex, accessCounter);
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

    public Page getNthPage (int index) {
        int count = 0;
        Page pointer = page;
        while (pointer != null) {
            if (count++ == index) return pointer;
            pointer = pointer.next;
        }
        return null;
    }

    public Bucket getNthBucket (int vindex, int hindex) {
        int counter = 0;
        Bucket b = this.hashTable.buckets[vindex];
        while (b != null) {
            if (counter++ == hindex) {
                return b;
            }
            b = b.bucketOverflow;
        }
        return null;
    }

    public Bucket[] getNthBuckets (int hindex) {
        Bucket[] bs= new Bucket[this.hashTable.buckets.length];
        for (int i = 0; i < this.hashTable.buckets.length; i++) {
            bs[i] = getNthBucket(i, hindex);
        }
        return bs;
    }
}
