package lo23.battleship.online;

public class CommunicationTest {

    public static void main(String[] args) {

        String host = "127.0.0.1";
        int port = 2345;
        ServerTest ts = new ServerTest(host, port);
        ts.open();
        System.out.println("Serveur initialisé.");

        for(int i = 0; i < 5; i++){
            Thread t = new Thread(new ClientConnection(host, port));
            t.start();
        }
    }
}