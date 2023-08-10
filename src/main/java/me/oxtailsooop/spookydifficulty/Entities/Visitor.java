package me.oxtailsooop.spookydifficulty.Entities;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

public class Visitor {
    public Visitor(Player player) {
        Spawn(player);
    }
    private void Spawn(Player player) {
        Monster visitor = (Monster) player.getWorld().spawnEntity(new Location(player.getWorld(), 0, 0, 0, 0, 0), EntityType.ENDERMAN);
        Location location = locationToSpawn(player, visitor);

        visitor.teleport(location);

        visitor.setAI(false);
        visitor.setTarget(player);
        // spawn behind the player
        // face the player
    }

    private Location locationToSpawn(Player player, Monster entity) {
        Location location = entity.getLocation().clone();
        location.setDirection(player.getLocation().getDirection().multiply(-1));
        return location;
    }
}
