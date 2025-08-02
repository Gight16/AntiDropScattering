package me.deaglewolf.antidropscattering.listener;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BukkitListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        List<ItemStack> drops = new ArrayList<>(event.getDrops());
        Location location = event.getEntity().getLocation();
        event.getDrops().clear();

        for (ItemStack item : drops) {
            location.getWorld().dropItem(location, item);
        }
    }
}
