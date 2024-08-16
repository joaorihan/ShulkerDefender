package com.joaorihan.shulkerDefender.listeners;

import com.joaorihan.shulkerDefender.ShulkerDefender;
import com.joaorihan.shulkerDefender.Shulker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class ShulkerListener implements Listener {
    final ShulkerDefender plugin;


    public ShulkerListener(ShulkerDefender plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Player player = e.getPlayer();

        if (Bukkit.getOnlinePlayers().size() >= plugin.getConfig().getInt("minimum-players"))
            return;

        for (Shulker shulker : Shulker.values()){
            if (shulker.getType().equals(e.getBlock().getType())){
                player.sendMessage(ChatColor.RED + "Você deve aguardar mais jogadores estarem online para quebrar este bloco! ("+plugin.getConfig().getInt("minimum-players")+")");
                e.setCancelled(true);
                //debug
                System.out.println("[DEBUG] BlockBreakEvent cancelled");
            }
        }
    }

}
