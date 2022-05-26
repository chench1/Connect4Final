package main.java;
/*
Author - Charles Chen
Date - 5/9/2022
Purpose - 
*/

import javax.swing.*;

public class test1 {
    public static void main(String[] args) {
        Object[] option = {"AI", "Two Player"};
        Object selectedValue = JOptionPane.showInputDialog(null,
                "Choose one", "Input",
                JOptionPane.INFORMATION_MESSAGE, null,
                option, option[0]);
        AI.PLAYS = selectedValue.toString().equals("AI");
        if (AI.PLAYS) {
            Object[] first = {"First", "Second"};
            Object res = JOptionPane.showInputDialog(null,
                    "Do you want the AI to go first or second", "Input",
                    JOptionPane.INFORMATION_MESSAGE, null,
                    first, first[0]);
            AI.FIRST = res.toString().equals("First");
        }
        BoardGUI b = new BoardGUI();
        b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b.setVisible(true);
        b.setSize(900,900);
        b.setLocationRelativeTo(null);
    }
}
