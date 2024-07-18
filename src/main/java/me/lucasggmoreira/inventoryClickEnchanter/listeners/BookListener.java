package me.lucasggmoreira.inventoryClickEnchanter.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.Map;

public class BookListener implements Listener {
    @EventHandler
    public void enchant(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player player;
            ItemStack item;
            ItemStack book;
            if (e.getCursor() != null && e.getCurrentItem() != null && e.getCursor().getType() != Material.AIR && e.getCurrentItem().getType() != Material.AIR) {
                player = (Player) e.getWhoClicked();
                if (e.getCursor().getType().equals(Material.ENCHANTED_BOOK)&& e.getCurrentItem().getType() == (Material.ENCHANTED_BOOK)){
                    return;
                }
                if (e.getCursor().getType().equals(Material.ENCHANTED_BOOK)) {
                    book = e.getCursor();
                    item = e.getCurrentItem();
                } else {
                    book = e.getCurrentItem();
                    item = e.getCursor();
                }
                int foundEnchantments = 0;
                EnchantmentStorageMeta meta = (EnchantmentStorageMeta) book.getItemMeta();
                assert meta != null;
                int numberOfEnchantments = meta.getStoredEnchants().size();
                try {
                    Map<Enchantment, Integer> enchantments = meta.getStoredEnchants();
                    if (numberOfEnchantments == 0){
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            if (item.containsEnchantment(entry.getKey())) {
                                foundEnchantments++;
                            }
                            try{
                                item.addEnchantment(entry.getKey(), entry.getValue());
                            } catch (IllegalArgumentException ignored){
                                return;
                            }
                        }
                    }
                    else{
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            if (item.containsEnchantment(entry.getKey())) {
                                foundEnchantments++;
                            }
                            try{
                                item.addEnchantment(entry.getKey(), entry.getValue());
                            } catch (IllegalArgumentException ignored){
                                foundEnchantments++;
                            }
                        }
                    }
                    if (foundEnchantments == numberOfEnchantments) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 10, -12);
                    } else {
                        ItemStack enchantedItem = new ItemStack(item);
                        e.getCurrentItem().setAmount(0);
                        e.getCursor().setAmount(0);
                        e.setCurrentItem(enchantedItem);
                        e.setCancelled(true);
                        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                    }

                } catch (IllegalArgumentException ignored) {
                }
            }
        }
    }
}
