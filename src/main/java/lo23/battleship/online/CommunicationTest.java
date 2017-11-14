package lo23.battleship.online;

public class CommunicationTest {

    public static void main(String[] args) {

        String host = "172.25.35.108";
        int port = 2345;
        ServerTest ts = new ServerTest(host, port);
        ts.open();
        System.out.println("Serveur initialisé.");

        //for(int i = 0; i < 5; i++){
//        ClientConnection cConnection = new ClientConnection(host, port);
//        cConnection.start();
        //}
    }
}