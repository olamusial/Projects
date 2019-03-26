/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organizer.server;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;

/** Class representing a server for the application
 *
 * @author Aleksandra Musiał
 * @version 1.0
 */
public class Server implements Closeable {

    /**
     * field represents the socket waiting for client connections
     */
    private ServerSocket serverSocket;

    /**
     * Creates the server socket
     *
     * @throws IOException when prot is already bind
     */
    Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }
    
    /**
     * Enable to get the server socket
     * 
     * @return server socket
     */
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    /**
     * Method override from the Closable interface, ends the connection
     * 
     * @throws IOException 
     */
    @Override
    public void close() throws IOException {
        if (serverSocket != null) {
            serverSocket.close();
        }

    }

}