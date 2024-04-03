package dev.cross.blissfulcore.command;

import dev.cross.blissfulcore.api.BlissfulAPI;
import dev.cross.blissfulcore.api.BlissfulTeams;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class BlissfulTeamCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player p) {
            Optional<BlissfulTeams> team = BlissfulAPI.getImpl().getTeamFrom(p);
            team.ifPresentOrElse(it -> p.sendMessage("You're in team " + it), () -> p.sendMessage("You don't have a team lmao"));
        }
        return true;
    }
}
