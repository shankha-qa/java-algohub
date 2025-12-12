package Generic;

import java.util.Scanner;

public class InputHandler {

    public static int getIntInputFromUser(String s){
        System.out.print("Enter one integer: ");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();

        return num;
    }

    public static long getLongInputFromUser(){
        System.out.print("Enter one long integer: ");
        Scanner scanner = new Scanner(System.in);
        long num = scanner.nextLong();

        return num;
    }

    public static String getStringInputFromUser(){
        System.out.print("Enter one string: ");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();

        return str;
    }

    public static int[] getIntArrayInputFromUser(int length){
        int[] num = new int[length];
        for(int i = 0; i < length ; i++) {
            System.out.print("Enter next element: ");
            Scanner scanner = new Scanner(System.in);
            num[i]= scanner.nextInt();
        }
        return num;
    }

    public static String[] getStringArrayInputFromUser(int length){
        String[] arr = new String[length];
        for(int i = 0; i < length ; i++) {
            System.out.print("Enter next element: ");
            Scanner scanner = new Scanner(System.in);
            arr[i]= scanner.next();
        }
        return arr;
    }

    public static char[] getCharArrayInputFromUser(int length){
        char[] arr = new char[length];
        for(int i = 0; i < length ; i++) {
            System.out.print("Enter next element: ");
            Scanner scanner = new Scanner(System.in);
            arr[i]= scanner.next().charAt(0);
        }
        return arr;
    }

}
