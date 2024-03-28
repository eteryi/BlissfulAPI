package dev.cross.blissfulcore.command;

import dev.cross.blissfulcore.BlissfulCore;
import dev.cross.blissfulcore.item.BlissfulItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class BlissfulItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player p) || !commandSender.isOp()) {
            return false;
        }
        String id = strings.length == 0 ? "" : strings[0];
        Optional<BlissfulItem> item = BlissfulCore.getRegistry().getItem(id);
        item.ifPresent(it -> p.getInventory().addItem(it.createItem()));
        return true;
    }
}
