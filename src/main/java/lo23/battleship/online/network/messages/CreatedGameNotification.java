package lo23.battleship.online.network.messages;

/* Attente interface dans les dossier lo23*/
import structData.Game;


public class CreatedGameNotification extends Message{

    Game game;

    public CreatedGameNotification(Game gameCreated) {
        this.game = gameCreated;
        this.type = "CreatedGameNotification";}

    public String getType() {
        return type;
    }

    public CreatedGameNotification process(){
        //addNewGameList(Game game);
        return new CreatedGameNotification(game);}

}
