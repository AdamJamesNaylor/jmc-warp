package com.jupiterminingcraft.warp;

import org.bukkit.Location;

public class LocationExtensions {

    public static Location getBlockLocationBeneath(Location location) {
        return new Location(location.getWorld(), location.getBlockX(), location.getBlockY() - 1, location.getBlockZ());
    }
}
