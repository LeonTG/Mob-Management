package com.leontg77.mobmanagement;

import com.google.common.collect.Lists;
import com.leontg77.mobmanagement.commands.MobReloadCommand;
import com.leontg77.mobmanagement.listeners.ChunkLoadListener;
import com.leontg77.mobmanagement.listeners.SpawnListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main class of the plugin.
 *
 * @author LeonTG77
 */
public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        reloadConfig();

        getConfig().addDefault("worldname1", Lists.newArrayList("COW"));
        getConfig().addDefault("worldname2", Lists.newArrayList());
        getConfig().addDefault("worldname3", Lists.newArrayList("PIG", "CHICKEN"));
        getConfig().options().copyDefaults(true);

        saveConfig();

        getCommand("mobreload").setExecutor(new MobReloadCommand(this));

        Bukkit.getPluginManager().registerEvents(new ChunkLoadListener(this), this);
        Bukkit.getPluginManager().registerEvents(new SpawnListener(this), this);
    }
}