package me.infinityz.queuing;

import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands;

public class RedisPub {

    private QueueManager instance;
    public StatefulRedisPubSubConnection<String, String> connection;
    private RedisPubSubAsyncCommands<String, String> async;

    public RedisPub(QueueManager instance) {
        this.instance = instance;
        this.connection = instance.redisClient.connectPubSub();
        async = connection.async();
    }

    public StatefulRedisPubSubConnection<String, String> getConnection() {
        return connection;
    }

    public RedisPubSubAsyncCommands<String, String> getAsync() {
        return async;
    }

    public void publish(String channel, String message){
        async.publish(channel, message);
    }
}
