package com.epiq;

/*
Fibonacci series:
    F(n) = F(n-1) + F(n-2)
*/

import java.util.Scanner;

public class Fib {

    public static void main(String[] args) {

        //Scanner sn
        System.out.println("Fibonacci series:");
        int n = 5;

        int outpu = getfibonacci(n);
        System.out.println("Fibonacci output:" + outpu);

    }

    private static int getfibonacci(int n) {
        if(n == 1){
            return 1;
        } else {
            return n*(n-1);
        }
    }


}
