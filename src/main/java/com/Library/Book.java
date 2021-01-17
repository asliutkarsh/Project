package com.Library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Book {
    /**
     * input book info
     */
    public void addBook(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Book ID");
        String bid =sc.nextLine();
        System.out.println("Enter Book Name");
        String bname=sc.nextLine();
        System.out.println("Enter Book Author");
        String bauthor = sc.nextLine();
        System.out.println("Enter Book Publisher");
        String bpub =sc.nextLine();
        System.out.println("Enter Number of Stock");
        int stock =sc.nextInt();
        BookinputDB(bid,bname,bauthor,bpub,stock);

    }

    /**
     * Inserting Data into DataBase
     */
    public void BookinputDB(String bid,String bname,String bauthor,String bpub,int stock){
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library", "root", "password")) {

            Statement smt = con.createStatement();
            String sql = "INSERT INTO library.student_info" +
                    " (book_id,book_name,author,publisher,stock)" +
                    " VALUES ('" + bid + "','" + bname + "','" + bauthor + "','"+ bpub + "','" + stock + "')";


            smt.executeUpdate(sql);
        } catch (Exception e) {

            e.printStackTrace();
        }


    }

    /**
     *To know the stock of a particular book
     */
    public void stockBook(String bid){
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "password")) {
              Statement stm = con.createStatement();
             String sql = "SELECT stock FROM library.book_info WHERE book_id ='"+bid+"'";
             ResultSet rs = stm.executeQuery(sql);
              while (rs.next()) {
                  int stock = rs.getInt("stock");
                  System.out.println("Stock:- "+stock);
              }

          } catch (Exception e) {
              e.printStackTrace();
          }

    }

    /**
     * Print ALl the information about any particular book
     */
    public void printBook(String bid){
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "password")) {
            Statement stm = con.createStatement();
            String sql = "SELECT * FROM book_info WHERE book_id ='"+bid+"'";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                String bookid = rs.getString("book_id");
                String bname = rs.getString("book_name");
                String bauthor = rs.getString("author");
                String bpub = rs.getString("publisher");
                int stock=rs.getInt("stock");
                System.out.println("Book ID:- "+bookid+"\n     Book Name:- " + bname + "\n     By:- " + bauthor + "\n     From:- " + bpub + "\n     Stock:- "+stock);
                System.out.println();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Print all book
     */
    public void printBooks() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "password")) {
            Statement stm = con.createStatement();
            String sql = "SELECT * FROM book_info";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                String bid = rs.getString("book_id");
                String bname = rs.getString("book_name");
                String bauthor = rs.getString("author");
                String bpub = rs.getString("publisher");
                int stock=rs.getInt("stock");
                System.out.println("Book ID:- "+bid+"\n     Book Name:- " + bname + "\n     By:- " + bauthor + "\n     From:- " + bpub + "\n     Stock:- "+stock);
                System.out.println();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
