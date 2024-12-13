import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;


public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650; // 50px for the text panel on top

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    int numRow = 3;
    int numCol = 3;
    JButton[][] board = new JButton[numRow][numCol];

    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    
    boolean gameOver = false;
    int turns = 0;


    TicTacToe(){
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null); //open window at the center of screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes window
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.BLUE);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.LIGHT_GRAY);
        frame.add(boardPanel);

        for (int row = 0; row < numRow; row++){
            for (int col = 0; col < numCol; col++){
                JButton tile = new JButton();
                board[row][col] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.lightGray);
                tile.setForeground(Color.black);
                tile.setFont(new Font("Times New Roman", Font.ITALIC, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        if (gameOver){
                            return;
                        }
                        JButton tile = (JButton) e.getSource();
                        if(tile.getText() == ""){
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver){
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn.");
                            }
                           
                        }
                    }
                });

            }
        }

    }
    void checkWinner(){
        //horizontal
        for (int row = 0; row < numRow; row++){
            if (board[row][0].getText() == "") continue;
            
            if (board[row][0].getText() == board[row][1].getText() && 
                board[row][1].getText() == board[row][2].getText()){
                for (int i = 0; i < numRow; i++){
                    setWinner(board[row][i]);
                }
                gameOver = true;
                return;
                }

            }
        //vertical 
       for (int col = 0; col < numCol; col++){
            if(board[0][col].getText() == "") continue;

            if(board[0][col].getText() == board[1][col].getText() &&
               board[1][col].getText() == board[2][col].getText()){
                for (int i = 0; i < numCol; i++){
                    setWinner(board[i][col]);
            }
                gameOver = true;
                return;
            }
              
        }
        //diagonal 
        if (board[0][0].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][2].getText() &&
            board[0][0].getText() != ""){
                for (int i = 0; i < 3; i++){
                    setWinner(board[i][i]);
                }
                gameOver = true;
                return;
            }
        //other diagonal 
        if (board[0][2].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][0].getText() &&
            board[0][2].getText() != ""){
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
            }
    
        if (turns == 9){
            for (int r = 0; r < 3; r++){
                for (int c = 0; c < 3; c++){
                    setTie( board[r][c]);
                }
            }
            gameOver = true;
        }

    }

        void setWinner(JButton tile){
            tile.setForeground(Color.MAGENTA);
            tile.setBackground(Color.WHITE);
            textLabel.setText("Player " + currentPlayer + " is the winner!");
        }

        void setTie (JButton tile){
            tile.setForeground(Color.orange);
            tile.setBackground(Color.WHITE);
            textLabel.setText("Tie");
        }
    }
