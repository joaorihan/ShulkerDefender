package com.joaorihan.shulkerDefender.listeners;

import com.joaorihan.shulkerDefender.ShulkerDefender;
import com.joaorihan.shulkerDefender.util.Checks;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ItemDespawnEvent;


public class LossPreventionListener implements Listener {
    final ShulkerDefender plugin;


    public LossPreventionListener(ShulkerDefender plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    @EventHandler
    public void onItemDespawn(ItemDespawnEvent e) {
        if (!Checks.isPluginShulker(e.getEntity().getItemStack().getType()))
            return;

        e.setCancelled(true);
        //debug
        plugin.getLogger().info("Prevent Despawn for a shulker");
    }


    @EventHandler
    public void onItemBurn(EntityCombustEvent e) {
        if (!(e.getEntity() instanceof Item))
            return;

        if (!Checks.isPluginShulker(((Item) e.getEntity()).getItemStack().getType()))
            return;

        e.setCancelled(true);
    }

    @EventHandler
    public void onItemDamage(EntityDamageEvent e) {
        //debug
        plugin.getLogger().info(e.getCause().toString());

        if (!(e.getEntity() instanceof Item))
            return;

        if (!Checks.isPluginShulker(((Item) e.getEntity()).getItemStack().getType()))
            return;

        e.setCancelled(true);
        //debug
        plugin.getLogger().info("Prevent destruction for a shulker");
    }

    @EventHandler
    public void onEntityExplode(BlockExplodeEvent e) {
        //debug
        plugin.getLogger().info("BlockExplodeEvent called''w");
        //todo fix

        for (Block block : e.blockList()){
            if (Checks.isPluginShulker(block)){
                e.setCancelled(true);
            }
        }

    }


}
