package main.java;/*
Author - Charles Chen
Date - 5/23/2022
Purpose - 
*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClickedEvent implements ActionListener {
    private final BoardLogic logic;
    public ButtonClickedEvent(BoardLogic b) {
        logic = b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int clicked = Integer.parseInt(e.getActionCommand());
        if (AI.PLAYS) {

        } else {
            try {
                logic.placeToken(clicked);
            } catch (InvalidMoveException ex) {
                System.out.println(ex.getMessage());
                logic.setTurn(logic.getTurn() - 1);
            }
        }
        logic.setTurn(logic.getTurn() + 1);
        System.out.println(logic.getTurn());
    }
}
