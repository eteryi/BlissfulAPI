package dev.cross.blissfulcore;

import dev.cross.blissfulcore.command.BlissfulItemCommand;
import dev.cross.blissfulcore.event.InteractionListener;
import dev.cross.blissfulcore.event.InventoryListener;
import dev.cross.blissfulcore.event.ItemListener;
import dev.cross.blissfulcore.item.BlissfulItem;
import dev.cross.blissfulcore.item.ItemRegistry;
import dev.cross.blissfulcore.ui.display.WindowDisplay;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

public final class BlissfulCore extends JavaPlugin {
    private static JavaPlugin plugin;
    static final ItemRegistry REGISTRY = new ItemRegistry();

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static void registerItem(BlissfulItem item) {
        REGISTRY.registerItem(item);
    }

    public static ItemRegistry getRegistry() {
        return REGISTRY;
    }

    private BukkitAudiences adventure;

    public @NonNull BukkitAudiences adventure() {
        if(this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return this.adventure;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.adventure = BukkitAudiences.create(this);
        plugin = this;
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        getServer().getPluginManager().registerEvents(new InteractionListener(), this);
        getServer().getPluginManager().registerEvents(new ItemListener(), this);
        getCommand("blissful-item").setExecutor(new BlissfulItemCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        WindowDisplay.end();
        if(this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
    }
}
