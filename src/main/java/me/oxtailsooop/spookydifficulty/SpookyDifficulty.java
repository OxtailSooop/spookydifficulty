package me.oxtailsooop.spookydifficulty;

import me.oxtailsooop.spookydifficulty.Commands.SpookySpawn;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpookyDifficulty extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getCommand("spookyspawn").setExecutor(new SpookySpawn()); // this is just for testing.

        getServer().getPluginManager().registerEvents(new EventListener(), this);
    }
}
