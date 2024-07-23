package me.lucasggmoreira.inventoryClickEnchanter.informations;

import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;

public class Enchantments {
    private static final ArrayList<String> stringEnchantments = initializeStringEnchantments();
    private static final ArrayList<Enchantment> normalEnchantments = initializeNormalEnchantments();

    public static ArrayList<Enchantment> getNormalEnchantments(){
        return normalEnchantments;
    }

    public static ArrayList<String> getStringEnchantments(){
        return stringEnchantments;
    }

    private static ArrayList<Enchantment> initializeNormalEnchantments() {
        ArrayList<Enchantment> enchantments = new ArrayList<>();
        for (Enchantment enchantment : Registry.ENCHANTMENT) {
            enchantments.add(enchantment);
        }
        return enchantments;
    }

    private static ArrayList<String> initializeStringEnchantments(){
        ArrayList<String> enchantments = new ArrayList<>();
        for (Enchantment enchantment : Registry.ENCHANTMENT) {
            String stringEnchantment = enchantment
                    .toString()
                    .substring(17)
                    .replace("]", "");
            enchantments.add(stringEnchantment);
        }
        return enchantments;
    }





}
