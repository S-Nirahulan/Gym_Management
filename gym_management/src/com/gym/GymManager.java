package com.gym;

import java.util.ArrayList;

//  Interface
public interface GymManager {

    // ArrayLists
    // Only accessible and updatable if a classes implement the interface
    // Used to store member objects
    ArrayList<StudentMember> studentMemberList = new ArrayList<>();
    ArrayList<Over60Member> over60MemberList = new ArrayList<>();
    ArrayList<DefaultMember> defaultMemberList = new ArrayList<>();

    // Interface methods
    // If a class implements the interface, the class must have these methods.
    // And class have to override the methods these already defined methods.

    // To add student member
    // Asking values based on member type
    // Adding values to class instance (Student/Default/Over60) variables and creating an object
    // Student member objects will be saved in defaultMemberList and studentMemberList
    void addMember(String memType, int memNo);

    // To delete member
    // Ask membership number for to find the specific member object from array list and delete them
    void deleteObjectsInSystem();

    // To delete a member from database
    // Ask membership number and iter through each document in "DefaultMember", "StudentMember" and "Over60Member"  collection
    // And delete which have the equal mem no
    void deleteObjectsInDatabase();

    // To print members values in the system
    // Which is saved in array list
    void printObjectsInSystem();

    // To print members which is in database
    // Retrieve member objs as documents from database as documents and get the values by their key and print them
    void printObjectsInDatabase();

    // To sort members in array list.(System)
    void sortObjectsInSystem();

    // Sorting based on the member's name
    // To sort members data from backup
    // Used to retrieved data from database as document and typecast them into string/ int and add them into array list and sort them and print them
    void sortObjectsInDatabase();

    // To save members in mongodb database
    // Saving the objs in the arraylist to database
    // Use the arraylist, and loop through each member objs and append them into default member collection as document
    void saveInDatabase();

    // To copy member into a file
    // Every time overriding the file
    // Because it's easy to save the even if member's details got deleted/ or user removed some details (Avoid duplication)
    void saveInFile();

}
