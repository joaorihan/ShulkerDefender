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


    public void addBypassedPlayer(Player player){
        assert !playersInBypass.contains(player);
        getPlayersInBypass().add(player);
        player.sendMessage(ChatColor.GREEN + "Você entrou no modo de bypass.");
    }


    public void removeBypassedPlayer(Player player){
        getPlayersInBypass().remove(player);
        player.sendMessage(ChatColor.GREEN + "Você saiu no modo de bypass.");

    }

    public void clearBypassedPlayers(){
        getPlayersInBypass().forEach(this::removeBypassedPlayer);
    }

    public boolean isInBypassMode(Player player){
        return getPlayersInBypass().contains(player);
    }



}
