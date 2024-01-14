package com.jupiterminingcraft.warp;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class WorldExtensions {

    public static Block getBlockBeneath(Block block) {
        Location sourceLocation = block.getLocation();
        Location targetLocation = new Location(block.getWorld(), sourceLocation.getBlockX(), sourceLocation.getBlockY() - 1, sourceLocation.getBlockZ());

        return block.getWorld().getBlockAt(targetLocation);
    }

}
