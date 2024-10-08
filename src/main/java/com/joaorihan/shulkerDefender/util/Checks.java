package com.joaorihan.shulkerDefender.util;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class Checks {

    /**
     * Used to check if a block is, or not a {@link Shulker}
     * @param block Block to be compared
     * @return {@code true} if block is an instance of one of the selected {@link Shulker}
     */
    public boolean isPluginShulker(@NonNull Block block){
        for (Shulker shulker : Shulker.values())
            if (shulker.getType().equals(block.getType()))
                return true;

        return false;
    }

    /**
     * Used to check if a material is, or not a {@link Shulker}
     * @param type Material to be compared
     * @return {@code true} if material is an instance of one of the selected {@link Shulker}
     */
    public boolean isPluginShulker(@NonNull Material type){
        for (Shulker shulker : Shulker.values())
            if (shulker.getType().equals(type))
                return true;

        return false;
    }

    /**
     * Checks if an inventory's content has a {@link Shulker}
     * @param inventory Inventory to be checked
     * @return {@code true} if the inventory contains a {@link Shulker}
     */
    public boolean containsPluginShulker(@NonNull Inventory inventory){
        for (ItemStack itemStack : inventory.getContents())
            if (itemStack != null && isPluginShulker(itemStack.getType()))
                return true;

        return false;
    }

}
