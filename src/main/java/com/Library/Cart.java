package com.Library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Cart {
    /**
     * to add books to cart
     */
    public void addtoCart(String sid,String bid){
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library", "root", "password")) {

            Statement smt = con.createStatement();
            String sql = "INSERT INTO `library`.`cart` " +
                         "(`student_id`, `book_id`)" +
                         " VALUES ('"+sid+"', '"+bid+"')";


            smt.executeUpdate(sql);
        } catch (Exception e) {

            e.printStackTrace();
        }

    }


    /**
     * to remove book from cart
     */
    public void removeCart(String sid,String bid){
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library", "root", "password")) {
             Statement smt = con.createStatement();
             //this sql for getting Cnum(cart number)
            String sql = "SELECT cnum FROM cart " +
                     "WHERE student_id = '"+sid+"' AND book_id = '"+bid+"'";
             ResultSet rs = smt.executeQuery(sql);
             int cnum = 0;
             while (rs.next()) {
                 cnum=rs.getInt("cnum");
                 if (cnum>0) break;
             }

             //this sql for deleting row
            String sql2 = "DELETE FROM `library`.`cart` WHERE cnum ='"+cnum+"'";
            smt.executeUpdate(sql2);
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    /**
     * to print cart of any user
     */
    public void printCart(String sid){
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "password")) {
            Statement stm = con.createStatement();
            String sql1 = "SELECT * FROM cart WHERE student_id = '" + sid + "'";
            ResultSet rs1 = stm.executeQuery(sql1);
            ArrayList<String > bookid =new ArrayList<String>();
            System.out.println("BOOKS IN CART :- \n");
            while (rs1.next()) {
                String book_id = rs1.getString("book_id");
                bookid.add(book_id);
            }
            for(int i=0;i< bookid.size();i++) {
                String sql2 = "SELECT * FROM book_info WHERE book_id = '" + bookid.get(i) + "'";
                ResultSet rs2 = stm.executeQuery(sql2);
                int x = 0;
                while (rs2.next()) {
                    String bid = rs2.getString("book_id");
                    String bname = rs2.getString("book_name");
                    String bauthor = rs2.getString("author");
                    String bpub = rs2.getString("publisher");
                    System.out.println("Book Name:- " + bname + "\n       By:- " + bauthor + "\n     From:- " + bpub + " publication ");
                    x++;
                    System.out.println();
                }
                if (x == 0) {
                    System.out.println("NO BOOK Found in Cart");
                }

            }
          } catch (Exception e) {
              e.printStackTrace();
          }

    }

}
