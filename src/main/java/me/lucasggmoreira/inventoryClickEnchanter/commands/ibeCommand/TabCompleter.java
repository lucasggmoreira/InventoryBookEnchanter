package me.lucasggmoreira.inventoryClickEnchanter.commands.ibeCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            List<String> arguments = new ArrayList<>();
            arguments.add("book");
            arguments.add("enchant");
            arguments.add("reload");
            return arguments;
        } else if (args.length > 1) {
            return new ArrayList<>();
        }
        return null;
    }
}
