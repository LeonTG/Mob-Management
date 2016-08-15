/**
 * Project: Mob-Management
 * Class: com.leontg77.mobmanagement.commands.MobReloadCommand
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

package com.leontg77.mobmanagement.commands;

import com.google.common.base.Joiner;
import com.leontg77.mobmanagement.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;

/**
 * MobReload command class.
 *
 * @author LeonTG77
 */
public class MobReloadCommand implements CommandExecutor {
    private final Main plugin;

    public MobReloadCommand(Main plugin) {
        this.plugin = plugin;
    }

    private static final String PREFIX = "§2Mob Manager §8» §7";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("mob.manage")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission.");
            return true;
        }

        sender.sendMessage(PREFIX + "Reloading config...");
        plugin.reloadConfig();

        for (String key : plugin.getConfig().getKeys(false)) {
            for (String value : plugin.getConfig().getStringList(key)) {
                try {
                    EntityType.valueOf(value);
                } catch (Exception e) {
                    sender.sendMessage(ChatColor.RED + "Failed to reload configuration, " + value + " is not a valid mob type.");
                    sender.sendMessage(ChatColor.RED + "Valid values (Needs to be upper case): " + Joiner.on(", ").join(EntityType.values()));
                    return true;
                }
            }
        }

        sender.sendMessage(PREFIX + "Config reload successful.");
        return true;
    }
}
