package me.infinityz.utils.scoreboard;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScoreboardManager {

    private Map<UUID, ArenaScoreboard> uuidArenaScoreboardMap;

    public ScoreboardManager() {
        uuidArenaScoreboardMap = new HashMap<>();
    }

    public Map<UUID, ArenaScoreboard> getUuidArenaScoreboardMap() {
        return uuidArenaScoreboardMap;
    }

    public void setUuidArenaScoreboardMap(Map<UUID, ArenaScoreboard> uuidArenaScoreboardMap) {
        this.uuidArenaScoreboardMap = uuidArenaScoreboardMap;
    }
}
