package cz.fel.cvut.hwEshop.shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest
{
    Order order;

    @BeforeEach
    void setUp()
    {
        ShoppingCart shoppingCart = new ShoppingCart();
        order = new Order(shoppingCart, "Pavel Durov",
                "Ploshad Vostanya 1300/8, Dubai",
                123
        );
    }

    @Test
    void NullDataInConstructorWithState()
    {
        Order newOrder = new Order(null, null, null, 123);
        Assertions.assertNotNull(newOrder);
    }

    @Test
    void NullDataInConstructorWithoutState()
    {
        Order newOrder = new Order(null, null, null);
        Assertions.assertNotNull(newOrder);
    }
}