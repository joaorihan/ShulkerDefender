package com.joaorihan.shulkerDefender.managers;

import com.joaorihan.shulkerDefender.ShulkerDefender;
import com.joaorihan.shulkerDefender.util.Checks;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Getter
public class ShulkerManager {
    private final ShulkerDefender plugin;
    private final HashMap<UUID, Integer> timers = new HashMap<>();
    private final HashMap<UUID, List<BukkitTask>> tasks =  new HashMap<>();

    public ShulkerManager(ShulkerDefender plugin){
        this.plugin = plugin;
    }

    public void startWitherTimer(Player player){

        if (!plugin.getConfig().getBoolean("wither-damage")){
            return;
        }

        timers.put(player.getUniqueId(), 0);
        // Debug
        plugin.getLogger().info("Started timer for " + player.getName());

        BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            if (!Checks.containsPluginShulker(player.getInventory())){
                cancelTasks(player.getUniqueId());
                return;
            }

            plugin.getLogger().info("Increased timer for " + player.getName());
            increaseTimer(player.getUniqueId());

            sendWitherAlert(player);

            if (getTimers().get(player.getUniqueId()) >= 600)
                player.addPotionEffect(PotionEffectType.WITHER.createEffect(100, 2));

        }, 0L, 20L);


       if (tasks.containsKey(player.getUniqueId())) {
            tasks.get(player.getUniqueId()).add(task);
        } else {
        tasks.put(player.getUniqueId(), Collections.singletonList(task));
        }
    }

    private void increaseTimer(UUID player){
        getTimers().replace(player, getTimers().get(player) + 1);
    }

    public void cancelTasks(UUID player){
        if (!tasks.containsKey(player)){
            return;
        }
        tasks.get(player).forEach(BukkitTask::cancel);
        tasks.remove(player);
        timers.remove(player);
        plugin.getLogger().info("Cancelling tasks for" + player.toString());


    }


    private void sendWitherAlert(Player player){

        switch (getTimers().get(player.getUniqueId())){
            case 300:
                player.sendMessage(ChatColor.RED + "Você tem 5 minutos para colocar sua caixa no chão.");
                break;
            case 360:
                player.sendMessage(ChatColor.RED + "Você tem 3 minutos para colocar sua caixa no chão.");
                break;
            case 540:
                player.sendMessage(ChatColor.RED + "Você tem 1 minuto para colocar sua caixa no chão!");
                break;
            case 590:
                player.sendMessage(ChatColor.RED + "Você tem 10 segundos para colocar sua caixa no chão.");
                break;
            case 595:
                player.sendMessage(ChatColor.RED + "Você tem 5 segundos para colocar sua caixa no chão.");
                break;
            case 596:
                player.sendMessage(ChatColor.RED + "Você tem 4 segundos para colocar sua caixa no chão.");
                break;
            case 597:
                player.sendMessage(ChatColor.RED + "Você tem 3 segundos para colocar sua caixa no chão.");
                break;
            case 598:
                player.sendMessage(ChatColor.RED + "Você tem 2 segundos para colocar sua caixa no chão.");
                break;
            case 599:
                player.sendMessage(ChatColor.RED + "Você tem 1 segundo para colocar sua caixa no chão.");
                break;
        }

    }

}
