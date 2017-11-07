package lo23.battleship.online.network;

import packageStructDonnées.Game;
import packageStructDonnées.Profile;
import packageStructDonnées.Shot;
import packageStructDonnées.User;

import java.util.ArrayList;

/**
 * Created by xzirva on 17/10/17.
 */

/*
    TODO: To be uncommented for final implementation
* */

public interface Interface {
    public boolean notifyReady(User user);

    public boolean sendChatMessage(String message);

    public Profile getProfile(User user);

    public boolean notifyNewGame(User user, Game game);

    public boolean joinGame(User user);

    public boolean askDisconnection(User user);

    public boolean sendShot(User player, Game game, Shot shot);

    public ArrayList<User> searchForPlayers(User user);
}
