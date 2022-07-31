package Util;

import main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static void SetItem(String name, Material type, int stack, List<String> lore, int loc, Inventory inv) {
        ItemStack item = new ItemStack(type, stack);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(loc, item);
    }

    public static void Enchant(ItemStack item, Inventory inv, int pos) {
        item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(item.getItemMeta().getLore());
        item.setItemMeta(meta);
        inv.setItem(pos, item);
    }



    public static void PageItem(Material material, Player player, Inventory inv2, int i2) {

        if(Main.plugin.data.get(player.getUniqueId()).GetBanBlock() != null){
            for (String name : Main.plugin.data.get(player.getUniqueId()).GetBanBlock()) {
                if (material.name().equalsIgnoreCase(name)) {
                    Util.Enchant(new ItemStack(material), inv2, i2);
                }
            }
        }
    }

    public static void addWorld() {
        ArrayList<String> names = new ArrayList<>();

        for (World worlds : Bukkit.getWorlds()) {
            names.add(worlds.getName());

        }
        Main.plugin.config.getConfig().set("worlds", names);

        Main.plugin.config.saveConfig();
    }
}
