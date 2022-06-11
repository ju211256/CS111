import java.util.Random;
import java.util.Scanner;

public class GuessRandom {

    public static void main(String[] args) {
        // The setup
        Scanner in = new Scanner(System.in);
        Random random = new Random();
        int number = random.nextInt(10) + 1;
        int guess;
        int subtraction;
        
        //Dialogue
        System.out.println("I'm thinking of a number from 1 to 10.");

        //Guessing number input
        System.out.print("Your guess?: ");
        guess = in.nextInt();

        //Calculating offset
        subtraction = Math.abs(guess - number);

        System.out.printf("It was %d. You were off by %d.%n", number, subtraction);
    }
}
