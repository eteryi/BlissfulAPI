package dev.cross.blissfulcore.api;

import org.bukkit.entity.Player;

import java.util.Optional;

public interface BlissfulAPI {
    /**
     * Returns the current implementation of the API, as this may be subject to change in the future.
     * Having this static factory function allows for greater flexibility when updating the API, and also
     * greater abstraction as it returns an object with the BlissfulAPI interface.
     *
     *
     * @return      The current implementation of the API
     */
    static BlissfulAPI getImpl() {
        return BlissfulAPIImpl.getInstance();
    }

    /**
     * Sets the amount of tokens a player has in the current scoreboard.
     * However, it's important to note that this, in the current implementation, will also
     * affect the amount of tokens a Team has if the player is currently participating in that team.
     * <p>
     * It's also incredibly important to note that this function allows for negative tokens, so be careful
     * when using it and always make sure that your tokens argument is above 0.
     * <p>
     * If a player at any occasion has a higher amount of tokens than the team it's currently participating,
     * and you try to use this function to set the player tokens to 0, it will make the team have negative tokens.
     *
     * @param   player  the player to set the tokens
     * @param   tokens  the amount of tokens
     **/
    void setTokensFor(Player player, int tokens);
    /**
     * Returns the current amount of tokens a player has.
     * This behaviour is determined by the Blissful Scoreboard.
     *
     * @param    player     the player
     * @return              the amount of tokens that player has inside the scoreboard
     **/
    int getTokens(Player player);

    /**
     * Returns the total amount of tokens a team has in the scoreboard through its identifier.
     * Used inside BlissfulTeams.getTokens()
     *
     * @param id    the identifier of the team, such as ".jade" or ".ruby"
     * @return      the total amount of tokens this team has
     * @see         BlissfulTeams
     **/
    int getTeamTokens(String id);

    /**
     * Sets the current tokens a team has in the Blissful Scoreboard.
     * This is also used as the implementation of BlissfulTeams.setTokens().
     *
     * @param    id     the identifier of the team, such as ".jade" or ".ruby"
     * @param   score   the total amount of tokens you wish to set
     * @see BlissfulTeams
     */
    void setTokensFor(String id, int score);

    /**
     * Returns the "player team" a player is currently in.
     * If the player isn't in one or the player is in a special team (such as "Admin" or "Mod"),
     * the return value will be an Optional.empty().
     *
     * @param   player    the player whose team you wish to know
     * @return            An optional value which may contain the team
     */
    Optional<BlissfulTeams> getTeamFrom(Player player);

}
