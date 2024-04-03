package dev.cross.blissfulcore.api;

import net.md_5.bungee.api.ChatColor;

public enum BlissfulTeams {
    RUBY(".ruby", ChatColor.RED),
    AMBER(".amber", ChatColor.GOLD),
    TOPAZ(".topaz", ChatColor.YELLOW),
    JADE(".jade", ChatColor.GREEN),
    AQUAMARINE(".aquamarine", ChatColor.DARK_AQUA),
    DIAMOND(".diamond", ChatColor.AQUA),
    SAPPHIRE(".sapphire", ChatColor.BLUE),
    AMETHYST(".amethyst", ChatColor.LIGHT_PURPLE);

    private final String scoreboardID;
    private final ChatColor color;

    BlissfulTeams(String scoreboardID, ChatColor color) {
        this.scoreboardID = scoreboardID;
        this.color = color;
    }

    public void setTokens(int tokens) {
        BlissfulAPI impl = BlissfulAPI.getImpl();
        impl.setTokensFor(this.scoreboardID, tokens);
    }

    public int getTokens() {
        return BlissfulAPI.getImpl().getTeamTokens(this.scoreboardID);
    }

    public ChatColor getColor() {
        return this.color;
    }
}
