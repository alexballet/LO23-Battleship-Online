package lo23.battleship.online;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] zero) {

        ServerSocket socketserver  ;
        Socket socketduserveur ;

        try {

            socketserver = new ServerSocket(2009);
            socketduserveur = socketserver.accept();
            System.out.println("Un zéro s'est connecté !");
            socketserver.close();
            socketduserveur.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
