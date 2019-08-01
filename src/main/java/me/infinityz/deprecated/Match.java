package me.infinityz.deprecated;

import org.bukkit.Location;
import org.bukkit.World;

public class Match {
    private Location spawn1, spawn2;
    private int x1, y1, z1, x2, y2, z2;
    private World world;
    private boolean canDestroy, regenerate;
    private String name;
    private ArenaPlayer player1, player2;

    public Match(Location spawn1, Location spawn2, int x1, int y1, int z1, int x2, int y2, int z2, World world, boolean canDestroy, boolean regenerate, String name) {
        this.spawn1 = spawn1;
        this.spawn2 = spawn2;
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
        this.world = world;
        this.canDestroy = canDestroy;
        this.regenerate = regenerate;
        this.name = name;
        player1 = null;
        player2 = null;
    }

    public ArenaPlayer getPlayer1() {
        return player1;
    }

    public void setPlayer1(ArenaPlayer player1) {
        this.player1 = player1;
    }

    public ArenaPlayer getPlayer2() {
        return player2;
    }

    public void setPlayer2(ArenaPlayer player2) {
        this.player2 = player2;
    }

    public int getZ1() {
        return z1;
    }

    public void setZ1(int z1) {
        this.z1 = z1;
    }

    public int getZ2() {
        return z2;
    }

    public void setZ2(int z2) {
        this.z2 = z2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getSpawn1() {
        return spawn1;
    }

    public void setSpawn1(Location spawn1) {
        this.spawn1 = spawn1;
    }

    public Location getSpawn2() {
        return spawn2;
    }

    public void setSpawn2(Location spawn2) {
        this.spawn2 = spawn2;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public boolean isCanDestroy() {
        return canDestroy;
    }

    public void setCanDestroy(boolean canDestroy) {
        this.canDestroy = canDestroy;
    }

    public boolean isRegenerate() {
        return regenerate;
    }

    public void setRegenerate(boolean regenerate) {
        this.regenerate = regenerate;
    }
}
