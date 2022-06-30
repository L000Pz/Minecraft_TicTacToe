package me.l00pz.tictactoe.tictactoe.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class deny implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            tttCmd.setNumber(-1);
            Player p1 = Bukkit.getPlayerExact(tttCmd.getP1());
            Player p2 = Bukkit.getPlayerExact(tttCmd.getP2());
            p1.sendMessage(p2.getDisplayName() + " Is a coward !!");
            p1.sendMessage("You've denied " + p2.getDisplayName() + " challenge!!");
            accept.setP1(null);
            accept.setP2(null);
            tttCmd.setP1(null);
            tttCmd.setP2(null);
            accept.getBreaker().clear();
            accept.getBoardLocation().clear();
            accept.getBoardBlock().clear();

        }
        return false;
    }
}
