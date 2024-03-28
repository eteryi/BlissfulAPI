package dev.cross.blissfulcore.ui.inventory.button;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface ClickableButton {
    int getSize();
    ItemStack getHitBox();
    ItemStack getTexture();
    boolean isHitbox(ItemStack itemStack);
    void onClick(InventoryClickEvent event);
    void createOn(Inventory inventory, int position);
}
