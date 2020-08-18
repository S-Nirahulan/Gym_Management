package com.gym_management;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // UI
        // Setting title for the Stage
        primaryStage.setTitle("Gym Management System - GUI View");

        // tableWithSearching is a static method in UI class and it's return vbox which have the table
        primaryStage.setScene(new Scene(UI.table(),785,480));

        // Setting primaryStage to show when launching the application
        primaryStage.show();
    }

    public static void main(String[] args) {

        // Welcome message
        System.out.println();
        System.out.println("\t\t\t-- Welcome to Gym management System --");
        System.out.println("\t\t\t\t\t  Console View\n");


        boolean breakFromLoop =false;
        int memCount = 1;

        while (!breakFromLoop) {
            // Instructing
            System.out.println("------------------------------------------------------------------");
            System.out.println("Access level : Activating functionalities");
            System.out.println();
            System.out.println("Type the following commands to access specific functionalities.");
            System.out.println("\t* Add a new member => ADD");
            System.out.println("\t* Delete a existing member => DEL");
            System.out.println("\t* Print members details => PRINT");
            System.out.println("\t* Save members details => SAVE");
            System.out.println("\t* Sort members based on names => SORT");
            System.out.println("\t* Show all members details in table => GUI");
            System.out.println("\t* Quit => QUIT");
            System.out.println("------------------------------------------------------------------");
            // Getting Input, verifying and continuing

            String command = Verifiers.commandVerification("add", "del","print","save","sort","gui","quit");


            switch (command) {
                case "add":
                    addFunction(memCount++);
                    break;
                case "del":
                    deleteFunction();
                    break;
                case "print":
                    printFunction();
                    break;
                case "save":
                    saveFunction();
                    break;
                case "sort":
                    sortFunction();
                    break;
                case "gui":
                    try {
                        launch(args);
                    }catch (Exception e){
                        System.out.println("Sorry you can only run GUI once");
                    }
                    break;
                case "quit":
                    System.out.println("Did you save the data!");
                    if (Verifiers.confirmation("Do you want to save the data? yes/no ")){
                        saveFunction();
                    }else{
                        System.out.println("Program quiting...");
                        System.out.println();
                        breakFromLoop = true;
                    }break;
            }
        }
    }

//================================================= Add ================================================================
    private static void addFunction(int count) {

        // Instructing
        System.out.println("------------------------------------------------------------------");
        System.out.println("Access level : Adding Members");
        System.out.println();
        System.out.println("Type the following commands to add specific members. ");
        System.out.println("\t* Add student member => STUDENT");
        System.out.println("\t* Add over sixty years old member => OVER60");
        System.out.println("\t* Add default member => DEFAULT");
        System.out.println("\t* Go back => BACK");
        System.out.println("------------------------------------------------------------------");

        // Getting Input, verifying and continuing
        String addCommands = Verifiers.commandVerification("student", "over60", "default", "back");

        // Calling MyGymManager class constructor to use MyGymManager methods
        MyGymManager managerAdd = new MyGymManager();

        // Switching to activate specific commands
        switch (addCommands) {
            case "student":
                managerAdd.addMember("s",count);
                break;
            case "over60":
                managerAdd.addMember("o",count);
                break;
            case "default":
                managerAdd.addMember("d",count);
                break;
            case "back":
                System.out.println("Going back main console menu...");
                break;
        }
    }

//================================================ Delete ==============================================================
    private static void deleteFunction() {

        // Instructing
        System.out.println("------------------------------------------------------------------");
        System.out.println("Access level : Deleting Members");
        System.out.println();
        System.out.println("Type the following commands to delete ");
        System.out.println("\t* Delete by member number in system => SYSTEM");
        System.out.println("\t* Delete by member number in database => DATABASE");
        System.out.println("\t* Go back => BACK");
        System.out.println("------------------------------------------------------------------");

        // Getting Input, verifying and continuing
        String deleteCommand=Verifiers.commandVerification("system", "database","back");

        MyGymManager managerDeleteFunction = new MyGymManager();

        switch (deleteCommand) {
            case "system":
                managerDeleteFunction.deleteObjectsInSystem();
                break;
            case "database":
                managerDeleteFunction.deleteObjectsInDatabase();
                break;
            case "back":
                System.out.println("Going back main console menu...");
                break;
        }

    }

//===================================================== Print ==========================================================
    private static void printFunction() {

        // Instructing
        System.out.println("------------------------------------------------------------------");
        System.out.println("Access level : Printing Members");
        System.out.println();
        System.out.println("Type the following commands to print specific members ");
        System.out.println("\t* Print member's details in the system  => SYSTEM");
        System.out.println("\t* Print member's details in the database => DATABASE");
        System.out.println("\t* Go back => BACK");
        System.out.println("------------------------------------------------------------------");

//        Getting Input, verifying and continuing
        String printCommand=Verifiers.commandVerification("system","database","back");
        System.out.println();

        MyGymManager managerPrintFunction = new MyGymManager();

        switch (printCommand) {
            case "system":
                managerPrintFunction.printObjectsInSystem();
                break;
            case "database":
                managerPrintFunction.printObjectsInDatabase();
                break;
            case "back":
                System.out.println("Going back main console menu...");
                break;
        }
    }

//================================================ Save ================================================================
    private static void saveFunction() {

        // Instructing
        // Instructing
        System.out.println("------------------------------------------------------------------");
        System.out.println("Access level : Saving Members");
        System.out.println();
        System.out.println("Type the following commands to save specific members ");
        System.out.println("\t* Save all members in database => DATABASE");
        System.out.println("\t* Save all members in file => FILE");
        System.out.println("\t* Go back => BACK");
        System.out.println();
        System.out.println(" Important");
        System.out.println("\t* Saving in database is allow you to read and write inside the program");
        System.out.println("\t* Coping in file dont have neither of the options ");
        System.out.println("------------------------------------------------------------------");


        // Getting Input, Verifying and Continuing
        String saveCommand=Verifiers.commandVerification("database", "file", "back");

        MyGymManager managerSaveFunction = new MyGymManager();
        // Switching between functionalities
        switch (saveCommand) {
            case "database":
                managerSaveFunction.saveInDatabase();
                break;
            case "file":
                managerSaveFunction.saveInFile();
                break;
            case "back":
                System.out.println("Going back main console menu...");
                break;
        }
    }

//================================================= Sort ===============================================================
    private static void sortFunction() {

        // Instructing
        System.out.println("------------------------------------------------------------------");
        System.out.println("Access level : Sorting Members");
        System.out.println();
        System.out.println("Type the following commands to save specific members ");
        System.out.println("\t* Sort members details in the system => SYSTEM");
        System.out.println("\t* Sort members details in the backup => DATABASE");
        System.out.println("\t* Go back => BACK");
        System.out.println("------------------------------------------------------------------");


        // Getting Input, verifying and continuing
        String sortCommand=Verifiers.commandVerification("system", "database", "back");
        System.out.println();

        MyGymManager managerSortFunction = new MyGymManager();
        // Switching between functionalities
        switch (sortCommand) {
            case "system":
                managerSortFunction.sortObjectsInSystem();
            case "database":
                managerSortFunction.sortObjectsInDatabase();
                break;
            case "back":
                System.out.println("Going back main console menu...");
                break;
        }
    }

}

