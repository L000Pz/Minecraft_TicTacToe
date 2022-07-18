package me.l00pz.tictactoe.tictactoe.commands;

import me.l00pz.tictactoe.tictactoe.TheGame.Board;
import me.l00pz.tictactoe.tictactoe.TheGame.GameFunctions;
import me.l00pz.tictactoe.tictactoe.TheGame.GameValues;
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
    private static Board board = null;

    public static Board getBoard() {
        return board;
    }
    public static void setBoard(Board board) {
        accept.board = board;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            try {
                GameValues.setP1(Bukkit.getPlayerExact(tttCmd.getP1()));
                GameValues.setP2(Bukkit.getPlayerExact(tttCmd.getP2()));
                if (sender == GameValues.getP2()) {
                    if (!GameValues.getBreaker().contains(GameValues.getP1().getUniqueId()) || !GameValues.getBreaker().contains(GameValues.getP2().getUniqueId())) {
                        createGame(GameValues.getP1(), GameValues.getP2());
                        tttCmd.setNumber(-1);
                    } else sender.sendMessage("You can't accept again man!");
                }else sender.sendMessage("You can't accept someone else's game!");
            }catch (IllegalArgumentException e){
                sender.sendMessage("No one challenged you!");
            }
        }
        return true;
    }

    public static void createGame(Player p1, Player p2){
        board = new Board();
        Location l = p1.getLocation().add(0,3,0);
        p1.sendMessage(p2.getDisplayName() + " Has accepted your challenge!");
        p2.sendMessage("You've accepted " + p1.getDisplayName() + " challenge!!");
        p2.sendMessage("Teleporting you to them!");
        GameValues.setP1L(p1.getLocation());
        GameValues.setP2L(p2.getLocation());
        GameValues.getBreaker().add(p1.getUniqueId());
        GameValues.getBreaker().add(p2.getUniqueId());
        GameValues.setGameTurn("p1");
        GameFunctions.GroundMaker(l);
        GameFunctions.BoardMaker(GameValues.getGroundLocation().get(23));
        GameFunctions.P1Teleport(GameValues.getGroundLocation().get(10));
        GameFunctions.P2Teleport(GameValues.getGroundLocation().get(38));
    }


}

