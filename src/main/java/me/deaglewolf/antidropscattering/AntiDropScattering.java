package me.deaglewolf.antidropscattering;

import me.deaglewolf.antidropscattering.listener.BukkitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiDropScattering extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new BukkitListener(), this);
    }
}
