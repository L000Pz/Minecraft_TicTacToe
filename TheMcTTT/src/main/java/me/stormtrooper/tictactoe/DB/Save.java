package me.stormtrooper.tictactoe.DB;



import me.stormtrooper.tictactoe.TheGame.Board;
import me.stormtrooper.tictactoe.TheGame.GameValues;
import me.stormtrooper.tictactoe.TheGame.game;
import me.stormtrooper.tictactoe.commands.accept;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class Save implements CommandExecutor {
    private final DataBase dataBase;
    private static int num;
    private static Player p;

    public Save(DataBase dataBase) {this.dataBase = dataBase;}

    public static int getNum() {
        return num;
    }

    public GameValues saveGame(int num) throws SQLException {
        GameValues gameValues= dataBase.findGame(num);
        if (gameValues == null){
            gameValues = new GameValues(num, accept.getP1().getUniqueId().toString(),accept.getP2().getUniqueId().toString(),
                    boardToString(accept.getBoard()),accept.getTurn());
            dataBase.SaveGameValues(gameValues);
            p.sendMessage("You have saved your game successfully !");
            p.sendMessage(" -> You can load your game with the load name : "+ num);
            p.sendMessage(" -> Note that the other player you just played with must be online!");
        }else p.sendMessage("There's a save file with that name, try another !");
        return gameValues;
    }

    public String boardToString(Board board){
        StringBuilder myBoard = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                myBoard.append(board.getBoard()[i][j]).append(",");
            }
        }
        return myBoard.toString();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            p = (Player) sender;
            if (args.length == 1){
                num = Integer.parseInt(args[0]);
                try {
                    saveGame(num);
                    game.setNumber(-1);

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return false;
    }
}
