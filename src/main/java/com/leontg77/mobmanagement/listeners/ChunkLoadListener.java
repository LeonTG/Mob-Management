package com.leontg77.mobmanagement.listeners;

import com.leontg77.mobmanagement.Main;
import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

/**
 * Chunk load listener class.
 *
 * @author Leon
 */
public class ChunkLoadListener implements Listener {
    private final Main plugin;

    public ChunkLoadListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void on(ChunkLoadEvent event) {
        Chunk chunk = event.getChunk();

        for (Entity entity : chunk.getEntities()) {
            if (plugin.getConfig().getStringList(entity.getWorld().getName()).contains(entity.getType().name())) {
                entity.remove();
            }
        }
    }
}