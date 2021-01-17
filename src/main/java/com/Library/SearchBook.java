package com.Library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchBook {

    /**
     * to search book by book name or author name for user
     */
    public void searchBookUser() {
        Scanner sc = new Scanner(System.in);
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library", "root", "password")) {
            Statement stm = con.createStatement();
            System.out.println("Enter Book ID Or Book Name Or Author Name");
            String book = sc.nextLine();
            String sql = "SELECT * FROM book_info WHERE book_id ='"+book+"' book_name LIKE '%" + book + "%' OR author LIKE '%" + book + "%'";
            ResultSet rs = stm.executeQuery(sql);
            int x = 0;
            int choice,c=1;
            ArrayList<String> forbid =new ArrayList<String>();

            while (rs.next()) {
                String bid = rs.getString("book_id");
                String bname = rs.getString("book_name");
                String bauthor = rs.getString("author");
                String bpub = rs.getString("publisher");
                System.out.println("Book ID:- "+bid+"\n     Book Name:- " + bname + "\n     By:- " + bauthor + "\n     From:- " + bpub );
                System.out.println("Enter "+c+" To Select");
                x++;c++;
            }

            if (x == 0) {
                System.out.println("NO BOOK Found");
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *to search book by Book id to for liberarian option 1
     * i.e search by book to issue it
     */
    public void searchBookLib1(){
        Scanner sc = new Scanner(System.in);
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library", "root", "password")) {
            Statement stm = con.createStatement();
            System.out.println("Enter Book ID");
            String book = sc.nextLine();
            String sql = "SELECT * FROM book_info WHERE book_id ='"+book+"'";
            ResultSet rs = stm.executeQuery(sql);
            int x = 0;int choice,c=1;
            ArrayList<String> forbid =new ArrayList<String>();
            while (rs.next()) {
                String bid = rs.getString("book_id");
                forbid.add(c,bid);
                String bname = rs.getString("book_name");
                String bauthor = rs.getString("author");
                String bpub = rs.getString("publisher");
                int stock=rs.getInt("stock");
                System.out.println("Book ID:- "+bid+"\n     Book Name:- " + bname + "\n     By:- " + bauthor + "\n     From:- " + bpub + "\n     Stock:- "+stock);
                System.out.println("Enter "+c+" To Select");
                x++;c++;
            }
            choice=sc.nextInt();
            String choicebook = forbid.get(choice);
            System.out.println("Enter Student ID To Issue It");
            String choicestud= sc.nextLine();
            transaction transaction =new transaction();
            transaction.NewIssue(choicestud,choicebook);

            if (x == 0) {
                System.out.println("NO BOOK Found");
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
