package lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    void SumTest() {
        Calculator cl = new Calculator();
        int expected = 9;
        int received = cl.add(4, 5);
        assertEquals(expected, received);
    }

    @Test
    void SubstractTest() {
        Calculator cl = new Calculator();
        int expected = 1;
        int received = cl.subtract(5, 4);
        assertEquals(expected, received);
    }

    @Test
    void MultiplyTest() {
        Calculator cl = new Calculator();
        int expected = 20;
        int received = cl.multiply(4, 5);
        assertEquals(expected, received);
    }

    @Test
    void DivideTest() {
        Calculator cl = new Calculator();
        int expected = 0;
        int received = cl.divide(5, 0);
        assertEquals(expected, received);
    }
}