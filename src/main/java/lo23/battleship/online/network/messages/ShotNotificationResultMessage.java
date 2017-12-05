package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Boat;
import structData.Shot;


import java.net.InetAddress;

public class ShotNotificationResultMessage extends Message{
    Shot shot;
    Boat boat;

    public ShotNotificationResultMessage(Shot s, Boat b) {
        this.shot = s;
        this.boat = b;
        this.type = "ShotNotificationResultMessage";}

    public String getType() {
        return type;
    }

    public void process(IDataCom IData){
        IData.coordinates(shot, boat);
    }

    public void process(IDataCom IData, InetAddress senderAddress){

    }



}
