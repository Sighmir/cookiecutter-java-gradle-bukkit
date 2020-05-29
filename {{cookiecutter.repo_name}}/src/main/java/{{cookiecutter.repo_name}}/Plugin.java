package {{cookiecutter.repo_name}};

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
    public Logger getCustomLogger() {
        if (Bukkit.getServer() != null) {
            return Bukkit.getLogger();
        } else {
            return Logger.getGlobal();
        }
    }

    @Override
    public void onEnable() {
        getCustomLogger().info("Enabled!");
    }

    @Override
    public void onDisable() {
        getCustomLogger().info("Disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("test")) {
            player.sendMessage(ChatColor.AQUA + "Hello, World!");
            return true;
        }
        return false;
    }
}
