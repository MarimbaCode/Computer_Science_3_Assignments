package net.thejrdev.assignments.cycle_3.stacks_queues;

import java.util.Stack;

public class RPN {

    public static double solve(String input){

        String[] expression = input.split(" ");
        Stack<Double> stack = new Stack<>();

        for(String str: expression){
            if("+-*/".contains(str)){
                double num1 = stack.pop(), num2 = stack.pop();
                switch (str){
                    case "+" -> {stack.push(num1 + num2);}
                    case "-" -> {stack.push(num2 - num1);}
                    case "*" -> {stack.push(num1 * num2);}
                    case "/" -> {stack.push(num2 / num1);}
                }
            }else{
                stack.push(Double.parseDouble(str));
            }
        }
        return stack.pop();
    }
}
