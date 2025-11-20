import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessGame extends JFrame {
    private final JPanel boardPanel = new JPanel(new GridLayout(8, 8));
    private final JButton[][] squares = new JButton[8][8];
    private int selectedRow = -1;
    private int selectedCol = -1;

    private final String[][] board = {
            {"♜", "♞", "♝", "♛", "♚", "♝", "♞", "♜"},
            {"♟", "♟", "♟", "♟", "♟", "♟", "♟", "♟"},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"♙", "♙", "♙", "♙", "♙", "♙", "♙", "♙"},
            {"♖", "♘", "♗", "♕", "♔", "♗", "♘", "♖"}
    };

    public ChessGame() {
        setTitle("Chess Game");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initializeBoard();
        add(boardPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void initializeBoard() {
        boolean white = true;
        for (int row = 0; row < 8; row++) {
            white = !white;
            for (int col = 0; col < 8; col++) {
                JButton square = new JButton(board[row][col]);
                square.setFont(new Font("SansSerif", Font.BOLD, 36));
                square.setFocusPainted(false);
                square.setBackground(white ? Color.WHITE : Color.GRAY);

                int r = row;
                int c = col;

                square.addActionListener(e -> handleClick(r, c));

                boardPanel.add(square);
                squares[row][col] = square;
                white = !white;
            }
        }
    }

    private void handleClick(int row, int col) {
        String clickedPiece = board[row][col];

        // First click: select piece
        if (selectedRow == -1 && selectedCol == -1) {
            if (!clickedPiece.isEmpty()) {
                selectedRow = row;
                selectedCol = col;
                squares[row][col].setBackground(Color.YELLOW);
            }
            return;
        }

        // Second click: try to move
        if (isValidMove(selectedRow, selectedCol, row, col)) {
            board[row][col] = board[selectedRow][selectedCol];
            board[selectedRow][selectedCol] = "";
            squares[row][col].setText(board[row][col]);
            squares[selectedRow][selectedCol].setText("");
        }

        // Reset selection
        resetColors();
        selectedRow = -1;
        selectedCol = -1;
    }

    private boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
        String piece = board[fromRow][fromCol];

        // Disallow same square
        if (fromRow == toRow && fromCol == toCol) return false;

        int rowDiff = toRow - fromRow;
        int colDiff = toCol - fromCol;

        switch (piece) {
            case "♙": // White pawn
                return rowDiff == -1 && fromCol == toCol && board[toRow][toCol].isEmpty();
            case "♟": // Black pawn
                return rowDiff == 1 && fromCol == toCol && board[toRow][toCol].isEmpty();
            case "♖": case "♜": // Rook
                return (fromRow == toRow || fromCol == toCol) && pathClear(fromRow, fromCol, toRow, toCol);
            case "♘": case "♞": // Knight
                return (Math.abs(rowDiff) == 2 && Math.abs(colDiff) == 1) ||
                        (Math.abs(rowDiff) == 1 && Math.abs(colDiff) == 2);
            case "♗": case "♝": // Bishop
                return Math.abs(rowDiff) == Math.abs(colDiff) && pathClear(fromRow, fromCol, toRow, toCol);
            case "♕": case "♛": // Queen
                return ((fromRow == toRow || fromCol == toCol) || Math.abs(rowDiff) == Math.abs(colDiff)) &&
                        pathClear(fromRow, fromCol, toRow, toCol);
            case "♔": case "♚": // King
                return Math.abs(rowDiff) <= 1 && Math.abs(colDiff) <= 1;
        }
        return false;
    }

    private boolean pathClear(int fromRow, int fromCol, int toRow, int toCol) {
        int rowStep = Integer.compare(toRow, fromRow);
        int colStep = Integer.compare(toCol, fromCol);
        int r = fromRow + rowStep;
        int c = fromCol + colStep;

        while (r != toRow || c != toCol) {
            if (!board[r][c].isEmpty()) return false;
            r += rowStep;
            c += colStep;
        }

        return true;
    }

    private void resetColors() {
        boolean white = true;
        for (int row = 0; row < 8; row++) {
            white = !white;
            for (int col = 0; col < 8; col++) {
                squares[row][col].setBackground(white ? Color.WHITE : Color.GRAY);
                white = !white;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChessGame::new);
    }
}
