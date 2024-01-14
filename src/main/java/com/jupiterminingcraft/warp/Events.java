package com.jupiterminingcraft.warp;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class Events implements Listener {

    @EventHandler
    public static void onPlayerMove(PlayerMoveEvent event) {
        Location from = event.getFrom();
        Location to = event.getTo();

        if (to == null ||
            (from.getBlockX() == to.getBlockX() &&
            from.getBlockY() == to.getBlockY() &&
            from.getBlockZ() == to.getBlockZ()))
            return; //The player hasn't changed block

        Location beneath = LocationExtensions.getBlockLocationBeneath(to);

        Warp warp = Warp.findWarpByLocation(beneath);
        if (warp == null)
            return; //This warpstone wasn't placed on another warp stone

        Player player = event.getPlayer();
        Warp destinationWarp = Warp.WARPS.get(warp.getFirstDestination());
        player.sendMessage(ChatColor.GREEN + "Warping to " + destinationWarp.getName());
        Location destination = destinationWarp.getLocation();

        Location adjustedDestination = new Location(destination.getWorld(), destination.getBlockX() + 0.5f, destination.getBlockY() + 1.0f, destination.getBlockZ() + 0.5f);
        player.teleport(adjustedDestination);//.sendMessage("Standing on warpstone with id " + warp.getId().toString() + " which is owned by " + warp.getOwnerId().toString());
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);
    }

    @EventHandler
    public static void onBlockPlace(BlockPlaceEvent event) {
        ItemStack itemStack = event.getItemInHand();
        UUID id = Warp.getId(itemStack);
        if (id == null)
            return; //probably not a warpstone being placed

        WarpstonePlacedEvent warpstonePlacedEvent = new WarpstonePlacedEvent(event, itemStack);
        Bukkit.getPluginManager().callEvent(warpstonePlacedEvent);
    }

    @EventHandler
    public static void onWarpStonePlaced(WarpstonePlacedEvent event) {
        if (event.getIsCancelled())
            return;

        Block block = event.getBlockPlaceEvent().getBlock();
        Location location = block.getLocation();
        Player player = event.getBlockPlaceEvent().getPlayer();
        ItemStack warpstoneItemStack = event.getWarpstoneItemStack();
        UUID warpstoneId = Warp.getId(warpstoneItemStack);

        Warp warp = new Warp(warpstoneId, player.getUniqueId());
        warp.setLocation(location);
        warp.setName(warpstoneItemStack.getItemMeta().getDisplayName());
        if (Warp.WARPS.containsKey(warpstoneId)) {
            Warp trackedWarp = Warp.WARPS.get(warpstoneId);
            trackedWarp.getDestinations().forEach(warp::addDestination);
        }

        Warp.WARPS.put(warpstoneId, warp);
        player.sendMessage(warp.getName() + " placed with id " + warpstoneId.toString());

        //now check for a bind attempt
        Location beneath = LocationExtensions.getBlockLocationBeneath(location);
        Warp warpBeneath = Warp.findWarpByLocation(beneath);
        if (warpBeneath == null) {
            return; //warpstone wasn't placed on top of another warpstone
        }

        WarpstoneBindEvent warpstoneBindEvent = new WarpstoneBindEvent(warpBeneath, warp, player);
        Bukkit.getPluginManager().callEvent(warpstoneBindEvent);
    }

    @EventHandler
    public static void onWarpstoneBind(WarpstoneBindEvent event) {

        Location location = event.getTo().getLocation();
        World world = location.getWorld();
        if (world == null) {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[JMC-Warp]: Skipping bind attempt because the Location has a null World.");
            return;
        }
        world.spawnParticle(Particle.ENCHANTMENT_TABLE, location.getBlockX() + 0.5, location.getBlockY() + 0.5, location.getBlockZ() + 0.5, 20);

        Warp from = event.getFrom();
        Warp to = event.getTo();
        from.addDestination(to.getId());
        to.addDestination(from.getId());
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.GREEN + from.getName() + " is now bound to " + to.getName() + ".");

        player.playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 1.0f, 1.0f);
    }

    @EventHandler
    public static void onCraftItem(CraftItemEvent event) throws Exception {
        if (!(event.getRecipe() instanceof Keyed))
            return;

        Keyed recipe = (Keyed) event.getRecipe();
        if (!recipe.getKey().equals(Items.WARPSTONE_KEY))
            return;

        //now we know we are crafting a warpstone, give it a unique id
        UUID warpstoneId = UUID.randomUUID();
        ItemStack warpstone = Items.CreateWarpstone(warpstoneId);

        event.setCurrentItem(warpstone);

        Player player = (Player)event.getWhoClicked();
        player.sendMessage("Crafted warpstone with id " + warpstoneId.toString());
    }

    @EventHandler
    public static void onBlockBreak(BlockBreakEvent event) {
        Warp warp = Warp.findWarpByLocation(event.getBlock().getLocation());
        if (warp == null)
            return; //this wasn't a warpstone block

        WarpstoneDestroyedEvent warpstoneDestroyEvent = new WarpstoneDestroyedEvent(warp, event.getBlock());
        Bukkit.getPluginManager().callEvent(warpstoneDestroyEvent);
    }

    @EventHandler
    public static void onBlockExplode(BlockExplodeEvent event) {
        Warp warp = Warp.findWarpByLocation(event.getBlock().getLocation());
        if (warp == null)
            return; //this wasn't a warpstone block

        WarpstoneDestroyedEvent warpstoneDestroyEvent = new WarpstoneDestroyedEvent(warp, event.getBlock());
        Bukkit.getPluginManager().callEvent(warpstoneDestroyEvent);
    }

    @EventHandler
    public static void onEntityExplode(EntityExplodeEvent event) {
        event.blockList().forEach(block -> {
            Warp warp = Warp.findWarpByLocation(block.getLocation());
            if (warp != null) {
                WarpstoneDestroyedEvent warpstoneDestroyEvent = new WarpstoneDestroyedEvent(warp, block);
                Bukkit.getPluginManager().callEvent(warpstoneDestroyEvent);
            }
        });
    }

    @EventHandler
    public static void onWarpstoneDestroy(WarpstoneDestroyedEvent event) {
        Warp warp = event.getWarp();

        ItemStack warpstoneDrop = Items.CreateWarpstone(warp.getName(), warp.getId());

        event.getBlock().setType(Material.AIR);
        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), warpstoneDrop);
    }
}
