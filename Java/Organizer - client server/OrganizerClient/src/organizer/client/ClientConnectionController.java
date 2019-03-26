/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organizer.client;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import organizer.view.ClientView;

/** Class for managing the connection 
 *
 * @author Aleksandra Musiał
 * @version 1.0
 */
public class ClientConnectionController implements Closeable {

    /**
     * Field representing the input stream from user's keybord
     */
    private Scanner scanner = new Scanner(System.in);
    
    /**
     * Field representing the socket waiting for client connections
     */
    private Socket socket;

    /**
     * Creates the socket by taking information from properties file
     *
     * @throws IOException when prot is already bind
     */
    ClientConnectionController() throws IOException {

        Properties properties = new Properties();    
        
        try (FileInputStream in = new FileInputStream(".properties")) {
            properties.load(in);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        try {
           this.socket = new Socket(properties.getProperty("address"), Integer.parseInt(properties.getProperty("port")));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Method used if the user entered the parameters for running the program 
     * in the command line
     * 
     * @param args arguments from command line
     * @throws IOException 
     */
    public void clientServiceWithParameters(String... args) throws IOException {

        Scanner serverScanner = new Scanner(socket.getInputStream());
        PrintStream requestForServer = new PrintStream(socket.getOutputStream());

        String messageForServer;
        if (args.length == 2) {
            messageForServer = args[0] + " " + args[1];
        } else {
            messageForServer = args[0] + " " + args[1] + " " + args[2];
        }
        Boolean connection = true;
        Boolean parameters = true;
        ClientView view = new ClientView();
        view.printServerMessage(serverScanner.nextLine());

        while (connection) {

            if (!parameters) {
                messageForServer = scanner.nextLine();
            }
            
            parameters = false;
            requestForServer.println(messageForServer);

            switch (serverScanner.nextLine()) {
                case "QUIT COMMAND":
                    connection = false;
                    break;
                case "HELP COMMAND":
                    view.printServerMessage(serverScanner.nextLine());
                    break;
                case "READ COMMAND":
                    int size = Integer.parseInt(serverScanner.nextLine());
                    ArrayList<String> listFromServer = new ArrayList<String>();
                    for (int i = 0; i < size; i++) {
                        listFromServer.add(serverScanner.nextLine());
                    }
                    view.printTasksFromServer(listFromServer);
                    break;
                case "WRONG PARAMETER":
                    view.printServerMessage(serverScanner.nextLine());
                    break;
                case "WRITE COMMAND":
                    view.printServerMessage(serverScanner.nextLine());
                    break;
                case "UNKNOWN COMMAND":
                    view.printServerMessage(serverScanner.nextLine());
                    break;
            }
        }
    }

    /**
     * Method used if the user did not enter the parameters for running the program 
     * in the command line
     * 
     * @throws IOException 
     */
    public void clientService() throws IOException {

        Scanner serverScanner = new Scanner(socket.getInputStream());
        PrintStream requestForServer = new PrintStream(socket.getOutputStream());
        String messageForServer;
        Boolean connection = true;
        ClientView view = new ClientView();
        view.printServerMessage(serverScanner.nextLine());

        while (connection) {

            messageForServer = scanner.nextLine();
            requestForServer.println(messageForServer);

            switch (serverScanner.nextLine()) {
                case "QUIT COMMAND":
                    connection = false;
                    break;
                case "HELP COMMAND":
                    view.printServerMessage(serverScanner.nextLine());
                    break;
                case "READ COMMAND":
                    int size = Integer.parseInt(serverScanner.nextLine());
                    ArrayList<String> listFromServer = new ArrayList<String>();
                    for (int i = 0; i < size; i++) {
                        listFromServer.add(serverScanner.nextLine());
                    }
                    view.printTasksFromServer(listFromServer);
                    break;
                case "WRONG PARAMETER":
                    view.printServerMessage(serverScanner.nextLine());
                    break;
                case "WRITE COMMAND":
                    view.printServerMessage(serverScanner.nextLine());
                    break;
                case "UNKNOWN COMMAND":
                    view.printServerMessage(serverScanner.nextLine());
                    break;
            }
        }
    }

    /**
     * Method override from the Closable interface, ends the connection
     * 
     * @throws IOException 
     */
    @Override
    public void close() throws IOException {
        if (socket != null) {
            socket.close();
        }
    }
}
