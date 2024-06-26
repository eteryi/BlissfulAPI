package dev.cross.blissfulcore.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.*;

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
        int playerTokens = getTokens(player);
        int tokenDif = tokens - getTokens(player);
        Optional<BlissfulTeams> optTeam = getTeamFrom(player);
        if (optTeam.isPresent()) {
            BlissfulTeams team = optTeam.get();
            team.setTokens(team.getTokens() + tokenDif);
        }

        getTokenObjective(player).getScore(player).setScore(playerTokens + tokenDif);
    }

    @Override
    public int getTokens(Player player) {
        return getTokenObjective(player).getScore(player).getScore();
    }

    private Scoreboard getMainScoreboard() {
        ScoreboardManager boardManager = Bukkit.getScoreboardManager();
        if (boardManager == null) throw new RuntimeException("ScoreboardManager couldn't be found");
        return boardManager.getMainScoreboard();
    }
    private Objective getTokenObjective() {
        Scoreboard board = getMainScoreboard();
        Objective tokenObj = board.getObjective(TOKEN_OBJECTIVE);
        if (tokenObj == null) throw new RuntimeException("Couldn't find TokenObjective inside of MainScoreboard");
        return tokenObj;
    }

    @Override
    public int getTeamTokens(String teamID) {
        return getTokenObjective().getScore(teamID).getScore();
    }

    @Override
    public void setTokensFor(String id, int score) {
        getTokenObjective().getScore(id).setScore(score);
    }

    @Override
    public Optional<BlissfulTeams> getTeamFrom(Player player) {
        Scoreboard board = getMainScoreboard();
        Team team = board.getEntryTeam(player.getName());
        if (team == null) return Optional.empty();
        String teamName = team.getName();
        return Arrays.stream(BlissfulTeams.values()).filter(it -> it.toString().equalsIgnoreCase(teamName)).findAny();
    }

    @Override
    public Set<Player> getPlayersFrom(BlissfulTeams team) {
        Team rawTeam = getScoreboardTeam(team).orElse(null);
        if (rawTeam == null) return Set.of();
        Set<Player> players = new HashSet<>();
        for (String entry : rawTeam.getEntries()) {
            Player player = Bukkit.getPlayer(entry);
            if (player == null) continue;
            if (!player.isOnline()) continue;
            players.add(player);
        }
        return players;
    }

    @Override
    public Optional<Team> getScoreboardTeam(BlissfulTeams team) {
        Scoreboard board = getMainScoreboard();
        String teamName = team.toString();
        Team boardTeam = board.getTeam(teamName.substring(0, 1).toUpperCase() + teamName.substring(1).toLowerCase());
        if (boardTeam == null) return Optional.empty();
        return Optional.of(boardTeam);
    }

    @Override
    public String getTeamName(BlissfulTeams team) {
        Optional<Team> rawTeam = team.getRawTeam();
        return rawTeam.map(Team::getPrefix).orElse("");
    }
}
