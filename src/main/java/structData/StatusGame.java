package structData;

/**
 * StatusGame is an emumerate of all the status of games.
 */
public enum StatusGame {
    WAITINGPLAYER, // When a game is waiting for a player
    WAITINGBOT, // When the bot player is placing his boats
    BOATPHASE, // When players are placing their boats
    PLAYER1READY, // When player 1 placed his boats
    PLAYER2READY, // When player 2 placed his boats
    PLAYING, // When players are still playing
    FINISHED // When the game is over
}
