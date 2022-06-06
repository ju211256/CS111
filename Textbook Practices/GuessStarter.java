import java.util.Random;
import java.util.Scanner;

public class GuessStarter {

    public static void main(String[] args) {
        // The setup
        Scanner in = new Scanner(System.in);
        Random random = new Random();
        int number = random.nextInt(100) + 1;
        int guess;
        int subtraction;
        
        //Dialogue
        System.out.println("I'm thinking of a whole number between 1 and 100 (Including both 1 and 100). ");
        System.out.println("Can you guess what it is?");

        //Guessing number input
        System.out.print("Type a whole number: ");
        guess = in.nextInt();

        //Calculating offset
        subtraction = guess - number;

        System.out.printf("Your guess is: %d %n", guess);
        System.out.printf("The whole number that I was thinking of is: %d %n", number);
        System.out.printf("You were off by: %d %n", subtraction);
    }
}