package me.lucasggmoreira.inventoryClickEnchanter.listeners;

import me.lucasggmoreira.inventoryClickEnchanter.listeners.service.BookListener.ItemEnchanter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class BookListener implements Listener {

    @EventHandler
    public void enchant(InventoryClickEvent e) {
        ItemEnchanter itemEnchanter = new ItemEnchanter(e);
        if (itemEnchanter.isValid()) {
            Player player = itemEnchanter.getPlayer();
            ItemStack item = itemEnchanter.getItem();
            ItemStack book = itemEnchanter.getBook();
            itemEnchanter.EnchantItem();
        }

    }
}