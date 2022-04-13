package cz.fel.cvut.hwEshop.shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class StandardItemTest
{
    StandardItem standardItem;
    StandardItem standardItemNotValid;

    @BeforeEach
    void setUp()
    {
        standardItem = new StandardItem(
                30123123, "Kozel Svetly",
                13, "Alkohol",
                228
        );

        standardItemNotValid = new StandardItem(
                30122123, "Kozel Svetly",
                13, "Alkohol",
                228
        );
    }

    @Test
    void copyTest()
    {
//        Assertions.assertEquals(standardItemNotValid, standardItem.copy());
        Assertions.assertEquals(standardItem, standardItem.copy());
    }

    @ParameterizedTest
    @MethodSource("generateStandardItem")
    void isEqual_ShouldReturnTrueForSameStandardItemObjects(Object object)
    {
        Assertions.assertTrue(standardItem.equals(object));
    }

    private static Stream<Object> generateStandardItem()
    {
       StandardItem testData1 =  new StandardItem(30123123, "Kozel Svetly",
                13, "Alkohol",
                228);
       DiscountedItem testData2 = new DiscountedItem(123123, "asdad", 21312,
               "as", 1121, "21.04.2233", "21.04.2233");
       StandardItem testData3 =  new StandardItem(30123123, "Kozel Svetly",
               13, "Alkohol",
               228);
       StandardItem testData4 =  new StandardItem(30123123, "Kozel Svetly",
                13, "Alkohol",
                212);
       String testData5 = new String();


        return Stream.of(testData1, testData2, testData3, testData4, testData5);
    }

}