package com.gym_management;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientException;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
//============================================= Connecting to the database =============================================
    // Every time appending or retrieving data, we have to connect to database
    // We can return database from method because of external library
    public static MongoDatabase database() {
        try {
            // Connecting to database
            MongoClient mongo = new MongoClient("localhost", 27017);
            // Authentication to access the database
            MongoCredential credential = MongoCredential.createCredential("sampleUser", "Gym", "password".toCharArray());
            // Connecting to the created database or creating a new database and connecting to it
            MongoDatabase database = mongo.getDatabase("Gym");

            // The following commands is used to prevent from showing logs in console(When connecting/Appending new document/Retrieving)
            Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
            mongoLogger.setLevel(Level.OFF);
            Logger mongoClusterLogger = Logger.getLogger("JULLogger log");
            mongoClusterLogger.setLevel(Level.OFF);

            // return connected database
            return database;

        }catch (MongoClientException e){
            // If something went wrong or cant connect to the database informing the user
            System.out.println("Database Connection issues, Please wait!");
            database();
        }
        return database();
    }

//================================= Appending values to the database ===================================================
    public static void defaultDataBase(DefaultMember defaultMember){

        // Retrieving collection and Creating a document and adding/ appending to the collection in database.

        // Creating a DefaultMember collection in database
        MongoCollection<Document> defaultMemberCollection = database().getCollection("DefaultMembers");
        // Creating new document
        Document defaultMemberDocument = new Document()
                // Appending default member's instances into document
                // "Key",Value
                // Key used to identify the values
                // "_id" is primary key for the collection. So it have to be unique for each document.
                .append("_id",defaultMember.getMemberNo())
                .append("Name",defaultMember.getName())
                .append("Started Date", defaultMember.getStartMemDate())
                .append("Membership Type",defaultMember.getMemberType());

        try {
            defaultMemberCollection.insertOne(defaultMemberDocument);
            System.out.println("Member's details are saved in database");
        }catch (com.mongodb.MongoWriteException e){
            // Informing the user which member already in the data base
            System.out.println("Invalid, Inserted details already exist!");
        }
    }

//================================= Appending values to the database ===================================================
    public static void studentDataBase(StudentMember studentMember){
        // Retrieving collection and Creating a document and adding to the collection.
        MongoCollection<Document> studentMemberCollection = database().getCollection("StudentMembers");

        Document studentMemberDocument = new Document()
                .append("_id",studentMember.getMemberNo())
                .append("Name",studentMember.getName())
                .append("School", studentMember.getSchoolName());

        try {
            studentMemberCollection.insertOne(studentMemberDocument);
            System.out.println("Member's details are saved in database");
        }catch (com.mongodb.MongoWriteException e){
            // Informing the user which member already in the data base
            System.out.println("Invalid, Inserted details already exist!");
        }
    }

//================================= Appending values to the database ===================================================
    public static void over60DataBase(Over60Member over60Member){
//        Retrieving collection and Creating a document and adding to the collection.
        MongoCollection<Document> over60MemberCollection = database().getCollection("Over60Members");
        Document over60MemberDocument = new Document()
                .append("_id",over60Member.getMemberNo())
                .append("Name",over60Member.getName())
                .append("Age", over60Member.getAge());

        try {
            over60MemberCollection.insertOne(over60MemberDocument);
            System.out.println("Member's details are saved in database");
        }catch (com.mongodb.MongoWriteException e){
            // Informing the user which member already in the data base
            System.out.println("Invalid, Inserted details already exist!");
        }
    }

//================================= Retrieving values from database ====================================================
    // Getting collection name as parameter
    // I can use the following code more than one collection

    // The method is used to retrieve data from database
    // It's going to retrieve as document from specified collection
    // Later we can use the document to get the value(Member's name, Member's age) by their keys
    public static ArrayList<Document> retriever(String collectionName){
        // Getting/ finding  the collection by the parameter value (Collection name)
        MongoCollection<Document> Collection = database().getCollection(collectionName);
        // Creating a Iterator to iterate each document in collection
        FindIterable<Document> iter = Collection.find();
        // Creating arraylist to add the documents to return them
        ArrayList <Document> list = new ArrayList<>();
        for (Document document : iter) {
            list.add(document);
            document.get("Name");
        }
        return list;
    }
}
