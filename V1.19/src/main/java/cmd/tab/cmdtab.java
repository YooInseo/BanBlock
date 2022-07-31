package cmd.tab;

import main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class cmdtab implements TabCompleter  {
    List<String> arguments = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {

        if(args.length == 1){

            ArrayList<String> worlds = (ArrayList<String>) Main.plugin.config.getConfig().getList("worlds");

            for(int i = 0; i < worlds.size() ; i++){
                arguments.add(worlds.get(i));

            }

            return arguments;
        }
        return arguments;
    }
}
