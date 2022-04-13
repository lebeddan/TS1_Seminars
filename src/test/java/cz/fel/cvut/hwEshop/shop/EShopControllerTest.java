package cz.fel.cvut.hwEshop.shop;

import cz.fel.cvut.hwEshop.archive.ItemPurchaseArchiveEntry;
import cz.fel.cvut.hwEshop.archive.PurchasesArchive;
import cz.fel.cvut.hwEshop.storage.ItemStock;
import cz.fel.cvut.hwEshop.storage.NoItemInStorage;
import cz.fel.cvut.hwEshop.storage.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

// NOT IMPLEMENTED YET
class EShopControllerTest
{

    EShopController eShopController;
    PurchasesArchive archive;
    Storage storage;

    ItemPurchaseArchiveEntry purchased1;
    ItemPurchaseArchiveEntry purchased2;
    ItemPurchaseArchiveEntry purchased3;

    Item[] storageItems;
    ArrayList<ItemStock> itemStocks;
    HashMap<Integer, ItemStock> stock;


    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUp() throws NoItemInStorage
    {
        eShopController = new EShopController();
        itemStocks = new ArrayList();
        stock = new HashMap<>();
        storageItems = new Item[]{
                new StandardItem(1, "Dancing Panda v.2", 5000, "GADGETS", 5),
                new StandardItem(2, "Dancing Panda v.3 with USB port", 6000, "GADGETS", 10),
                new StandardItem(3, "Screwdriver", 200, "TOOLS", 5),
                new DiscountedItem(4, "Star Wars Jedi buzzer", 500, "GADGETS", 30, "1.8.2013", "1.12.2013"),
                new DiscountedItem(5, "Angry bird cup", 300, "GADGETS", 20, "1.9.2013", "1.12.2013"),
                new DiscountedItem(6, "Soft toy Angry bird (size 40cm)", 800, "GADGETS", 10, "1.8.2013", "1.12.2013")
        };

        for (Item i : storageItems)
        {
           itemStocks.add(new ItemStock(i));
        }

        int j = 0;
        for (int i = 1; i <= storageItems.length; i++)
        {
            stock.put(i, itemStocks.get(j++));
        }

        storage = new Storage(stock);

        int[] itemCount = {10,10,4,5,10,2};

        for (int i = 0; i < storageItems.length; i++)
        {
            storage.insertItems(storageItems[i], itemCount[i]);
        }

        purchased1 = new ItemPurchaseArchiveEntry(new Item(1, "Dancing Panda v.2", 5000, "GADGETS"));
        purchased2 = new ItemPurchaseArchiveEntry(new Item(2, "Dancing Panda v.3 with USB port", 6000, "GADGETS"));
        purchased3 = new ItemPurchaseArchiveEntry(new Item(3, "Screwdriver", 200, "TOOLS"));

        HashMap<Integer, ItemPurchaseArchiveEntry> hashMap = new HashMap<>();
        hashMap.put(1, purchased1);
        hashMap.put(2, purchased2);
        hashMap.put(3, purchased3);
        archive = new PurchasesArchive(hashMap, null);

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams()
    {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }


    @Test
    void testPrintListOfStoredItems()
    {
        String exeptedTitle = "STORAGE IS CURRENTLY CONTAINING:";

        storage.printListOfStoredItems();
                Assertions.assertEquals(exeptedTitle + System.lineSeparator() +
                        stock.get(1).toString() + System.lineSeparator() +
                        stock.get(2).toString() + System.lineSeparator() +
                        stock.get(3).toString() + System.lineSeparator() +
                        stock.get(4).toString() + System.lineSeparator() +
                        stock.get(5).toString() + System.lineSeparator() +
                        stock.get(6).toString() + System.lineSeparator(),
                        outContent.toString()
        );
    }

    @Test
    void testPrintItemPurchaseStatistics() throws NoItemInStorage {
        ShoppingCart newCart = new ShoppingCart();
        newCart.addItem(storageItems[0]);
        newCart.addItem(storageItems[1]);
        newCart.addItem(storageItems[2]);
        newCart.addItem(storageItems[4]);
        newCart.addItem(storageItems[5]);
        String[] args = null;
        eShopController.main(args);

        String exeptedTitle = "ITEM PURCHASE STATISTICS:";

        eShopController.purchaseShoppingCart(newCart, "Libuse Novakova","Kosmonautu 25, Praha 8");

    }
}