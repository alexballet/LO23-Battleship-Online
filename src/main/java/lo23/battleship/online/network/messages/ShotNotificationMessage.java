package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Shot;


import java.net.InetAddress;

public class ShotNotificationMessage extends Message{
        Shot shot;

    public ShotNotificationMessage(Shot s) {
        this.shot = s;
        this.type = "ShotNotificationMessage";}

    public String getType() {
        return type;
    }

    public void process(IDataCom IData){}

    public void process(IDataCom IData, InetAddress senderAddress){
        IData.coordinate(shot);
        System.out.println( "send shot: " + shot.getX() + ";"+ shot.getY());

    }



}
