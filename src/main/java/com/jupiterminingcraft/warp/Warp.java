package com.jupiterminingcraft.warp;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;
import java.util.stream.Collectors;

public class Warp {

    public static final Map<UUID, Warp> WARPS = new HashMap<>();

    public static Warp findWarpByLocation(Location location) {
        for (Warp warp : WARPS.values()) {
            if (warp.getLocation().equals(location))
                return warp;
        }

        return null;
    }

    public static boolean isWarpstoneBlock(Block block) {
        return findWarpByLocation(block.getLocation()) != null;
    }

    public static UUID getId(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null)
            return null;

        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        String uuidString = persistentDataContainer.get(Items.WARPSTONE_KEY, PersistentDataType.STRING);
        if (uuidString == null)
            return null;

        return UUID.fromString(uuidString);
    }

    public static void setId(ItemStack itemStack, UUID id) throws NullPointerException {
        ItemMeta warpstoneItemMeta = itemStack.getItemMeta();
        if (warpstoneItemMeta == null) {
            throw new NullPointerException("Attempt to set warpstone id on an ItemStack with null ItemMeta.");
        }
        warpstoneItemMeta.getPersistentDataContainer().set(Items.WARPSTONE_KEY, PersistentDataType.STRING, id.toString());
        itemStack.setItemMeta(warpstoneItemMeta);
    }

    private final UUID id;
    private final UUID ownerId;
    private Location location;
    private String name;
    private final List<UUID> destinations = new ArrayList<>();

    public Warp(UUID id, UUID ownerId) {
        this.id = id;
        this.ownerId = ownerId;
    }

    public UUID getId() {
        return id;
    }

    /**
     * The player who placed the block is considered the owner.
     * They are unable to use their own warpstones as this could be considered an exploit.
     * @return The ID of the player who placed the warpstone.
     */
    public UUID getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addDestination(UUID destination) {
        destinations.add(destination);
    }
    public List<UUID> getDestinations() {
        return destinations;
    }

    public UUID getFirstDestination() {
        return destinations.get(0);
    }
}
