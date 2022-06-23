public class Factor
    {
        public static void main(String[] args)
            {
                // Declaring Var
                int input = Integer.parseInt(args[0]);
                System.out.print(input + ": ");
                
                //Looping for Prime Factorization
                for (int start = 2; start <= input; start++) 
                {
                     while(input % start == 0) 
                    {
                        System.out.print(start + " ");
                        input = (input / start);
                    }
                }
                System.out.print("\n");
            }
        }
