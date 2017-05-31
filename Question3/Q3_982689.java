/**
 *
 * @author Linus
 */

import java.util.Scanner;

public class Q3_982689 {
    public static void main(String arg[])
    {
        try
        {
            Scanner sc = new Scanner(System.in);
            int capacity = Integer.parseInt(sc.nextLine());
            int noOfItems = Integer.parseInt(sc.nextLine());

            Item items[] = new Item[noOfItems];
            
            /* Storing inputs from user */
            for(int i=0; i<noOfItems; i++)
            {
                char itemName = sc.next().charAt(0);
                int availableQuantity = sc.nextInt();
                int unitWeight = sc.nextInt();
                int cost = sc.nextInt();
                Item item = new Item(itemName, availableQuantity, unitWeight, cost);
                items[i] = item;
            }
            
            for(int i=0; i<noOfItems; i++)
            {
                for(int j=0; j<i; j++)    /* handling constraints */
                {
                    if(items[j].itemName == items[i].itemName)
                    {
                        System.out.print(-1);
                        return;
                    }
                }
                if(items[i].unitWeight < 0 || items[i].availableQuantity < 0)    /* handling constraints */
                {
                    System.out.print(-1);
                    return;
                }
            }
            
            /* Sorting the items on basis of cost per unit weight */
            for(int i=(noOfItems-2); i>=0; i--)
            {
                for(int j=0; j<=i; j++)
                {
                    if(items[j].costPerUnitWeight<items[j+1].costPerUnitWeight)
                    {
                        Item temp = items[j];
                        items[j] = items[j+1];
                        items[j+1] = temp;                        
                    }
                }
            }
            
            int added[] = new int[items.length];
            for(int i=0; i<noOfItems;)
            {
                if(capacity >= items[i].unitWeight && items[i].availableQuantity > 0)
                {
                    added[i]++;
                    capacity -= items[i].unitWeight;
                    items[i].availableQuantity--;
                }
                else
                {
                    i++;
                }
            }
            
            String takeAwayItems = "";
            for(int i=0; i<noOfItems; i++)
            {
                if(added[i] != 0)
                {
                    takeAwayItems += "" + added[i] + (char)(items[i].itemName);
                }
            }
            System.out.print(takeAwayItems);
        }
        catch(Exception e)
        {
            System.out.print(-1);
        }
    }    //main()
}    //class

class Item
{
    public char itemName;
    public int availableQuantity;
    public int unitWeight;
    public int cost;
    double costPerUnitWeight;
    
    public Item(char pItemName, int pAvailableQuantity, int pUnitWeight, int pCost)
    {
        itemName = pItemName;
        availableQuantity = pAvailableQuantity;
        unitWeight = pUnitWeight;
        cost = pCost;
        costPerUnitWeight = ((double)cost)/unitWeight;
    }    //Item()
}    //Item