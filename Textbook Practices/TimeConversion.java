import java.util.Scanner;

public class TimeConversion {
    public static void main(String[] args) {
        //Declaring Var
        Scanner in = new Scanner(System.in);
        int totalSeconds;
        int hours;
        int minutes;
        int seconds;

        //Seconds Input
        System.out.print("Enter the total number of seconds: ");
        totalSeconds = in.nextInt();

        hours = totalSeconds / 3600;
        minutes = (totalSeconds % 3600) / 60;
        seconds = totalSeconds % 60;
        System.out.printf("%d Seconds = %d Hours, %d Minutes, and %d Seconds.", totalSeconds, hours, minutes, seconds );
    }
}