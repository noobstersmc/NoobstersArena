package me.infinityz.queuing;

import io.lettuce.core.RedisFuture;
import io.lettuce.core.pubsub.RedisPubSubListener;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands;
import org.bukkit.Bukkit;

public class RedisSub {

    private QueueManager instance;
    public StatefulRedisPubSubConnection<String, String> connection;
    public RedisPubSubAsyncCommands<String, String> async;
    public RedisFuture<Void> future;

    public RedisSub(QueueManager instance) {
        this.instance = instance;
        this.connection = instance.redisClient.connectPubSub();
        connection.addListener(new RedisPubSubListener<String, String>() {
            @Override
            public void message(String channel, String message) {
                Bukkit.broadcastMessage("[" + channel + "] " + message );

            }

            @Override
            public void message(String s, String k1, String s2) {

            }

            @Override
            public void subscribed(String s, long l) {

            }

            @Override
            public void psubscribed(String s, long l) {

            }

            @Override
            public void unsubscribed(String s, long l) {

            }

            @Override
            public void punsubscribed(String s, long l) {

            }

        });
        async = connection.async();
        future = async.subscribe("MatchMaking");
    }

    public StatefulRedisPubSubConnection<String, String> getConnection() {
        return connection;
    }

    public RedisPubSubAsyncCommands<String, String> getAsync() {
        return async;
    }

    public RedisFuture<Void> getFuture() {
        return future;
    }
}
