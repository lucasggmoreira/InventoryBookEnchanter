package me.lucasggmoreira.inventoryClickEnchanter.listeners.service.BookListener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.plugin.Plugin;

import java.util.Map;

public class ItemEnchanter {

    private final InventoryClickEvent event;
    private Player player;
    private ItemStack item;
    private ItemStack book;
    private ItemStack enchantedItem;

    private final boolean allowUnsafeEnchantments;
    private final boolean enchantmentsCanBeStacked;

    private Map<Enchantment, Integer> bookEnchantments;
    private int foundEnchantments;
    private int numberOfEnchantments;
    private int invalidEnchantments;


    public ItemEnchanter(InventoryClickEvent event) {
        final Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("InventoryBookEnchanter");
        this.event = event;
        this.player = (Player) event.getWhoClicked();
        this.allowUnsafeEnchantments = plugin.getConfig().getBoolean("allow-unsafe-enchantments");
        this.enchantmentsCanBeStacked = plugin.getConfig().getBoolean("enchantments-can-be-stacked");
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getItem() {
        return item;
    }

    public ItemStack getBook() {
        return book;
    }


    // Checks if the item and the book are valid, and then sets the variables accordingly
    public boolean isValid() {
        if (event.getWhoClicked() instanceof Player) {
            if (event.getCursor() != null && event.getCurrentItem() != null && event.getCursor().getType() != Material.AIR
                    && event.getCurrentItem().getType() != Material.AIR) {
                player = (Player) event.getWhoClicked();
                if (event.getCursor().getType().equals(Material.ENCHANTED_BOOK) && event.getCurrentItem().getType() == (Material.ENCHANTED_BOOK)) {
                    return false;
                }
                if (event.getCursor().getType().equals(Material.ENCHANTED_BOOK)) {
                    this.book = event.getCursor();
                    this.item = event.getCurrentItem();
                    return hasEnchantments() && canBeEnchanted();
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public void EnchantItem() {
        validateEnchantments();
        int numberOfEnchantments = bookEnchantments.size();
        if (numberOfEnchantments == invalidEnchantments || numberOfEnchantments == foundEnchantments) {
            event.setCancelled(true);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 10, -12);
            if (numberOfEnchantments == invalidEnchantments) {
                if (numberOfEnchantments == 1) {
                    player.sendMessage("This enchantment is not compatible with this item!");
                } else {
                    player.sendMessage("These enchantments are not compatible with this item!");
                }
            } else if (numberOfEnchantments == foundEnchantments) {
                if (numberOfEnchantments == 1) {
                    player.sendMessage("This item already has this enchantment!");
                } else {
                    player.sendMessage("This item already has these enchantments!");
                }
            }
        }
        else{
            ItemStack enchantedItem = new ItemStack(item);
            event.setCurrentItem(new ItemStack(Material.AIR));
            event.getWhoClicked().setItemOnCursor(new ItemStack(Material.AIR));
            event.setCurrentItem(enchantedItem);
            event.setCancelled(true);
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
        }
    }

    private boolean hasEnchantments() {
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) book.getItemMeta();
        if (meta != null) {
            int numberOfEnchantments = meta.getStoredEnchants().size();
            if (numberOfEnchantments > 0) {
                this.bookEnchantments = meta.getStoredEnchants();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean canBeEnchanted() {
        ItemStack testItem = new ItemStack(item);
        try {
            testItem.removeEnchantments();
            testItem.addEnchantment(Enchantment.UNBREAKING, 1);
            return true;
        } catch (IllegalArgumentException ignored) {
            return false;
        }

    }

    private void validateEnchantments(){
        for (Map.Entry<Enchantment, Integer> entry : bookEnchantments.entrySet()) {
            Enchantment bookEnchantment = entry.getKey();
            int enchantmentLevel = entry.getValue();
            if (!enchantmentsCanBeStacked){
                try{
                    if (item.containsEnchantment(bookEnchantment)) {
                        if (item.getEnchantmentLevel(bookEnchantment) < enchantmentLevel) {
                            item.addEnchantment(bookEnchantment, enchantmentLevel);
                        }
                        else{
                            foundEnchantments++;
                        }
                    }
                    else{
                        item.addEnchantment(bookEnchantment, enchantmentLevel);
                    }
                } catch (IllegalArgumentException ignored){
                    foundEnchantments++;
                    invalidEnchantments++;
                }
            }
            else{
                try{
                    if (item.containsEnchantment(bookEnchantment)) {
                        if (item.getEnchantmentLevel(bookEnchantment) > enchantmentLevel) {
                            foundEnchantments++;
                        }
                        else if (item.getEnchantmentLevel(bookEnchantment) == enchantmentLevel) {
                            if (item.getEnchantmentLevel(bookEnchantment) == bookEnchantment.getMaxLevel()){
                                foundEnchantments++;
                            }
                            else{
                                enchantmentLevel++;
                                item.addEnchantment(bookEnchantment, enchantmentLevel);
                            }
                        }
                        else{
                            item.addEnchantment(bookEnchantment, enchantmentLevel);
                        }
                    }
                    else{
                        item.addEnchantment(bookEnchantment, enchantmentLevel);
                    }
                } catch (IllegalArgumentException ignored){
                    foundEnchantments++;
                    invalidEnchantments++;
                }
            }
        }
    }
}



