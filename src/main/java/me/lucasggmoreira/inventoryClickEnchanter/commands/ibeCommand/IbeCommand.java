package me.lucasggmoreira.inventoryClickEnchanter.commands.ibeCommand;

import me.lucasggmoreira.inventoryClickEnchanter.commands.ibeCommand.subcommands.EnchantCommand;
import me.lucasggmoreira.inventoryClickEnchanter.commands.ibeCommand.subcommands.ReloadCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IbeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length < 1) {
                sender.sendMessage("Usage: /ibe <command>");
                return true;
            } else {
                switch (args[0]) {
                    case "enchant":
                        EnchantCommand enchantCommand = new EnchantCommand();
                        enchantCommand.onCommand(sender, args);
                        return true;
                    case "reload":
                        ReloadCommand reloadCommand = new ReloadCommand();
                        reloadCommand.onCommand();
                        sender.sendMessage("Plugin reloaded!");
                        return true;
                    case "help":
                        sender.sendMessage("""
                        Available commands:
                        
                        /ibe enchant <enchantment> <level>
                        
                        /ibe reload
                        """);
                        return true;
                    default:
                        sender.sendMessage("Invalid command. Use /ibe help for more information.");
                        return true;
                }
            }
        }
        else{
            sender.sendMessage("Only players can use this command!");
            return true;
        }

    }
}
