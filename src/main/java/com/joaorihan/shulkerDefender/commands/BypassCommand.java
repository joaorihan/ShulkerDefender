package com.joaorihan.shulkerDefender.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class BypassCommand extends Command{


    public BypassCommand(){
        super("bypass",
                new String[]{},
                "Toggles bypass for all shulker limitations",
                "shulkerdefender.bypass");
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        // /bypass <on/off> <player>
        if (!(sender instanceof Player player))
            return;

        if (args.length > 2){
            player.sendMessage(ChatColor.RED + "Usagem incorreta! Use /bypass <on/off> <player>");
            return;
        }

        switch (args.length) {
            case 0:
                if (getPlugin().getPlayerManager().isInBypassMode(player))
                    getPlugin().getPlayerManager().removeBypassedPlayer(player);
                else
                    getPlugin().getPlayerManager().addBypassedPlayer(player);
                break;

            case 1:
                switch (args[0]){
                    case "on":
                        if (getPlugin().getPlayerManager().isInBypassMode(player)) {
                            player.sendMessage(ChatColor.RED + "Você não está com o bypass ligado.");
                            return;
                        }

                        getPlugin().getPlayerManager().addBypassedPlayer(player);
                        break;

                    case "off":
                        if (!getPlugin().getPlayerManager().isInBypassMode(player)) {
                            player.sendMessage(ChatColor.RED + "Você já está com o bypass ligado.");
                            return;
                        }
                        getPlugin().getPlayerManager().addBypassedPlayer(player);
                        break;
                    default:
                        player.sendMessage(ChatColor.RED + "Usagem incorreta.");
                }
                break;

            case 2:
                Player target = Bukkit.getPlayer(args[1]);

                if (target == null) {
                    player.sendMessage(ChatColor.RED + "Este jogador não foi encontrado. Rebole lentinho pros crias");
                    return;
                }

                switch (args[0]){
                    case "on":
                        if (getPlugin().getPlayerManager().isInBypassMode(target)) {
                            player.sendMessage(ChatColor.RED + "Este jogador não está com o bypass ligado.");
                            return;
                        }
                        getPlugin().getPlayerManager().addBypassedPlayer(target);
                        player.sendMessage(ChatColor.GREEN + "Você ligou o bypass para " + target.getName());
                        break;

                    case "off":
                        if (!getPlugin().getPlayerManager().isInBypassMode(target)) {
                            player.sendMessage(ChatColor.RED + "Este jogador já está com o bypass ligado.");
                            return;
                        }
                        getPlugin().getPlayerManager().addBypassedPlayer(target);
                        player.sendMessage(ChatColor.GREEN + "Você desligou o bypass para " + target.getName());
                        break;
                    default:
                        player.sendMessage(ChatColor.RED + "Usagem incorreta.");
                        break;
                }
            break;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return List.of();
    }
}
