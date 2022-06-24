public class Sqrt 
{
    public static void main(String[] args)
    {
        double x = Double.parseDouble(args[0]);
        double y = x/2;
        double guess = y;

        do 
        {
            y = guess;
            guess = (y + (x/y))/2;
        } while ((Math.abs(y - guess)) > 1e-6);
        
        System.out.println(guess);
    }
}

