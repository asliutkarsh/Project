package com.Library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Profile {
    /**
     * to print info of user
     * @param id = roll no/user id
     */
    public void printUserInfo(String id){
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library", "root", "password")) {
            Statement stm = con.createStatement();
            String sql = "SELECT student_id FROM student_info WHERE student_id ='" + id + "'";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String roll = rs.getString("student_id");
                String name = rs.getString("student_name");
                String phone =rs.getString("phone");
                String email=rs.getString("email");
                String course=rs.getString("course");
                String branch=rs.getString("branch");
                System.out.println("Roll Number/Id:- "+roll);
                System.out.println("Name:- "+name);
                System.out.println("Phone:- "+phone);
                System.out.println("Email:- "+email);
                System.out.println("Course:- "+course);
                System.out.println("Branch:- "+branch);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * to print book issued by user and its last return date
     * @param id = roll no/user id
     */
    public void issuedBook(String id){

    }


}
