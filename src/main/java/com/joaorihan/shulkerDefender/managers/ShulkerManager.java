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

    /**
     * Main method for the wither functionality.
     * Starts a counter for a player and applied wither effects
     * @param player Target
     */
    public void startWitherTimer(Player player){
        if (!plugin.getConfig().getBoolean("wither-damage"))
            return;

        timers.put(player.getUniqueId(), 0);

        // Creates a task which runs default procedures
        BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            // Checks if the player still has a box in their inventory
            if (!Checks.containsPluginShulker(player.getInventory())){
                // if not, cancel tasks
                cancelTasks(player.getUniqueId());
                return;
            }

            // if they do, run:
            increaseTimer(player);
            applyWitherEffect(player);
        }, 0L, 20L);



        // Adds the referring task to List<BukkitTask> tasks
        if (tasks.containsKey(player.getUniqueId()))
            tasks.get(player.getUniqueId()).add(task);
        else
            tasks.put(player.getUniqueId(), Collections.singletonList(task));

    }

    /**
     * Increments the player's {@link #timers}, and {@link #sendWitherAlert(Player)}
     * @param player Player to increment timer from
     */
    private void increaseTimer(Player player){
        getTimers().replace(player.getUniqueId(), getTimers().get(player.getUniqueId()) + 1);

        sendWitherAlert(player);
    }

    /**
     * Cancels all {@link #tasks} for a specified player
     * @param player UUID of player to cancel the tasks from
     */
    public void cancelTasks(UUID player){
        if (!tasks.containsKey(player)){
            return;
        }
        tasks.get(player).forEach(BukkitTask::cancel);
        tasks.remove(player);
        timers.remove(player);
    }

    /**
     * Creates the wither effect, if the players timer is greater than the maximum allowed.
     * @param player Player to receive the effect
     */
    private void applyWitherEffect(Player player){
        //todo: change for config check
        if (getTimers().get(player.getUniqueId()) >= 600)
            player.addPotionEffect(PotionEffectType.WITHER.createEffect(100, 2));
    }

    /**
     * Checks the player's current Timer status, and sends an alert, if applicable.
     * @param player Alert receiver
     */
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
