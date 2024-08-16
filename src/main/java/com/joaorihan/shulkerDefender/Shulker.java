package com.joaorihan.shulkerDefender;

import lombok.Getter;
import org.bukkit.Material;

@Getter
public enum Shulker {

    WHITE_SHULKER_BOX(Material.WHITE_SHULKER_BOX),
    ORANGE_SHULKER_BOX(Material.ORANGE_SHULKER_BOX),
    MAGENTA_SHULKER_BOX(Material.MAGENTA_SHULKER_BOX),
    LIGHT_BLUE_SHULKER_BOX(Material.LIGHT_BLUE_SHULKER_BOX),
    YELLOW_SHULKER_BOX(Material.YELLOW_SHULKER_BOX),
    LIME_SHULKER_BOX(Material.LIME_SHULKER_BOX),
    PINK_SHULKER_BOX(Material.PINK_SHULKER_BOX),
    GRAY_SHULKER_BOX(Material.GRAY_SHULKER_BOX),
    LIGHT_GRAY_SHULKER_BOX(Material.LIGHT_GRAY_SHULKER_BOX),
    CYAN_SHULKER_BOX(Material.CYAN_SHULKER_BOX),
    PURPLE_SHULKER_BOX(Material.PURPLE_SHULKER_BOX),
    BLUE_SHULKER_BOX(Material.BLUE_SHULKER_BOX),
    BROWN_SHULKER_BOX(Material.BROWN_SHULKER_BOX),
    GREEN_SHULKER_BOX(Material.GREEN_SHULKER_BOX),
    RED_SHULKER_BOX(Material.RED_SHULKER_BOX),
    BLACK_SHULKER_BOX(Material.BLACK_SHULKER_BOX);

    private final Material type;

    Shulker(Material type){
        this.type = type;
    }

}
