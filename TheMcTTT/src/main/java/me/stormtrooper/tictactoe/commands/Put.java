package me.l00pz.tictactoe.tictactoe.commands;

import me.l00pz.tictactoe.tictactoe.TheGame.Result;
import me.l00pz.tictactoe.tictactoe.TheGame.game;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Put implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (args.length == 1) {
                int pos = Integer.parseInt(args[0]);
                if (accept.getP1()!=null && accept.getP2()!= null) {
                    game.setProgress(Result.gameResult(accept.getBoard().getBoard()) == Result.state.In_Progress);
                    game.setWinner(String.valueOf(Result.gameResult(accept.getBoard().getBoard())));
                    if (p.equals(accept.getP1())) {
                        if (pos == 1) {
                            game.P1(accept.getBoardBlock().get(pos + 7));
                        } else if (pos == 2) {
                            game.P1(accept.getBoardBlock().get(pos + 5));
                        } else if (pos == 3) {
                            game.P1(accept.getBoardBlock().get(pos + 3));
                        } else if (pos == 4) {
                            game.P1(accept.getBoardBlock().get(pos + 1));
                        } else if (pos == 5) {
                            game.P1(accept.getBoardBlock().get(pos - 1));
                        } else if (pos == 6) {
                            game.P1(accept.getBoardBlock().get(pos - 3));
                        } else if (pos == 7) {
                            game.P1(accept.getBoardBlock().get(pos - 5));
                        } else if (pos == 8) {
                            game.P1(accept.getBoardBlock().get(pos - 7));
                        } else if (pos == 9) {
                            game.P1(accept.getBoardBlock().get(0));
                        }
                    } else if (p.equals(accept.getP2())) {
                        if (pos == 1) {
                            game.P2(accept.getBoardBlock().get(pos + 7));
                        } else if (pos == 2) {
                            game.P2(accept.getBoardBlock().get(pos + 5));
                        } else if (pos == 3) {
                            game.P2(accept.getBoardBlock().get(pos + 3));
                        } else if (pos == 4) {
                            game.P2(accept.getBoardBlock().get(pos + 1));
                        } else if (pos == 5) {
                            game.P2(accept.getBoardBlock().get(pos - 1));
                        } else if (pos == 6) {
                            game.P2(accept.getBoardBlock().get(pos - 3));
                        } else if (pos == 7) {
                            game.P2(accept.getBoardBlock().get(pos - 5));
                        } else if (pos == 8) {
                            game.P2(accept.getBoardBlock().get(pos - 7));
                        } else if (pos == 9) {
                            game.P2(accept.getBoardBlock().get(0));
                        }
                    }
                }else System.out.println("Wrong");
            }else p.sendMessage("Wrong use of command!");
        }
        return false;
    }
}
