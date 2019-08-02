package me.infinityz.listeners;

import me.infinityz.NoobstersArenaLobby;
import me.infinityz.events.QueueEvent;
import me.infinityz.utils.scoreboard.ArenaScoreboard;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class QueueAndMatchListener implements Listener {
    private NoobstersArenaLobby instance;

    public QueueAndMatchListener(NoobstersArenaLobby instance) {
        this.instance = instance;
    }

    @EventHandler(priority =  EventPriority.HIGHEST)
    public void onQueueEvent(QueueEvent event){
        if(event.isCancelled())return;
        event.getPlayer().sendMessage(ChatColor.GREEN + "You have joined the queue for " +ChatColor.WHITE + event.getMatchType() + " " + event.getLadderType());
        event.getPlayer().getOpenInventory().close();
        if(instance.getScoreboardManager().getUuidArenaScoreboardMap().containsKey(event.getPlayer().getUniqueId())) {
            instance.getScoreboardManager().getUuidArenaScoreboardMap().get(event.getPlayer().getUniqueId()).destroy();
            instance.getScoreboardManager().getUuidArenaScoreboardMap().remove(event.getPlayer().getUniqueId());
        }

        ArenaScoreboard arenaScoreboard = new ArenaScoreboard(event.getPlayer(),
                "&l&e  Noobsters Practice  ",
                "&m&l------------------",
                "&eOnline: &f" + Bukkit.getOnlinePlayers().size(),
                "&eIn fights: &f0",
                "&m&l-------------&m&l-----",
                "&eQueue Time: &f00:00",
                "&a" + event.getMatchType() + event.getLadderType(),
                "&m&l-------------&m&m&l-----");
        arenaScoreboard.setFights(3);
        arenaScoreboard.setOnline(2);
        arenaScoreboard.setTime(1);

        instance.getScoreboardManager().getUuidArenaScoreboardMap().put(event.getPlayer().getUniqueId(), arenaScoreboard);
/*
        Bukkit.getScheduler().runTaskTimerAsynchronously(instance, () -> {
            event.getLadderType();
            event.getMatchType();

        }, 20L, 20L);*/
    }
}
