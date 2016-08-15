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
