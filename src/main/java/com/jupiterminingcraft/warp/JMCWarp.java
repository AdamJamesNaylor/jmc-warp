package com.jupiterminingcraft.warp;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class JMCWarp extends JavaPlugin {

    @Override
    public void onEnable() {
        Items.Init();
        getServer().getPluginManager().registerEvents(new Events(), this);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() { }

    public static JMCWarp getInstance() {
        return getPlugin(JMCWarp.class);
    }
}
