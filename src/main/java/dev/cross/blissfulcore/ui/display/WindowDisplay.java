package dev.cross.blissfulcore.ui.display;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public abstract class WindowDisplay {
    private static final Collection<WindowDisplay> displays = new ArrayList<>();

    public static WindowDisplay from(Entity entity) {
        AtomicReference<WindowDisplay> found = new AtomicReference<>();
        displays.forEach(it -> {
            if (entity == null) return;
            if (it.isEntityFrom(entity)) {
                found.set(it);
                return;
            }
        });
        return found.get();
    }

    public static void end() {
        displays.forEach(WindowDisplay::kill);
    }

    private ItemDisplay displayEntity;
    private final int customModelData;

    public WindowDisplay(int customModelData) {
        this.customModelData = customModelData;
        displays.add(this);
    }

    public ItemDisplay getDisplayEntity() {
        return displayEntity;
    }

    public void spawn(Location location) {
        this.displayEntity = (ItemDisplay) location.getWorld().spawnEntity(location, EntityType.ITEM_DISPLAY);
        ItemStack stack = new ItemStack(Material.PAPER);
        ItemMeta meta = stack.getItemMeta();
        meta.setCustomModelData(customModelData);
        stack.setItemMeta(meta);
        displayEntity.setItemStack(stack);
        // TODO kill myself again if this doesn't work
        this.displayEntity.setInterpolationDuration(20);
        unSeen();
    }

    public void kill() {
        getEntities().forEach(Entity::remove);
    }

    public List<Entity> getEntities() {
        return List.of(this.displayEntity);
    }

    protected boolean isEntityFrom(Entity entity) {
        return entity == this.displayEntity;
    }
    abstract protected void onSeen();

    abstract protected void unSeen();

    private final HashSet<Player> viewers = new HashSet<>();
    private boolean isSeen = false;

    public void setSeen(boolean b) {
        if (b && !isSeen) onSeen();
        if (!b && isSeen) unSeen();
        this.isSeen = b;
    }
    public void onLookAt(Player player) {
        if (viewers.add(player) && !isSeen) {
            setSeen(true);
        }}

    public void unLook(Player player) {
        if (viewers.remove(player) && viewers.isEmpty() && isSeen) {
            setSeen(false);
        }
    }
}

