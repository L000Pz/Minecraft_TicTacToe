package me.l00pz.tictactoe.tictactoe.TheGame;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class GameValues {
    private int gameNum;
    private String p1UUID;
    private String p2UUID;
    private String board;
    private String Turn;

    private static Player p1 =null;
    private static Player p2 = null;
    private static Location P1L = null;
    private static Location P2L = null;
    private static String gameTurn;
    private static ArrayList<UUID> breaker = new ArrayList<>();
    private static ArrayList<Block> boardBlock = new ArrayList<>();
    private static ArrayList<Location> boardLocation = new ArrayList<>();
    private static ArrayList<Location> GroundLocation = new ArrayList<>();
    private static ArrayList<Location> FenceLocation = new ArrayList<>();


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

    public static Player getP1() {
        return p1;
    }

    public static void setP1(Player p1) {
        GameValues.p1 = p1;
    }

    public static Player getP2() {
        return p2;
    }

    public static void setP2(Player p2) {
        GameValues.p2 = p2;
    }

    public static Location getP1L() {
        return P1L;
    }

    public static void setP1L(Location p1L) {
        P1L = p1L;
    }

    public static Location getP2L() {
        return P2L;
    }

    public static void setP2L(Location p2L) {
        P2L = p2L;
    }

    public static String getGameTurn() {
        return gameTurn;
    }

    public static void setGameTurn(String gameTurn) {
        GameValues.gameTurn = gameTurn;
    }

    public static ArrayList<UUID> getBreaker() {
        return breaker;
    }

    public static void setBreaker(ArrayList<UUID> breaker) {
        GameValues.breaker = breaker;
    }

    public static ArrayList<Block> getBoardBlock() {
        return boardBlock;
    }

    public static void setBoardBlock(ArrayList<Block> boardBlock) {
        GameValues.boardBlock = boardBlock;
    }

    public static ArrayList<Location> getBoardLocation() {
        return boardLocation;
    }

    public static void setBoardLocation(ArrayList<Location> boardLocation) {
        GameValues.boardLocation = boardLocation;
    }


    public static ArrayList<Location> getGroundLocation() {
        return GroundLocation;
    }

    public static void setGroundLocation(ArrayList<Location> groundLocation) {
        GroundLocation = groundLocation;
    }

    public static ArrayList<Location> getFenceLocation() {
        return FenceLocation;
    }

    public static void setFenceLocation(ArrayList<Location> fenceLocation) {
        FenceLocation = fenceLocation;
    }
}
