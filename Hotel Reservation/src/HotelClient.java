/**
 **  Author:        Mitra Rouhipour
 **  Date:          10 April, 2021
 **  Course:        CPSC 1150
 **  Compiler:       JDK 1.8
 */
import java.io.*;
import java.net.*;
import java.util.*;
/**
 * The purpose of the client is to send command to the server to do reservation,cancel reservation and
 * show the availability info from the user.
 * The client communicates to the server that it's done send commands to the server
 * acknowledges that the client is done with the commands
 */

public class HotelClient {
    public static void main(String[] args) throws IOException{
        final int SBAP_PORT = 8888;

        //create a socket in localhost and HotelServer port number
        Socket s = new Socket("localhost", SBAP_PORT);

        InputStream instream = s.getInputStream();
        OutputStream outstream = s.getOutputStream();

        Scanner in = new Scanner(instream);
        PrintWriter out = new PrintWriter(outstream);

        //ask for user name
        Scanner input = new Scanner(System.in);
        System.out.println("Please Enter the user name");
        String userName = input.next();

        //send the user name to the server
        out.println("USER " + userName);
        out.flush();
        System.out.println("Receiving: " + in.nextLine());

        int menuItem;
        boolean quit =false;

        do {
            // print the menu
            System.out.println("\nPlease enter:");
            System.out.println("1. To do reservation");
            System.out.println("2. To cancel reservation");
            System.out.println("3. to show availability");
            System.out.println("4.to quit");

            //read the item
            menuItem = input.nextInt();
            //write a switch case and send request commands to the servers and receive response

            switch (menuItem) {
                case 1://To do reservation
                    System.out.println("Please enter the first day of the reservation");
                    int first = input.nextInt();

                    System.out.println("Please enter the last day of the reservation");
                    int last = input.nextInt();

                    //sent request to the server
                    out.println("RESERVE " + " " + first + " " + last);
                    out.flush();

                    //receive response
                    System.out.println("Receiving: " + in.nextLine());
                    break;

                case 2://To cancel reservation
                    //sent request to the server
                    out.println("CANCEL" );
                    out.flush();

                    //receive response
                    System.out.println("Receiving: " + in.nextLine());
                    break;

                case 3://to show availability
                    //sent request to the server
                    out.println("AVAIL" );
                    out.flush();

                    //receive response
                    System.out.println("Receiving:");
                    boolean continueReading = true;

                    do{
                        String line = in.nextLine();
                        System.out.println(line);
                        if(line.contains("31")){
                            continueReading = false;
                        }
                    } while(continueReading);

                    break;

                case 4://to quit
                    //sent request to the server
                    quit = true;
                    out.println("QUIT" );
                    out.flush();

                    //receive response
                    System.out.println("Receiving: " + in.nextLine());
                    break;
                default:
                    System.out.println("Invalid option");
                    }

        } while (quit == false);

        //close Socket
        s.close();
    }
}
