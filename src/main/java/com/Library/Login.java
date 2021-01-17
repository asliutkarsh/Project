package com.Library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Login {
    public static void student_login() {
        Scanner sc = new Scanner(System.in);
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library", "root", "password")) {
            Statement stm = con.createStatement();
            System.out.println("Enter Your Roll Number");
            String roll = sc.nextLine();
            System.out.println("Enter Your Password");
            String pass = sc.nextLine();
            String sql = "SELECT student_id,password FROM student_info WHERE student_id ='" + roll + "'";
            ResultSet rs = stm.executeQuery(sql);
            int x = 0;
            while (rs.next()) {
                String id = rs.getString("student_id");
                String password = rs.getString("password");
                if (id.equalsIgnoreCase(roll) && password.equalsIgnoreCase(pass)) {
                    System.out.println("Successfully Log In");
                    x++;
                }
            }
            if (x == 0) {
                System.out.println("Wrong Input");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
