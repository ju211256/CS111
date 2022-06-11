public class ConvertSeconds {
    public static void main(String[] args) {
        //Declaring Var
        int input = Integer.parseInt(args[0]);

        //Seconds Calculation
        int years = input / 31536000;
        int days = (input % 31536000) / 86400;
        int hours = (input % 86400) / 3600;
        int minutes = (input % 3600) / 60;
        int seconds = input % 60;
        System.out.printf("%d years, %d days, %d hours, %d minutes, and %d seconds", years, days, hours, minutes, seconds);
    }
}