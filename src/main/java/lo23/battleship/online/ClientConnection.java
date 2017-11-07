package lo23.battleship.online;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class ClientConnection extends Thread {

    private Socket senderSocket = null;
    private ObjectOutputStream writer = null;
    private ObjectInputStream reader = null;

    //Type of messages
    private String[] listMessages = {"Connect", "Ready", "Disconnect", "Chat", "RageQuit"};
    private static int count = 0;
    private String name = "Client-";

    public ClientConnection(String host, int port){
        name += ++count;
        try {
            System.out.println("TEST");
            senderSocket = new Socket(host, port);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void run(){

        System.out.println("BLA");

        //10 request per thread (Just for the test)
        for(int i =0; i < 10; i++){
            try {
                // Waiting 1s between to request
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {


                writer = new ObjectOutputStream(senderSocket.getOutputStream());
                reader = new ObjectInputStream(senderSocket.getInputStream());

                Message message = newRandomMessage();
                writer.writeObject(message);

                System.out.println("Message " + message + " sent to server");

                //Waiting for server response
                Message response = read();
                System.out.println("\t * " + name + " : Server response: " + response);

            } catch (IOException e1) {
                e1.printStackTrace();
            }

            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.writeObject(new CustomMessage("CLOSE"));
            writer.close();
        } catch (IOException e) {
            System.out.println("----------Something went wrong while closing---------");
        }
    }

    //Random Messages
    private Message newRandomMessage(){
        Random rand = new Random();
        return new CustomMessage(listMessages[rand.nextInt(listMessages.length)].toUpperCase());
    }

    //Read data:  byte --> string
    private Message read() throws IOException{
        try {
            Message message = (Message) reader.readObject();
            return message;
        } catch(ClassNotFoundException e) {
            return new CustomMessage("UNKNOWN");
        }
    }
}