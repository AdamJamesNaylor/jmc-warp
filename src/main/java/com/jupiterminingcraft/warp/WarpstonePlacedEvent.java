package com.jupiterminingcraft.warp;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class WarpstonePlacedEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private final BlockPlaceEvent blockPlaceEvent;
    private boolean isCancelled = false;

    public BlockPlaceEvent getBlockPlaceEvent() {
        return blockPlaceEvent;
    }

    private final ItemStack warpstoneItemStack;

    public WarpstonePlacedEvent(BlockPlaceEvent event, ItemStack warpstoneItemStack) {
        blockPlaceEvent = event;
        this.warpstoneItemStack = warpstoneItemStack;
    }

    public ItemStack getWarpstoneItemStack() {
        return warpstoneItemStack;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public boolean getIsCancelled() {
        return isCancelled;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
}
