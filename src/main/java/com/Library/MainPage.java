package com.Library;

import java.util.Scanner;

public class MainPage {
    public static void main(String[] args) {
        Signup s=new Signup();

        Scanner sc =new Scanner(System.in);
        System.out.println("Enter 1 For Sign Up");
        System.out.println("Enter 2 For Log In");
        System.out.println("Enter 3 For Liberarian");
        int choice= sc.nextInt();
        switch (choice){
            case 1:
                s.inputUser();
        }
    }

}
