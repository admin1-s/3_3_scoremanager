package bean;

import java.io.Serializable;

public class Test extends User implements Serializable{
	private String student_no;
	private String subject_no;
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
        return subject_no;
    }

    public void setSubjectNo(String subject_no) {
        this.subject_no = subject_no;
    }

    public String getSchoolCd() {
        return school_cd;
    }

    public void setSchoolCd(String school_cd) {
        this.school_cd = school_cd;
    }
//
}