package com.joaorihan.shulkerDefender.commands;

import com.joaorihan.shulkerDefender.ShulkerDefender;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@Setter
@Getter
public abstract class Command extends BukkitCommand {

    private ShulkerDefender plugin;

    public Command(String command, String[] aliases, String description, String permission) {
        super(command);

        this.setAliases(Arrays.asList(aliases));
        this.setDescription(description);
        this.setPermission(permission);
        this.setPermissionMessage(ChatColor.RED + "Você não tem permissão para utilizar este comando! " + permission);

        try {
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            CommandMap map = (CommandMap) field.get(Bukkit.getServer());
            map.register(command, "shulkers", this);
        } catch (NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }

        setPlugin(ShulkerDefender.getPlugin(ShulkerDefender.class));
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        execute(commandSender, strings);
        return false;
    }

    public abstract void execute(CommandSender sender, String[] args);

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return onTabComplete(sender, args);
    }

    public abstract List<String> onTabComplete(CommandSender sender, String[] args);

}