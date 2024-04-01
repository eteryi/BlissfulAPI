package dev.cross.blissfulcore.api;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;

public class BlissfulAPIImpl implements BlissfulAPI {
    private static final String TOKEN_OBJECTIVE = "TokensThisEvent";
    private static BlissfulAPIImpl INSTANCE;

    static BlissfulAPIImpl getInstance() {
        if (INSTANCE == null) INSTANCE = new BlissfulAPIImpl();
        return INSTANCE;
    }

    private BlissfulAPIImpl() {

    }
    private Objective getTokenObjective(Player player) {
        Objective tokenObjective = player.getScoreboard().getObjective(TOKEN_OBJECTIVE);
        if (tokenObjective == null) {
            throw new RuntimeException("Player doesn't have the token scoreboard, aborting...");
        }
        return tokenObjective;
    }

    @Override
    public void setTokensFor(Player player, int tokens) {
        getTokenObjective(player).getScore(player).setScore(tokens);
    }

    @Override
    public int getTokens(Player player) {
        return getTokenObjective(player).getScore(player).getScore();
    }
}
