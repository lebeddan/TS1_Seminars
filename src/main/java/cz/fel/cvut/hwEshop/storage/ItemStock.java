package cz.fel.cvut.hwEshop.storage;

import cz.fel.cvut.hwEshop.shop.Item;


/**
 * Auxiliary class for item storage
 */
public class ItemStock {
    private Item refItem;
    private int count;
    
    public ItemStock(Item refItem) {
        this.refItem = refItem;
        count = 0;
    }
    
    @Override
    public String toString() {
        return "STOCK OF ITEM:  "+refItem.toString()+"    PIECES IN STORAGE: "+count;
    }
    
    void IncreaseItemCount(int x) {
        count += x;
    }
    
    void decreaseItemCount(int x) {
        count -= x;
    }
    
    int getCount() {
        return count;
    }
    
    Item getItem() {
        return refItem;
    }
}