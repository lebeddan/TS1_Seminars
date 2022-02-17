package cz.fel.cvut.ts1;

public class Username
{
    public long factorial(int n)
    {
        long ret = 1;
        int i = 1;
        while (i <= n)
        {
            ret *= i;
            i++;
        }
        return ret;
    }
}
