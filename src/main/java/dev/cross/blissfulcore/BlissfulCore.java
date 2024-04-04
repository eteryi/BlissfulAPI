package dev.cross.blissfulcore;

import dev.cross.blissfulcore.command.BlissfulItemCommand;
import dev.cross.blissfulcore.event.InteractionListener;
import dev.cross.blissfulcore.event.InventoryListener;
import dev.cross.blissfulcore.event.ItemListener;
import dev.cross.blissfulcore.item.BlissfulItem;
import dev.cross.blissfulcore.item.ItemRegistry;
import dev.cross.blissfulcore.ui.display.WindowDisplay;
import org.bukkit.plugin.java.JavaPlugin;

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

    @Override
    public void onEnable() {
        // Plugin startup logic
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
    }
}
