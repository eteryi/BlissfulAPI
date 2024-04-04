package dev.cross.blissfulcore.ui.bossbar;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.function.Function;
import java.util.function.Supplier;

public class SimpleBossBar implements IBossBar {

    private final Player player;
    private final Function<Player, BaseComponent> supplier;
    private BossBar current;

    public SimpleBossBar(Player player, Supplier<BaseComponent> supplier) {
        this(player, (player1) -> supplier.get());
    }

    public SimpleBossBar(Player player, Function<Player, BaseComponent> supplier) {
        this.player = player;
        this.supplier = supplier;
    }

    public void update() {
        String newComponent = this.supplier.apply(player).toLegacyText();
        if (this.current.getTitle().equals(newComponent)) return;

        this.current.setTitle(newComponent);
    }

    public void start() {
        this.current = Bukkit.createBossBar(supplier.apply(player).toLegacyText(), BarColor.WHITE, BarStyle.SOLID);
        this.current.addPlayer(player);
    }

    public void kill() {
        this.current.removeAll();
        this.current = null;
    }
}
