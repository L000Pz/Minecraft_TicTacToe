package me.stormtrooper.tictactoe.TheGame;

public class GameValues {
    private int gameNum;
    private String p1UUID;
    private String p2UUID;
    private String board;
    private String Turn;

    public GameValues(int gameNum, String p1UUID, String p2UUID, String board, String turn) {
        this.gameNum = gameNum;
        this.p1UUID = p1UUID;
        this.p2UUID = p2UUID;
        this.board = board;
        Turn = turn;
    }

    public int getGameNum() {
        return gameNum;
    }

    public void setGameNum(int gameNum) {
        this.gameNum = gameNum;
    }

    public String getP1UUID() {
        return p1UUID;
    }

    public void setP1UUID(String p1UUID) {
        this.p1UUID = p1UUID;
    }

    public String getP2UUID() {
        return p2UUID;
    }

    public void setP2UUID(String p2UUID) {
        this.p2UUID = p2UUID;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getTurn() {
        return Turn;
    }

    public void setTurn(String turn) {
        Turn = turn;
    }
}
