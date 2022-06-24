public class Quadratic
    {
        public static void main(String[] args)
            {
                // Declaring Common Var
                double a = Double.parseDouble(args[0]);
                double b = Double.parseDouble(args[1]);
                double c = Double.parseDouble(args[2]);
                double insideSqrt = (b * b) - (4 * a * c );


                if (insideSqrt < 0) 
                {
                    insideSqrt = Math.abs(insideSqrt);
                    double resolvedSqrt = Math.sqrt(insideSqrt);
                    double dividedSqrt = resolvedSqrt / (2 * a);
                    double root = (-b / (2 * a));

                    double intCheck1 = Math.rint(root);
                    double intCheck2 = Math.rint(dividedSqrt);


                    if (intCheck1 == root && intCheck2 == dividedSqrt)
                        {
                        int result = (int) root;
                        int result2 = (int) dividedSqrt;
                            if (result == 0 || result2 == 0)
                            {
                                System.out.println(result + result2 + "i" + ", " + "-" + result + result2 + "i");
                            } 
                            else if (result2 == 1)
                            {
                                System.out.println(result + " + " + "i" + ", " + result + " - " + "i");
                            }
                            else 
                            {
                                System.out.println(result + " + " + result2 + "i" + ", " + result + " - " + result2 + "i");
                            }
                        }
                    else if (intCheck1 == root) 
                        {
                        int result = (int) root;
                            if (result == 0)
                            {
                                System.out.println(dividedSqrt + "i" + ", " + "-" + dividedSqrt + "i");
                            }
                            else if (dividedSqrt == 1)
                            {
                                System.out.println(result + " + " + "i" + ", " + result + " - " + "i");
                            }
                            else 
                            {
                                System.out.println(result + " + " + dividedSqrt + "i" + ", " + result + " - " + dividedSqrt + "i");
                            }
                        }
                        else 
                        {
                            if (root == 0 || dividedSqrt == 0)
                            {
                                System.out.println(dividedSqrt + "i" + ", " + root + "-" + dividedSqrt + "i");
                            }
                            else if (dividedSqrt == 1)
                            {
                                System.out.println(root + " + " + "i" + ", " + root + " - " + "i");
                            }
                            else 
                            {
                                System.out.println(root + " + " + dividedSqrt + "i" + ", " + root + " - " + dividedSqrt + "i");
                            }
                        }

                } else 
                {
                    double resolvedSqrt = Math.sqrt(insideSqrt);
                    double dividedSqrt = resolvedSqrt / (2 * a);
                    double root = ((-1 * b) / (2 * a));

                    double finalCalculation1 = root + dividedSqrt;
                    double finalCalculation2 = root - dividedSqrt;

                    double intCheck1 = Math.rint(finalCalculation1);
                    double intCheck2 = Math.rint(finalCalculation2);

                    if ((intCheck1 == finalCalculation1) && (intCheck2 == finalCalculation2))
                    {
                        int result1 = (int) finalCalculation1;
                        int result2 = (int) finalCalculation2;
                        System.out.println(result1 + ", " + result2);
                    }
                    else
                    {
                        System.out.println(finalCalculation1 + ", " + finalCalculation2);
                    }
                }
                
            }
    }