package com.joaorihan.shulkerDefender;

import com.joaorihan.shulkerDefender.listeners.LossPreventionListener;
import com.joaorihan.shulkerDefender.listeners.ShulkerListener;
import com.joaorihan.shulkerDefender.managers.PlayerManager;
import com.joaorihan.shulkerDefender.managers.ShulkerManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

@Setter
@Getter
public final class ShulkerDefender extends JavaPlugin {

    private ShulkerDefender plugin;
    private ShulkerManager shulkerManager;
    private PlayerManager playerManager;
    @Override
    public void onEnable() {
        setPlugin(this);

        setShulkerManager(new ShulkerManager(plugin));
        setPlayerManager(new PlayerManager());

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        new ShulkerListener(plugin);
        new LossPreventionListener(plugin);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
