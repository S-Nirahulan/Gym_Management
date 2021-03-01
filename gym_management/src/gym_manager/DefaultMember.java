package gym_manager;

public class DefaultMember implements Comparable<DefaultMember> {
    // Encapsulation
    // instance variables are set to private
    // And only setters are the way to set values for each object's variables.

    // Validation
    // Setter methods are used to validate the inputs before assigning them into class instance variables

    // Instance variables for the class
    private int memberNo;
    private String name;
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

    // Getters and Setters to retrieve values and to assign values to the instance variables
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

    public void setStartMemDate(String startMemDate) {this.startMemDate = startMemDate;}

    // Overriding compare to method
    // Strings are ordered by lexicographically order (Number)
    // this.name means current object
    // defaultMember means another object which we are comparing to
    @Override
    public int compareTo(DefaultMember defaultMember) {return (this.name).compareTo(defaultMember.getName());}
}
