public class Calculations {
    public static void main(String[] args) {  
        //Declaring inputs
        int first = Integer.parseInt(args[0]);
        int second = Integer.parseInt(args[1]);

        //Declaring calculations
        int sum = (first + second);
        int difference = (first - second);
        int product = (first * second);
        double quotient = ((double)first / (double)second);
        int remainder = (first % second);
        double average = ((first + second)/2);

        //Print results
		System.out.println("Sum: " + sum);
        System.out.println("Difference: " + difference);
        System.out.println("Product: " + product);
        System.out.println("Quotient: " + quotient);
        System.out.println("Remainder: " + remainder);
        System.out.println("Average: " + average);
    }
}
