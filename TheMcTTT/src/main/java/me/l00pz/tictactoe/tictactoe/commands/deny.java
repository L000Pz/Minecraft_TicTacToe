package me.l00pz.tictactoe.tictactoe.commands;

import me.l00pz.tictactoe.tictactoe.TheGame.GameFunctions;
import me.l00pz.tictactoe.tictactoe.TheGame.GameValues;
import me.l00pz.tictactoe.tictactoe.TheGame.game;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class deny implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (GameValues.getBreaker().contains(((Player) sender).getUniqueId())) {
                tttCmd.setNumber(-1);
                Player p1 = Bukkit.getPlayerExact(tttCmd.getP1());
                Player p2 = Bukkit.getPlayerExact(tttCmd.getP2());
                p1.sendMessage(p2.getDisplayName() + " Is a coward !!");
                p1.sendMessage("You've denied " + p2.getDisplayName() + " challenge!!");
                GameFunctions.ClearBoard();
                GameValues.setP1(null);
                GameValues.setP2(null);
                tttCmd.setP1(null);
                tttCmd.setP2(null);
                GameValues.getBreaker().clear();
                GameValues.getBoardLocation().clear();
                GameValues.getBoardBlock().clear();
            }else sender.sendMessage("You can't deny that match");
        }
        return false;
    }
}
