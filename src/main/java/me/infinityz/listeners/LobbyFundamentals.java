package me.infinityz.listeners;

import me.infinityz.NoobstersArenaLobby;
import me.infinityz.events.QueueEvent;
import me.infinityz.queuing.Queue;
import me.infinityz.utils.ItemUtils;
import me.infinityz.utils.scoreboard.ArenaScoreboard;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LobbyFundamentals implements Listener {
    private NoobstersArenaLobby instance;
    private Inventory unrankedSelector, rankedSelector;
    private ItemStack buildUhcUnranked, buildUhcRanked, noDebuffRanked, noDebuffUnranked, debuffRanked, debuffUnranked;

    public LobbyFundamentals(NoobstersArenaLobby instance) {
        this.instance = instance;


        unrankedSelector = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&a&lJoin Unranked Queue..."));
        //All the items for the unranked menu
        ItemStack buu = ItemUtils.createItem(Material.LAVA_BUCKET, 1, "&a&lBuildUHC", false, " ", "&l&aIn fights: &f0", "&l&aQueued: &f0", " ", "&eClick here to select &l&eBuildUHC.");
        unrankedSelector.setItem(0, buu);
        buildUhcUnranked = unrankedSelector.getItem(0);

        ItemStack ndbu = ItemUtils.createItem(Material.POTION, 1, "&l&aNoDebuff", false, " ", "&l&aIn fights: &f0", "&l&aQueued: &f0", " ", "&eClick here to select &l&eNoDebuff.");
        ndbu.setDurability((short)8229);
        unrankedSelector.setItem(1, ndbu);
        noDebuffUnranked = unrankedSelector.getItem(1);

        ItemStack dbu = ItemUtils.createItem(Material.POTION, 1, "&a&lBuildUHC", false, " ", "&l&aIn fights: &f0", "&l&aQueued: &f0", " ", "&eClick here to select &l&eBuildUHC.");
        dbu.setDurability((short)8196);
        unrankedSelector.setItem(2, dbu);
        debuffUnranked = unrankedSelector.getItem(2);


        rankedSelector = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6&lJoin Ranked Queue..."));
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        ArenaScoreboard arenaScoreboard = new ArenaScoreboard(event.getPlayer(),
                "&l&e  Noobsters Practice  ",
                "&m&l------------------------",
                "&eOnline: &f" + Bukkit.getOnlinePlayers().size(),
                "&eIn fights: &f0",
                "&m&l-------------&m&l-----------");
        arenaScoreboard.setFights(3);
        arenaScoreboard.setOnline(2);

        instance.getScoreboardManager().getUuidArenaScoreboardMap() .put(event.getPlayer().getUniqueId(), arenaScoreboard);
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e){

        instance.getScoreboardManager().getUuidArenaScoreboardMap() .remove(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerKickEvent(PlayerKickEvent e){

        instance.getScoreboardManager().getUuidArenaScoreboardMap() .remove(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e){
        final Player player = e.getPlayer();
        switch (e.getAction()){
            case RIGHT_CLICK_BLOCK:
            case RIGHT_CLICK_AIR:
                if(player.getItemInHand().getType() == Material.AIR){
                    return;
                }
                switch (player.getItemInHand().getType()){
                    case IRON_SWORD:{
                        e.setCancelled(true);
                        player.openInventory(unrankedSelector);
                        break;
                    }

                    case DIAMOND_SWORD:{
                        e.setCancelled(true);
                        player.openInventory(rankedSelector);
                        break;
                    }
                    case BOW:{
                        e.setCancelled(true);
                        break;
                    }
                }
                break;
        }
    }





    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent e){
        if(e.getInventory() == null || e.getClickedInventory() == null)return;
        if(e.getView().getTopInventory().getType() == InventoryType.CRAFTING) e.setCancelled(true);
        if(e.getView().getTopInventory().getHolder() != unrankedSelector.getHolder() && e.getView().getTopInventory().getHolder() != rankedSelector.getHolder())return;
        e.setCancelled(true);
        switch (e.getCurrentItem().getType()){
            case LAVA_BUCKET:{
                QueueEvent queueEvent = new QueueEvent((Player)e.getWhoClicked(), Queue.MatchType.Unranked, Queue.LadderType.BuildUHC);
                Bukkit.getPluginManager().callEvent(queueEvent);
                break;
            }
            case AIR:{
                break;

            }
        }

    }


    @EventHandler
    public void onInventoryDragEvent(InventoryDragEvent e){
        if(e.getInventory().getHolder() != unrankedSelector.getHolder() && e.getInventory().getHolder() != rankedSelector.getHolder())return;
        e.setCancelled(true);
    }
}
