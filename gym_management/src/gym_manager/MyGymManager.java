package gym_manager;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.Document;
import java.io.*;
import java.util.*;

//  GymManager class
public class MyGymManager implements GymManager {
    Date date =new Date();

//==================================================== Delete ==========================================================
    // Interface method (Interface : GymManager)
    // The following method is used to add default member
    @Override
    public void addMember(String memberType,int memNo) {

        // Default member is super class
        // All other member classes are sub class for the default member


        // Manager can only add members if default members list size is less than hundred
        // DefaultMemberList inside the interface (Interface : GymManager)
        // If we want to alter or use same arraylist in different interface methods we have to initialize inside the interface
        if (defaultMemberList.size() < 100) {

            // To inform the user how many members user can add
            System.out.println("------------------------------------------------------------------");
            System.out.println("\t* Available space : "+(100-defaultMemberList.size()));
            System.out.println("\t* Stored Members  : "+defaultMemberList.size());
            System.out.println("------------------------------------------------------------------");

            // Getting inputs for each member objects
            // Verifiers class have static methods
            // These methods have do while and while loop to loop through until user input valid inputs
            String memName = Verifiers.stringVerifier("Enter member's name?");

            // Assigning  value to member type
            String memType;
            String schoolName;
            String fullDate;
            int age;

            switch (memberType){
                case "d":
                    memType = "Default";
                    fullDate=date.getFullDate();
                    defaultMemberList.add(new DefaultMember(memNo,memType, memName,fullDate));
                    break;

                case "s":
                    memType = "Student";
                    schoolName = Verifiers.stringVerifier("Enter member's school name? ");
                    fullDate=date.getFullDate();
                    defaultMemberList.add(new DefaultMember(memNo,memType, memName,fullDate));
                    studentMemberList.add(new StudentMember(memNo,memType,memName,schoolName,fullDate));
                    break;

                case "o":
                    memType = "Over60";
                    age = Verifiers.ageLimitVerifier("Enter member's age? ",60,110);
                    fullDate=date.getFullDate();
                    defaultMemberList.add(new DefaultMember(memNo,memType, memName,fullDate));
                    over60MemberList.add(new Over60Member(memNo,memType,memName,age,fullDate));
                    break;

                default:
                    throw new IllegalStateException("Unexpected error in adding member");
            }
            // Instructing to save
            System.out.println("Inserted values are added to the system. Don't forget to save!");
        }else{
            // If user/manager already added hundred members they can't add any more member
            // So the following command used to inform user
            System.out.println("You can only store details for maximum hundred members!!!");
            // To inform the user how many members user can add
            System.out.println("------------------------------------------------------------------");
            System.out.println("\t* Available space : "+(100-defaultMemberList.size()));
            System.out.println("\t* Stored Members  : "+defaultMemberList.size());
            System.out.println("------------------------------------------------------------------");
        }
    }

//============================================= Delete =================================================================
    // Interface method (Interface : GymManager)
    // The following method is used to delete already added member by their membership number
    // User can delete the same member object in data base as well
    @Override
    public void deleteObjectsInSystem() {

        // Getting membership no as input and verifying the user inputs using already created integerVerifier static method
        // And delete from the system
        System.out.println("You can now delete members in the system by their membership number!!!");
        int membershipNo = Verifiers.integerVerifier("Enter member's membership number? ");

        // Looping through the list removing the specific object which have manager inserted membership no.
        for (int i = 0; i < defaultMemberList.size(); i++) {
            DefaultMember defaultMember = defaultMemberList.get(i);
            if (defaultMember.getMemberNo() == membershipNo) {
                // Showing some info to ensure whether the person is correct

                System.out.println("Please check the following values are correct before delete!!!");
                System.out.println("Member's name : " + defaultMember.getName());
                System.out.println("Member's membership number :" + defaultMember.getMemberNo());

                // Confirmation before delete
                // Verification have a static method which will return true as output for yes input and false for no
                if (Verifiers.confirmation("Do you want to delete? yes/no ")) {
                    defaultMemberList.remove(defaultMember);

                    for (int j = 0; j < studentMemberList.size(); j++) {
                        StudentMember studentMember = studentMemberList.get(j);
                        if (studentMember.getMemberNo() == membershipNo) {
                            studentMemberList.remove(studentMember);
                        }
                    }

                    // Looping through other list to delete same data
                    for (int k = 0; k < over60MemberList.size(); k++) {
                        Over60Member over60Member = over60MemberList.get(k);
                        if (over60Member.getMemberNo() == membershipNo) {
                            over60MemberList.remove(over60Member);
                        }
                    }

                }else{
                    System.out.println("Don't worry data is still available in system");
                }
            }
        }
        // To let manager/user how free spaces are available after deletion
        // To inform the user how many members user can add
        System.out.println("------------------------------------------------------------------");
        System.out.println("\t* Available space : "+(100-defaultMemberList.size()));
        System.out.println("\t* Stored Members  : "+defaultMemberList.size());
        System.out.println("------------------------------------------------------------------");
    }

    @Override
    public void  deleteObjectsInDatabase(){
        System.out.println("You can now delete members in the system by their membership number!!!");
        int membershipNo=Verifiers.integerVerifier("Enter the membership no? ");
        if (Verifiers.confirmation("Do you want to delete? yes/no ")) {
            // Retrieving documents from all three collections in the database
            MongoCollection<Document> defaultMemberCollection = Database.database().getCollection("DefaultMembers");
            MongoCollection<Document> studentMemberCollection = Database.database().getCollection("StudentMembers");
            MongoCollection<Document> over60MemberCollection = Database.database().getCollection("Over60Members");

            // Finding the whether same membership no exist in the database and deleting them.
            defaultMemberCollection.deleteOne(Filters.eq("_id", membershipNo));
            studentMemberCollection.deleteOne(Filters.eq("_id", membershipNo));
            over60MemberCollection.deleteOne(Filters.eq("_id", membershipNo));

            System.out.println("Data deleted from data base");
        }else{
            System.out.println("Data is available in data base");
        }
    }
//====================================== Print data in system ==========================================================

    // Interface method (Interface : GymManager)
    // The following method is used to print added members in the list
    @Override
    public void printObjectsInSystem() {
        // Looping through the defaultMemberList and print required values
        int noOfDocument = 0;
        for (DefaultMember defaultMember:defaultMemberList) {

            System.out.println(" Member's name :"+ defaultMember.getName() );
            System.out.println(" Membership type :"+defaultMember.getMemberType());
            System.out.println(" Membership starting date :"+defaultMember.getStartMemDate());
            System.out.println(" Member's number :"+defaultMember.getMemberNo());

            if(defaultMember.getMemberType().equals("Student")){
                for (StudentMember studentMember : studentMemberList){
                    if (defaultMember.getMemberNo()==studentMember.getMemberNo()){
                        System.out.println(" Member's school name :"+studentMember.getSchoolName());
                        System.out.println();
                    }
                }
            }else if(defaultMember.getMemberType().equals("Over60")){
                for (Over60Member over60Member : over60MemberList) {
                    if (defaultMember.getMemberNo()==over60Member.getMemberNo()) {
                        System.out.println(" Member's age :" +over60Member.getAge());
                        System.out.println();
                    }
                }
            }else{
                System.out.println();
            }
            noOfDocument++;
        }
        if (noOfDocument ==0 ){
            System.out.println("Invalid, No data available to print!");
        }
    }
//====================================== Print data in backup ==========================================================

    // Interface method (Interface : GymManager)
    // the following method used to print the member objects in data base
    @Override
    public void printObjectsInDatabase() {
        // Looping through documents in collection which inside Gym database
        int noOfDocument = 0;
        for (Document document : Database.retriever("DefaultMembers")) {

            String name = (String) document.get("Name");
            int memNo = (int) document.get("_id");
            String startedDate = (String) document.get("Started Date");
            String memType = (String) document.get("Membership Type");

            System.out.println(" Member's name :"+ name);
            System.out.println(" Membership type :"+memType);
            System.out.println(" Membership starting date :"+startedDate);
            System.out.println(" Member's number :"+memNo);
            System.out.println();

            noOfDocument++;
        }
        if (noOfDocument==0){
            System.out.println("Invalid, No data available to print!");
        }
    }

//================================================= Saving =============================================================
    @Override
    public void saveInDatabase() {

        for (DefaultMember defaultMember:defaultMemberList) {
            Database.defaultDataBase(defaultMember);
        }
        for (StudentMember studentMember:studentMemberList) {
            Database.studentDataBase(studentMember);
        }
        for (Over60Member over60Member: over60MemberList) {
            Database.over60DataBase(over60Member);
        }
    }

    @Override
    public void saveInFile() {
        File copy = new File("src/backup/Copy.txt");
        PrintWriter out;
        try{
            out= new PrintWriter(copy);
            out.println("\t\t\t" + "No " + "\t\t\t|\t\t" + " Type " + "\t\t\t|\t\t" + " Date " + "\t\t\t\t|\t\t\t" + " Name " + "\t\t\t");
            out.println("------------------------------------------------------------------------------------------------------------------------");

            for (DefaultMember defaultMember : defaultMemberList) {
                out.println("\t\t\t" + defaultMember.getMemberNo() + "\t\t\t|\t\t" + defaultMember.getMemberType() + "\t\t\t|\t\t" + defaultMember.getStartMemDate() + "\t\t\t|\t\t\t" + defaultMember.getName() + "\t\t");
            }

            out.close();
        }catch (FileNotFoundException e) {
            System.out.println("Unexpected error in finding the file");
        }
    }

//======================================================== Sorting =====================================================
    @Override
    public void sortObjectsInSystem() {
        for (DefaultMember defaultMember : sortingAlgorithm(defaultMemberList)) {

            System.out.println(" Member's name :"+ defaultMember.getName() );
            System.out.println(" Membership type :"+defaultMember.getMemberType());
            System.out.println(" Membership starting date :"+defaultMember.getStartMemDate());
            System.out.println(" Member's number :"+defaultMember.getMemberNo());

            if(defaultMember.getMemberType().equals("Student")){
                for (StudentMember studentMember : studentMemberList){
                    if (defaultMember.getMemberNo()==studentMember.getMemberNo()){
                        System.out.println(" Member's school name :"+studentMember.getSchoolName());
                        System.out.println();
                    }
                }
            }else if(defaultMember.getMemberType().equals("Over60")){
                for (Over60Member over60Member : over60MemberList) {
                    if (defaultMember.getMemberNo()==over60Member.getMemberNo()) {
                        System.out.println(" Member's age :" +over60Member.getAge());
                        System.out.println();
                    }
                }
            }else{
                System.out.println();
            }
        }
    }

    @Override
    public void sortObjectsInDatabase() {
        ArrayList<DefaultMember> defaultList =  new ArrayList<>();

        MongoCollection<Document> Collection = Database.database().getCollection("DefaultMembers");
        FindIterable<Document> iterDoc = Collection.find();

        for (Document document : iterDoc) {

            String name = (String) document.get("Name");
            int memNo = (int) document.get("_id");
            String startedDate = (String) document.get("Started Date");
            String memType = (String) document.get("Membership Type");
            defaultList.add(new DefaultMember(memNo, memType, name, startedDate));

        }

        for (DefaultMember defaultMember : sortingAlgorithm(defaultList)) {

            System.out.println(" Member's name :"+ defaultMember.getName() );
            System.out.println(" Membership type :"+defaultMember.getMemberType());
            System.out.println(" Membership starting date :"+defaultMember.getStartMemDate());
            System.out.println(" Member's number :"+defaultMember.getMemberNo());
            System.out.println();

        }
    }

    // sortingAlgorithm Method will sort based on members name.
    // The following method will get an Array list as parameter and sort the values and return the Array list
    // Help ful to avoid code duplication
    public static ArrayList<DefaultMember> sortingAlgorithm(ArrayList<DefaultMember> defaultList){
        int listSize = defaultList.size();
        for (int i = 0; i < listSize - 1; i++) {
            for (int j = 0; j < listSize - i - 1; j++) {
                if (defaultList.get(j).compareTo(defaultList.get(j + 1)) > 0) {
                    DefaultMember temp = defaultList.get(j);
                    defaultList.set(j, defaultList.get(j + 1));
                    defaultList.set(j + 1, temp);
                }
            }
        }return defaultList;
    }

//==================================================== Observable list =================================================
    public static ObservableList<DefaultMember> observableList(){
        ObservableList<DefaultMember> defaultMembersObservableList = FXCollections.observableArrayList();
        for (DefaultMember defaultMember:defaultMemberList) {
            defaultMembersObservableList.add(new DefaultMember(defaultMember.getMemberNo(),defaultMember.getMemberType(),defaultMember.getName(),defaultMember.getStartMemDate()));
        }
        return defaultMembersObservableList;
    }

}
