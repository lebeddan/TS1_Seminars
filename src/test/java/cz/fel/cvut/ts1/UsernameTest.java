package cz.fel.cvut.ts1;

import org.junit.Test;

import static org.junit.Assert.*;

public class UsernameTest
{
    @Test
    public void FactorialTest()
    {
        Username test1 = new Username();
        long a = 6;
        long b = test1.factorial(3);
        assertEquals(a, b);
    }

    @Test
    public void NullCheck()
    {
        Username test2 = new Username();
        long a = 1;
        long b = test2.factorial(0);
        assertEquals(a, b);
    }
}