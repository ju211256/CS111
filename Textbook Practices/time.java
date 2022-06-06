class Time {
    public static void main(String[] args) {
        //defining vars
        int hour = 23;
        int minute = 30;
        int second = 30;
        int secondsPass = ((hour*60*60)+(minute*60)+second);
        int secondsRemain = (86400 - secondsPass);
        double percentage = ((secondsPass/86400.0)*100);
        //arithmatic calculations everything to seconds
        System.out.print("Number of seconds since midnight: ");
        System.out.println(secondsPass);
        System.out.print("Number of seconds remaining in the day: ");
        System.out.println(secondsRemain);
        System.out.print("Percentage of the day that has passed: ");
        System.out.println(percentage);
    }
}