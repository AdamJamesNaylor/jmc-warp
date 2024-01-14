package com.jupiterminingcraft.warp;

import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.UUID;

public class WarpstoneDestroyedEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Warp warp;
    private final Block block;

    public WarpstoneDestroyedEvent(Warp warp, Block block) {
        this.warp = warp;
        this.block = block;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public Warp getWarp() {
        return warp;
    }

    public Block getBlock() {
        return block;
    }
}
