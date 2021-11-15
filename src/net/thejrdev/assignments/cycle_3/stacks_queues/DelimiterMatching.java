package net.thejrdev.assignments.cycle_3.stacks_queues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DelimiterMatching {

    public static String match(String str){

        String[] characters = str.split("");
        Stack<String> dels = new Stack<>();

        for(String s: characters){
            if("([{<".contains(s)){
                dels.push(s);
            }else if(")]}>".contains(s)){

                if(dels.isEmpty()){
                    return "missing left delimiter";
                }

                switch (s){
                    case ")" -> {
                        if(!dels.pop().equals("(")){
                            return "matching error";
                        }
                    }
                    case "]" -> {
                        if(!dels.pop().equals("[")){
                            return "matching error";
                        }
                    }
                    case "}" -> {
                        if(!dels.pop().equals("{")){
                            return "matching error";
                        }
                    }
                    case ">" -> {
                        if(!dels.pop().equals("<")){
                            return "matching error";
                        }
                    }
                }
            }
        }

        if(dels.isEmpty()){
            return "good";
        }else{
            return "missing right delimiter";
        }
    }


}
