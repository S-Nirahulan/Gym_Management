package com.gym_management;

public class Over60Member extends DefaultMember {

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
