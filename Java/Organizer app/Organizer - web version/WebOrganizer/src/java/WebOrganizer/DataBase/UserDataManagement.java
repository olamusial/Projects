/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebOrganizer.DataBase;

import WebOrganizer.Exceptions.DatePriorTodayException;
import WebOrganizer.FileSupport.UserManagementInterface;
import WebOrganizer.Model.TaskManager;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Class implementing UserManagementInterface. Responsible for accessing the
 * data base with users' nicks and passwords and with tasks
 *
 * @author Aleksandra Musiał
 * @version 1.0
 */
public class UserDataManagement implements UserManagementInterface {

    /**
     * Field representig the statement for executing a static SQL statement and
     * returning the results it produces
     */
    private Statement statement;

    /**
     * Constructor. The connection with data base is made only once when the
     * object is created. Tables in data base are made if they do not exist
     * 
     * @param JdbcDriver driver name
     * @param databaseName name of the database
     * @param databaseUserName user name for connecting the database
     * @param databaseUserPassword password for connecting the database
     */
    public UserDataManagement(String JdbcDriver, String databaseName, String databaseUserName, String databaseUserPassword ) {
        try {
            Class.forName(JdbcDriver);
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
            return;
        }

        try {
            Connection con = DriverManager.getConnection(databaseName, databaseUserName, databaseUserPassword);
            DatabaseMetaData metaData = con.getMetaData();
            statement = con.createStatement();
            java.sql.ResultSet tables = metaData.getTables(null, null, "USERS", null);
            if (!tables.next()) {

                statement.executeUpdate("CREATE TABLE USERS "
                        + "(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY"
                        + ", nick VARCHAR(50), "
                        + "password VARCHAR(50))");
            }
            tables.close();

            java.sql.ResultSet table = metaData.getTables(null, null, "TASKS", null);
            if (!table.next()) {

                statement.executeUpdate("CREATE TABLE TASKS "
                        + "(date VARCHAR(15), "
                        + "name VARCHAR(50))");
            }
            table.close();

        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }

    }

    /**
     * Overriden method for checking if the user is in the base by executing the
     * SQL select statement
     *
     * @param nick name of the user to be checked
     * @param password password of the user to be checked
     * @return true if the user is in the base, if not returns false
     */
    @Override
    public boolean checkUser(String nick, String password) {

        try {
            ResultSet rs = statement.executeQuery("SELECT password FROM USERS where nick = '" + nick + "'");
            String found;
            while (rs.next()) {
                found = rs.getString("password");
                if (found.equals(password)) {
                    return true;
                }
            }
            rs.close();
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
        return false;
    }

     /**
     * Overriden method for adding new account by executing the
     * SQL insert statement
     * 
     * @param nick name of user to be written 
     * @param password password of user to be written 
     */
    @Override
    public void addUser(String nick, String password) {

        try {
            statement.executeUpdate("INSERT INTO USERS (nick, password) VALUES ('" + nick + "', '" + password + "')");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }

    }

    /**
     * Method for saving new tasks to the base by executing the
     * SQL insert statement
     * 
     * @param date date of the task to be store in the base
     * @param name name of the task to be store in the base
     */
    public void saveTasksToBase(String date, String name) {
        try {
            statement.executeUpdate("INSERT INTO TASKS VALUES ('" + date + "', '" + name + "')");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    /**
     * Method for getting data from the base by executing the SQL insert statement.
     * Data is stored in the model object 
     * 
     * @param taskManager model object 
     */
    public void readTaskFromBase(TaskManager taskManager) {
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM TASKS");
            while (rs.next()) {
                try {
                    taskManager.addTask(rs.getString("date"), rs.getString("name"));
                } catch (DatePriorTodayException ex) {
                    Logger.getLogger(UserDataManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            rs.close();
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

}
