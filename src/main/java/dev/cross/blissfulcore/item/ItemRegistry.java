package dev.cross.blissfulcore.item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Optional;

public class ItemRegistry {
    private final HashMap<String, BlissfulItem> items;

    public ItemRegistry() {
        this.items = new HashMap<>();
    }

    public void registerItem(BlissfulItem item) {
        items.put(item.getID(), item);
    }

    public Optional<BlissfulItem> getItem(String id) {
        return Optional.ofNullable(items.get(id));
    }

    public Optional<BlissfulItem> getItem(ItemStack stack) {
        ItemMeta meta = stack.getItemMeta();
        if (meta == null) return Optional.empty();
        if (!BlissfulItem.isCustom(stack)) return Optional.empty();
        Optional<String> id = BlissfulItem.getID(stack);
        if (id.isEmpty()) return Optional.empty();
        return getItem(id.get());
    }
}
