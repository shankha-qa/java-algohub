package Generic;

import java.util.ArrayList;
import java.util.HashMap;

public class OutputPrinter {

    public static void printOutput(int num){
        System.out.println("The output of the program is : " + num);
    }

    public static void printOutput(double num){
        System.out.println("The output of the program is : " + num);
    }

    public static void printOutput(long num){
        System.out.println("The output of the program is : " + num);
    }

    public static void printOutput(String str){
        System.out.println("The output of the program is : " + str);
    }

    public static void printOutput(boolean flag){
        System.out.println("The output of the program is : " + flag);
    }

    public static void printOutput(int[] arr){
        System.out.print("The output of the program is : ");
        for (int element : arr) {
            System.out.print(element + " ");
        }
    }

    public static void printOutput(String[] arr){
        System.out.print("The output of the program is : ");
        for (String element : arr) {
            System.out.print(element + " ");
        }
    }

    public static void printOutput(HashMap hm){
        System.out.println("The display of the hashmap is : " + hm);
    }

    public static void printOutput(ArrayList<Integer> al){
        System.out.println("The display of the arraylist is : " + al);
    }

}
