/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebOrganizer.FileSupport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class implementing UserManagementInterface. Responsible for accessing the file
 * 
 * @author Aleksandra Musiał
 * @version 1.0
 */
public class UserFileManagement implements UserManagementInterface {
    
    /**
     * Name of the file or path to the file with users data
     */
    private String fileName = "users.txt";
   /**
    * Object for reading character files
    */
    private FileReader reader;
    /**
     * Object for writing character files
     */
    private FileWriter writer;
    /**
     * Represent the file which will be accessed
     */
    private File file;
    /**
     * Object for reading text from a character-input stream and buffering characters
     */
    private BufferedReader in;

    /**
     * Constructor
     */
    public UserFileManagement() {
        file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(UserFileManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Overriden method for checking if the user is in the base by reading the file
     * with users data
     * 
     * @param nick name of the user to be checked
     * @param password password of the user to be checked
     * @return true if the user is in the base, if not returns false
     */
    @Override
    public boolean checkUser(String nick, String password) {

        try {
            reader = new FileReader(fileName);
            in = new BufferedReader(reader);

        } catch (FileNotFoundException ex) {
            return false;
        } 

        String line;
        String[] fileOutput;
        try {
            while ((line = in.readLine()) != null) {
                fileOutput = line.split("\\s");

                if (fileOutput.length == 2) {
                    if (fileOutput[0].equals(nick) && fileOutput[1].equals(password)) {
                        in.close();
                        reader.close();
                        return true;
                    }
                }
            }
            in.close();
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(UserFileManagement.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return false;
    }

    /**
     * Overriden method for adding new account by writing new line to the file
     * 
     * @param nick name of user to be written 
     * @param password password of user to be written 
     */
    @Override
    public void addUser(String nick, String password) {

            try {
                writer = new FileWriter(fileName, true);
            } catch (IOException ex) {
                Logger.getLogger(UserFileManagement.class.getName()).log(Level.SEVERE, null, ex);
            }

        try {

            writer.write(nick + " " + password + "\r\n");
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(UserFileManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
