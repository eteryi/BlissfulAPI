package dev.cross.blissfulcore.event;

import dev.cross.blissfulcore.BlissfulCore;
import dev.cross.blissfulcore.item.BlissfulItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Optional;

public class ItemListener implements Listener {

    @EventHandler
    public void onInteraction(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        if (BlissfulItem.isCustom(event.getItem())) {
            Optional<String> id = BlissfulItem.getID(event.getItem());
            if (id.isEmpty()) return;
            Optional<BlissfulItem> blissfulItem = BlissfulCore.getRegistry().getItem(id.get());
            blissfulItem.ifPresent(it -> it.onClick(event));
        }
    }
}
