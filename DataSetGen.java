package rpn;

import java.io.File;
import static java.lang.Character.isDigit;
import java.util.*;
import java.util.regex.*;
public class RPN {

    public static String input(String expr)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Press 1 to enter expression through console\npress 2 to enter expression from file");
        int choice = sc.nextInt();sc.nextLine();
        if(choice == 1)
        {
            System.out.println("Enter expression: ");
            expr = sc.nextLine();
        }
        else if(choice == 2)
        {
            try{
            File file = new File("C:\\NetBeansProjects\\RPN\\src\\rpn\\expr.txt");
            Scanner scf = new Scanner(file);
            expr = scf.nextLine();
            System.out.println(expr);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        return expr;
    }
    public static boolean verify(String expr)
    {
        boolean b = true;
        int count=0;
        int len=expr.length();
        int i=0;
        while(i<len)
        {
            if(isDigit(expr.charAt(i)))
            {
                count++;
            }
            else if(expr.charAt(i)=='+' ||expr.charAt(i)=='-'||expr.charAt(i)=='*'||expr.charAt(i)=='/'||expr.charAt(i)=='^')
            {
                count--;
            }
            else if(expr.charAt(i)==' '){}
            else
            {
                b = false;
                break;
            }
            i++;
        }
        if(count!=1)
            b = false;
      
        return b;
    }
    public static int evaluateExpr(String expr)
    {
        Stack<Integer> stack = new Stack<Integer>();
        int i=0;
        int src, src1, src2;
        int len = expr.length();
        while(i<len)
        {
          
            if(isDigit(expr.charAt(i)))
            {
                src = Integer.parseInt(expr.charAt(i)+"");
                stack.push(src);
            }
            else
            {
                src2 = stack.pop();
                src1 = stack.pop();
                char ch = expr.charAt(i);
                if(ch == '+')
                {
                    stack.push(src1 + src2);
                }
                else if(ch == '-')
                {
                    stack.push(src1 - src2);
                }
                else if(ch == '*')
                {
                    stack.push(src1 * src2);
                }
                else if(ch == '/')
                {
                    stack.push(src1/src2);
                }
                else if(ch=='^')
                {
                    stack.push((int)Math.pow(src1*1.0, src2*1.0));
                }
            }
            i++;
        }
        return stack.pop();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
      
        String expr = null;
        expr = input(expr);
        boolean check = verify(expr);
      
        if(check)
        {
            int eval = evaluateExpr(expr);
            System.out.println("eval = "+eval);
        }
        else
        {
            System.out.println("Invalid Expression!!");
        }
    }
  
}