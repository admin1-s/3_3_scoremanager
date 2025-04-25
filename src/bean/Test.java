package bean;

import java.io.Serializable;

public class Test implements Serializable{
	private String student_no;
	private String subject_cd;
	private String school_cd;
	private int no;
	private int point;
	private String class_num;

    public String getStudentNo() {
        return student_no;
    }

    public void setStudentNo(String student_no) {
        this.student_no = student_no;
    }

    public String getSubjectNo() {
        return subject_cd;
    }

    public void setSubjectNo(String subject_cd) {
        this.subject_cd = subject_cd;
    }

    public String getSchoolCd() {
        return school_cd;
    }

    public void setSchoolCd(String school_cd) {
        this.school_cd = school_cd;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getClassNum() {
        return class_num;
    }

    public void setClassNum(String class_num) {
        this.class_num = class_num;
    }
}