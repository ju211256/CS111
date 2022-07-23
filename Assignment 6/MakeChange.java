import java.util.*;
import java.io.*;

public class MakeChange
{
    public static int sum;
    public static int arr[] = new int[4];
    public static int coins[] = {1, 5, 10, 25};
    public static int count = 0;

    public static void calculations(int sum, int[] coins, int[] arr)
    {
        if (sum == 0)
        {
            System.out.println(arr[3] + " quarters, " + arr[2] + " dimes, " + arr[1] + " nickels, " + arr[0] + " pennies");
            count++;
        }
        else
        {
            for (int i = 3; i >= 0; i--)
            {
                if(sum - coins[i] >= 0)
                {
                    arr[i] += 1;
                    calculations(sum - coins[i], coins, arr);
                    arr[i] -= 1;
                }
            }
        }
    }
    
    public static void main(String[] args)
    {
        sum = Integer.parseInt(args[0]);

        //Declaring Printstream to hold System.out
        ByteArrayOutputStream keyHolder = new ByteArrayOutputStream();
        PrintStream catcher = new PrintStream(keyHolder);

        //Back up the old System.out
        PrintStream backup = System.out;

        //Use Printstream instead
        System.setOut(catcher);

        //Make Printstream hold bunch of lines
        calculations(sum, coins, arr);

        //Recall old System.out
        System.out.flush();
        System.setOut(backup);

        System.out.println(count + " ways:");

        //Release held lines from Printstream
        System.out.print(keyHolder.toString());
    }    
}


    

