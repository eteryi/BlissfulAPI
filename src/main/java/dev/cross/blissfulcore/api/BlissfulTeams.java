package dev.cross.blissfulcore.api;

public enum BlissfulTeams {
    RUBY(".ruby"),
    AMBER(".amber"),
    TOPAZ(".topaz"),
    JADE(".jade"),
    AQUAMARINE(".aquamarine"),
    DIAMOND(".diamond"),
    SAPPHIRE(".sapphire"),
    AMETHYST(".amethyst");

    private final String scoreboardID;
    BlissfulTeams(String scoreboardID) {
        this.scoreboardID = scoreboardID;
    }

    public void setTokens(int tokens) {
        BlissfulAPI impl = BlissfulAPI.getImpl();
        impl.setTokensFor(this.scoreboardID, tokens);
    }

    public int getTokens() {
        return BlissfulAPI.getImpl().getTeamTokens(this.scoreboardID);
    }
}
