package com.joaorihan.shulkerDefender.managers;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class PlayerManager {

    private List<Player> playersInBypass;

    public PlayerManager() {
        setPlayersInBypass(new ArrayList<>());
    }

    /**
     * Adds a {@link Player} to {@link #playersInBypass}
     * @param player Player to be added
     */
    public void addBypassedPlayer(Player player){
        assert !playersInBypass.contains(player);
        getPlayersInBypass().add(player);
        player.sendMessage(ChatColor.GREEN + "Você entrou no modo de bypass.");
    }

    /**
     * Removes a {@link Player} from {@link #playersInBypass}
     * @param player Player to be removed
     */
    public void removeBypassedPlayer(Player player){
        getPlayersInBypass().remove(player);
        player.sendMessage(ChatColor.GREEN + "Você saiu no modo de bypass.");

    }

    /**
     * Removes all bypassed players from the {@link #playersInBypass}
     */
    public void clearBypassedPlayers(){
        getPlayersInBypass().forEach(this::removeBypassedPlayer);
    }

    /**
     * Checks if a {@link Player} is in the {@link #playersInBypass} List
     * @param player Player to be checked
     * @return {@code true} if the list contains the player
     */
    public boolean isInBypassMode(Player player){
        return getPlayersInBypass().contains(player);
    }



}
