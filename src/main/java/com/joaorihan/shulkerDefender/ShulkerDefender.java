package com.joaorihan.shulkerDefender;

import com.joaorihan.shulkerDefender.listeners.ShulkerListener;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

@Setter
@Getter
public final class ShulkerDefender extends JavaPlugin {

    private ShulkerDefender plugin;

    @Override
    public void onEnable() {
        setPlugin(this);

        getConfig().options().copyDefaults();
        saveDefaultConfig();
        new ShulkerListener(plugin);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
