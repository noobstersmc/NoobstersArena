package me.infinityz.listeners;

import me.infinityz.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class LobbyProtections implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        final Player player = event.getPlayer();
        player.setGameMode(GameMode.SURVIVAL);
        player.setHealth(20.0D);
        player.setFoodLevel(20);
        player.setSaturation(1.0F);
        player.getInventory().clear();
        player.setTotalExperience(0);
        player.setLevel(0);
        player.setExp(0);
        if(!player.getActivePotionEffects().isEmpty()){
            player.getActivePotionEffects().forEach(potionEffect ->{
                player.removePotionEffect(potionEffect.getType());
            });
        }
        player.getInventory().setItem(0, ItemUtils.createItem(Material.IRON_SWORD, 1, "&l&f&lUnranked Queue", true,"&6- Right click to join unranked queue!"));
        player.getInventory().setItem(1, ItemUtils.createItem(Material.DIAMOND_SWORD, 1, "&l&6&lRanked Queue", true,"&6- Right click to join ranked queue!"));
        player.getInventory().setItem(4, ItemUtils.createItem(Material.EMERALD, 1, "&l&a&lCreate Party", false,"&6- Coming soon"));
        player.getInventory().setItem(7, ItemUtils.createItem(Material.EYE_OF_ENDER, 1, "&l&bToggle Visibility",false, "&6- Coming soon"));
        player.getInventory().setItem(8, ItemUtils.createItem(Material.BOOK, 1, "&l&f&lKit editor", false,"&6- Coming soon"));
        World lobby = Bukkit.getWorlds().get(0);
        assert lobby!= null;
        player.teleport(lobby.getSpawnLocation());
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        e.setCancelled(true);
    }
    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void damage(EntityDamageEvent event){
        event.setCancelled(true);
    }
}
