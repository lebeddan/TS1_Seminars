package cz.fel.cvut.hwEshop.storage;

import cz.fel.cvut.hwEshop.shop.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ItemStockTest {

    ItemStock itemStock;

    @BeforeEach
    void setUp()
    {
        Item item = new Item(123, "Kozel Svetly", 13, "Alkohol");
        itemStock = new ItemStock(item);
    }

    @Test
    void NullItemInConstructor()
    {
        Item testItem = null;
        ItemStock itemStockTest = new ItemStock(testItem);
        assertNotNull(itemStockTest);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 123, 13, 0, 3, 13})
    void testIncraseMethod(int n)
    {
        int oldCount = itemStock.getCount();
        itemStock.IncreaseItemCount(n);
        assertNotEquals(oldCount, itemStock.getCount());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 123, 13, 0, 3, 13})
    void testDecraseMethod(int n)
    {
        int oldCount = itemStock.getCount();
        itemStock.decreaseItemCount(n);
        assertNotEquals(oldCount, itemStock.getCount());
    }
}