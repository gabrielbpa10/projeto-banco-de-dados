package ReturnTypes;

import controller.Page;

public class TableControllerInsertReturn {

    public Page pointer;
    public int bucketIndex;

    public TableControllerInsertReturn (Page p, int bi) {
        pointer = p;
        bucketIndex = bi;
    }
}
