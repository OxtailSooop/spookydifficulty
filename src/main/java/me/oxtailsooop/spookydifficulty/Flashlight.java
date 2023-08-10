package me.oxtailsooop.spookydifficulty;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Flashlight {
    int maxDistance = 12;

    public ItemStack flashlightItem = new ItemStack(Material.COAL);
    private ItemMeta flashlightItemMeta = flashlightItem.getItemMeta();

    private ItemStack flashlightArmorItem = new ItemStack(Material.TORCH);
    private ItemMeta flashlightArmorItemMeta = flashlightArmorItem.getItemMeta();

    public Flashlight() {
        flashlightItemMeta.setDisplayName("Flashlight");
        flashlightItem.setItemMeta(flashlightItemMeta);

        flashlightArmorItemMeta.setDisplayName("Flashlight");
        flashlightArmorItem.setItemMeta(flashlightItemMeta);
    }

    // Runs every time player moves and joins.
    public void UpdateFlashlight(PlayerMoveEvent playerMoveEvent) {
        if (playerMoveEvent.getPlayer().getInventory().getItemInOffHand().isSimilar(flashlightItem)) {
            for (int i = 0; i < maxDistance; i++) {
                for (Entity entity : playerMoveEvent.getPlayer().getWorld().getEntities()) {
                    if (entity.getScoreboardTags().contains("Flashlight" + playerMoveEvent.getPlayer().getName() + i)) {
                        entity.teleport(new Location(
                                playerMoveEvent.getPlayer().getWorld(),
                                playerMoveEvent.getPlayer().getLocation().getX() + playerMoveEvent.getPlayer().getLocation().getDirection().getX() * i,
                                playerMoveEvent.getPlayer().getLocation().getY() + playerMoveEvent.getPlayer().getLocation().getDirection().getY() * i + 0.5,
                                playerMoveEvent.getPlayer().getLocation().getZ() + playerMoveEvent.getPlayer().getLocation().getDirection().getZ() * i,
                                0,
                                0
                        ));
                    }
                }
            }
        } else {
            GiveFlashlight(playerMoveEvent.getPlayer());
        }
    }

    public void PlayerJoin(PlayerJoinEvent playerJoinEvent) {
        GiveFlashlight(playerJoinEvent.getPlayer());
        for (int i = 0; i < maxDistance; i++) {
            ArmorStand armorstand = (ArmorStand) playerJoinEvent.getPlayer().getWorld().spawnEntity(playerJoinEvent.getPlayer().getLocation(), EntityType.ARMOR_STAND);
            armorstand.setMarker(true);
            armorstand.addScoreboardTag("Flashlight" + playerJoinEvent.getPlayer().getName() + i);
            armorstand.getEquipment().setHelmet(flashlightArmorItem);
            armorstand.setInvisible(true);
            armorstand.setSmall(true);
        }
    }

    public void GiveFlashlight(Player player) {
        player.getInventory().setItemInOffHand(flashlightItem);
    }

    public void KillFlashLight(PlayerQuitEvent playerQuitEvent) {
        for (int i = 0; i < maxDistance; i++) {
            for (int x = 0; x < playerQuitEvent.getPlayer().getWorld().getEntities().size(); x++) {
                if (playerQuitEvent.getPlayer().getWorld().getEntities().get(x).getScoreboardTags().contains("Flashlight" + playerQuitEvent.getPlayer().getName() + i)) {
                    playerQuitEvent.getPlayer().getWorld().getEntities().get(x).remove();
                }
            }
        }
    }

    // TODO: Implement
    public void ToggleFlashlight(PlayerInteractEvent event) {
        // turn the flashlight on or off by adding or removing light item on armor stand
    }
}
