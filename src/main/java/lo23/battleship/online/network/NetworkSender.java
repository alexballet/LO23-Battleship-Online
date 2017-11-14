package lo23.battleship.online.network;

import lo23.battleship.online.network.messages.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Date;

//  {"Connect", "Ready", "Disconnect", "Chat", "RageQuit"};
public class NetworkSender implements Runnable{

    private Socket sock;
    private ObjectOutputStream writer = null;
    private ObjectInputStream reader = null;
    private static int count = 0;
    private Message message;
    private String name;
    public NetworkSender(Socket socket, Message message) {
        sock = socket;
        this.message = message;
    }

    //New thread to process request
    public void run(){
        try {
            boolean closeConnexion = false;
            writer = new ObjectOutputStream(sock.getOutputStream());
            String timeStamps = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM).format(new Date());
            System.out.println("Message " + message.getType() + " sent to server at " + timeStamps);
            writer.writeObject(message);

            if (message.getType().equals("CommunicationOver")) closeConnexion = true;
            if(closeConnexion){
                System.err.println("Close Message");
                writer = null;
                reader = null;
                System.err.println("----------------Closing-----------------");
                sock.close();
            }
        }catch(SocketException e){
            System.err.println(" / ! \\ Interrupted: Something went wrong / ! \\");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    //While socket is not close (active connection)
        while(!sock.isClosed()){


        }
    }
}