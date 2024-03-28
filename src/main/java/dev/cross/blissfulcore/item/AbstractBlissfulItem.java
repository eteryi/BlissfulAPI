package dev.cross.blissfulcore.item;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public abstract class AbstractBlissfulItem implements BlissfulItem {
    private final String id;
    private final ItemStack model;

    public AbstractBlissfulItem(String id, ItemStack stack) {
        this.id = id;
        this.model = stack;
    }

    @Override
    abstract public void onClick(PlayerInteractEvent event);

    @Override
    public ItemStack createItem() {
        ItemStack stack = model.clone();
        ItemMeta meta = stack.getItemMeta();
        if (meta == null) throw new RuntimeException("We are COOKED !!!");
        meta.getPersistentDataContainer().set(BlissfulItem.ITEM_KEY, PersistentDataType.STRING, this.getID());
        stack.setItemMeta(meta);
        return stack;
    }

    @Override
    public String getID() {
        return this.id;
    }
}
