package dev.cross.blissfulcore.ui.inventory;

import dev.cross.blissfulcore.ui.inventory.button.ClickableButton;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public abstract class GUIInventory {
    private static final HashMap<Inventory, GUIInventory> guiInventoryMap = new HashMap<>();

    public static GUIInventory from(Inventory inventory) {
        return guiInventoryMap.get(inventory);
    }


    private final Player player;
    private Inventory inventory;
    private final boolean interact;

    private final Map<ClickableButton, Integer> buttonsLoaded;

    public GUIInventory(Player player, boolean interact) {
        this.player = player;
        this.interact = interact;
        this.buttonsLoaded = new HashMap<>();
    }

    public Player getPlayer() {
        return this.player;
    }

    protected abstract Inventory inventorySupplier();

    protected void addButton(ClickableButton button, int position) {
        this.buttonsLoaded.put(button, position);
    }

    public void open() {
        if (this.inventory != null) return;
        this.inventory = inventorySupplier();
        buttonsLoaded.forEach((k, v) -> {
            k.createOn(this.inventory, v);
        });
        this.player.openInventory(this.inventory);
        guiInventoryMap.put(this.inventory, this);
    }

    public void interact(InventoryClickEvent event) {
        if (!interact) event.setCancelled(true);
        assert event.getClickedInventory() != null;
        this.buttonsLoaded.forEach((k, v) -> {
            if (k.isHitbox(event.getClickedInventory().getItem(event.getSlot()))) {
                k.onClick(event);
            }
        });
        onInteract(event);
    }

    public void closeEvent(InventoryCloseEvent event) {
        guiInventoryMap.remove(this.inventory);
        this.inventory = null;
        onClose(event);
    }

    public void close() {
        if (inventory != null) {
            inventory.getViewers().forEach(HumanEntity::closeInventory);
        }
    }

    protected abstract void onInteract(InventoryClickEvent event);
    protected abstract void onClose(InventoryCloseEvent event);
}
