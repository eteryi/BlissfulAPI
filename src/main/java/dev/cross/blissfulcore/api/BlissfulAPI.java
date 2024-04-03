package dev.cross.blissfulcore.api;

import org.bukkit.entity.Player;

import java.util.Optional;

public interface BlissfulAPI {
    static BlissfulAPI getImpl() {
        return BlissfulAPIImpl.getInstance();
    }
    
    void setTokensFor(Player player, int tokens);
    int getTokens(Player player);
    int getTeamTokens(String id);
    void setTokensFor(String id, int score);
    Optional<BlissfulTeams> getTeamFrom(Player player);
}
