package dev.cross.blissfulcore.event;

import dev.cross.blissfulcore.ui.display.InteractWindowDisplay;
import dev.cross.blissfulcore.ui.display.WindowDisplay;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class InteractionListener implements Listener {

    @EventHandler
    public void onInteraction(PlayerInteractEntityEvent event) {
        if (event.getRightClicked().getType() == EntityType.INTERACTION) {
            WindowDisplay windowDisplay = WindowDisplay.from(event.getRightClicked());
            if (windowDisplay instanceof InteractWindowDisplay interactWindowDisplay) {
                interactWindowDisplay.onInteraction(event.getPlayer());
            }
        }
    }
}
