package me.stormtrooper.tictactoe;

import org.bukkit.plugin.java.JavaPlugin;

public final class TicTacToe extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Started!");
        getServer().getPluginCommand("tic-tac-toe").setExecutor(new tttCmd());
    }



}
