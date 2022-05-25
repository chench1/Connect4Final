package main.java;
/*
Author - Charles Chen
Date - 5/9/2022
Purpose - 
*/

import javax.swing.*;


public class test1 {
    public static void main(String[] args) {
        BoardGUI b = new BoardGUI();
        b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b.setVisible(true);
        b.setSize(900,900);
        b.setLocationRelativeTo(null);
    }
}
