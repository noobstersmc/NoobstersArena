package me.infinityz.events;

import me.infinityz.queuing.Queue;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class QueueEvent extends Event implements Cancellable {
    private boolean isCancelled;
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private Queue.MatchType matchType;
    private Queue.LadderType ladderType;
    private Player player;

    public QueueEvent(Player player, Queue.MatchType matchType, Queue.LadderType ladderType) {
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

    public Queue.MatchType getMatchType() {
        return matchType;
    }

    public void setMatchType(Queue.MatchType matchType) {
        this.matchType = matchType;
    }

    public Queue.LadderType getLadderType() {
        return ladderType;
    }

    public void setLadderType(Queue.LadderType ladderType) {
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
