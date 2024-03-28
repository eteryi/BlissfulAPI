package dev.cross.blissfulcore.ui.inventory.button;

import dev.cross.blissfulcore.Pair;
import net.kyori.adventure.platform.bukkit.BukkitComponentSerializer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

abstract public class AbstractClickableButton implements ClickableButton {
    public static final Pair<Material, Integer> BLANK_ITEM = Pair.of(Material.PAPER, 4);

    public record Hitbox(Component title, List<Component> lore) {
        public ItemStack createItemStack() {
            ItemStack stack = new ItemStack(BLANK_ITEM.first());
            ItemMeta meta = stack.getItemMeta();
            meta.setCustomModelData(BLANK_ITEM.second());
            LegacyComponentSerializer componentSerializer = BukkitComponentSerializer.legacy();
            meta.setDisplayName(componentSerializer.serialize(title));
            meta.setLore(this.lore.stream().map(it -> it.decoration(TextDecoration.ITALIC, false).colorIfAbsent(NamedTextColor.WHITE)).map(componentSerializer::serialize).collect(Collectors.toList()));
            stack.setItemMeta(meta);
            return stack;
        }
    }
    public record Texture(Material material, int modelData) {
        public ItemStack createItemStack() {
            ItemStack stack = new ItemStack(material);
            ItemMeta meta = stack.getItemMeta();
            meta.setCustomModelData(modelData);
            meta.setDisplayName(" ");
            stack.setItemMeta(meta);
            return stack;
        }
    }

    private final Hitbox hitbox;
    private final Texture texture;
    private final int size;
    // Offset describes the offset of the texture item
    // [0] represents x
    // [1] represents y
    private final int[] offset;
    private int textureSlot;
    private List<ItemStack> hitboxes;
    private Inventory inventory;

    public AbstractClickableButton(int size, Hitbox hitbox, Texture texture) {
        this.size = size;
        this.hitbox = hitbox;
        this.texture = texture;
        this.offset = new int[]{0, 0};
        this.hitboxes = new ArrayList<>();
    }

    protected void setOffset(int x, int y) {
        this.offset[0] = x;
        this.offset[1] = y;
    }
    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public ItemStack getHitBox() {
        return this.hitbox.createItemStack();
    }

    @Override
    public ItemStack getTexture() {
        return this.texture.createItemStack();
    }

    @Override
    abstract public void onClick(InventoryClickEvent event);

    @Override
    public boolean isHitbox(ItemStack itemStack) {
        return this.hitboxes.contains(itemStack);
    }

    protected int getTextureSlot() {
        return this.textureSlot;
    }

    @Override
    public void createOn(Inventory inventory, int position) {
        this.inventory = inventory;
        for (int i = position; i < position + this.getSize(); i++) {
            ItemStack stack = this.getHitBox();
            inventory.setItem(i, stack);
            this.hitboxes.add(stack);
        }
        ItemStack texture = this.getTexture();
        inventory.setItem(position + this.getSize(), texture);
        this.textureSlot = position + this.getSize();
    }
}
