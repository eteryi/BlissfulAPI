package dev.cross.blissfulcore.item;

import dev.cross.blissfulcore.BlissfulCore;
import org.bukkit.NamespacedKey;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

public interface BlissfulItem {
    public static final @NotNull NamespacedKey ITEM_KEY = Objects.requireNonNull(NamespacedKey.fromString("custom_item", BlissfulCore.getPlugin()));

    // TODO Replace event with a custom Args
    void onClick(PlayerInteractEvent event);
    ItemStack createItem();
    String getID();

    static boolean isCustom(ItemStack stack) {
        ItemMeta meta = stack.getItemMeta();
        if (meta == null) return false;
        PersistentDataContainer container = meta.getPersistentDataContainer();
        return container.has(ITEM_KEY, PersistentDataType.STRING);
    }

    static Optional<String> getID(ItemStack stack) {
        ItemMeta meta = stack.getItemMeta();
        if (meta == null) return Optional.empty();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        return Optional.ofNullable(container.get(ITEM_KEY, PersistentDataType.STRING));
    }

}
