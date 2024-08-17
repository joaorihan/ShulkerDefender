package com.joaorihan.shulkerDefender.managers;

import com.joaorihan.shulkerDefender.ShulkerDefender;
import com.joaorihan.shulkerDefender.util.Checks;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;

@Getter
public class ShulkerManager {
    private final ShulkerDefender plugin;
    private final HashMap<UUID, Integer> timers = new HashMap<>();
    private final HashMap<UUID, BukkitTask> tasks =  new HashMap<>();

    public ShulkerManager(ShulkerDefender plugin){
        this.plugin = plugin;
    }

    public void startWitherTimer(Player player){

        if (!plugin.getConfig().getBoolean("wither-damage")){
            return;
        }

        timers.put(player.getUniqueId(), 0);
        //debug
        plugin.getLogger().info("Started timer for " + player.getName());

        BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (ItemStack itemStack : player.getInventory()) {
                if (Checks.isPluginShulker(itemStack.getType())) {
                    //debug
                    plugin.getLogger().info("Increased timer for " + player.getName());
                    increaseTimer(player.getUniqueId());
                    if (getTimers().get(player.getUniqueId()) >= 10)
                        player.addPotionEffect(PotionEffectType.WITHER.createEffect(60, 0));
                    return;
                }
            }
            cancelTasks(player.getUniqueId());
        }, 0L, 20L);

        if (!task.isCancelled())
            tasks.put(player.getUniqueId(), task);
    }

    private void increaseTimer(UUID player){
        getTimers().replace(player, getTimers().get(player) + 1);
    }

    private void cancelTasks(UUID player){
        plugin.getLogger().info("Cancelling tasks for" + player.toString());
        tasks.get(player).cancel();
        tasks.remove(player);
        timers.remove(player);
    }

}
