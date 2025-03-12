import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Client {
    int port=2030;

    InetAddress serverAddress;
    DatagramSocket dSocket;
    DatagramPacket outPacket;
    DatagramPacket inPacket;



    public Client()  {
        try {

            serverAddress = InetAddress.getLocalHost();

            System.out.println("Indirizzo del server trovato!");

        } catch (UnknownHostException e) {
            System.err.println("Errore DNS");

    }

    }
    public void input(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("Inserisci una stringa: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")){
                break;
            }
            send(input);

        }
        scanner.close();

    }

    public void send(String message)  {
        try {
            dSocket = new DatagramSocket();
        } catch (SocketException e) {
            System.err.println("Errore Socket");
        }


        //si prepara il datagramma con i dati da inviare
        outPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, port);

        //si inviano i dati
        try {
            dSocket.send(outPacket);
        } catch (IOException e) {
            System.err.println("Errore di invio");
        }



    }
    }
