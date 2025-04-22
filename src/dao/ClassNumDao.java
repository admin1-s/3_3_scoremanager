package dao;

<<<<<<< HEAD
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao {

    public ClassNum get(String class_num, School school) throws Exception {
        // 実装
    }

    public List<String> filter(School school) throws Exception {
        // 実装
    }

    public boolean save(ClassNum classNum) throws Exception {
        // 実装
    }

    public boolean save(ClassNum classNum, String newClassNum) throws Exception {
        // 実装
=======
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao {

    public ClassNum get(String class_num, School school) throws Exception {
        // 仮実装：常にnullを返す（エラーは起きない）
        return null;
    }

    public List<String> filter(School school) throws Exception {
        // 仮実装：空のリストを返す（エラーは起きない）
        return new ArrayList<>();
    }

    public boolean save(ClassNum classNum) throws Exception {
        // 仮実装：常にtrueを返す（エラーは起きない）
        return true;
    }

    public boolean save(ClassNum classNum, String newClassNum) throws Exception {
        // 仮実装：常にtrueを返す（エラーは起きない）
        return true;
>>>>>>> branch 'master' of https://github.com/admin1-s/3_3_scoremanager.git
    }
}
