package me.infinityz.utils.scoreboard;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public class ArenaScoreboard extends ScoreboardSign {
    /**
     * Create a scoreboard sign for a given player and using a specifig objective name
     *
     * @param player        the player viewing the scoreboard sign
     * @param objectiveName the name of the scoreboard sign (displayed at the top of the scoreboard)
     */
    private int online, fights, time;

    public ArenaScoreboard(Player player, String objectiveName, String... lines) {
        super(player, ChatColor.translateAlternateColorCodes('&', objectiveName));
        create();
        if(lines.length == 0)return;
        int i = 14;
        for (String line : lines) {
            setLine(i, ChatColor.translateAlternateColorCodes('&', line));
            i--;
        }
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getFights() {
        return fights;
    }

    public void setFights(int fights) {
        this.fights = fights;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
