package me.infinityz.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemUtils {

    public static ItemStack createItem(Material materialType, int amount, String displayName, boolean unbreakable, String... lore){
        ItemStack itemStack = new ItemStack(materialType, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();

        if(unbreakable)itemMeta.spigot().setUnbreakable(true);

        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        if(lore != null && lore.length > 0) {
            List<String> loreList = new ArrayList<>();
            for (String s : lore) {
               assert s != null;
                loreList.add(ChatColor.translateAlternateColorCodes('&', s));
            }
            itemMeta.setLore(loreList);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
