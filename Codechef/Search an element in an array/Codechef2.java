import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef2
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		int a,b;
		
		
		Scanner sn  = new Scanner(System.in);
		a = sn.nextInt();// no.of items
		b = sn.nextInt(); // ele
		
		boolean isValuePresent = false;
		
		int[] arr = new int[a];
		
		for(int i= 0; i<a; i++){
		    arr[i] = sn.nextInt();
		     if(arr[i] == b){
		         isValuePresent = true;
		         break;
		     }
		}
		
		if(isValuePresent){ 
		    System.out.println("YES");
		}
		else {
		     System.out.println("NO");
		}

	}
}
