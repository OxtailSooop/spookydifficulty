package me.oxtailsooop.spookydifficulty;

import org.bukkit.GameRule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.WorldLoadEvent;

public class EventListener implements Listener {
    Flashlight flashlight = new Flashlight();

    @EventHandler
    public void WorldLoadEvent(WorldLoadEvent event) {
        // Game rules
        event.getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        event.getWorld().setGameRule(GameRule.DO_MOB_SPAWNING, false);
        event.getWorld().setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        event.getWorld().setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
        event.getWorld().setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        event.getWorld().setGameRule(GameRule.SHOW_DEATH_MESSAGES, false);

        event.getWorld().setTime(18000);

        // TODO: kill all flashlights at world load so we dont have unnessessary flashlights in the world.
//        for (int i = 0; i < flashlight.maxDistance; i++) {
//            for (int x = 0; x < event.getWorld().getEntities().size(); x++) {
//                if (event.getWorld().getEntities().get(x).getScoreboardTags().contains("Flashlight")) {
//                    event.getWorld().getEntities().get(x).remove();
//                }
//            }
//        }
    }

    @EventHandler
    public void PlayerMoveEvent(PlayerMoveEvent event) {
        flashlight.UpdateFlashlight(event);
    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        event.getPlayer().setWalkSpeed(0.175f);
        flashlight.PlayerJoin(event);
    }

    @EventHandler
    public void PlayerQuitEvent(PlayerQuitEvent event) {
        flashlight.KillFlashLight(event);
    }

    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event) {
        flashlight.ToggleFlashlight(event);
    }

    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent event) {
        if(event.getCursor().isSimilar(flashlight.flashlightItem)) {
            flashlight.GiveFlashlight((Player) event.getWhoClicked());
            event.setCancelled(true);
        }
    }
}
