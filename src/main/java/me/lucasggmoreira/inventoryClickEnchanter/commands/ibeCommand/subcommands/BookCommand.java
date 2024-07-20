package me.lucasggmoreira.inventoryClickEnchanter.commands.ibeCommand.subcommands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class BookCommand {
    public boolean onCommand(CommandSender sender) {
        if (sender instanceof Player player){
            try {
                ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
                EnchantmentStorageMeta meta = (EnchantmentStorageMeta) book.getItemMeta();
                meta.addStoredEnchant(Enchantment.SHARPNESS, 5, true);
                meta.addStoredEnchant(Enchantment.UNBREAKING, 3, true);
                meta.addStoredEnchant(Enchantment.PROTECTION, 4, true);
                book.setItemMeta(meta);
                player.getInventory().addItem(book);
            } catch (Exception e) {
                sender.sendMessage(e.getMessage());
            }
        }
        else{
            sender.sendMessage("Only players can use this command!");
        }
        return true;
    }
}
