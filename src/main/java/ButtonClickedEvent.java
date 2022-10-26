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
        try {
            logic.placeToken(clicked);
        } catch (InvalidMoveException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(logic.getTurn());
    }
}
