package me.lucasggmoreira.inventoryClickEnchanter.commands.ibeCommand;

import me.lucasggmoreira.inventoryClickEnchanter.informations.Enchantments;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;


public class TabCompleter implements org.bukkit.command.TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            List<String> arguments = new ArrayList<>();
            arguments.add("help");
            arguments.add("enchant");
            arguments.add("reload");
            return arguments;
        } else if (args.length == 2) {
            if (args[0].equals("enchant")){
                return Enchantments.getStringEnchantments();
            }
            return new ArrayList<>();
        }
        return null;
    }


}
