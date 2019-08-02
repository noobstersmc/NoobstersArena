package me.infinityz;

import me.infinityz.listeners.LobbyFundamentals;
import me.infinityz.listeners.LobbyProtections;
import me.infinityz.listeners.QueueAndMatchListener;
import me.infinityz.queuing.Queue;
import me.infinityz.utils.scoreboard.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class NoobstersArenaLobby extends JavaPlugin {

    private ScoreboardManager scoreboardManager;
    private Queue queue;

    @Override
    public void onEnable(){
        scoreboardManager = new ScoreboardManager();
        Bukkit.getPluginManager().registerEvents(new LobbyProtections(), this);
        Bukkit.getPluginManager().registerEvents(new QueueAndMatchListener(this), this);
        Bukkit.getPluginManager().registerEvents(new LobbyFundamentals(this), this);
        queue = new Queue();
        queue.init();


    }

    @Override
    public void onDisable() {
        queue.stop();

    }

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }
}
