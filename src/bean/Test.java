package bean;

import java.io.Serializable;

public class Test implements Serializable{
<<<<<<< HEAD
	private String student_no;
	private String subject_cd;
	private String school_cd;
=======
	private Student student;
	private Subject subject;
	private School school;
>>>>>>> branch 'master' of https://github.com/admin1-s/3_3_scoremanager.git
	private int no;
	private int point;
	private String classnum;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

<<<<<<< HEAD
    public String getSubjectNo() {
        return subject_cd;
=======
    public Subject getSubject() {
        return subject;
>>>>>>> branch 'master' of https://github.com/admin1-s/3_3_scoremanager.git
    }

<<<<<<< HEAD
    public void setSubjectNo(String subject_cd) {
        this.subject_cd = subject_cd;
=======
    public void setSubject(Subject subject) {
        this.subject = subject;
>>>>>>> branch 'master' of https://github.com/admin1-s/3_3_scoremanager.git
    }

    public School getSchool() {
        return school;
    }

    public void setSchoolCd(School school) {
        this.school= school;
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
        return classnum;
    }

    public void setClassNum(String classnum) {
        this.classnum = classnum;
    }
}
