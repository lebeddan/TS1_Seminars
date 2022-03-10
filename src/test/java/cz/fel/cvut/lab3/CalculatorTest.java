package cz.fel.cvut.lab3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest(name = "{0} plus {1} should be equal to {2}")
    @CsvSource({"1, 2, 3", "2, 3, 5"})
    public void add_addsAandB_returnsC(int a, int b, int c) {
// arrange
        Calculator calc = new Calculator();
        int expectedResult = c;
// act
        int result = calc.add(a, b);
// assert
        assertEquals(expectedResult, result);
    }
}
