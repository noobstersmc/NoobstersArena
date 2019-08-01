package me.infinityz.deprecated;

import me.infinityz.deprecated.Match;
import org.bukkit.entity.Player;

public class ArenaPlayer {
    private Player player;
    private int elo;
    public Match match;

    public ArenaPlayer(Player player, int elo) {
        this.player = player;
        this.elo = elo;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getElo() {
        return elo;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }
}
