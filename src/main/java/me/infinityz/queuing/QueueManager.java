package me.infinityz.queuing;

import io.lettuce.core.RedisClient;
import me.infinityz.NoobstersArenaLobby;
import org.json.simple.JSONObject;

import java.util.Random;
import java.util.UUID;

public class QueueManager {
    private NoobstersArenaLobby instance;
    public RedisClient redisClient;
    private RedisPub redisPub;
    private RedisSub redisSub;

    public QueueManager(NoobstersArenaLobby instance) {
        this.instance = instance;
        this.redisClient = RedisClient.create("redis://p1p2p3p4p5p6@redis-19702.c15.us-east-1-2.ec2.cloud.redislabs.com:19702/0");
        this.redisSub = new RedisSub(this);
        this.redisPub = new RedisPub(this);

    }

    public RedisSub getRedisSub() {
        return redisSub;
    }

    public RedisPub getRedisPub() {
        return redisPub;
    }

    @SuppressWarnings("unchecked")
    public void sendMatchMakingRequest(UUID uuid, LadderType ladderType, MatchType matchType){
        assert ladderType != null;
        assert  matchType != null;
        assert uuid != null;
        JSONObject jsonString = new JSONObject();
        jsonString.put("UUID",  "" + uuid);
        jsonString.put("MatchType",  "" + matchType);
        jsonString.put("LadderType", "" + ladderType);
        jsonString.put("Time", "" + System.currentTimeMillis());
        jsonString.put("Elo", "" + (1400 + new Random().nextInt(50)+1));
        redisPub.publish("MatchMaking", jsonString.toJSONString());


    }

    public enum LadderType{
        BuildUHC, NoDebuff, Debuff

    }

    public enum MatchType{
        Ranked, Unranked, PartyGame
    }
}
