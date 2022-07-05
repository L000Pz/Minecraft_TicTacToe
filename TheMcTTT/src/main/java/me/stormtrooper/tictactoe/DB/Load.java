package me.stormtrooper.tictactoe.DB;

import me.stormtrooper.tictactoe.TheGame.GameValues;
import me.stormtrooper.tictactoe.TheGame.Result;
import me.stormtrooper.tictactoe.TheGame.game;
import me.stormtrooper.tictactoe.commands.accept;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.UUID;

public class Load implements CommandExecutor {
    private final DataBase dataBase;
    private static Player p;
    private int num ;

    public Load(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public GameValues getGame(int num) throws SQLException {

        GameValues gameValues= dataBase.findGame(num);
        if (gameValues == null){
            p.sendMessage("There are no save files with the name : "+ num);
        }else {
            p.sendMessage("Ok, loading file...");

        }
        return gameValues;
    }


    public void loadGame(GameValues values){
        Player p1 = (Player) Bukkit.getEntity(UUID.fromString(values.getP1UUID()));
        if (p1 == p){
            Player p2 = (Player) Bukkit.getEntity(UUID.fromString(values.getP2UUID()));
            if (p2 != null) {
                String[] part = values.getBoard().split(",");
                int pointer = 0;
                int [][] newBoard = new int[3][3];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        newBoard[i][j] = Integer.parseInt(part[pointer]);
                        pointer++;
                    }
                }
                String state = String.valueOf(Result.gameResult(newBoard));
                if (state.equals("P1")){
                    p1.sendMessage("You won that match!");
                    p2.sendMessage(p1.getDisplayName() + " won that match!");
                }else if (state.equals("P2")){
                    p2.sendMessage("You won that match!");
                    p1.sendMessage(p2.getDisplayName() + " won that match!");
                }else if (state.equals("In_Progress")) {
                    accept.getBreaker().add(p1.getUniqueId());
                    accept.getBreaker().add(p2.getUniqueId());
                    accept.setP1(p1);
                    accept.setP2(p2);
                    accept.createBoard(p1, p2);
                    for (int i=0 ; i<9 ; i++){
                        if (Integer.parseInt(part[i])==1){
                            Block b = accept.getBoardBlock().get(i);
                            game.P1(b);
                        }else if (Integer.parseInt(part[i])==2){
                            Block b = accept.getBoardBlock().get(i);
                            game.P2(b);
                        }
                    }
                    accept.setTurn(values.getTurn());
                }
            }else p1.sendMessage("The player is not online!");
        }else p.sendMessage("You can't load someone else game! ");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            p = (Player) sender;
            if (args.length==1){
                num = Integer.parseInt(args[0]);
                try {
                    loadGame(getGame(num));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
