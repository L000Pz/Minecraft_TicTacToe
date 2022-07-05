package me.stormtrooper.tictactoe;



import me.stormtrooper.tictactoe.DB.DataBase;
import me.stormtrooper.tictactoe.DB.Load;
import me.stormtrooper.tictactoe.DB.Save;
import me.stormtrooper.tictactoe.Listeners.Events;
import me.stormtrooper.tictactoe.commands.Put;
import me.stormtrooper.tictactoe.commands.accept;
import me.stormtrooper.tictactoe.commands.deny;
import me.stormtrooper.tictactoe.commands.tttCmd;
import org.bukkit.plugin.java.JavaPlugin;


import java.sql.SQLException;


public final class TicTacToe extends JavaPlugin {

    private DataBase dataBase;
    @Override
    public void onEnable() {
        this.dataBase=new DataBase();
        try{
            this.dataBase.initializeDatabase();
        }catch (SQLException e){
            e.getMessage();
        }
        System.out.println("Started!");
        getServer().getPluginManager().registerEvents(new Events(),this);
        getServer().getPluginCommand("challenge").setExecutor(new tttCmd(this));
        getServer().getPluginCommand("accept").setExecutor(new accept());
        getServer().getPluginCommand("deny").setExecutor(new deny());
        getServer().getPluginCommand("put").setExecutor(new Put());
        getServer().getPluginCommand("save").setExecutor(new Save(dataBase));
        getServer().getPluginCommand("load").setExecutor(new Load(dataBase));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
