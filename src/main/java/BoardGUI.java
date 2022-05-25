package main.java;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class BoardGUI extends JFrame {
    private JLabel[][] slotsIcons;
    private JButton[] buttons;
    private JLabel res;
    private JPanel panel;
    private ImageIcon blank;
    private ImageIcon yellow;
    private ImageIcon red;
    private BoardLogic logic;

    public BoardGUI() {
        super("Connect 4 main.java.Board");
        createBoard();
    }

    public void createBoard() {
        loadIcons();
        res = new JLabel();
        logic = new BoardLogic(this);
        buttons = new JButton[7];
        slotsIcons = new JLabel[6][7];
        GridLayout layout = new GridLayout(8,7);
        panel = new JPanel();
        panel.setLayout(layout);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                try {
                    slotsIcons[i][j] = new JLabel(blank);
                } catch (Exception e) {
                    System.out.println("File not found");
                }
                panel.add(slotsIcons[i][j]);
            }
        }
        for (int i = 0; i < 7; i++) {
            buttons[i] = new JButton("" + (i+1));
            buttons[i].addActionListener(new main.java.ButtonClickedEvent(getLogic()));
            panel.add(buttons[i]);
        }
        this.add(panel);
    }

    private void loadIcons() {
        try {
            yellow = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("yellowCircle.png")).getScaledInstance(BoardLogic.WIDTH, BoardLogic.HEIGHT, Image.SCALE_DEFAULT));
            blank = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("blank.png")).getScaledInstance(BoardLogic.WIDTH, BoardLogic.HEIGHT, Image.SCALE_DEFAULT));
            red = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("redCircle.png")).getScaledInstance(BoardLogic.WIDTH, BoardLogic.HEIGHT, Image.SCALE_DEFAULT));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playMove(int x, int y, int turn) {
        try {
            if (turn % 2 == 0) {
                slotsIcons[y][x].setIcon(yellow);
            } else {
                slotsIcons[y][x].setIcon(red);
            }
        } catch (Exception e) {
            System.out.println("Missing file");
        }
    }

    public JLabel[][] getSlotsIcons() {
        return slotsIcons;
    }

    public ImageIcon getBlank() {
        return blank;
    }

    public ImageIcon getYellow() {
        return yellow;
    }

    public ImageIcon getRed() {
        return red;
    }

    public BoardLogic getLogic() {
        return logic;
    }

    public void setRes(String text) {
        res.setText(text);
    }
}
