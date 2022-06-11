public class ConvertSeconds {
    public static void main(String[] args) {
        //Declaring Var
        int input = Integer.parseInt(args[0]);

        //Seconds Calculations
        int years = input / 31536000;
        //1 Year = 31,536,000 Seconds
        int days = (input % 31536000) / 86400;
        //1 Day = 86,400 Seconds
        int hours = (input % 86400) / 3600;
        //1 Hour = 3,600 Seconds
        int minutes = (input % 3600) / 60;
        //1 Minute = 60 Seconds
        int seconds = input % 60;
        System.out.printf("%d years, %d days, %d hours, %d minutes, and %d seconds", years, days, hours, minutes, seconds);
    }
}
