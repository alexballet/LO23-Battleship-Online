package lo23.battleship.online.network;

import packageStructDonnées.Game;
import packageStructDonnées.Profile;
import packageStructDonnées.Shot;
import packageStructDonnées.User;

import java.util.ArrayList;

/**
 * Created by xzirva on 17/10/17.
 * TODO: To be completed with the methods' implementation
 */
public class NetworkModuleInterface implements Interface{
    public boolean notifyReady(User user) {
        return true;
    }

    public boolean sendChatMessage(String message) {
        return true;
    }


    public Profile getProfile(User user) {
        return new Profile();
    }


    public boolean notifyNewGame(User user, Game game) {
        return true;
    }

    public boolean joinGame(User user) {
        return true;
    }

    public boolean askDisconnection(User user) {
        return true;
    }

    public boolean sendShot(User player, Game game, Shot shot) {
        return true;
    }

    public ArrayList<User> searchForPlayers(User user) {
        return new ArrayList<>();
    }
}
