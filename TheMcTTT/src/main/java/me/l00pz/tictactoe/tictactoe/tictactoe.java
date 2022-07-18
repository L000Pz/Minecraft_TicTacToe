package me.l00pz.tictactoe.tictactoe;


import me.l00pz.tictactoe.tictactoe.DB.DataBase;
import me.l00pz.tictactoe.tictactoe.DB.Load;
import me.l00pz.tictactoe.tictactoe.DB.Save;
import me.l00pz.tictactoe.tictactoe.Listeners.Events;
import me.l00pz.tictactoe.tictactoe.TheGame.game;
import me.l00pz.tictactoe.tictactoe.commands.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.sql.SQLException;


public final class tictactoe extends JavaPlugin {

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
