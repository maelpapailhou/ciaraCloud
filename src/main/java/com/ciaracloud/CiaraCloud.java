package com.ciaracloud;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class CiaraCloud extends JavaPlugin {
    private ScoreboardManager scoreboardManager;

    @Override
    public void onEnable() {
        scoreboardManager = new ScoreboardManager(this);
        getCommand("afficherscoreboard").setExecutor(this);
        System.out.println("CiaraCloud activé");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("afficherscoreboard")) {
            if (sender instanceof Player) {
                scoreboardManager.updateScoreboard((Player) sender);
            }
            return true;
        }
        return false;
    }
}
