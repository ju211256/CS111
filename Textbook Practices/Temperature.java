import java.util.Scanner;

public class Temperature {
    public static void main(String[] args) {
        //Declaring Var
        String line;
        Scanner in = new Scanner(System.in);
        double celcius;

        //Celcius Input
        System.out.print("Enter a temperature in Celcius: ");
        celcius = in.nextDouble();

        //Conversion factor C to F, F = C * 1.8 + 32.0
        final double farenheit = celcius * 1.8 + 32.0;
        System.out.printf("%.2f Celcius = %.2f Farenheit", celcius, farenheit);
    }
}