package me.l00pz.tictactoe.tictactoe.commands;

import me.l00pz.tictactoe.tictactoe.TheGame.Board;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.UUID;

public class accept implements CommandExecutor, Listener {
    private static Player p1 =null;
    private static Player p2 = null;
    private static String turn;
    private static ArrayList<UUID> breaker = new ArrayList<>();
    private static ArrayList<Block> boardBlock = new ArrayList<>();
    private static ArrayList<Location> boardLocation = new ArrayList<>();
    private static Board board = null;

    public static Player getP1() {
        return p1;
    }

    public static void setP1(Player p1) {
        accept.p1 = p1;
    }

    public static Player getP2() {
        return p2;
    }

    public static void setP2(Player p2) {
        accept.p2 = p2;
    }

    public static ArrayList<UUID> getBreaker() {
        return breaker;
    }

    public static void setBreaker(ArrayList<UUID> breaker) {
        accept.breaker = breaker;
    }

    public static ArrayList<Block> getBoardBlock() {
        return boardBlock;
    }

    public static void setBoardBlock(ArrayList<Block> boardBlock) {
        accept.boardBlock = boardBlock;
    }

    public static ArrayList<Location> getBoardLocation() {
        return boardLocation;
    }

    public static void setBoardLocation(ArrayList<Location> boardLocation) {
        accept.boardLocation = boardLocation;
    }

    public static String getTurn() {
        return turn;
    }

    public static void setTurn(String turn) {
        accept.turn = turn;
    }

    public static Board getBoard() {
        return board;
    }

    public static void setBoard(Board board) {
        accept.board = board;
    }

    public static void createBoard(Player p1, Player p2){
        board = new Board();
        Location l = p1.getLocation();
        p1.sendMessage(p2.getDisplayName() + " Has accepted your challenge!");
        p2.sendMessage("You've accepted " + p1.getDisplayName() + " challenge!!");
        p2.sendMessage("Teleporting you to them!");
        p2.teleport(l);
        l.add(3, 0, 0);
        breaker.add(p1.getUniqueId());
        breaker.add(p2.getUniqueId());
        setTurn("p1");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                l.getBlock().setType(Material.LIGHT_GRAY_WOOL);
                boardBlock.add((l.getBlock()));
                boardLocation.add(l.getBlock().getLocation());
                l.add(1, 0, 0);
            }
            l.add(-3, 0, 0);
            l.add(0, 1, 0);
        }

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            p1 = Bukkit.getPlayerExact(tttCmd.getP1());
            p2 = Bukkit.getPlayerExact(tttCmd.getP2());
            if (p2 != null) {
                createBoard(p1,p2);
                tttCmd.setNumber(-1);
            }else{
                p1.sendMessage("Player is not online!");
            }
        }
        return true;
    }
}

