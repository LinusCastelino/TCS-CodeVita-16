/**
 *
 * @author Linus
 */

import java.util.Scanner;

public class Q5_982689 {

    public static void main(String arg[]) {
        try {
            Scanner sc = new Scanner(System.in);

            int noOfDigits = sc.nextInt();
            int limit = ( noOfDigits * ((int)Math.pow(10, noOfDigits - 1))) - 1;    /* Creating a limit factor to increase efficiency. This will reduce the number of steps than computing it 99999..noOfdigit times*/

            String number[];
            boolean foundSolution = false;    /* boolean flag to print -1 if no solution found */

            for (int i = 0; i <= limit; i++) {
                /* Reducing the number space to reduce number of complex iterations and increase efficient search */
                if (check(i, noOfDigits)) {
                    number = convertInttoStringArray(i, noOfDigits);
                    boolean flag = true;    /* flag to indicate if it is a solution */

                    for (int j = 0; j < noOfDigits; j++) {
                        int y = 0;    /* Counter for intended number of occurrences of the place value */

                        for (int k = 0; k < noOfDigits; k++) {
                            if (Integer.parseInt(number[k]) == j) {
                                y++;
                            }
                        }
                        if (Integer.parseInt(number[j]) != y) {
                            flag = false;
                            break;
                        }
                    }

                    if (flag) {
                        foundSolution = true;
                        int solution = convertArrayToInt(number);
                        System.out.println(solution);
                    }
                }
            }
            if (!foundSolution) {
                System.out.println(-1);
                return;
            }
        } catch (Exception e) {
            System.out.print(-1);
        }
    }    //main()

    public static int convertArrayToInt(String number[]) {
        String numberStr = "";
        for (int i = 0; i < number.length; i++) {
            numberStr += number[i];
        }
        return Integer.parseInt(numberStr);

    }    //convertArrayToInt()

    public static String[] convertInttoStringArray(int number, int noOfDigits) {
        String numberStr = "";
        while (number != 0) {
            numberStr += "" + number % 10;
            number /= 10;
        }

        String numberArray[] = new String[noOfDigits];
        /* Initializing the array to zeros */
        for (int i = 0; i < numberArray.length; i++) {
            numberArray[i] = "0";
        }

        for (int i = numberArray.length - 1, j = 0; j < numberStr.length(); j++, i--) {
            numberArray[i] = "" + numberStr.charAt(j);
        }

        return numberArray;
    }    //convertInttoStringArray()

    public static boolean check(int i, int noOfDigits) {
        int sumOfDigits = 0;
        /* Reducing the search space to increase efficiency */ 
        while(i != 0) {   
            int placeValue = i%10;
            if (placeValue >= noOfDigits) {
                return false;
            }
            sumOfDigits += placeValue;
            i /= 10;
        }
        if(sumOfDigits != noOfDigits)
            return false;
        
        return true;
    }    //check()
}    //class