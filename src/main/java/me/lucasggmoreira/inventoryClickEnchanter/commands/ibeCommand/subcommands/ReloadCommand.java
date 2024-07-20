package me.lucasggmoreira.inventoryClickEnchanter.commands.ibeCommand.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class ReloadCommand{

    final Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("InventoryBookEnchanter");


    public boolean onCommand() {
        plugin.reloadConfig();
        return true;
    }
}
