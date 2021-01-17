package com.Library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

public class transaction {

    /**
     *For issuing new Book
     */
    public void NewIssue(String sid,String bid){
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library", "root", "password")) {
            LocalDate issueddate = LocalDate.now();
            LocalDate expecteddate =LocalDate.now().plusDays(50);

            Statement smt = con.createStatement();
            String sql = "INSERT INTO `library`.`loan` " +
                    "(`studentID`, `bookID`, `borrowDate`, `expectedDate`) " +
                    "VALUES ('"+sid+"', '"+bid+"', '"+issueddate+"', '"+expecteddate+"')";


            smt.executeUpdate(sql);
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    /**
     *For Reissuing book
     */
    public void ReIssue(String sid,String bid){
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library", "root", "password")) {
            LocalDate issueddate = LocalDate.now();
            LocalDate expecteddate =LocalDate.now().plusDays(50);

            Statement smt = con.createStatement();
            String sql1 = "SELECT loanID FROM loan " +
                    "WHERE studentID = '"+sid+"' AND bookID = '"+bid+"'";
            ResultSet rs = smt.executeQuery(sql1);
            String loanid ="";
            while (rs.next()){
                loanid=rs.getString("loanID");
            }


            String sql2 = "UPDATE `library`.`loan`" +
                    " SET `borrowDate` = '"+issueddate+"', `expectedDate` = '"+expecteddate+"'" +
                    " WHERE (`loanID` = '"+loanid+"');";


            smt.executeUpdate(sql2);
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    /**
     *For return book
     */
    public void Return(String sid,String bid){
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library", "root", "password")) {
            LocalDate returndate = LocalDate.now();
            Statement smt = con.createStatement();

            String sql1 = "SELECT loanID FROM loan " +
                    "WHERE studentID = '"+sid+"' AND bookID = '"+bid+"'";
            ResultSet rs = smt.executeQuery(sql1);
            String loanid ="";
            while (rs.next()){
                loanid=rs.getString("loanID");
            }

            String sql2 = "UPDATE `library`.`loan` " +
                    "SET `returnDate` = '"+returndate+"' " +
                    "WHERE `loanID` = ' "+loanid+"'";
            smt.executeUpdate(sql2);
        } catch (Exception e) {

            e.printStackTrace();
        }


    }

}
