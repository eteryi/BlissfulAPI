package dev.cross.blissfulcore.api;

import org.bukkit.entity.Player;

public interface BlissfulAPI {
    static BlissfulAPI getImpl() {
        return BlissfulAPIImpl.getInstance();
    }
    
    void setTokensFor(Player player, int tokens);
    int getTokens(Player player);

}
