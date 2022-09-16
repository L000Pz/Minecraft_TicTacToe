package me.l00pz.tictactoe.tictactoe.TheGame;

public class Board {
    private int[][] board = new int[3][3];

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public boolean placed (int pos){
        int row = (pos - 1)/3;
        int col = (pos - (row*3))-1;
        return board[row][col] != 0;
    }
    public void placerP1(String player, int pos){
        int row = (pos - 1)/3;
        int col = (pos - (row*3))-1;
        String turn = "p1";
        if (!placed(pos)){
            if (player.equals(turn)){
                board[row][col] = 1; //prints X
            }
        }else System.out.println("it's full dummy!");
    }
    public void placerP2(String player, int pos){
        int row = (pos - 1)/3;
        int col = (pos - (row*3))-1;
        if (board[row][col] == 0 ){
            if (player.equals("p2")){
                board[row][col] = 2;
            }
        }else System.out.println("it's full dummy!");
    }
    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
