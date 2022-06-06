public class FinalExam {
    public static void main (String[] args) {
        //Declaring inputs
        double assignment = Double.parseDouble(args[0]);
        double exam1 = Double.parseDouble(args[1]);
        double exam2 = Double.parseDouble(args[2]);
        double recitation = Double.parseDouble(args[3]);

        //Declaring calculations to find out what grade you need for final exam to get at least an A
        double examf = (90 - ((0.5*assignment) + (0.15*exam1) + (0.15*exam2) + (0.05*recitation)))/0.15;

        //Print calculation results for final exam grade
        System.out.println(examf);
    }
}