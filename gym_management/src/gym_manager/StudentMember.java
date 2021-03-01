package gym_manager;

// Subclass for DefaultMember
// extends used to inherit variables and methods to this class from DefaultMember class
public class StudentMember extends DefaultMember {
    // Encapsulation
    // instance variables are set to private
    // And only setters are the way to set values for each objects.

    // Validation
    // Setter methods are used to validate the inputs before assigning them into class instance variables

    // Instance variables
    private String schoolName;


    public StudentMember(int memberNo,String memType, String name ,String schoolName, String startMemDate){
        // Constructor
        // Assigning value to super class instance variables
        super(memberNo,memType,name,startMemDate);
        // Assigning value to instance variable for this class
        this.setSchoolName(schoolName);
    }

    public String getSchoolName() {return schoolName;}

    public void setSchoolName(String schoolName) {
        // Validation
        // If input is empty setter method will throw an exception
        this.schoolName = schoolName;}


}
