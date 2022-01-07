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
 * Given a socket that is opened already, it sets up the ComputationService
 * so that it can receive commands from the client and so that it can do reservation,
 * cancel reservation, show reservation info or quit. The response is sent to the Client.
 * Once the client indicates that there is no more data being sent, the
 * socket is closed.
 */
public class HotelService implements Runnable {
    private Socket s;
    private Scanner in;
    private PrintWriter out;
    private Hotel hotel;
    private String user = null;


    /**
     * Receives an open socket so that input/output streams can be attached
     * @param aSocket the socket that is opened
     * @param aHotel  a Hotel object that we do reserve or cancel in it.
     */
    public HotelService(Socket aSocket, Hotel aHotel) {
        s = aSocket;
        hotel = aHotel;
    }

    /**
     * Runs the code which is in its thread:
     *  - sets up the DataInputStream and the DataOutputStream
     *  - calls for the commands from the client to be executed
     *  - cleans up
     */
    @Override
    public void run() {
        //handle exceptions

        try {
            try {
                //get the input stream of the Socket which is passed in the constructor
                //we use Scanner object which we defined as an instance variable
                in = new Scanner(s.getInputStream());

                //get the output stream of the Socket which is passed in the constructor
                //we use PrintWriter object which we defined as an instance variable
                out = new PrintWriter(s.getOutputStream());
                doService();
            } finally {
                //close the socket if exception is generated
                s.close();
            }
        }
        catch (IOException exception) {
            //if an exception happen will show the reason
            exception.printStackTrace();
        }
    }
    //method that we intreprete the request and provide answer to the client
    public void doService() throws IOException{
        while(true){// an infinite loop. as long as the client is connected, we want to provide the service
            if(!in.hasNext()){ //in inputStream  does not have s any information
                return;// the connection will  be colsed
            }
            //the command can be any reservation actions in the hotel
            String command = in.next();
            switch(command){
                case "USER":
                    user = in.next();
                    out.println("Hello, " + user);
                    break;
                case "RESERVE":
                    //ask user the first and last day of the month
                    int first = in.nextInt();
                    int last = in.nextInt();
                    boolean didReserve = hotel.requestReservation(user,first,last);
                    if (didReserve){
                        out.println("Reservation made: " + user + " from " + first + " through " + last);
                    } else {
                        out.println("Reservation unsuccessful: " + user + " from " + first + " through " + last);
                    }
                    break;
                case "CANCEL":
                    boolean isCanceled = hotel.cancelReservation(user);
                    if(isCanceled){
                        out.println("Reservations successfully canceled for " + user);

                    }else{
                        out.println("Reservations not canceled for " + user + ", no current reservation.");
                    }
                    break;

                case "AVAIL":
                    out.println(hotel.toString());
                    break;
                case "QUIT":
                    out.println("Closing Connection");
                    out.flush();
                    return;
                default:
                    out.println("Invalid command: Closing Connection");
                    out.flush();
                    return;

            }
            out.flush();
        }
    }

}
