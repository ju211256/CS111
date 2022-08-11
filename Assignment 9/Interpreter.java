import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;

class Interpreter {
    public static Map<String, Integer> nMap = new HashMap<>();
    public ArrayList<String[]> list = new ArrayList<>();

    public static int ra;
    public static int rb;
    public static int rc;
    public static int rd;

    public void readProgram(String filename)
    {
        // You can ignore the "try" and "catch" parts. These are just
        // to deal with I/O errors (e.g., file not found).
        try
        {
            Scanner fileIn = new Scanner(new File(filename));
            Scanner in = new Scanner(System.in);

            //Converts each line of program into an array of strings using spaces as a delimiter
            //Creates String arraylist based on the array of strings generated
            while (fileIn.hasNextLine())
            {
                String line = fileIn.nextLine();
                String[] input = line.split(" ");
                list.add(input);
            }
            fileIn.close();

            //Variable q keeps track of which specific line of the program is currently running by indexing Arrays inside of Arraylist "list"
            for (int q = 0 ; q < list.size(); q++)
            {
                //Parses first element of each array (line of program), matches it to the list of commands available, then executes if it matched.
                if (list.get(q)[0].contains("add"))
                {
                    int i = nMap.get(list.get(q)[1]) + nMap.get(list.get(q)[2]);

                    nMap.put(list.get(q)[3], i);
                }

                if (list.get(q)[0].contains("sub"))
                {
                    int i = nMap.get(list.get(q)[2]) - nMap.get(list.get(q)[1]);

                    nMap.put(list.get(q)[3], i);
                }

                if (list.get(q)[0].contains("mul"))
                {
                    int i = nMap.get(list.get(q)[1]) * nMap.get(list.get(q)[2]);

                    nMap.put(list.get(q)[3], i);
                }

                if (list.get(q)[0].contains("div"))
                {
                    int i = nMap.get(list.get(q)[1]) / nMap.get(list.get(q)[2]);

                    nMap.put(list.get(q)[3], i);
                }

                if (list.get(q)[0].contains("incr"))
                {
                    switch (list.get(q)[1]) {
                        case ("ra") -> nMap.put("ra", nMap.get("ra") + 1);
                        case ("rb") -> nMap.put("rb", nMap.get("rb") + 1);
                        case ("rc") -> nMap.put("rc", nMap.get("rc") + 1);
                        case ("rd") -> nMap.put("rd", nMap.get("rd") + 1);
                    }
                }

                if (list.get(q)[0].contains("decr"))
                {
                    switch (list.get(q)[1]) {
                        case ("ra") -> nMap.put("ra", nMap.get("ra") - 1);
                        case ("rb") -> nMap.put("rb", nMap.get("rb") - 1);
                        case ("rc") -> nMap.put("rc", nMap.get("rc") - 1);
                        case ("rd") -> nMap.put("rd", nMap.get("rd") - 1);
                    }
                }

                if (list.get(q)[0].contains("copy"))
                {
                    int i = nMap.get(list.get(q)[1]);
                    nMap.put(list.get(q)[2], i);
                }

                if (list.get(q)[0].contains("load"))
                {
                    switch (list.get(q)[2]) {
                        case ("ra") -> {
                            int i = Integer.parseInt(list.get(q)[1]);
                            nMap.put("ra", i);
                        }
                        case ("rb") -> {
                            int j = Integer.parseInt(list.get(q)[1]);
                            nMap.put("rb", j);
                        }
                        case ("rc") -> {
                            int k = Integer.parseInt(list.get(q)[1]);
                            nMap.put("rc", k);
                        }
                        case ("rd") -> {
                            int l = Integer.parseInt(list.get(q)[1]);
                            nMap.put("rd", l);
                        }
                    }
                }

                if (list.get(q)[0].contains("read"))
                {
                    switch (list.get(q)[1]) {
                        case ("ra") -> {
                            int i = in.nextInt();
                            nMap.put("ra", i);
                        }
                        case ("rb") -> {
                            int j = in.nextInt();
                            nMap.put("rb", j);
                        }
                        case ("rc") -> {
                            int k = in.nextInt();
                            nMap.put("rc", k);
                        }
                        case ("rd") -> {
                            int l = in.nextInt();
                            nMap.put("rd", l);
                        }
                    }
                }

                if (list.get(q)[0].contains("print"))
                {
                    switch (list.get(q)[1]) {
                        case ("ra") -> System.out.println(nMap.get("ra"));
                        case ("rb") -> System.out.println(nMap.get("rb"));
                        case ("rc") -> System.out.println(nMap.get("rc"));
                        case ("rd") -> System.out.println(nMap.get("rd"));
                    }
                }

                if (list.get(q)[0].contains("goto"))
                {
                    int b = Integer.parseInt(list.get(q)[1]);
                    q = b - 1;
                }

                if (list.get(q)[0].contains("if"))
                {
                    switch(list.get(q)[2])
                    {
                        case (">"):
                            if (nMap.containsKey(list.get(q)[1]) && (!nMap.containsKey(list.get(q)[3])))
                            {
                                if (nMap.get(list.get(q)[1]) > Integer.parseInt(list.get(q)[3]))
                                {
                                    int b = Integer.parseInt(list.get(q)[5]);
                                    q = b - 1;
                                }
                            }
                            else if (nMap.containsKey(list.get(q)[3]) && (!nMap.containsKey(list.get(q)[1])))
                            {
                                if (Integer.parseInt(list.get(q)[1]) > nMap.get(list.get(q)[3]))
                                {
                                    int b = Integer.parseInt(list.get(q)[5]);
                                    q = b - 1 - 1;
                                }
                            }
                            else if (nMap.containsKey(list.get(q)[1]) && nMap.containsKey(list.get(q)[3]))
                            {
                                if (nMap.get(list.get(q)[1]) > nMap.get(list.get(q)[3]))
                                {
                                    int b = Integer.parseInt(list.get(q)[5]);
                                    q = b - 1;
                                }
                            } else
                            {
                                if (Integer.parseInt(list.get(q)[1]) > Integer.parseInt(list.get(q)[3]))
                                {
                                    int b = Integer.parseInt(list.get(q)[5]);
                                    q = b - 1;
                                }
                            }
                            break;

                        case (">="):
                            if (nMap.containsKey(list.get(q)[1]) && (!nMap.containsKey(list.get(q)[3])))
                            {
                                if ((int)nMap.get(list.get(q)[1]) >= Integer.parseInt(list.get(q)[3]))
                                {
                                    int b = Integer.parseInt(list.get(q)[5]);
                                    q = b - 1;
                                }
                            }
                            else if (nMap.containsKey(list.get(q)[3]) && (!nMap.containsKey(list.get(q)[1])))
                            {
                                if (Integer.parseInt(list.get(q)[1]) >= (int)nMap.get(list.get(q)[3]))
                                {
                                    int b = Integer.parseInt(list.get(q)[5]);
                                    q = b - 1 - 1;
                                }
                            }
                            else if (nMap.containsKey(list.get(q)[1]) && nMap.containsKey(list.get(q)[3]))
                            {
                                if ((int)nMap.get(list.get(q)[1]) >= (int)nMap.get(list.get(q)[3]))
                                {
                                    int b = Integer.parseInt(list.get(q)[5]);
                                    q = b - 1;
                                }
                            } else
                            {
                                if (Integer.parseInt(list.get(q)[1]) >= Integer.parseInt(list.get(q)[3]))
                                {
                                    int b = Integer.parseInt(list.get(q)[5]);
                                    q = b - 1;
                                }
                            }
                            break;

                        case ("<"):
                            if (nMap.containsKey(list.get(q)[1]) && (!nMap.containsKey(list.get(q)[3])))
                            {
                                if (nMap.get(list.get(q)[1]) < Integer.parseInt(list.get(q)[3]))
                                {
                                    int b = Integer.parseInt(list.get(q)[5]);
                                    q = b - 1;
                                }
                            }
                            else if (nMap.containsKey(list.get(q)[3]) && (!nMap.containsKey(list.get(q)[3])))
                            {
                                if (Integer.parseInt(list.get(q)[1]) < nMap.get(list.get(q)[3]))
                                {
                                    int b = Integer.parseInt(list.get(q)[5]);
                                    q = b - 1;
                                }
                            }
                            else if (nMap.containsKey(list.get(q)[1]) && nMap.containsKey(list.get(q)[3]))
                            {
                                if (nMap.get(list.get(q)[1]) < nMap.get(list.get(q)[3]))
                                {
                                    int b = Integer.parseInt(list.get(q)[5]);
                                    q = b - 1;
                                }
                            } else
                            {
                                if (Integer.parseInt(list.get(q)[1]) < Integer.parseInt(list.get(q)[3]))
                                {
                                    int b = Integer.parseInt(list.get(q)[5]);
                                    q = b - 1;
                                }
                            }
                            break;

                        case ("<="):
                            if (nMap.containsKey(list.get(q)[1]) && (!nMap.containsKey(list.get(q)[3])))
                            {
                                if ((int)nMap.get(list.get(q)[1]) <= Integer.parseInt(list.get(q)[3]))
                                {
                                    int b = Integer.parseInt(list.get(q)[5]);
                                    q = b - 1;
                                }
                            }
                            else if (nMap.containsKey(list.get(q)[3]) && (!nMap.containsKey(list.get(q)[3])))
                            {
                                if (Integer.parseInt(list.get(q)[1]) <= (int)nMap.get(list.get(q)[3]))
                                {
                                    int b = Integer.parseInt(list.get(q)[5]);
                                    q = b - 1;
                                }
                            }
                            else if (nMap.containsKey(list.get(q)[1]) && nMap.containsKey(list.get(q)[3]))
                            {
                                if ((int)nMap.get(list.get(q)[1]) <= (int)nMap.get(list.get(q)[3]))
                                {
                                    int b = Integer.parseInt(list.get(q)[5]);
                                    q = b - 1;
                                }
                            } else
                            {
                                if (Integer.parseInt(list.get(q)[1]) <= Integer.parseInt(list.get(q)[3]))
                                {
                                    int b = Integer.parseInt(list.get(q)[5]);
                                    q = b - 1;
                                }
                            }
                            break;

                        case ("=="):
                            if (nMap.containsKey(list.get(q)[1]) && (!nMap.containsKey(list.get(q)[3])))
                            {
                                if ((int)nMap.get(list.get(q)[1]) == Integer.parseInt(list.get(q)[3]))
                                {
                                    int b = Integer.parseInt(list.get(q)[5]);
                                    q = b - 1;
                                }
                            }
                            else if (nMap.containsKey(list.get(q)[3]) && (!nMap.containsKey(list.get(q)[3])))
                            {
                                if (Integer.parseInt(list.get(q)[1]) == (int)nMap.get(list.get(q)[3]))
                                {
                                    int b = Integer.parseInt(list.get(q)[5]);
                                    q = b - 1;
                                }
                            }
                            else if (nMap.containsKey(list.get(q)[1]) && nMap.containsKey(list.get(q)[3]))
                            {
                                if ((int)nMap.get(list.get(q)[1]) == (int)nMap.get(list.get(q)[3]))
                                {
                                    int b = Integer.parseInt(list.get(q)[5]);
                                    q = b - 1;
                                }
                            } else
                            {
                                if (Integer.parseInt(list.get(q)[1]) == Integer.parseInt(list.get(q)[3]))
                                {
                                    int b = Integer.parseInt(list.get(q)[5]);
                                    q = b - 1;
                                }
                            }
                            break;

                        case ("!="):
                            if (nMap.containsKey(list.get(q)[1]) && (!nMap.containsKey(list.get(q)[3])))
                            {
                                if ((int)nMap.get(list.get(q)[1]) != Integer.parseInt(list.get(q)[3]))
                                {
                                    int b = Integer.parseInt(list.get(q)[5]);
                                    q = b - 1;
                                }
                            }
                            else if (nMap.containsKey(list.get(q)[3]) && (!nMap.containsKey(list.get(q)[3])))
                            {
                                if (Integer.parseInt(list.get(q)[1]) != (int)nMap.get(list.get(q)[3]))
                                {
                                    int b = Integer.parseInt(list.get(q)[5]);
                                    q = b - 1;
                                }
                            }
                            else if (nMap.containsKey(list.get(q)[1]) && nMap.containsKey(list.get(q)[3]))
                            {
                                if ((int)nMap.get(list.get(q)[1]) != (int)nMap.get(list.get(q)[3]))
                                {
                                    int b = Integer.parseInt(list.get(q)[5]);
                                    q = b - 1;
                                }
                            } else
                            {
                                if (Integer.parseInt(list.get(q)[1]) != Integer.parseInt(list.get(q)[3]))
                                {
                                    int b = Integer.parseInt(list.get(q)[5]);
                                    q = b - 1;
                                }
                            }
                            break;
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        if (args.length != 1) {
            System.out.println("Usage: java Interpreter <filename>");
            return;
        }
        nMap.put("ra", ra);
        nMap.put("rb", rb);
        nMap.put("rc", rc);
        nMap.put("rd", rd);

        Interpreter interpreter = new Interpreter();
        interpreter.readProgram(args[0]);
    }
}
