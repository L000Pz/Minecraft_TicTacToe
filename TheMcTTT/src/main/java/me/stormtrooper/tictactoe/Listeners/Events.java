package me.l00pz.tictactoe.tictactoe.Listeners;

import me.l00pz.tictactoe.tictactoe.TheGame.Result;
import me.l00pz.tictactoe.tictactoe.commands.accept;
import me.l00pz.tictactoe.tictactoe.TheGame.game;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;
import java.util.*;

public class Events implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent be){
        if(accept.getBoardLocation().contains(be.getBlock().getLocation())) {
            be.setCancelled(true);
        }
    }
    @EventHandler
    public void OnRightClick(PlayerInteractEvent e) throws SQLException {
        Block block = null;
        try {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock() != null) {
                UUID id = e.getPlayer().getUniqueId();
                if (accept.getBreaker().contains(id)) {
                    if (accept.getBoardLocation().contains(e.getClickedBlock().getLocation())) {
                        block = e.getClickedBlock();
                        game.setProgress(Result.gameResult(accept.getBoard().getBoard()) == Result.state.In_Progress);
                        game.setWinner(String.valueOf(Result.gameResult(accept.getBoard().getBoard())));
                        if (id == accept.getP1().getUniqueId() && block.getType() == Material.LIGHT_GRAY_WOOL &&
                                 game.isProgress() && !accept.getBoard().isFull()) {
                            if (accept.getTurn().equals("p1")) {
                                game.P1(block);
                            }else e.getPlayer().sendMessage("no lol");
                        } else if (id == accept.getP2().getUniqueId() && block.getType() == Material.LIGHT_GRAY_WOOL
                                && game.isProgress() && !accept.getBoard().isFull()) {
                            if (accept.getTurn().equals("p2")) {
                                game.P2(block);
                            }else e.getPlayer().sendMessage("no lol");
                        }

                    }
                }
            }
        }catch (NullPointerException en){
            en.getMessage();
        }
    }


}
