package events;

import Data.Data;
import Data.StringData;
import Util.Util;
import main.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class ClickEvent implements Listener {
    @EventHandler
    public void Click(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        Inventory inv = event.getClickedInventory();

        if (event.getCurrentItem() != null) {
            Data data = Main.plugin.data.get(player.getUniqueId());

            if (Main.plugin.data.containsKey(player.getUniqueId())) {
                ArrayList<Material> materials = new ArrayList<Material>();
                if (inv.equals(data.getPageclass().getInv())) {
                    if (event.getCurrentItem().getItemMeta().hasDisplayName()) {

                        if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(StringData.NextPageName)) {
                            data.getPageclass().increase(player);
                        } else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(StringData.PreviousPageName)) {
                            data.getPageclass().decrease(player);
                        }
                    } else {
                        if (event.isShiftClick()) {
                            data.RemoveBanBlock(event.getCurrentItem().getType().name());
                            Util.SetItem(event.getCurrentItem().getItemMeta().getDisplayName(), event.getCurrentItem().getType(), event.getCurrentItem().getAmount(), event.getCurrentItem().getItemMeta().getLore(), event.getSlot(), data.getPageclass().getInv());
                        } else {
                            Util.Enchant(event.getCurrentItem(), data.getPageclass().getInv(), event.getSlot());
                            materials.add(event.getCurrentItem().getType());
                            data.addBanBlock(event.getCurrentItem().getType().name());
                        }
                    }
                    event.setCancelled(true);
                }
            } else {
                player.sendMessage("§c페이지 데이타가 존재 하지 않습니다. 기본 명령어를 입력해 주세요!");
            }

        }
    }
}
