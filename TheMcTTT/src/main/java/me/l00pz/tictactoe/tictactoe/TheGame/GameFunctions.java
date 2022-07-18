package me.l00pz.tictactoe.tictactoe.TheGame;

import me.l00pz.tictactoe.tictactoe.commands.accept;
import me.l00pz.tictactoe.tictactoe.commands.tttCmd;
import org.bukkit.Location;
import org.bukkit.Material;

public class GameFunctions {
    public static void GroundMaker(Location l){
        boolean color = false;
        for (int i = 0 ; i<7 ; i++){
            for (int j = 0; j<7 ; j++){
                if (!color) {
                    l.getBlock().setType(Material.COAL_BLOCK);
                    GameValues.getGroundLocation().add(l.getBlock().getLocation());
                    color = true;
                }else{
                    l.getBlock().setType(Material.CALCITE);
                    GameValues.getGroundLocation().add(l.getBlock().getLocation());
                    color = false;
                }
                l.add(1,0,0);
            }
            l.add(-7,0,1);
        }
        FenceMaker(l.add(-1,1,0));
    }

    public static void FenceMaker(Location l){
        for (int i=0;i<8;i++){
            l.getBlock().setType(Material.SPRUCE_FENCE);
            GameValues.getFenceLocation().add(l.getBlock().getLocation());
            l.add(1,0,0);
        }
        for (int i=0;i<8;i++){
            l.getBlock().setType(Material.SPRUCE_FENCE);
            GameValues.getFenceLocation().add(l.getBlock().getLocation());
            l.add(0,0,-1);
        }
        for (int i=0;i<8;i++){
            l.getBlock().setType(Material.SPRUCE_FENCE);
            GameValues.getFenceLocation().add(l.getBlock().getLocation());
            l.add(-1,0,0);
        }
        for (int i=0;i<8;i++){
            l.getBlock().setType(Material.SPRUCE_FENCE);
            GameValues.getFenceLocation().add(l.getBlock().getLocation());
            l.add(0,0,1);
        }
    }

    public static void BoardMaker(Location l){
        l.add(0,1,0);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                l.getBlock().setType(Material.WHITE_STAINED_GLASS);
                GameValues.getBoardBlock().add((l.getBlock()));
                GameValues.getBoardLocation().add(l.getBlock().getLocation());
                l.add(1, 0, 0);
            }
            l.add(-3, 0, 0);
            l.add(0, 1, 0);
        }
    }

    public static void P1Teleport(Location l){
        l.add(0,1,0);
        GameValues.getP1().teleport(l);
    }

    public static void P2Teleport(Location l){
        l.add(0,1,0);
        GameValues.getP2().teleport(l);
    }

    public static void ClearBoard(){
        for (int i = 0;i<9;i++){
            GameValues.getBoardBlock().get(i).setType(Material.AIR);
        }
        Location l = GameValues.getGroundLocation().get(0);
        for (int i=0;i<7;i++){
            for (int j = 0; j<7; j++) {
                l.getBlock().setType(Material.AIR);
                l.add(1,0,0);
            }
            l.add(-7,0,1);
        }

        for (int i=0;i<32;i++){
            GameValues.getFenceLocation().get(i).getBlock().setType(Material.AIR);
        }
        GameValues.getBoardBlock().clear();
        GameValues.getBoardLocation().clear();
        GameValues.getGroundLocation().clear();
        GameValues.getFenceLocation().clear();
    }

    public static void reset(){
        GameValues.getP1().teleport(GameValues.getP1L());
        GameValues.getP2().teleport(GameValues.getP2L());
        GameValues.setP1(null);
        GameValues.setP2(null);
        GameValues.setP1L(null);
        GameValues.setP2L(null);
        tttCmd.setP1(null);
        tttCmd.setP2(null);
        GameValues.getBreaker().clear();
        ClearBoard();
        game.setCount(0);
        accept.getBoard().setBoard(null);
        accept.setBoard(null);
    }

}
