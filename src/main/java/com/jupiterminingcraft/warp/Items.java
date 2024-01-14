package com.jupiterminingcraft.warp;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class Items {

    public static final String WARP_STONE_NAME = "Warpstone";
    public static final NamespacedKey WARPSTONE_KEY = new NamespacedKey(JMCWarp.getInstance(), WARP_STONE_NAME.toLowerCase());

    public static void Init() throws NullPointerException {
        ItemStack warpstone = CreateWarpstone();
        AddWarpstoneRecipe(warpstone);
    }

    public static ItemStack CreateWarpstone() {
        return CreateWarpstone(WARP_STONE_NAME, UUID.randomUUID());
    }

    public static ItemStack CreateWarpstone(UUID id) {
        return CreateWarpstone(WARP_STONE_NAME, id);
    }

    public static ItemStack CreateWarpstone(String name, UUID id) {
        ItemStack warpstone = new ItemStack(Material.LODESTONE, 1);
        ItemMeta warpstoneItemMeta = warpstone.getItemMeta();
        if (warpstoneItemMeta == null)
            throw new NullPointerException("Item meta was null when creating Warpstone.");

        warpstoneItemMeta.setDisplayName(name);
        warpstoneItemMeta.setLore(Arrays.asList(
                "When bound to another",
                WARP_STONE_NAME + " this block will",
                "allow you to teleport",
                "between the two " + WARP_STONE_NAME + "s."
        ));
        warpstoneItemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        warpstoneItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        warpstoneItemMeta.getPersistentDataContainer().set(WARPSTONE_KEY, PersistentDataType.STRING, id.toString());
        warpstone.setItemMeta(warpstoneItemMeta);

        return warpstone;
    }
    public static void AddWarpstoneRecipe(ItemStack warpstone) {
        if (Bukkit.getRecipe(WARPSTONE_KEY) != null)
            return;

        ShapelessRecipe recipe = new ShapelessRecipe(WARPSTONE_KEY, warpstone);
        recipe.addIngredient(new RecipeChoice.ExactChoice(warpstone));
        recipe.addIngredient(1, Material.ENDER_PEARL);

        Bukkit.addRecipe(recipe);
    }

    public static <T, Z> boolean hasItemMetaKey(ItemStack item, NamespacedKey key, PersistentDataType<T, Z> persistentDataType) {
        if (item == null || key == null)
            return false;
        ItemMeta itemMeta = item.getItemMeta();
        return itemMeta != null && itemMeta.getPersistentDataContainer().has(key, persistentDataType);
    }
}
