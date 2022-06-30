package me.l00pz.tictactoe.tictactoe.TheGame;

import me.l00pz.tictactoe.tictactoe.DB.DataBase;
import me.l00pz.tictactoe.tictactoe.DB.Save;
import me.l00pz.tictactoe.tictactoe.commands.accept;
import me.l00pz.tictactoe.tictactoe.tictactoe;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;

import static org.bukkit.Bukkit.getServer;

public class game {
    private static int ID = 0;
    private static int pos;
    private static boolean progress = Result.gameResult(accept.getBoard().getBoard()) == Result.state.In_Progress;
    private static String winner = String.valueOf(Result.gameResult(accept.getBoard().getBoard()));
    private static int count;
    private static int number;
    private static Plugin plugin = tictactoe.getPlugin(tictactoe.class);


    public static int getCount() {
        return count;
    }

    public static boolean isProgress() {
        return progress;
    }

    public static void setProgress(boolean progress) {
        game.progress = progress;
    }

    public static String getWinner() {
        return winner;
    }

    public static void setWinner(String winner) {
        game.winner = winner;
    }

    public int getNumber() {
        return number;
    }

    public static void setNumber(int number) {
        game.number = number;
    }

    public static void P1(Block b){
        if (b!=null){
            if (accept.getBoardBlock().contains(b)){
                pos = accept.getBoardLocation().indexOf(b.getLocation())+1;

            }
        }
        if (accept.getTurn().equals("p1")) {
            try {
                if (!accept.getBoard().placed(pos)) {
                    b.setType(Material.RED_WOOL);
                    accept.getBoard().placerP1("p1", pos);
                    accept.setTurn("p2");
                    count++;
                    progress = Result.gameResult(accept.getBoard().getBoard()) == Result.state.In_Progress;
                    winner = String.valueOf(Result.gameResult(accept.getBoard().getBoard()));
                } else {
                    accept.getP1().sendMessage("Already taken");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong entry, try again!");
            }
            if (game.isProgress() && game.getCount() == 9) {
                game.Draw();
            }else if (!game.isProgress()){
                game.GameOver();
            }
        }
    }
    public static void P2(Block b){
        if (b!=null){
            if (accept.getBoardBlock().contains(b)){
                pos = accept.getBoardLocation().indexOf(b.getLocation())+1;
            }
        }
        if (accept.getTurn().equals("p2")) {
            try {
                if (!accept.getBoard().placed(pos) ) {
                    b.setType(Material.BLUE_WOOL);
                    accept.getBoard().placerP2("p2", pos);
                    accept.setTurn("p1");
                    count++;
                    progress = Result.gameResult(accept.getBoard().getBoard()) == Result.state.In_Progress;
                    winner = String.valueOf(Result.gameResult(accept.getBoard().getBoard()));
                } else {
                    accept.getP2().sendMessage("Already taken");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong entry, try again!");
            }
            if (game.isProgress() && game.getCount() == 9) {
                game.Draw();
            }else if (!game.isProgress()){
                game.GameOver();
            }
        }
    }
    public static void Draw(){
        accept.getP1().sendMessage("This game was a draw!");
        accept.getP2().sendMessage("This game was a draw!");

        for (int i=0;i<9;i++){
            accept.getBoardLocation().get(i).getBlock().setType(Material.AIR);
        }
        accept.getP1().sendMessage("You have 10 seconds to save this game...");
        timer(accept.getP1());
    }
    public static void GameOver(){
        if (getWinner().equals("P1")){
            accept.getP1().sendMessage("You won!");
            accept.getP1().playSound(accept.getP1().getLocation(),Sound.ENTITY_PLAYER_LEVELUP,4.0F, 4.0F);
            accept.getP1().playSound(accept.getP1().getLocation(),Sound.ENTITY_VILLAGER_AMBIENT,4.0F, 4.0F);
            accept.getP2().sendMessage( accept.getP1().getDisplayName() + " Won !");
            accept.getP2().playSound(accept.getP2().getLocation(),Sound.ENTITY_VILLAGER_NO,4.0F, 4.0F);
            for (int i=0;i<9;i++){
                accept.getBoardLocation().get(i).getBlock().setType(Material.AIR);
            }

            Location loc = accept.getP1().getLocation();
            Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
            FireworkMeta fwm = fw.getFireworkMeta();
            fwm.setPower(2);
            fwm.addEffect(FireworkEffect.builder().withColor(Color.LIME).flicker(true).build());
            fw.setFireworkMeta(fwm);
            fw.detonate();
            for(int i = 0; i<5; i++) {
                Firework fw2 = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
                fw2.setFireworkMeta(fwm);
            }

            accept.getP1().sendMessage("You have 10 seconds to save this game...");
            timer(accept.getP1());

        }else if (getWinner().equals("P2")){
            accept.getP2().sendMessage( "You won!");
            accept.getP2().playSound(accept.getP2().getLocation(),Sound.ENTITY_PLAYER_LEVELUP,4.0F, 4.0F);
            accept.getP1().sendMessage( accept.getP2().getDisplayName() + " Won !");
            accept.getP1().playSound(accept.getP1().getLocation(),Sound.ENTITY_VILLAGER_NO,4.0F, 4.0F);
            for (int i=0;i<9;i++){
                accept.getBoardLocation().get(i).getBlock().setType(Material.AIR);
            }

            Location loc = accept.getP2().getLocation();
            Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
            FireworkMeta fwm = fw.getFireworkMeta();
            fwm.setPower(2);
            fwm.addEffect(FireworkEffect.builder().withColor(Color.LIME).flicker(true).build());
            fw.setFireworkMeta(fwm);
            fw.detonate();
            for(int i = 0; i<5; i++){
                Firework fw2 = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
                fw2.setFireworkMeta(fwm);
            }

            accept.getP1().sendMessage("You have 10 seconds to save this game...");
            timer(accept.getP2());

        }
    }
    public static void timer(Player p){
        setNumber(10);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (number > -1){
                    if( number > 0){
                        p.sendMessage(""+number);
                        number--;
                    }else if (number == 0) {
                        p.sendMessage("You ran out of time, sorry!");
                        number--;
                    }
                }else if (number == -1){
                    reset();
                    number--;
                    cancel();
                }

            }
        }.runTaskTimer(plugin, 0, 20);

    }
    public static void reset(){
        accept.getBreaker().clear();
        accept.getBoardBlock().clear();
        accept.getBoardLocation().clear();
        accept.setP1(null);
        accept.setP2(null);
        count = 0;
        accept.getBoard().setBoard(null);
        accept.setBoard(null);
    }
}
