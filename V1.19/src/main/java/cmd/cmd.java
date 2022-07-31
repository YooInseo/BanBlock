package cmd;

import Data.Data;
import main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class cmd implements CommandExecutor {

    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String label,String[] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            Data data = new Data(player);
            data.init();

        } else{
            if(args[0].equalsIgnoreCase("reload")) {
                if (player.isOp()) {
                    Main.plugin.config.reloadConfig();
                    player.sendMessage("§a성공적으로 리로드를 완료 했습니다!");
                }

            } else{
                if (args.length == 1) {
                    String name = args[0];
                    if(name.equalsIgnoreCase("all")){
                        Main.plugin.data.put(player.getUniqueId(), new Data(player));
                        Data data = Main.plugin.data.get(player.getUniqueId());
                        data.setPlayer(player);
                        data.banblock();
                        data.setWorld(name);

                        data.OpenGUI(name);
                        data.getPageclass().nextsign();
                    } else if (Main.plugin.config.getConfig().getList("worlds").contains(name)) {
                        Main.plugin.data.put(player.getUniqueId(), new Data(player));
                        Data data = Main.plugin.data.get(player.getUniqueId());
                        data.setPlayer(player);
                        data.banblock();
                        data.setWorld(name);

                        data.OpenGUI(name);
                        data.getPageclass().nextsign();

                    }
                }
            }

        }

        return false;
    }
}
