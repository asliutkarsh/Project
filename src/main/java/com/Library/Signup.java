package com.Library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;

public class Signup {
    /**
     * Taking input from user
     */
    public void inputUser(){
        String roll;
        String name;
        String phone;
        String email;
        String course;
        String branch;
        String pass;
        Scanner sc = new Scanner(System.in);
        int error=0;
        do {
            System.out.println("Enter Your Roll Number");
            roll = sc.nextLine();
            System.out.println("Enter Your Name");
            name = sc.nextLine();
            System.out.println("Enter Your Phone");
            phone = sc.nextLine();
            System.out.println("Enter Your Email");
            email = sc.nextLine();
            System.out.println("Enter Your Course");
            course = sc.nextLine();
            System.out.println("Enter Your Branch");
            branch = sc.nextLine();
            System.out.println("Enter Your Password");
            pass = sc.nextLine();
            if (pass.length()>8){
                System.out.println("Password should not be greater than 8 character");
                error++;
            }
            else if (pass.length()==0){
                System.out.println("Password should not be empty");
                error++;
            }
        }while (error>0);
        if (error==0){
            BookinputDB(roll,name,phone,email,course,branch,pass);
        }
    }

    /**
     * Inserting Data into DataBase
     */
    public void BookinputDB(String roll,String name,String phone,String email,String course,String branch,String pass){
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library", "root", "password")) {

            Statement smt = con.createStatement();
            String sql = "INSERT INTO library.student_info" +
                    " (student_id,student_name,phone,email,course,branch,password)" +
                    " VALUES ('" + roll + "','" + name + "','" + phone + "','" + email + "','" + course + "','" + branch + "','" + pass + "')";


            smt.executeUpdate(sql);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}
