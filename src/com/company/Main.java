package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert expression");
        String expression = sc.nextLine();

        /*String expression = "(5*(8-(2+4)/5)*(3+8))/2";*/
        System.out.println(new ExpressionParser().getExpression(expression).count());
    }
}
