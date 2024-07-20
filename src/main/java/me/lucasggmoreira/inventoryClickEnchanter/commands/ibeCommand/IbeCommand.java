package me.lucasggmoreira.inventoryClickEnchanter.commands.ibeCommand;

import me.lucasggmoreira.inventoryClickEnchanter.commands.ibeCommand.subcommands.BookCommand;
import me.lucasggmoreira.inventoryClickEnchanter.commands.ibeCommand.subcommands.EnchantCommand;
import me.lucasggmoreira.inventoryClickEnchanter.commands.ibeCommand.subcommands.ReloadCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IbeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            switch(args[0]){
                case "enchant":
                    new EnchantCommand();
                    return true;
                case "book":
                    BookCommand bookCommand = new BookCommand();
                    bookCommand.onCommand(sender);
                    sender.sendMessage("Book received!");
                    return true;
                case "reload":
                    ReloadCommand reloadCommand = new ReloadCommand();
                    reloadCommand.onCommand();
                    sender.sendMessage("Plugin reloaded!");
                    return true;
            }
        }
        return false;
    }
}
