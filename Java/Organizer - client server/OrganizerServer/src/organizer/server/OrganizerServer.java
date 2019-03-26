/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organizer.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import organizer.exceptions.DatePriorTodayException;
import organizer.model.TaskManager;

/**
 * The main class of the server 
 * 
 * @author Aleksandra Musiał
 * @version 1.0
 */
public class OrganizerServer {

    /**
     * Main method of the program - the user can ask server for showing the tasks 
     * or for writing the new one. HELP command to get information, QUIT command 
     * to close the connection
     * Configuration of the port by taking information from the propeties file
     *
     * @param args all parametres are ignored
     */
    public static void main(String[] args) {

        
        Properties properties = new Properties();
        
        TaskManager taskManager = new TaskManager();
        
        try (FileInputStream in = new FileInputStream(".properties")) {
            properties.load(in);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        try (Server server = new Server(Integer.parseInt(properties.getProperty("port")))) {
            System.out.println("Server started");
            while (true) {
                Socket socket = server.getServerSocket().accept();
                try (OrganizerSingleService singleService = new OrganizerSingleService(socket)) {
                    try {
                        singleService.realize(taskManager);
                    } catch (DatePriorTodayException ex) {
                        Logger.getLogger(OrganizerServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
}
