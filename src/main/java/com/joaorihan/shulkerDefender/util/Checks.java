package com.joaorihan.shulkerDefender.util;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.block.Block;

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

    public boolean isPluginShulker(@NonNull Material type){
        for (Shulker shulker : Shulker.values())
            if (shulker.getType().equals(type))
                return true;

        return false;
    }

}
