package me.infinityz.events;

import me.infinityz.queuing.QueueManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class QueueEvent extends Event implements Cancellable {
    private boolean isCancelled;
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private QueueManager.MatchType matchType;
    private QueueManager.LadderType ladderType;
    private Player player;

    public QueueEvent(Player player, QueueManager.MatchType matchType, QueueManager.LadderType ladderType) {
        this.matchType = matchType;
        this.ladderType = ladderType;
        this.player = player;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        isCancelled = b;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public QueueManager.MatchType getMatchType() {
        return matchType;
    }

    public void setMatchType(QueueManager.MatchType matchType) {
        this.matchType = matchType;
    }

    public QueueManager.LadderType getLadderType() {
        return ladderType;
    }

    public void setLadderType(QueueManager.LadderType ladderType) {
        this.ladderType = ladderType;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

}
