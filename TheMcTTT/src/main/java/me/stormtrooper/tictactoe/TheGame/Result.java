package me.l00pz.tictactoe.tictactoe.TheGame;

public class Result {
    public static boolean check(int[][] board,int row, int col){

        int pieceLoc = board[row][col];
        // each column down to up
        boolean range = (row-1 < 0);
        if (!range && (board[row-1][col] == pieceLoc)){
            range = (row-2<0);
            if(!range && (board[row-2][col] == pieceLoc)){
                return true;
            }
        }
        // diagonal down left to up right
        range = (row-1<0) || (col+1>board[0].length-1);
        if ( !range && (board[row-1][col+1] == pieceLoc)){
            range = (row-2<0) || (col+2>board[0].length-1);
            if(!range && (board[row-2][col+2] == pieceLoc)){
                return true;
            }
        }

        // left to right(checking each column from left to right)
        range = (col+1>board[0].length-1);
        if ( !range && (board[row][col+1] == pieceLoc)){
            range = (col+2>board[0].length-1);
            if(!range && (board[row][col+2] == pieceLoc)){
                return true;
            }
        }

        // diagonal up left to down right
        range =row+1>board.length-1 || (col+1>board[0].length-1);
        if ( !range && (board[row+1][col+1] == pieceLoc)){
            range = row+2>board.length-1 || (col+2>board[0].length-1);
            if(!range && (board[row+2][col+2] == pieceLoc)){
                return true;
            }
        }

        // each column up to down
        range =row+1>board.length-1;
        if ( !range && (board[row+1][col] == pieceLoc)){
            range = row+2>board.length-1;
            if(!range && (board[row+2][col] == pieceLoc)){
                return true;
            }
        }

        // diagonal up right to down left
        range =row+1>board.length-1 || (col-1<0);
        if ( !range && (board[row+1][col] == pieceLoc)){
            range =row+2>board.length-1 || (col-2<0);
            if(!range && (board[row+2][col-2] == pieceLoc)){
                return true;
            }
        }

        // each column from right to left
        range = (col-1<0);
        if ( !range && (board[row][col-1] == pieceLoc)){
            range =(col-2<0);
            if(!range && (board[row][col-2] == pieceLoc)){
                return true;
            }
        }

        // diagonal down right to up left
        range = (row-1<0) || (col-1<0);
        if ( !range && (board[row-1][col-1] == pieceLoc)){
            range = (row-2<0) || (col-2<0);
            if(!range && (board[row-2][col-2] == pieceLoc)){
                return true;
            }
        }

        return false;
    }
    public static state gameResult(int[][] board){
        for (int i = 0; i < 3 ;i++){
            for (int j = 0 ; j < 3 ; j++){
                boolean inLine = check(board , i , j);
                if (inLine){
                    if(board[i][j]==1){
                        return state.P1;
                    }else if(board[i][j]==2){
                        return state.P2;
                    }
                }
            }
        }
        return state.In_Progress;

    }
    public enum state{
        DRAW,
        P1,
        P2,
        In_Progress;
    }
}
