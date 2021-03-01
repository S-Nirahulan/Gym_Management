package gym_manager;

// Sub class of the DefaultMember class
public class Over60Member extends DefaultMember {

    // Member's age
    private int age;

    public Over60Member(int memberNo,String memType,String name,int age, String startMemDate){
        super(memberNo,memType,name,startMemDate);
        this.setAge(age);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {this.age = age;}

}
