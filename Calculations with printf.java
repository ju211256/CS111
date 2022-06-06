public class Calculations {
    public static void main(String[] args) {  
        //Declaring Var
        int first = Integer.parseInt(args[0]);
        int second = Integer.parseInt(args[1]);

        //Declaring calculations
        int sum = (first + second);
        int difference = (first - second);
        int product = (first * second);
        double quotient = ((double)first / (double)second);
        int remainder = (first % second);
        double average = ((first + second)/2);

		System.out.printf("Sum: %d %n", sum);
        System.out.printf("Difference: %d %n", difference);
        System.out.printf("Product: %d %n", product);
        System.out.printf("Quotient: %.1f %n", quotient);
        System.out.printf("Remainder: %d %n", remainder);
        System.out.printf("Average: %.1f %n", average);
    }
}