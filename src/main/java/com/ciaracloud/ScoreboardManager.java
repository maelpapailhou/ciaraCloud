package com.ciaracloud;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.io.File;

public class ScoreboardManager {

    private final CiaraCloud plugin;
    private final Scoreboard scoreboard;
    private final Objective objective;

    public ScoreboardManager(CiaraCloud plugin) {
        this.plugin = plugin;
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.objective = scoreboard.registerNewObjective("main", "dummy", ChatColor.GREEN + "Votre Scoreboard");

        loadScoreboard();
    }

    private void loadScoreboard() {
        File configFile = new File(plugin.getDataFolder(), "scoreboard.yml");
        if (!configFile.exists()) {
            plugin.saveResource("scoreboard.yml", false);
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        ConfigurationSection scoreboardConfig = config.getConfigurationSection("scoreboard");

        if (scoreboardConfig != null) {
            String title = ChatColor.translateAlternateColorCodes('&', scoreboardConfig.getString("title", "Votre Scoreboard"));
            objective.setDisplayName(title);
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);

            int index = 1;
            for (String line : scoreboardConfig.getStringList("lines")) {
                String formattedLine = ChatColor.translateAlternateColorCodes('&', line);
                Team team = scoreboard.registerNewTeam("line_" + index);
                team.addEntry(formattedLine);
                objective.getScore(formattedLine).setScore(index);
                index++;
            }
        }
    }

    public void updateScoreboard(Player player) {
        player.setScoreboard(scoreboard);
    }
}
