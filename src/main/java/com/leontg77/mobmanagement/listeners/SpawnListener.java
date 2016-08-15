package com.leontg77.mobmanagement.listeners;

import com.leontg77.mobmanagement.Main;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * Spawn listener class.
 *
 * @author Leon
 */
public class SpawnListener implements Listener {
    private final Main plugin;

    public SpawnListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void on(CreatureSpawnEvent event) {
        Entity entity = event.getEntity();

        if (plugin.getConfig().getStringList(entity.getWorld().getName()).contains(entity.getType().name())) {
            event.setCancelled(true);
        }
    }
}