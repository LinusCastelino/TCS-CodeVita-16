/**
 *
 * @author Linus
 */

import java.util.Scanner;

public class Q4_982689{
    
    public static void main(String arg[])
    {        
        try
        {
            Scanner sc = new Scanner(System.in);
            int m = sc.nextInt();
            int n = sc.nextInt();
            int map[][] = new int[m][n];
            
            int x = sc.nextInt();
            for(int i=0 ; i<x ; i++)    /* Marking the cells that cannot be visited */
            {
                int xm = sc.nextInt();
                int xn = sc.nextInt();
                map[xm][xn] = 1;    
            }
            
            int am = sc.nextInt();
            int an = sc.nextInt();
            int bm = sc.nextInt();
            int bn = sc.nextInt();
            
            if(map[am][an] == 1 || map[bm][bn] == 1)     /* If the src and dest overlap with forbidden cells */
            {
                System.out.print(-1);
                return;
            }
            
            int cost = 0;
            cost = findMaxCostRecursive(map, am, an, bm ,bn, new String(), 0 );
            
            if(cost < 0)
                System.out.print(-1);
            else
                System.out.print(cost);
        }
        catch(Exception e)
        {
            System.out.println("Exception");
            System.out.print(-1);
        }
    }    //main()
    
    public static int findMaxCostRecursive(int map[][], int mm, int nn, int bm, int bn, String route, int iteration)
    {
        if((mm == bm) && (nn == bn))    /* termination condition : Reached the destination */
            return 0;
        else
        {
            boolean hoppedInSomeDirection = false;
            int costDown = -1, costUp = -1, costRight = -1, costLeft = -1;
            String newRoute = new String(route+mm+","+nn+";");    /* Keeping a record of the visited cells delimited by ';' */
            if((mm+1 < map.length) && (map[mm+1][nn] != 1) && !(route.contains((mm+1)+","+(nn))))    /* Down */
            {               
                hoppedInSomeDirection = true;
                costDown = findMaxCostRecursive(map, mm+1, nn, bm, bn, newRoute, iteration+1);
            }
            if((mm-1 >= 0) && (map[mm-1][nn] != 1) && !(route.contains((mm-1)+","+(nn))))    /* Up */
            {
                hoppedInSomeDirection = true;
                costUp = findMaxCostRecursive(map, mm-1, nn, bm, bn, newRoute, iteration+1);                
            }
            if((nn+1 < map[0].length) && (map[mm][nn+1] != 1) && !(route.contains((mm)+","+(nn+1))))    /* Right */
            {
                hoppedInSomeDirection = true;
                costRight = findMaxCostRecursive(map, mm, nn+1, bm, bn, newRoute, iteration+1);
            }
            if((nn-1 >= 0) && (map[mm][nn-1] != 1) && !(route.contains((mm)+","+(nn-1))))    /* Left */
            {
                hoppedInSomeDirection = true;
                costLeft = findMaxCostRecursive(map, mm, nn-1, bm, bn, newRoute, iteration+1);
            }
            if(!hoppedInSomeDirection)    /* If not hop can be made in any direction from the current cell */
            {
                return -(map.length * map[0].length)-1;
            }
            
            int max = (costUp > costDown)?costUp:costDown;
            max = (max > costLeft)?max:costLeft;
            max = (max > costRight)?max:costRight;
            return max+1;
        }
    }
}    //class