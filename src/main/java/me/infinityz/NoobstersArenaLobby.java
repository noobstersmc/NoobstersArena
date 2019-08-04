package me.infinityz;

import me.infinityz.commands.PublishCommand;
import me.infinityz.listeners.LobbyFundamentals;
import me.infinityz.listeners.LobbyProtections;
import me.infinityz.listeners.QueueAndMatchListener;
import me.infinityz.queuing.QueueManager;
import me.infinityz.utils.scoreboard.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class NoobstersArenaLobby extends JavaPlugin {

    private ScoreboardManager scoreboardManager;
    private QueueManager queueManager;

    @Override
    public void onEnable(){
        scoreboardManager = new ScoreboardManager();
        queueManager = new QueueManager(this);
        Bukkit.getPluginManager().registerEvents(new LobbyProtections(), this);
        Bukkit.getPluginManager().registerEvents(new QueueAndMatchListener(this), this);
        Bukkit.getPluginManager().registerEvents(new LobbyFundamentals(this), this);
        getCommand("publish").setExecutor(new PublishCommand(this));

    }

    @Override
    public void onDisable() {

        queueManager.getRedisSub().connection.close();
        queueManager.getRedisPub().connection.close();
        queueManager.redisClient.shutdown();

    }

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    public QueueManager getQueueManager() {
        return queueManager;
    }
}
