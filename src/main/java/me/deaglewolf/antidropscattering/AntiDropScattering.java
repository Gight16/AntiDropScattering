package me.deaglewolf.antidropscattering;

import me.deaglewolf.antidropscattering.commands.ADSCommand;
import me.deaglewolf.antidropscattering.listener.BukkitListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class AntiDropScattering extends JavaPlugin {
    private boolean enabled;

    private final File configFile = new File(getDataFolder(), "config.yml");
    private YamlConfiguration yamlConfiguration;

    private static AntiDropScattering instance;

    public static AntiDropScattering getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        Bukkit.getPluginManager().registerEvents(new BukkitListener(), this);

        getCommand("ads").setExecutor(new ADSCommand());

        loadConfig();
    }

    public boolean pluginIsEnabled() {
        return this.enabled;
    }

    public void toggle(boolean b) {
        this.enabled = b;
        savePluginConfig();
    }

    private void loadConfig() {
        if (!configFile.exists() || configFile.isDirectory()) {
            configFile.delete();
            saveResource("config.yml", false);
        }

        this.yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);

        this.enabled = this.yamlConfiguration.getBoolean("enabled");
    }

    private void savePluginConfig() {
        this.yamlConfiguration.set("enabled", this.enabled);

        try {
            this.yamlConfiguration.save(this.configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
