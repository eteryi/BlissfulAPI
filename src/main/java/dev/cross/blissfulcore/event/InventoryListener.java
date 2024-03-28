package dev.cross.blissfulcore.event;

import dev.cross.blissfulcore.ui.inventory.GUIInventory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        GUIInventory inventory = GUIInventory.from(event.getClickedInventory());
        if (inventory != null) {
            inventory.interact(event);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        GUIInventory inventory = GUIInventory.from(event.getInventory());
        if (inventory != null) {
            inventory.closeEvent(event);
        }
    }
}
