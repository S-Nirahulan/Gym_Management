package com.gym_management;

public class DefaultMember implements Comparable<DefaultMember> {
    // Encapsulation
    // instance variables are set to private
    // And only setters are the way to set values for each objects.

    // Validation
    // Setter methods are used to validate the inputs before assigning them into class instance variables

    // Instance variables in the requirements
    private int memberNo;
    private String name;
    // Extra Instance variables
    // memberType is protected because all the members are "default" other than student and over60
    // To change member type in subclasses, protect used to access them in subclasses (student and over60)
    private String memberType;
    private String startMemDate;

    public DefaultMember(int memberNo,String memType,String name,String startMemDate) {
        // Constructor
        // Assigning values to DefaultMember class instance variables
        this.setMemberNo(memberNo);
        this.setName(name);
        this.setStartMemDate(startMemDate);
        this.setMemberType(memType);
    }

    // Getters and Setters to assign values to the instance variables
    public String getMemberType() { return memberType; }

    public void setMemberType(String memberType) {
        this.memberType =memberType;
    }

    public int getMemberNo() {return memberNo;}

    public void setMemberNo(int memberNo) {
        this.memberNo=memberNo;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name; }

    public String getStartMemDate() { return startMemDate;}

    // Setting started membership date for the member
    public void setStartMemDate(String startMemDate) {this.startMemDate = startMemDate;}

    // Overriding compare to method
    // String ordered by lexicographically(Number)
    // this.name means current object
    // defaultMember means another object to compare
    @Override
    public int compareTo(DefaultMember defaultMember) {return (this.name).compareTo(defaultMember.getName());}
}
