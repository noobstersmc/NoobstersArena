package me.infinityz.deprecated;

import me.infinityz.deprecated.Listeners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class NoobstersArena extends JavaPlugin {
    //ProtocolManager protocolManager;

    @Override
    public void onEnable(){
        //protocolManager = ProtocolLibrary.getProtocolManager();
        Bukkit.getPluginManager().registerEvents(new Listeners(this), this);

    }
}
