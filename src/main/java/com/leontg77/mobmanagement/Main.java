/**
 * Project: Mob-Management
 * Class: com.leontg77.mobmanagement.Main
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Leon Vaktskjold <leontg77@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

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