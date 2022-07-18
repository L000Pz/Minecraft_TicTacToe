package me.l00pz.tictactoe.tictactoe.commands;

import me.l00pz.tictactoe.tictactoe.TheGame.GameValues;
import me.l00pz.tictactoe.tictactoe.tictactoe;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static org.bukkit.Bukkit.getServer;

public class tttCmd implements CommandExecutor  {
    private static Player p;
    public static Player target;
    private static int number;
    private static String p2 = null;
    private static String p1 = null;
    tictactoe plugin;

    public static String getP1() {
        return p1;
    }

    public static void setP1(String p1) {
        tttCmd.p1 = p1;
    }

    public static String getP2() {
        return p2;
    }

    public static void setP2(String p2) {
        tttCmd.p2 = p2;
    }

    public int getNumber() {
        return number;
    }

    public static void setNumber(int number) {
        tttCmd.number = number;
    }

    public tttCmd(tictactoe plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            p = (Player) sender;
            if (args.length == 1) {
                if (!GameValues.getBreaker().contains(p.getUniqueId())) {
                    target = Bukkit.getPlayerExact(args[0]);
                    if (target != null) {
                        if (!GameValues.getBreaker().contains(target.getUniqueId())) {
                            setP1(p.getDisplayName());
                            setP2(args[0]);
                            number = 10;
                            target.sendMessage(p.getDisplayName() + " Has challenged you to a game of Tic-Tac-Toe!");
                            target.sendMessage("You can accept their challenge with : " + "/<accept> or /<a> " +
                                    "\n" + "You can reject their challenge with : " + "/deny or /<d>"
                                    + "\n" + "You have 10 seconds to answer...");
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    if (number != -1) {
                                        if (number != 0) {
                                            target.sendMessage("" + number);
                                        } else {
                                            target.sendMessage("You ran out of time!");
                                            p.sendMessage("Your friend didn't accept the request!");
                                            setP1(null);
                                            p = null;
                                            setP2(null);
                                            target = null;
                                            cancel();
                                        }
                                        number--;
                                    } else if (number <= -1) {
                                        cancel();
                                    }
                                }
                            }.runTaskTimer(plugin, 0, 20);
                        } else p.sendMessage("You can't challenge a player who's already playing a game");
                    }else p.sendMessage("Player is not online!");
                }else p.sendMessage("You are playing a game, can't start a new one!");
            }else p.sendMessage("You've used a wrong command try again!!" + "\n" + "Use : /ttt <you> <your friend>");
    }return false;

}


}



