package com.gym;

import java.util.Scanner;

public class Verifiers {

//========================================== Verification  =============================================================

//    Validation for the user inputs
    public static boolean confirmation(String question){
        Scanner input = new Scanner(System.in);
        // Printing out the question
        System.out.println(question);
        // Converting when assigning the value to variable
        String confirmCommandLower=input.next().toLowerCase();

        // Looping until user enters valid input
        while (!(confirmCommandLower.equals("yes") || confirmCommandLower.equals("no"))){
            System.out.println("Invalid command, please try again!");
            System.out.println(question);
            confirmCommandLower=input.next().toLowerCase();
        }

        // returning the value based on the user inputs
        switch (confirmCommandLower){
            case "yes":
                return true;
            case "no":
                return false;
        }
        return false;
    }

    // Validation for the user inputs
    // Method Overloading
    // Same methods but different number of parameters
    public static String commandVerification(String function1, String function2, String function3,String function4, String function5, String function6, String function7){
        Scanner input = new Scanner(System.in);
        System.out.println("What functionality do you want to use now? ");
        String command =input.next().toLowerCase();

        while (!(command.equals(function1) || command.equals(function2) || command.equals(function3) || command.equals(function4)|| command.equals(function5)|| command.equals(function6)|| command.equals(function7) )) {
            System.out.println("Invalid command, please try again!");
            System.out.println("What functionalities do you want to use? ");
            command=input.next().toLowerCase();
        }
        return command;
    }

    public static String commandVerification(String function1, String function2, String function3,String function4){
        Scanner input = new Scanner(System.in);
        System.out.println("What functionality do you want to use now? ");
        String command =input.next().toLowerCase();

        while (!(command.equals(function1) || command.equals(function2) || command.equals(function3) || command.equals(function4))) {
            System.out.println("Invalid command, Please enter again!");
            System.out.println("What functionalities do you want to use? ");
            command=input.next().toLowerCase();
        }
        return command;
    }

    public static String commandVerification(String function1, String function2,String function3){
        Scanner input = new Scanner(System.in);
        System.out.println("What functionality do you want to use now? ");
        String command =input.next().toLowerCase();

        while (!(command.equals(function1) || command.equals(function2) || command.equals(function3))) {
            System.out.println("Invalid command, Please enter again!");
            System.out.println("What functionalities do you want to use? ");
            command=input.next().toLowerCase();
        }
        return command;
    }


//===================================== Verification for MyGymManager class ============================================

    // All string value's first letter will be changed to capital
    // It will be easy to compare and sort two strings without errors (C and c)
    // Validating whether user inputs letters only not numbers or signs (#/.)
    // Looping until user enters correct input
    public static String stringVerifier(String question){
        Scanner input = new Scanner(System.in);
        System.out.println(question);
        String answer;
        boolean invalid = false;

        while(true){
            answer =input.next().toLowerCase();

            for (int i=0; i<answer.length(); i++){
                try{
                    // Checking if user entered any number in the name
                    invalid= Integer.parseInt(String.valueOf(answer.charAt(i))) > 0;
                    break;
                }catch (NumberFormatException e){
                    invalid=false;
                }
            }
            if (invalid){
                System.out.println("Invalid, Please try again!");
                System.out.println(question);
            }
            else break;
        }
        // converting first letter to capital
        answer = answer.substring(0, 1).toUpperCase() + answer.substring(1);
        return answer;
    }

    // Validating whether user inserting integers values or not
    // By hasNextInt with while loop
    // Looping until user enters correct input
    public static int integerVerifier(String question){
        Scanner input = new Scanner(System.in);
        int answer;
        do {
            // For this program there is no use case for negative numbers
            System.out.println(question);
            while (!input.hasNextInt()) {
                System.out.println("Invalid data type, Please try again!");
                System.out.println(question);
                input.next();
            }
            answer=input.nextInt();
            if (answer<0){
                System.out.println("Invalid Negative number, Please try again!");
            }
        }while ((answer<0));
        return answer;
    }

    // This method used to get input between two integer values
    // Used for age validation
    // Looping until user enters correct input
    public static int ageLimitVerifier(String question,int min, int max){
        Scanner input = new Scanner(System.in);
        int answer;
        do {
            System.out.println(question);
            while (!input.hasNextInt()) {
                System.out.println("Invalid data type, Please try again!");
                System.out.println(question);
                input.next();
            }
            answer =input.nextInt();
            if (answer <min || answer >max){
                System.out.println("Invalid date for the category, Please try again!");
            }
        }while ((answer<min ||  answer>max));
        return answer;
    }


}
