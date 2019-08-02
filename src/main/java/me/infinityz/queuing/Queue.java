package me.infinityz.queuing;

import me.infinityz.utils.CustomThread;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Queue {
    public static Map<UUID, LadderType> unranked = new HashMap<>();
    public MatchMaking matchMaking;

    static class MatchMaking extends CustomThread{

        public MatchMaking(int sleepInterval) {
            super(sleepInterval);
        }

        @Override
        public void logic(){
            if(unranked.isEmpty())return;
            //Matchmaking logic
            try{
                unranked.forEach(((uuid, ladderType) -> {


                }
                ));
            }catch (Exception io){
                io.printStackTrace();
            }

        }
    }

    public void init(){
        matchMaking = new MatchMaking(1000);

    }

    public void stop(){
        matchMaking.stop();
    }

    public static void queue(Player player, LadderType ladderType, MatchType matchType){
        player.sendMessage(ChatColor.GREEN + "You have joined the queue for " +ChatColor.WHITE + matchType + " " + ladderType);
        player.getOpenInventory().close();
    }



    public enum LadderType{
        BuildUHC, NoDebuff, Debuff

    }

    public enum MatchType{
        Ranked, Unranked, PartyGame
    }
}
