package bean;

import java.io.Serializable;

public class ClassNum implements Serializable{
    private School school_cd;
    private String class_num;
    private School school;

    public School getSchoolCd() {
        return school_cd;
    }

    public void setSchoolCd(School school_cd) {
        this.school_cd = school_cd;
    }

    public String getClassNum() {
        return class_num;
    }

    public void setClassNum(String class_num) {
        this.class_num = class_num;
    }

    public School getSchool() {
        return this.school;
    }

    public void setSchool(School school) {
        this.school = school;
    }





}
