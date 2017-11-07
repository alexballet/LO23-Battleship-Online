package lo23.battleship.online;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.text.DateFormat;
import java.util.Date;

//  {"Connect", "Ready", "Disconnect", "Chat", "RageQuit"};
public class ClientProcessor implements Runnable{

    private Socket sock;
    private ObjectOutputStream writer = null;
    private ObjectInputStream reader = null;

    public ClientProcessor(Socket clientSocket){
        sock = clientSocket;
    }

    //New thread to process request
    public void run(){
        System.err.println("New client request processing");

        boolean closeConnexion = false;
        
        //While socket is not close (active connection)
        while(!sock.isClosed()){

            try {
                writer = new ObjectOutputStream(sock.getOutputStream());
                reader = new ObjectInputStream(sock.getInputStream());

                //Waiting for client request
                Message request = read();
                InetSocketAddress remote = (InetSocketAddress)sock.getRemoteSocketAddress();

                //Displaying info about request
                String debug = "";
                debug = "Thread : " + Thread.currentThread().getName() + ". ";
                debug += "Sender : " + remote.getAddress().getHostAddress() +".";
                debug += "Port : " + remote.getPort() + ".\n";
                debug += "\t -> Request Content : " + request + "\n";
                String timeStamps = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM).format(new Date());
                debug += "\n Request Received at " + timeStamps;
                System.err.println("\n" + debug);

                Message response = request.process();
                //Send response
                writer.writeObject(response);
                //Warning: use flush()
                //Otherwise data is not sent to the client(infinitely waiting)
                //writer.flush();
                if (response.type.equals("Communication Over")) closeConnexion = true;
                if(closeConnexion){
                    System.err.println("Close Message");
                    writer = null;
                    reader = null;
                    System.err.println("----------------Closing-----------------");
                    sock.close();
                    break;
                }
            }catch(SocketException e){
                System.err.println(" / ! \\ Interrupted: Something went wrong / ! \\");
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Read data:  byte --> string
    private Message read() throws IOException{
        try {
            Message message = (Message) reader.readObject();
            return message;
        } catch(ClassNotFoundException e) {
            return new Message("UNKNOWN");
        }
    }

}