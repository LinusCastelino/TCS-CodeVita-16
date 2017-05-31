/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Linus
 */


import java.util.Scanner;

public class Q1_982689 {
    public static void main(String args[])
    {
        try
        {
            Scanner sc = new Scanner(System.in);
            
            String str = sc.nextLine();
            Stack s= new Stack(str.length());

            if(str.charAt(0) != '{')    /*to check if the JSON object begins with a '{' character. If not, it is an invalid object*/
            {
                System.out.println(-1);
                return;
            }

            char current[] = new char[str.length()];    /* An array to keep a track if the character under consideration is currently inside an array or inside an object */
            int k = -1; 

            for(int i=0; i<str.length(); i++)
            {                
                if(str.charAt(i) == '{' || str.charAt(i) == '[')    /* Checking starting of an object or array */
                {
                    s.push(str.charAt(i));
                    if(str.charAt(i) == '[')
                    {
                        current[++k] = 'a';
                    }
                    if(str.charAt(i) == '{')
                    {
                        current[++k] = 'o';
                    }
                }
                else if(str.charAt(i) == '}' || str.charAt(i) == ']')    /* Checking ending of an object or array */
                {
                    char x = s.pop();
                    if(str.charAt(i) == '}')
                    {                 
                        if(x != '{')
                        {
                            System.out.println(-1);
                            return;
                        }
                        if((i+1) < str.length())
                        {
                            if(str.charAt(i+1) == ':' || str.charAt(i+1) == '{' || str.charAt(i+1) == '[')
                            {
                                System.out.println(-1);
                                return;
                            }
                        }
                    }
                    else if(str.charAt(i) == ']')
                    {                    
                        if(x != '[' || str.charAt(i+1) == ':')
                        {
                            System.out.println(-1);
                            return;
                        }
                    }
                    k--;
                }
                else if(str.charAt(i) == ',')
                {
                    if(current[k] == 'a')
                    {
                        if(!(str.charAt(i-1) == '}' && str.charAt(i+1) == '{'))
                        {
                            System.out.println(-1);
                            return;
                        }
                    }    
                    else if(current[k] == 'o')
                    {
                        if(!(str.charAt(i+1) == ':'))
                        {
                            System.out.println(-1);
                            return;
                        }
                    }
                }
                else if(str.charAt(i) == ':')
                {
                    if(current[k] == 'a')
                    {
                        System.out.println(-1);
                        return;
                    }
                    else if(current[k] == 'o')
                    {
                        if(!((str.charAt(i-1) == '{' || str.charAt(i-1) == ',') && (str.charAt(i+1) == ',' || str.charAt(i+1) == '}' || str.charAt(i+1) == '[' || str.charAt(i+1) == '{')))
                        {
                            System.out.println(-1);
                            return;
                        }
                    }
                }
            }    //for

            int stackSize = s.getStackSize();
            if(stackSize != 0)
            {
                System.out.println(-1);   
                return;
            }
            System.out.println(1);    /* All cases passed */
        }
        catch(Exception e)
        {
            System.out.println(-1);
            return;
        }
    }   //main()
}    //Q1_982689


class Stack
{
    char array[];
    int i;
    
    public Stack(int size)
    {
        array = new char[size];        
        i = 0;
    }    //Stack()
    
    public void push(char a)
    {
        array[i++]=a;
    }    //push()
    
    public char pop()
    {
        char x = array[--i];
        return x;
    }    //pop()
    
    public int getStackSize()
    {
        return i;
    }    //getStackSize()
}    //Stack