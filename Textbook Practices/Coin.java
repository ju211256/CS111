import java.util.Scanner;

public class Coin 
{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
    
        System.out.print("Enter number of grades: " );
        int n = in.nextInt();
    
        int sum = 0;
    
        for (int i = 0; i < n; i++) {
            System.out.print("Enter grade "+ (i + 1) + ": ");
            int a = in.nextInt();
            sum += a;
        }
    
        double average = sum / n;
    
        System.out.println("Average value of array elements is : " + average);
    }
}