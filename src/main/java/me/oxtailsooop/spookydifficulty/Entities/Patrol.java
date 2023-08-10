package me.oxtailsooop.spookydifficulty.Entities;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Patrol {
    public Patrol(Player player) {
        Spawn(player);
    }

    private void Spawn(Player player) {
        Location location = locationToSpawn(player);
        ItemStack leatherChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);

        // Do some ooga booga bullshit to get a black leather chestplate.
        LeatherArmorMeta leatherChestplateMeta = (LeatherArmorMeta) leatherChestplate.getItemMeta();

        leatherChestplateMeta.setColor(Color.BLACK);
        leatherChestplate.setItemMeta(leatherChestplateMeta);

        // Spawn entity.
        LivingEntity patrol = (LivingEntity) player.getWorld().spawnEntity(location, EntityType.SKELETON);

        // Just for style.
        patrol.getEquipment().setHelmet(new ItemStack(Material.WITHER_SKELETON_SKULL));
        patrol.getEquipment().setChestplate(leatherChestplate);
        patrol.setInvisible(true); // Shhh it's not a skeleton (:
        patrol.setRemoveWhenFarAway(true); // if it gets removed we wait some time and respawn it (depends on spawn algorithm).
        patrol.setCustomName("???"); // Add some eeriness, I guess.

        // Clear doesn't work \: and we need to account for the chance of a left-handed skeleton.
        patrol.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
        patrol.getEquipment().setItemInOffHand(new ItemStack(Material.AIR));

        // Potion effect (Make it unbeatable).
        patrol.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2147483647, 14, false, false));
        patrol.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2147483647, 32, false, false));
    }

    public Location locationToSpawn(Player player) {
        return player.getLocation();
    }
}
