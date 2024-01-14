package com.jupiterminingcraft.warp;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class WarpstoneBindEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private boolean isCancelled = false;

    private final Warp from;
    private final Warp to;
    private final Player player;

    public WarpstoneBindEvent(Warp from, Warp to, Player player) {
        this.from = from;
        this.to = to;
        this.player = player;
    }

    public Warp getFrom() {
        return from;
    }
    public Warp getTo() {
        return to;
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

    public Player getPlayer() {
        return player;
    }
}
