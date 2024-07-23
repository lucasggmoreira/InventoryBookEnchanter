package me.lucasggmoreira.inventoryClickEnchanter.commands.ibeCommand.subcommands;

import me.lucasggmoreira.inventoryClickEnchanter.informations.Enchantments;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.awt.*;

public class EnchantCommand{

    final Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("InventoryBookEnchanter");

    final boolean allowUnsafeEnchantments = plugin.getConfig().getBoolean("allow-unsafe-command-enchantments");


    public void onCommand(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.of(Color.RED) + "This command is only available for players!");
        }
        else {
            if (args.length < 2) {
                sender.sendMessage(ChatColor.of(Color.RED) + "Usage: /ibe enchant <enchantment> <level>");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 10, -12);
                return;
            }
            int enchantmentLevel;
            if (args.length < 3){
                enchantmentLevel = 1;
            }
            else{
                try{
                    enchantmentLevel = Integer.parseInt(args[2]);
                }catch (Exception e){
                    sender.sendMessage(ChatColor.of(Color.RED) + "Enchantment level must be a number!");
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 10, -12);
                    return;
                }
            }
            ItemStack item = player.getInventory().getItemInMainHand();
            Enchantment enchantment = null;
            if (item.equals(new ItemStack(Material.AIR))) {
                sender.sendMessage(ChatColor.of(Color.RED) + "You need to hold an item to enchant!");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 10, -12);
            } else {
                for (int i = 0; i < Enchantments.getStringEnchantments().size(); i++) {
                    if (Enchantments.getStringEnchantments().get(i).equals(args[1])) {
                        enchantment = Enchantments.getNormalEnchantments().get(i);
                        break;
                    }
                }
                if (enchantment == null) {
                    sender.sendMessage(ChatColor.of(Color.RED) + "Invalid enchantment!");
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 10, -12);
                } else {
                    if (!allowUnsafeEnchantments) {
                        try {
                            item.addEnchantment(enchantment, enchantmentLevel);
                            ItemStack enchantedItem = new ItemStack(item);
                            player.getInventory().setItemInMainHand(enchantedItem);
                            player.sendMessage(ChatColor.of(Color.GREEN) + "Item enchanted!");
                            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        } catch (IllegalArgumentException e) {
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 10, -12);
                            if (enchantment.getMaxLevel() < enchantmentLevel) {
                                sender.sendMessage(ChatColor.of(Color.RED) + "Enchantment level is too high!");
                            } else if (enchantmentLevel < 1) {
                                sender.sendMessage(ChatColor.of(Color.RED) + "Enchantment level cannot be below one!");
                            } else {
                                sender.sendMessage(ChatColor.of(Color.RED) + "This enchantment is not compatible with this item!");
                            }
                        }
                    } else {
                        try {
                            item.addUnsafeEnchantment(enchantment, enchantmentLevel);
                            ItemStack enchantedItem = new ItemStack(item);
                            player.getInventory().setItemInMainHand(enchantedItem);
                            player.sendMessage(ChatColor.of(Color.GREEN) + "Item enchanted!");
                            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        } catch (IllegalArgumentException e) {
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 10, -12);
                            if (enchantmentLevel < 1) {
                                sender.sendMessage(ChatColor.of(Color.RED) + "Enchantment level cannot be below one!");
                            }
                        }
                    }
                }
            }
        }
    }

}
