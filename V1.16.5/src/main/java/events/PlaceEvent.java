package events;

import Data.Data;
import main.Main;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;

public class PlaceEvent implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        Data data = Main.plugin.data.get(player.getUniqueId());
        ArrayList<String> materials = (ArrayList<String>) Main.plugin.config.getConfig().getList(player.getWorld().getName()+ ".banblocks");
        ArrayList<String> allmaterials = (ArrayList<String>) Main.plugin.config.getConfig().getList(  "all.banblocks");
        if(!player.isOp()){
            if(materials.contains(block.getType().name()) || allmaterials.contains(block.getType().name())){
                String test = Main.plugin.config.getString("CancelMessage");

                player.sendMessage(test);
                event.setCancelled(true);
            }
        }

    }

}
