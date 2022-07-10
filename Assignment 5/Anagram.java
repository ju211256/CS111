import java.util.Arrays;

public class Anagram
{
    public static void main(String[] args)
    {
        //Establish input
        String regular = args[0];
        String anagram = args[1];
        
        //Establish array based off of input
        char[] arrayRegular = regular.toCharArray();
        //Counter to verify if input has an anagram
        int[] matchCounter = { 0 };
    
        //Check if 1st rule of anagram (both inputs equal lengths apply)
        if (regular.length() == anagram.length())
        {
            //Nested loop, search array and increase counter if corresponding char is found
            for  (int i = 0; i < regular.length(); i++)
            {
                char x = anagram.charAt(i);

                for (char check : arrayRegular)
                {
                    if (check == x)
                    {
                        matchCounter[0]++;
                        break;
                    }
                }
            }
            if (matchCounter[0] == regular.length() && matchCounter[0] == anagram.length())
            {
                boolean output = true;
                System.out.println(output);
            }
            else
            {
                boolean output = false;
                System.out.println(output);
            }
        } 
        else
        {
            boolean output = false;
            System.out.println(output);
        }
    }
}