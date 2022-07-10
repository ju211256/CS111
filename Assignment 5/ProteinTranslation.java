import java.util.*;
import java.util.Arrays;

public class ProteinTranslation 
{
    public static void translator(String endcutRNA)
    {
        //Splices the remaining nucleotides into triplets (Start/End codons have already been taken out)
        StringBuilder tripletRNA = new StringBuilder();
        for (int x = 1; x <= endcutRNA.length(); x++) 
        {
            tripletRNA.append(endcutRNA.charAt(x - 1));
            if (x % 3 == 0)
            {
                tripletRNA.append(" ");
            }
        }
        String middleCodons = tripletRNA.toString();

        //Turns RNA nucleotide triplet String into an Array (Each triplet assigned to a new element)  
        String[] arrayCodons = middleCodons.split(" ", 0);
        
        //Establish new ArrayList
        ArrayList<String> product = new ArrayList<String>();

        //Translate each nucleotide triplets into a valid codon, add to ArrayList
        for (int i = 0; i < arrayCodons.length; i++)
        {
            if (arrayCodons[i].equals("GCA") || arrayCodons[i].equals("GCC") || arrayCodons[i].equals("GCG") || arrayCodons[i].equals("GCU"))
            {
                product.add("Ala");
            }
            else if (arrayCodons[i].equals("CGA") || arrayCodons[i].equals("CGC") || arrayCodons[i].equals("CGG") || arrayCodons[i].equals("CGU") || arrayCodons[i].equals("AGA") || arrayCodons[i].equals("AGG"))
            {
                product.add("Arg");
            }
            else if (arrayCodons[i].equals("AAU") || arrayCodons[i].equals("AAC"))
            {
                product.add("Asn");
            }
            else if (arrayCodons[i].equals("GAU") || arrayCodons[i].equals("GAC"))
            {
                product.add("Asp");
            }
            else if (arrayCodons[i].equals("UGU") || arrayCodons[i].equals("UGC"))
            {
                product.add("Cys");
            }
            else if (arrayCodons[i].equals("CAA") || arrayCodons[i].equals("CAG"))
            {
                product.add("Gln");
            }
            else if (arrayCodons[i].equals("GAA") || arrayCodons[i].equals("GAG"))
            {
                product.add("Glu");
            }
            else if (arrayCodons[i].equals("GGA") || arrayCodons[i].equals("GGC") || arrayCodons[i].equals("GGG") || arrayCodons[i].equals("GGU"))
            {
                product.add("Gly");
            }
            else if (arrayCodons[i].equals("CAU") || arrayCodons[i].equals("CAC"))
            {
                product.add("His");
            }
            else if (arrayCodons[i].equals("AUU") || arrayCodons[i].equals("AUC") || arrayCodons[i].equals("AUA"))
            {
                product.add("Ile");
            }
            else if (arrayCodons[i].equals("CUA") || arrayCodons[i].equals("CUC") || arrayCodons[i].equals("CUG") || arrayCodons[i].equals("CUU") || arrayCodons[i].equals("UAA") || arrayCodons[i].equals("UUG"))
            {
                product.add("Leu");
            }
            else if (arrayCodons[i].equals("AAA") || arrayCodons[i].equals("AAG"))
            {
                product.add("Lys");
            }
            else if (arrayCodons[i].equals("UUU") || arrayCodons[i].equals("UUC"))
            {
                product.add("Phe");
            }
            else if (arrayCodons[i].equals("CCA") || arrayCodons[i].equals("CCC") || arrayCodons[i].equals("CCG") || arrayCodons[i].equals("CCU"))
            {
                product.add("Pro");
            }
            else if (arrayCodons[i].equals("UCA") || arrayCodons[i].equals("UCC") || arrayCodons[i].equals("UCG") || arrayCodons[i].equals("UCU") || arrayCodons[i].equals("AGU") || arrayCodons[i].equals("AGC"))
            {
                product.add("Ser");
            }
            else if (arrayCodons[i].equals("ACA") || arrayCodons[i].equals("ACC") || arrayCodons[i].equals("ACG") || arrayCodons[i].equals("ACU"))
            {
                product.add("Thr");
            }
            else if (arrayCodons[i].equals("UGG"))
            {
                product.add("Trp");
            }
            else if (arrayCodons[i].equals("UAU") || arrayCodons[i].equals("UAC"))
            {
                product.add("Tyr");
            }
            else if (arrayCodons[i].equals("GUA") || arrayCodons[i].equals("GUC") || arrayCodons[i].equals("GUG") || arrayCodons[i].equals("GUU"))
            {
                product.add("Val");
            }
        }
        //Print Met, to account for runChecker(); being valid (Passed both start/end codon check)
        System.out.print("Met" + " ");

        //Print the rest of translated codons
        for(String output : product)
        {
            System.out.print(output + " ");
        }
        System.out.println();
    } 

    public static void runChecker(String stringRNA)
    {
        //Start codon check, check for the first AUG in string
        if (stringRNA.indexOf("AUG") != -1)
            {
            String[] startRNA = stringRNA.split("AUG", 2);
            String startcutRNA = new String(startRNA[1]);
                //End codon check, check for the first stop codon in string
                if (startcutRNA.indexOf("UAA") != -1)
                {
                    String[] endRNA = startcutRNA.split("UAA", 2);
                    String endcutRNA = new String(endRNA[0]);
                    translator(endcutRNA);
                } else if (startcutRNA.indexOf("UGA") != -1)
                {
                    String[] endRNA = startcutRNA.split("UGA", 2);
                    String endcutRNA = new String(endRNA[0]);
                    translator(endcutRNA);
                } else if (startcutRNA.indexOf("UAG") != -1)
                {
                    String[] endRNA = startcutRNA.split("UGA", 2);
                    String endcutRNA = new String(endRNA[0]);
                    translator(endcutRNA);
                } else
                {
                    System.out.println("No end codons found. This is not a valid input!");
                }
            } else
            {
                System.out.println("No start codon found. This is not a valid input!");
            }
    }
    public static void main(String[] args)
    {
        //Establish Input
        String input = args[0];

        //Turn input into char array
        char[] arrayDNA = input.toCharArray();

        //Transcription, pair DNA bases with matching RNA bases
        for (int i = 0; i < arrayDNA.length; i++)
        {
            if (arrayDNA[i] == 'A')
            {
                arrayDNA[i] = 'U';
            } else if (arrayDNA[i] == 'C')
            {
                arrayDNA[i] = 'G';
            } else if (arrayDNA[i] == 'G')
            {
                arrayDNA[i] = 'C';
            } else if (arrayDNA[i] == 'T')
            {
                arrayDNA[i] = 'A';
            } else
            {
                arrayDNA[i] = arrayDNA[i];
                System.out.println("False nucleotide detected. This is not a valid input!");
            }
        }
        //Converting RNA char array to String
        String stringRNA = new String(arrayDNA);
        runChecker(stringRNA);
    }
}