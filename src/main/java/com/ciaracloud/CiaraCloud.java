package com.ciaracloud;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class CiaraCloud extends JavaPlugin implements Listener {

    private ScoreboardManager scoreboardManager;

    @Override
    public void onEnable() {
        scoreboardManager = new ScoreboardManager(this);
        getServer().getPluginManager().registerEvents(this, this);
        System.out.println("CiaraCloud activé");
    }

    @Override
    public void onDisable() {
        // Logique de désactivation du plugin
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player != null && player.isOnline()) {
            System.out.println("Player joined: " + player.getName());
            scoreboardManager.updateScoreboard(player);
        }
    }
}
