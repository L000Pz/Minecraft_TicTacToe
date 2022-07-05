package me.stormtrooper.tictactoe.TheGame;

public class Board {
    private int[][] board = new int[3][3];

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void defaultBoard(){
        System.out.println("| - | - | - |");
        System.out.println("| 1 | 2 | 3 |");
        System.out.println("| - | - | - |");
        System.out.println("| 4 | 5 | 6 |");
        System.out.println("| - | - | - |");
        System.out.println("| 7 | 8 | 9 |");
        System.out.println("| - | - | - |");
    }
    public void drawer(){
        System.out.println("| - | - | - |");
        System.out.println(printRow (0));
        System.out.println("| - | - | - |");
        System.out.println(printRow (1));
        System.out.println("| - | - | - |");
        System.out.println(printRow (2));
        System.out.println("| - | - | - |");
    }
    private String printRow(int row){
        StringBuilder rowMaker = new StringBuilder("| ");
        for (int i=0;i<3;i++){
            if(board[row][i] == 0) rowMaker.append(" ");
            if(board[row][i] == 1) rowMaker.append("X");
            if(board[row][i] == 2) rowMaker.append("O");
            rowMaker.append(" | ");
        }
        rowMaker.deleteCharAt(rowMaker.lastIndexOf(" "));
        return rowMaker.toString();
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
