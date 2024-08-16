package com.joaorihan.shulkerDefender.listeners;

import com.joaorihan.shulkerDefender.util.Checks;
import com.joaorihan.shulkerDefender.ShulkerDefender;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.Objects;

@Getter
public class ShulkerListener implements Listener {
    final ShulkerDefender plugin;


    public ShulkerListener(ShulkerDefender plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Player player = e.getPlayer();

        if (Bukkit.getOnlinePlayers().size() >= getPlugin().getConfig().getInt("minimum-players")
                || !Checks.isPluginShulker(e.getBlock()))
            return;

        player.sendMessage(ChatColor.RED + "Você deve aguardar mais jogadores estarem online para quebrar este bloco! ("+getPlugin().getConfig().getInt("minimum-players")+")");
        e.setCancelled(true);
        //debug
        plugin.getLogger().info("[DEBUG] BlockBreakEvent cancelled");

    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e){
        System.out.println(e.getInventory().getType().getDefaultTitle());

        if (!Checks.isPluginShulker(Objects.requireNonNull(e.getInventory().getLocation()).getBlock()))
            return;


        e.setCancelled(true);
        e.getPlayer().sendMessage(ChatColor.RED + "Você não pode abrir uma caixa especial.");
    }


}
