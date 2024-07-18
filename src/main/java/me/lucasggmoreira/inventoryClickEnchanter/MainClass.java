package me.lucasggmoreira.inventoryClickEnchanter;

import me.lucasggmoreira.inventoryClickEnchanter.listeners.BookListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MainClass extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("InventoryBookEnchanter - Plugin initialized successfully!");
        getServer().getPluginManager().registerEvents(new BookListener(), this);
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("InventoryBookEnchanter - Plugin uninitialized successfully!");
    }
}
