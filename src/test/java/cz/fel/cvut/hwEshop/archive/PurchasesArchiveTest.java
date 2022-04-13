package cz.fel.cvut.hwEshop.archive;

import cz.fel.cvut.hwEshop.shop.Item;
import cz.fel.cvut.hwEshop.shop.Order;
import cz.fel.cvut.hwEshop.shop.ShoppingCart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;


class PurchasesArchiveTest
{
    PurchasesArchive purchasesArchive;
    ShoppingCart shoppingCart;
    ItemPurchaseArchiveEntry purchased1;
    ItemPurchaseArchiveEntry purchased2;
    ItemPurchaseArchiveEntry purchased3;
    Order order;
    ArrayList<Item> items;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUp()
    {
        items = new ArrayList();
        items.add( new Item(123, "Kozel Svetly", 13, "Alkohol"));
        items.add( new Item(124, "Kozel Cerny", 13, "Alkohol"));
        items.add( new Item(125, "Gambrinus", 17, "Alkohol"));

        shoppingCart = new ShoppingCart(items);
        order = new Order(shoppingCart, "Pavel Durov", "Ploshad Vostanya 1300/8, Dubai");

        purchased1 = new ItemPurchaseArchiveEntry(new Item(123, "Kozel Svetly", 13, "Alkohol"));
        purchased2 = new ItemPurchaseArchiveEntry(new Item(124, "Kozel Cerny", 13, "Alkohol"));
        purchased3 = new ItemPurchaseArchiveEntry(new Item(125, "Gambrinus", 17, "Alkohol"));

        HashMap<Integer, ItemPurchaseArchiveEntry> hashMap = new HashMap<>();
        hashMap.put(1, purchased1);
        hashMap.put(2, purchased2);
        hashMap.put(3, purchased3);
        purchasesArchive = new PurchasesArchive(hashMap, null);


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
    void testPrintItemPurchaseStatisticsOut()
    {
        String exeptedTitle = "ITEM PURCHASE STATISTICS:";

        purchasesArchive.printItemPurchaseStatistics();
        Assertions.assertEquals(exeptedTitle + System.lineSeparator() + purchased1.toString()
                + System.lineSeparator() + purchased2.toString()
                + System.lineSeparator() + purchased3.toString() + System.lineSeparator(),
                outContent.toString()
        );
    }

    @Test
    void testGetHowManyTimesHasBeenItemSold()
    {
        Item item1 = items.get(0);
        Item item2 = items.get(1);
        Item item3 = items.get(2);

        ItemPurchaseArchiveEntry firstItemMock = Mockito.mock(ItemPurchaseArchiveEntry.class);
        ItemPurchaseArchiveEntry secondItemMock = Mockito.mock(ItemPurchaseArchiveEntry.class);
        ItemPurchaseArchiveEntry thirdItemMock = Mockito.mock(ItemPurchaseArchiveEntry.class);

        Mockito.doReturn(0).when(firstItemMock).getCountHowManyTimesHasBeenSold();
        Mockito.doReturn(0).when(secondItemMock).getCountHowManyTimesHasBeenSold();
        Mockito.doReturn(0).when(thirdItemMock).getCountHowManyTimesHasBeenSold();

        int firstReturned = purchasesArchive.getHowManyTimesHasBeenItemSold(item1);
        int secondReturned= purchasesArchive.getHowManyTimesHasBeenItemSold(item2);
        int thirdReturned= purchasesArchive.getHowManyTimesHasBeenItemSold(item3);

        Assertions.assertEquals(0, firstReturned);
        Assertions.assertEquals(0, secondReturned);
        Assertions.assertEquals(0, thirdReturned);
    }

    @Test
    void testPutOrderToPurchasesArchive()
    {
        purchasesArchive = new PurchasesArchive();
        purchasesArchive.putOrderToPurchasesArchive(order);

        int firstSold = purchasesArchive.getHowManyTimesHasBeenItemSold(items.get(0));
        int secondSold = purchasesArchive.getHowManyTimesHasBeenItemSold(items.get(1));
        int thirdSold = purchasesArchive.getHowManyTimesHasBeenItemSold(items.get(2));

        Assertions.assertEquals(1, firstSold);
        Assertions.assertEquals(1, secondSold);
        Assertions.assertEquals(1, thirdSold);
    }



}