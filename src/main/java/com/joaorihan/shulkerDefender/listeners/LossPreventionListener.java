package com.joaorihan.shulkerDefender.listeners;

import com.joaorihan.shulkerDefender.ShulkerDefender;
import com.joaorihan.shulkerDefender.util.Checks;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ItemDespawnEvent;

import java.util.Iterator;

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
        //debug
        plugin.getLogger().info("Prevent combust for a shulker");
    }

    @EventHandler
    public void onItemDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Item))
            return;

        if (e.getCause() != EntityDamageEvent.DamageCause.CONTACT)
            return;

        if (!Checks.isPluginShulker(((Item) e.getEntity()).getItemStack().getType()))
            return;

        e.setCancelled(true);
        plugin.getLogger().info("Prevent destruction for a shulker from cactus");
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        for (Block block : event.blockList())
            for (Entity entity : block.getWorld().getNearbyEntities(block.getLocation(), 1, 1, 1))
                if (entity instanceof Item
                    && Checks.isPluginShulker(((Item) entity).getItemStack().getType()))
                {
                    event.setCancelled(true);
                    return;
                }
    }

}
