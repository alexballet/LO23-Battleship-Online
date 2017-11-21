package lo23.battleship.online.network.messages;

import structData.StatusGame;

/* Attente interface dans les dossier lo23
Attente de data pour une méthode contenant les statuts*/

public class UpdateGame extends Message{

    StatusGame status;

    public UpdateGame(StatusGame s){
        this.status = s;
        this.type = "UpdateGame";
    }

    public String getType() {
        return type;
    }

    public UpdateGame process(){
        //changeStatusGame(game);
        return new UpdateGame(status);}
}
