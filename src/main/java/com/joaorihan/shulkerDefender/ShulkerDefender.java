package com.joaorihan.shulkerDefender;

import com.joaorihan.shulkerDefender.commands.BypassCommand;
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

        // Register Managers
        setShulkerManager(new ShulkerManager(plugin));
        setPlayerManager(new PlayerManager());

        // Setup Configs
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Register Commands
        new BypassCommand();

        // Register Listeners
        new ShulkerListener(plugin);
        new LossPreventionListener(plugin);
    }

    @Override
    public void onDisable() {
        getPlayerManager().clearBypassedPlayers();


    }
}
