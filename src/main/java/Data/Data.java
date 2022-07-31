package Data;

import main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class Data {
    private Player player;
    private Main plugin;

    private String world;

    public static HashMap<UUID, ArrayList<Material>> banblocks = new HashMap<>();

    HashMap<UUID, MaterialPageData> pages = new HashMap<>();


    public Data(Player player) {
        plugin = Main.plugin;
        this.player = player;
    }

    public MaterialPageData getPageclass() {
        return pages.get(player.getUniqueId());
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void init() {
        ArrayList<String> names = new ArrayList<>();

        for (World worlds : Bukkit.getWorlds()) {
            names.add(worlds.getName());
            Main.plugin.config.newArrayList(worlds.getName() + ".banblocks");


        }
        plugin.config.getConfig().set("worlds", names);
        plugin.config.saveConfig();
    }

    public void OpenGUI(String name) {
        if (!pages.containsKey(player.getUniqueId())) {
            pages.put(player.getUniqueId(), new MaterialPageData());
            MaterialPageData page = pages.get(player.getUniqueId());
            page.Show(player, name);
        }
    }

    public void banblock() {
        List<Material> materials = Arrays.stream(Material.values()).filter(Material::isSolid).collect(Collectors.toList());
        ArrayList<Material> banblock = new ArrayList<>();
        for (Material material : materials) {
            banblock.add(material);
            banblocks.put(player.getUniqueId(), banblock);
        }
    }

    public void addBanBlock(String material) {
        ArrayList<String> materials = (ArrayList<String>) Main.plugin.config.getConfig().getList(getWorld()+ ".banblocks");

        materials.add(material);
        Main.plugin.config.saveConfig();
    }


    public ArrayList<String> GetBanBlock() {
        ArrayList<String> materials = (ArrayList<String>) Main.plugin.config.getConfig().getList(getWorld()+ ".banblocks");

        return materials;
    }

    public void RemoveBanBlock(String material) {
        ArrayList<String> materials = (ArrayList<String>) Main.plugin.config.getConfig().getList(getWorld()+ ".banblocks");
        materials.remove(material);
        Main.plugin.config.saveConfig();
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public String getWorld() {
        return pages.get(player.getUniqueId()).getWorld();
    }

}
