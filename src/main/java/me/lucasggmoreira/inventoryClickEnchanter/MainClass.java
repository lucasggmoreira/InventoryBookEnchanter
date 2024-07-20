package me.lucasggmoreira.inventoryClickEnchanter;

import me.lucasggmoreira.inventoryClickEnchanter.commands.ibeCommand.IbeCommand;
import me.lucasggmoreira.inventoryClickEnchanter.commands.ibeCommand.TabCompleter;
import me.lucasggmoreira.inventoryClickEnchanter.listeners.BookListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MainClass extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage("InventoryBookEnchanter - Plugin initialized successfully!");
        getServer().getPluginManager().registerEvents(new BookListener(), this);
        this.getCommand("ibe").setTabCompleter(new TabCompleter());
        this.getCommand("ibe").setExecutor(new IbeCommand());
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("InventoryBookEnchanter - Plugin shutting down!");
    }
}
