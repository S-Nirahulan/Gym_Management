package com.gym_management;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Date {
    // Instance variable
    // Following encapsulation principals
    private int year;
    private int month;
    private int date;

    // Constructor
    public Date(){
        super();
        this.setDate(date);
        this.setMonth(month);
        this.setYear(year);
    }
    // Getters and setters for the class
    public int getYear() { return year;}

    public void setYear(int year) {this.year = year;}

    public int getMonth() {return month;}

    public void setMonth(int month) {this.month = month;}

    public int getDate() {return date;}

    public void setDate(int date) {this.date = date;}


    // Setting values for setters in the class
    public String setFullDate() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the full date? dd/mm/yyyy");
        return input.next();
    }


    // Created custom method return full date as string
    // exe : 23.12.2000
    public String getFullDate() {
        String fullDate;

        do{
           fullDate = setFullDate();

           while (fullDate.length()!=10){
               System.out.println("Invalid date, Please try again");
               fullDate = setFullDate();
           }

           if ((yearVerifier(fullDate) || monthVerifier(fullDate) || dateVerifier(fullDate))){
                System.out.println("Invalid date, Please try again!");
           }

        } while((yearVerifier(fullDate) || monthVerifier(fullDate) || dateVerifier(fullDate)));
        return getDate() + "/" + getMonth() + "/" + getYear();
    }


    // Verifying to enter correct year
    // The following method will loop through until user valid input
    // Following have limitation
    // If the manger going to use the program for long he can update the limitation
    public boolean yearVerifier(String fullDate) {
        int year ;
        boolean invalid;

        try {
            invalid = Integer.parseInt(fullDate.substring(6, 10))<0;
        }catch (NumberFormatException e){
            invalid = true;
        }

        if (!invalid) {
            year = Integer.parseInt(fullDate.substring(6, 10));
            invalid = year < 1980 || year > 2020;
            if (!invalid){
                setYear(year);
            }
        }
        return invalid;
    }


    // The following method is used to verify  the month input
    // Always month will 0-12 so until user enter correct integer value the method will loop to get the correct input
    public boolean monthVerifier(String fullDate){
        int month;
        boolean invalid;

        try {
            invalid = Integer.parseInt(fullDate.substring(3, 5))<0;
        }catch (NumberFormatException e){
            invalid = true;
        }

        if (!invalid){
            month = Integer.parseInt(fullDate.substring(3, 5));
            invalid = month < 1 || month > 12;
            if (!invalid){
                setMonth(month);
            }
        }
        return  invalid;
    }

    // the following method to validate the date
    // The following method is have limitation for the date value based on their previously entered month and year
    // Manger can change the month if he input the wrong date (optional)
    public boolean dateVerifier(String fullDate) {
        Scanner input = new Scanner(System.in);
        int date;
        boolean invalid;

        try {
            invalid = Integer.parseInt(fullDate.substring(0, 2)) < 0;
        } catch (NumberFormatException e) {
            invalid = true;
        }

        if (!invalid) {
            date = Integer.parseInt(fullDate.substring(0, 2));
            if (getMonth() == 1 || getMonth() == 3 || getMonth() == 5 || getMonth() == 7 || getMonth() == 8 || getMonth() == 10 || getMonth() == 12) {
                invalid = date <= 0 || date > 31;
            }
            // Leap year
            else if (getMonth() == 2) {
                if (getYear() % 4 == 0) {
                    invalid = date <= 0 || date > 29;
                } else {
                    invalid = date <= 0 || date > 28;
                }
            } else if (getMonth() == 4 || getMonth() == 6 || getMonth() == 9 || getMonth() == 11) {
                invalid = date <= 0 || date > 30;
            }
            if (!invalid){
                setDate(date);
            }
        }
        return invalid;
    }
}
