package lo23.battleship.online;

import lo23.battleship.online.network.NetworkController;
import lo23.battleship.online.network.NetworkModuleInterface;
import lo23.battleship.online.network.NetworkServer;

public class CommunicationTest {

    public static void main(String[] args) {
        NetworkController controller = NetworkController.getInstance();
        System.out.println("Serveur initialisé.");

        String host = "172.25.35.108";
        int port = 2345;
//        NetworkModuleInterface networkInterface = new NetworkModuleInterface();
        //networkInterface.sendRandomMessage(host, port);

    }
}