import java.io.*;
import java.net.*;
import java.util.Date;


public class Server {


    DatagramSocket dSocket;
    DatagramPacket inPacket;
    byte[] bufferIn;

    String messageIn;

    private InputStream in;
    private OutputStream out;


    public Server(int port) {
        try {
            //si crea il socket e si associa alla porta specifica
            dSocket = new DatagramSocket(port);
            System.out.println("Apertura porta in corso!");


        } catch (BindException e) {
            System.err.println("Errore porta già in uso");
        } catch (SocketException e) {
            System.err.println("Errore Socket");


        }
        System.out.println("Server in ascolto sulla porta " + port + "!\n");



    }
    public void receive() {
        while(true){
            bufferIn = new byte[256];

            inPacket = new DatagramPacket(bufferIn, bufferIn.length);

            try {
                dSocket.receive(inPacket);
            } catch (IOException e) {
                System.err.println("Errore");
            }


            messageIn = new String(inPacket.getData(), 0, inPacket.getLength());
            System.out.println("Messaggio Client: " + messageIn);


        }
    }
}

