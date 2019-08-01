package me.infinityz.deprecated;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class Listeners implements Listener {
    private NoobstersArena instance;
    private Inventory selector;
    private ItemStack buildUHC;
    private int queued;
    private int in_game;
    private List<ArenaPlayer> builduhcQueue;
    private Map<UUID, ArenaPlayer> inGame;
    private List<Match> availableMatches;
    private List<Match> ingameMatches;

    public Listeners(NoobstersArena instance) {
        this.instance = instance;

        availableMatches = new ArrayList<Match>();
        ingameMatches = new ArrayList<Match>();
        builduhcQueue = new ArrayList<ArenaPlayer>();
        inGame = new HashMap<UUID, ArenaPlayer>();

        availableMatches.add(new Match(new Location(Bukkit.getWorlds().get(0), 0, 145, 4), new Location(Bukkit.getWorlds().get(0), 0, 145, -4), -4, 147, 0, 4, 143, -6, Bukkit.getWorlds().get(0), false, true, "Test Match"));

        selector = Bukkit.createInventory(null, 9 * 3, "MATCH MAKING");
        ItemStack item = new ItemStack(Material.LAVA_BUCKET, 1);

        selector.addItem(item);
        queued = 0;
        in_game = 0;

        buildUHC = selector.getItem(0);
        Bukkit.getScheduler().runTaskTimerAsynchronously(instance, new Runnable() {
            public void run() {
                in_game = new Random().nextInt(200);
                queued = new Random().nextInt(10);
                setMetaData(buildUHC, "&6Queued: &f" + builduhcQueue.size(),  "&6Ingame: &f" + in_game);

            }
        }, 1, 2);


        Bukkit.getScheduler().runTaskTimerAsynchronously(instance, new Runnable() {
            public void run() {
                if(builduhcQueue.isEmpty())return;
                if(builduhcQueue.size() <2)return;
                if(availableMatches.isEmpty())return;

                Match match = availableMatches.get(0);

                ArenaPlayer arenaPlayer = builduhcQueue.get(0);

                ArenaPlayer arenaPlayer2 = builduhcQueue.get(1);


                match.setPlayer1(arenaPlayer);


                match.setPlayer2(arenaPlayer2);
                arenaPlayer.match = match;

                arenaPlayer2.match = match;


                sendToMatch(match, arenaPlayer, arenaPlayer2);

                ingameMatches.add(match);
                availableMatches.remove(0);
                builduhcQueue.remove(0);
                builduhcQueue.remove(0);
                Bukkit.broadcastMessage("Match found for " + arenaPlayer.getPlayer().getName() + " and " + arenaPlayer2.getPlayer().getName());


            }
        }, 20, 5);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e){
        final Player player = e.getPlayer();
        switch (e.getAction()){
            case RIGHT_CLICK_BLOCK:
            case RIGHT_CLICK_AIR:
                if(player.getItemInHand().getType() == Material.AIR){
                    return;
                }
                switch (player.getItemInHand().getType()){
                    case DIAMOND_SWORD:{
                        e.setCancelled(true);
                        player.openInventory(selector);
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
    public void onIn(InventoryClickEvent e){
        if(e.getInventory() == null || e.getClickedInventory() == null)return;
        if(e.getView().getTopInventory().getType() == InventoryType.CRAFTING) e.setCancelled(true);
        if(e.getView().getTopInventory().getHolder() != selector.getHolder())return;
        e.setCancelled(true);
        switch (e.getCurrentItem().getType()){
            case LAVA_BUCKET:{
                matchMaking((Player) e.getWhoClicked(), LadderType.BUILD_UHC, MatchType.UNRANKED);
                break;
            }
            case AIR:{
                break;

            }
        }

    }


    @EventHandler
    public void onIn(InventoryDragEvent e){
        if(e.getInventory().getHolder() != selector.getHolder())return;
        e.setCancelled(true);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.getPlayer().teleport(Bukkit.getWorlds().get(0).getSpawnLocation());

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        if(inGame.containsKey(e.getEntity().getUniqueId())){
            e.getEntity().setHealth(20.0D);



            Match match = inGame.get(e.getEntity().getUniqueId()).match;
            match.getPlayer1().getPlayer().sendMessage(ChatColor.RED + e.getEntity().getName() + " has lost the match!\n " + ChatColor.GREEN + e.getEntity().getKiller().getName() + " wins!");

            match.getPlayer2().getPlayer().sendMessage(ChatColor.RED + e.getEntity().getName() + " has lost the match!\n " + ChatColor.GREEN + e.getEntity().getKiller().getName() + " wins!");
            inGame.remove(match.getPlayer1().getPlayer().getUniqueId());
            inGame.remove(match.getPlayer2().getPlayer().getUniqueId());

            Location spawn = Bukkit.getWorlds().get(0).getSpawnLocation();
            match.getPlayer1().getPlayer().teleport(spawn);
            match.getPlayer2().getPlayer().teleport(spawn);
            prepare(match.getPlayer1().getPlayer());
            prepare(match.getPlayer2().getPlayer());

            ingameMatches.remove(match);
            availableMatches.add(match);

        }
    }

    private ItemStack setMetaData(ItemStack itemStack, String... str){
        ItemMeta meta = itemStack.getItemMeta();
        List<String> lines = new ArrayList<String>();
        for (String s : str) {
            lines.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        meta.setLore(lines);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    private void matchMaking(Player player, LadderType ladderType, MatchType matchType){
        player.getOpenInventory().close();
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou've been added to &f" + matchType + " " + ladderType +"&a matchmaking!"));
        builduhcQueue.add(new ArenaPlayer(player, 1000));

    }

    private void sendToMatch(Match match, ArenaPlayer... players){
        prepare(players[0].getPlayer());
        prepare(players[1].getPlayer());
        inGame.put(players[0].getPlayer().getUniqueId(), players[0]);
        inGame.put(players[1].getPlayer().getUniqueId(), players[1]);

        players[0].getPlayer().teleport(match.getSpawn1());
        players[1].getPlayer().teleport(match.getSpawn2());

    }
    private void prepare(final Player player){
        Bukkit.getScheduler().runTask(instance, new Runnable() {
            public void run() {
                player.setGameMode(GameMode.SURVIVAL);
                player.setHealth(20);
                player.setFoodLevel(20);
                player.setSaturation(1.0F);
                player.getInventory().clear();
                player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
            }
        });

    }

    enum LadderType{
        BUILD_UHC, POT

    }
    enum MatchType{
        RANKED, UNRANKED
    }


}
