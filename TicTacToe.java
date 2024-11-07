import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends Frame implements ActionListener {
    private Button[][] buttons = new Button[3][3];
    private boolean playerXTurn = true;
    
    public TicTacToe() {
        setLayout(new GridLayout(3, 3));
        initializeBoard();
        
        setTitle("Tic-Tac-Toe Game");
        setSize(300, 300);
        setVisible(true);
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    private void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new Button("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Button buttonClicked = (Button) e.getSource();
        
        if (!buttonClicked.getLabel().equals("")) {
            return; // Button already clicked
        }
        
        buttonClicked.setLabel(playerXTurn ? "X" : "O");
        buttonClicked.setEnabled(false);

        if (checkWin()) {
            showWinner(playerXTurn ? "X" : "O");
            resetBoard();
        } else if (isDraw()) {
            showDraw();
            resetBoard();
        } else {
            playerXTurn = !playerXTurn; // Switch turn
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getLabel().equals(buttons[i][1].getLabel()) && 
                buttons[i][1].getLabel().equals(buttons[i][2].getLabel()) && 
                !buttons[i][0].getLabel().equals("")) {
                return true;
            }

            if (buttons[0][i].getLabel().equals(buttons[1][i].getLabel()) && 
                buttons[1][i].getLabel().equals(buttons[2][i].getLabel()) && 
                !buttons[0][i].getLabel().equals("")) {
                return true;
            }
        }

        if (buttons[0][0].getLabel().equals(buttons[1][1].getLabel()) && 
            buttons[1][1].getLabel().equals(buttons[2][2].getLabel()) && 
            !buttons[0][0].getLabel().equals("")) {
            return true;
        }

        if (buttons[0][2].getLabel().equals(buttons[1][1].getLabel()) && 
            buttons[1][1].getLabel().equals(buttons[2][0].getLabel()) && 
            !buttons[0][2].getLabel().equals("")) {
            return true;
        }

        return false;
    }

    private boolean isDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getLabel().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showWinner(String winner) {
        Dialog dialog = new Dialog(this, "Game Over", true);
        dialog.setLayout(new FlowLayout());
        dialog.add(new Label("Player " + winner + " wins!"));
        Button okButton = new Button("OK");
        okButton.addActionListener(e -> dialog.setVisible(false));
        dialog.add(okButton);
        dialog.setSize(200, 100);
        dialog.setVisible(true);
    }

    private void showDraw() {
        Dialog dialog = new Dialog(this, "Game Over", true);
        dialog.setLayout(new FlowLayout());
        dialog.add(new Label("It's a draw!"));
        Button okButton = new Button("OK");
        okButton.addActionListener(e -> dialog.setVisible(false));
        dialog.add(okButton);
        dialog.setSize(200, 100);
        dialog.setVisible(true);
    }

    private void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setLabel("");
                buttons[row][col].setEnabled(true);
            }
        }
        playerXTurn = true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
