/**
 *
 * @author Linus
 */

import java.util.Scanner;

public class Q2_982689 {
    public static void main(String args[])
    {
        try
        {
            Scanner sc = new Scanner(System.in);
            String cryptPassword = sc.nextLine().trim();
            String pwdChars = sc.nextLine().trim();

            String C = cryptPassword.split("\\|\\|")[0];
            String BA = cryptPassword.split("\\|\\|")[1];    /* invalid input; The input after "||" must ATLEAST contain the first digit of the pattern; Will throw an exception if not present, which will be caught by "catch" clause*/
            String charsIP[] = pwdChars.split("");
            String posC[]= C.split("\\|");    /* will hold individual character positions */

            /* Piece of code entered to overcome Philacodist simulator error. The simulator adds a null character at the begining of the second line input */
            String chars[] = new String[charsIP.length-1];
            System.arraycopy(charsIP, 1, chars, 0, (charsIP.length-1));
            
            if(posC.length != 10 || chars.length != 10)    /* invalid input; The input pattern must have 10 divisions for numbers from 0 to 9; The distinct character list must contain 10 distinct characters */
            {
                System.out.println(-1);
                return;
            }
            
            for(int i=1; i<chars.length; i++)    /* Checking for duplicates in distinct character list */
            {
                for(int j=i-1; j>=0; j--)
                {
                    if(chars[j].equals(chars[i]))
                    {
                        System.out.print(-1);
                        return;
                    }
                }
            }
            
            int pwdLength=0;
            for(String s:posC)
            {
                pwdLength += s.length()-1;
            }

            int numPassword[] = new int[pwdLength];    /* numeric value of the password */
            for(int i=0; i<numPassword.length ; i++)    /* initializing array to -1 */
                numPassword[i] = -1;

            for(int i= 0; i<posC.length; i++)
            {
                int value = Integer.parseInt((posC[i].charAt(posC[i].length()-1))+"");    /* Storing the last digit in each group */
                for(int j=0; j<(posC[i].length()-1); j++)    /* j<(posC[i].length()-1) to ignore last digit in every group */
                {
                    int pos = Integer.parseInt(posC[i].charAt(j)+"");
                    if(numPassword[pos] != -1)    /* overlapping entry ; incorrect input */
                    {                        
                        System.out.print(-1);
                        return;
                    }
                    numPassword[pos] = value;
                }
            }
            
            for(int i=0; i<BA.length()-1; i++)    /* BA.length()-1 codition to ignore the last number in the sequence */
            {
                int pos = Integer.parseInt(BA.charAt(i)+"");
                numPassword[pos] += 10;
            }
            
            numPassword[(numPassword.length-1)] -= Integer.parseInt(BA.charAt((BA.length()-1))+"");
            for(int i=(numPassword.length-2); i>=0; i--)
            {
                numPassword[i] -= numPassword[i+1];
            }
            
            String password = "";
            for(int i=0; i<numPassword.length; i++)
            {
                password += chars[numPassword[i]];                
            }
            System.out.print(password);
        }
        catch(Exception e)
        {
            System.out.print(-1);
        }
    }    //main()
}    //class