/**
 **  Author:        Mitra Rouhipour
 **  Date:          10 April, 2021
 **  Course:        CPSC 1150
 **  Compiler:       JDK 1.8
 */
import java.io.*;
import java.net.*;

/**
 * The purpose of the server is to do reservation, cancel the reservation
 * show availability information of the hotel when a the command is sent by a client.
 * The HotelServer should serve many clients.
 * The client communicates to the server that it's done sending command and the server
 * acknowledges that the client is done by doing that command.
 */

public class HotelServer {
    public static void main(String[] args)throws IOException {

        //creating an object from Hotel class
        Hotel reservation = new Hotel();

        //a constant that shows the port number
        final int SBAP_PORT = 8888;

        //Create a Socket
        ServerSocket server = new ServerSocket(SBAP_PORT);
        System.out.println("Waiting for client to connect . . .");

        while(true){
            Socket s = server.accept();
            System.out.println("Welcome to the hotel.");

            //when the service is created we pass a socket which accept return and
            // also we plugin reservation
            HotelService service =new HotelService(s, reservation);
            Thread t = new Thread(service);
            t.start();
        }


    }
}
