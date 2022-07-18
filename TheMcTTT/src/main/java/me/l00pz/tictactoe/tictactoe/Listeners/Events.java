package me.l00pz.tictactoe.tictactoe.Listeners;

import me.l00pz.tictactoe.tictactoe.TheGame.GameValues;
import me.l00pz.tictactoe.tictactoe.TheGame.Result;
import me.l00pz.tictactoe.tictactoe.commands.accept;
import me.l00pz.tictactoe.tictactoe.TheGame.game;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.sql.SQLException;
import java.util.*;

public class Events implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent be){
        if(GameValues.getBoardLocation().contains(be.getBlock().getLocation()) || GameValues.getGroundLocation().contains(be.getBlock().getLocation())
                || GameValues.getFenceLocation().contains(be.getBlock().getLocation()) ) {
            be.setCancelled(true);
        }
    }

    @EventHandler
    public void OnPlace(BlockPlaceEvent e){
        if (GameValues.getBoardLocation().contains(e.getBlockAgainst().getLocation()) || GameValues.getGroundLocation().contains(e.getBlockAgainst().getLocation())
                || GameValues.getFenceLocation().contains(e.getBlockAgainst().getLocation())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void OnRightClick(PlayerInteractEvent e){
        Block block;
        try {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock() != null && e.getHand() == EquipmentSlot.HAND) {
                Player p = e.getPlayer();
                UUID id = p.getUniqueId();
                Location L =  e.getClickedBlock().getLocation();
                if (GameValues.getBreaker().contains(id) && GameValues.getBoardLocation().contains(L)) {
                    block = e.getClickedBlock();
                    move(block,p,id);
                }
            }
        }catch (NullPointerException en){
            en.getMessage();
        }
    }

    public void move(Block block,Player p,UUID id){
        game.setProgress(Result.gameResult(accept.getBoard().getBoard()) == Result.state.In_Progress);
        game.setWinner(String.valueOf(Result.gameResult(accept.getBoard().getBoard())));
        if (id == GameValues.getP1().getUniqueId() && block.getType() == Material.WHITE_STAINED_GLASS &&
                game.isProgress() && !accept.getBoard().isFull()) {
            if (GameValues.getGameTurn().equals("p1")) {
                game.P1(block);
            }else p.sendMessage("no lol");
        } else if (id == GameValues.getP2().getUniqueId() && block.getType() == Material.WHITE_STAINED_GLASS
                && game.isProgress() && !accept.getBoard().isFull()) {
            if (GameValues.getGameTurn().equals("p2")) {
                game.P2(block);
            }else p.sendMessage("no lol");
        }
    }

}
