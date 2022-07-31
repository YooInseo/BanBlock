package main;


import Data.ConfigManager;
import Data.Data;
import cmd.cmd;
import cmd.tab.cmdtab;
import events.ClickEvent;
import events.CloseEvent;
import events.PlaceEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin {
    public static Main plugin;
    public ConfigManager config;
    public HashMap<UUID, Data> data = new HashMap<UUID, Data>();

    public void onEnable() {
        plugin = this;
        config = new ConfigManager("config");

        getCommand("밴블록").setExecutor(new cmd());
        getCommand("밴블록").setTabCompleter(new cmdtab());

        Listener[] events = {new ClickEvent(), new CloseEvent(), new PlaceEvent()};
        PluginManager pm = Bukkit.getPluginManager();
        Arrays.stream(events).forEach(classes -> {
            pm.registerEvents(classes, this);
        });

        init();
    }

    @Override
    public void onDisable() {
        Bukkit.getOnlinePlayers().forEach(players -> {
            if(data.containsKey(players.getUniqueId())){
                if (players.getOpenInventory().getTopInventory().equals(data.get(players.getUniqueId()).getPageclass().getInv())) {
                    players.closeInventory();
                }

            }

        });
    }

    public void init() {
        ArrayList<String> names = new ArrayList<>();

        for (World worlds : Bukkit.getWorlds()) {
            names.add(worlds.getName());
            if (config.getConfig().getList(worlds.getName() + ".banblocks") == null) {
                plugin.config.getConfig().set(worlds.getName() + ".banblocks", new ArrayList<>());
            }
        }

        if (config.getConfig().getList("all") == null) {
            plugin.config.getConfig().set("all.banblocks", new ArrayList<>());
        }

        Main.plugin.config.getConfig().set("worlds", names);

        ArrayList<String> blockname = new ArrayList<>();

        for (World worlds : Bukkit.getWorlds()) {
            blockname.add(worlds.getName());
            Main.plugin.config.newArrayList(worlds.getName() + ".banblocks");

        }


        if (config.getConfig().getList("worlds") == null) {
            plugin.config.getConfig().set("worlds", blockname);
        }

        config.setString("CancelMessage", "");

        Main.plugin.config.saveConfig();

    }
}
