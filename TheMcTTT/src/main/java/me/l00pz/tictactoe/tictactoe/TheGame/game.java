package me.l00pz.tictactoe.tictactoe.TheGame;

import me.l00pz.tictactoe.tictactoe.commands.accept;
import me.l00pz.tictactoe.tictactoe.commands.tttCmd;
import me.l00pz.tictactoe.tictactoe.tictactoe;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class game {
    private static int pos;
    private static boolean progress = Result.gameResult(accept.getBoard().getBoard()) == Result.state.In_Progress;
    private static String winner = String.valueOf(Result.gameResult(accept.getBoard().getBoard()));
    private static int count;
    private static int number;
    private static Plugin plugin = tictactoe.getPlugin(tictactoe.class);

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        game.count = count;
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
            if (GameValues.getBoardBlock().contains(b)){
                pos = GameValues.getBoardLocation().indexOf(b.getLocation())+1;

            }
        }
        if (GameValues.getGameTurn().equals("p1")) {
            try {
                if (!accept.getBoard().placed(pos)) {
                    b.setType(Material.RED_STAINED_GLASS);
                    accept.getBoard().placerP1("p1", pos);
                    GameValues.setGameTurn("p2");
                    count++;
                    progress = Result.gameResult(accept.getBoard().getBoard()) == Result.state.In_Progress;
                    winner = String.valueOf(Result.gameResult(accept.getBoard().getBoard()));
                } else {
                    GameValues.getP1().sendMessage("Already taken");
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
            if (GameValues.getBoardBlock().contains(b)){
                pos = GameValues.getBoardLocation().indexOf(b.getLocation())+1;
            }
        }
        if (GameValues.getGameTurn().equals("p2")) {
            try {
                if (!accept.getBoard().placed(pos) ) {
                    b.setType(Material.BLUE_STAINED_GLASS);
                    accept.getBoard().placerP2("p2", pos);
                    GameValues.setGameTurn("p1");
                    count++;
                    progress = Result.gameResult(accept.getBoard().getBoard()) == Result.state.In_Progress;
                    winner = String.valueOf(Result.gameResult(accept.getBoard().getBoard()));
                } else {
                    GameValues.getP2().sendMessage("Already taken");
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
        GameValues.getP1().sendMessage("This game was a draw!");
        GameValues.getP2().sendMessage("This game was a draw!");

        GameFunctions.ClearBoard();

        GameValues.getP1().sendMessage("You have 10 seconds to save this game...");
        timer(GameValues.getP1());
    }

    public static void GameOver(){
        if (getWinner().equals("P1")){
            GameValues.getP1().sendMessage("You won!");
            GameValues.getP1().playSound(GameValues.getP1().getLocation(),Sound.ENTITY_PLAYER_LEVELUP,4.0F, 4.0F);
            GameValues.getP1().playSound(GameValues.getP1().getLocation(),Sound.ENTITY_VILLAGER_AMBIENT,4.0F, 4.0F);
            GameValues.getP2().sendMessage( GameValues.getP1().getDisplayName() + " Won !");
            GameValues.getP2().playSound(GameValues.getP2().getLocation(),Sound.ENTITY_VILLAGER_NO,4.0F, 4.0F);

            Location loc = GameValues.getP1().getLocation().add(2,5,0);
            FireWork(loc);

            GameValues.getP1().sendMessage("You have 10 seconds to save this game...");
            timer(GameValues.getP1());

        }else if (getWinner().equals("P2")){
            GameValues.getP2().sendMessage("You won!");
            GameValues.getP2().playSound(GameValues.getP2().getLocation(),Sound.ENTITY_PLAYER_LEVELUP,4.0F, 4.0F);
            GameValues.getP1().sendMessage( GameValues.getP2().getDisplayName() + " Won !");
            GameValues.getP1().playSound(GameValues.getP1().getLocation(),Sound.ENTITY_VILLAGER_NO,4.0F, 4.0F);

            Location loc = GameValues.getP2().getLocation().add(2,5,0);
            FireWork(loc);

            GameValues.getP2().sendMessage("You have 10 seconds to save this game...");
            timer(GameValues.getP2());

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
                    GameFunctions.reset();
                    number--;
                    cancel();
                }

            }
        }.runTaskTimer(plugin, 0, 20);

    }

    public static void FireWork(Location loc){
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
    }

}
