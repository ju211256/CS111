import java.util.Scanner;

public class CaesarCipher
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        System.out.println("What's the message?");
        String input = in.nextLine();
        System.out.println("What's the shift?");
        int shift = in.nextInt();

        String text = "";
        char x;

        for (int i = 0; i < input.length(); i++)
        {
            x = input.charAt(i);

            if(x >= 'a' && x <= 'z') 
            {
                 x = (char) (x + shift);

                    if (x < 'a')
                    {
                        x = (char) (x + 'z' - 'a' + 1);
                    }
                    if(x > 'z') 
                    {
                        x = (char) (x + 'a' - 'z' - 1);
                    }

                    text = text + x;
            }
            else if(x >= 'A' && x <= 'Z') 
            {
              x = (char) (x + shift);
 
                    if (x < 'A')
                    {
                        x = (char) (x + 'Z' - 'A' + 1);
                    }
                    if(x > 'Z') 
                    {
                        x = (char) (x + 'A' - 'Z' - 1);
                    }


              text = text + x;
            }
            else 
            {
              text = text + x;
            }
        }
        System.out.println(text);    
    }
}
