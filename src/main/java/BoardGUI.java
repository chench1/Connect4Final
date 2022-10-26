import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
        super("Wowie Connect 4 wooo");
        createBoard();
    }

    public void createBoard() {
        loadIcons();
        res = new JLabel();
        logic = new BoardLogic(this);
        buttons = new JButton[7];
        slotsIcons = new JLabel[6][7];
        GridLayout layout = new GridLayout(8, 7);
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
            buttons[i] = new JButton("" + (i + 1));
            buttons[i].addActionListener(new ButtonClickedEvent(getLogic()));
            panel.add(buttons[i]);
        }
        this.add(panel);
        startSetDifficulty();
    }

    private void loadIcons() {
        try {
            yellow = new ImageIcon(ImageIO.read(getClass().getResource("yellowCircle.png"))
                    .getScaledInstance(BoardLogic.WIDTH, BoardLogic.HEIGHT, Image.SCALE_DEFAULT));
            red = new ImageIcon(ImageIO.read(getClass().getResource("redCircle.png"))
                    .getScaledInstance(BoardLogic.WIDTH, BoardLogic.HEIGHT, Image.SCALE_DEFAULT));
            blank = new ImageIcon(ImageIO.read(getClass().getResource("blank.png"))
                    .getScaledInstance(BoardLogic.WIDTH, BoardLogic.HEIGHT, Image.SCALE_DEFAULT));
            // yellow = new
            // ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("yellowCircle.png"))
            // .getScaledInstance(BoardLogic.WIDTH, BoardLogic.HEIGHT,
            // Image.SCALE_DEFAULT));
            // blank = new
            // ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("blank.png"))
            // .getScaledInstance(BoardLogic.WIDTH, BoardLogic.HEIGHT,
            // Image.SCALE_DEFAULT));
            // red = new
            // ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("redCircle.png"))
            // .getScaledInstance(BoardLogic.WIDTH, BoardLogic.HEIGHT,
            // Image.SCALE_DEFAULT));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playMove(int x, int y, int turn) {
        try {
            if (turn == -1) {
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

    public void startSetDifficulty() {
        JFrame parent = new JFrame();

        JOptionPane optionPane = new JOptionPane();
        JSlider slider = getSlider(optionPane);
        optionPane.setMessage(new Object[] { "Select a value: ", slider });
        optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
        optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        JDialog dialog = optionPane.createDialog(parent, "Choose AI difficulty");
        dialog.setVisible(true);
        MiniMax.setMaxDepth((int)(optionPane.getInputValue()));
    }

    static JSlider getSlider(final JOptionPane optionPane) {
        JSlider slider = new JSlider(1, 10);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        // slider.setValue(5);
        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                JSlider theSlider = (JSlider) changeEvent.getSource();
                if (!theSlider.getValueIsAdjusting()) {
                    optionPane.setInputValue(theSlider.getValue());
                }
            }
        };
        slider.addChangeListener(changeListener);
        return slider;
    }

    public void showWinner(int player) {
        JOptionPane.showMessageDialog(panel,
                (player == 1 ? "Red" : "Yellow") + " wins!",
                "Yay connect 4",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public int getSlotSymbol(int x, int y) {
        if (slotsIcons[y][x].getIcon().equals(red))
            return 1;
        if (slotsIcons[y][x].getIcon().equals(yellow))
            return -1;
        return 0;
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
