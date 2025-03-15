import java.io.*;
import java.net.*;
import java.util.Date;


public class Server {


    DatagramSocket dSocket;
    DatagramPacket inPacket;
    byte[] bufferIn;
    public static final String giallo = "\u001B[33m";
    public static final String reset = "\u001B[0m";


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

            int clientPort = inPacket.getPort();
            InetAddress clientAddress = inPacket.getAddress();
            messageIn = new String(inPacket.getData(), 0, inPacket.getLength());
            System.out.println("Messaggio Client "+clientAddress+":"+ clientPort+": "+giallo + messageIn+reset);
            send();

        }
    }
    public void send() {
        InetAddress clientAddress = inPacket.getAddress();
        int clientPort = inPacket.getPort();
        //si crea un oggetto Date con la data corrente
        Date d = new Date();
        //si crea il messaggio del server in uscita associandolo alla connessione aperta con il client
        String messageOut = d.toString();
        byte[] bufferOut = messageOut.getBytes();
        //si crea un datagramma UDP in cui trasportare il messaggio di lunghezza length
        DatagramPacket outPacket = new DatagramPacket(bufferOut, bufferOut.length, clientAddress, clientPort);
        //si invia il messaggio al client
        try {
            dSocket.send(outPacket);
        } catch (IOException e) {
            System.err.println("Errore");
        }

    }
}

