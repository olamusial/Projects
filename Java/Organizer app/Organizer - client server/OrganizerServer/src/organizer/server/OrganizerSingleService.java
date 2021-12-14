/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organizer.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import organizer.exceptions.DatePriorTodayException;
import organizer.model.Task;
import organizer.model.TaskManager;

/**
 *The server class servicing a single connection
 * 
 * @author Aleksandra Musiał
 * @version 1.0
 */
public class OrganizerSingleService implements Closeable {

    /**
     * Field representing connection to the client
     */
    private Socket socket;
    
    /**
     * Field representing input stream
     */
    private BufferedReader input;
    
    /**
     * Field representing output stream
     */
    private PrintWriter output;

    /**
     * The constructor of instance of the OrganizerSingleService class
     *
     * @param socket socket representing connection to the client
     */
    public OrganizerSingleService(Socket socket) throws IOException {
        this.socket = socket;
        output = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream())), true);
        input = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));
    }

    /**
     * Realizes the service
     * 
     * @param taskManager instance of the TaskManager class (model)
     * @throws DatePriorTodayException 
     */
    public void realize(TaskManager taskManager) throws DatePriorTodayException {
        output.println("START");
        Boolean connection = true;

        try {
            while (connection) {

                String request = input.readLine();

                if (request.toUpperCase().equals("QUIT")) {
                    connection = quitCommand();
                } else if (request.toUpperCase().equals("HELP")) {
                    helpCommand();
                } else {
                    String[] splitRequest = request.split("\\s+");
                    if (splitRequest[0].toLowerCase().equals("r")) {
                        readCommand(splitRequest, taskManager);
                    } else {
                        if (splitRequest[0].toLowerCase().equals("w")) {
                            writeCommand(splitRequest, taskManager);
                        } else {
                            output.println("UNKNOWN COMMAND");
                            output.println("Type HELP to get information");
                        }
                    }
                }
            }
            System.out.println("closing...");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    /**
     * Manage the answer for the write command
     * 
     * @param splitRequest request from user divided into single words
     * @param taskManager instance of the TaskManager class (model)
     */
    private void writeCommand(String[] splitRequest, TaskManager taskManager) {
        if (splitRequest.length >= 3) {
            output.println("WRITE COMMAND");
            try {
                taskManager.addTask(splitRequest[1], splitRequest[2]);
                output.println("Adding new task - success");
            } catch (DatePriorTodayException e) {
                output.println("Adding new task - fail: date prior today");
            }
        } else {
            output.println("WRONG PARAMETER");
            output.println("To write new task enter: 'w dd/mm/yyyy taskName'");
        }
    }
    
    /**
     * Manage the answer for the read command
     * 
     * @param splitRequest request from user divided into single words
     * @param taskManager instance of the TaskManager class (model)
     */
    private void readCommand(String[] splitRequest, TaskManager taskManager) {
        if (splitRequest.length >= 2) {
            ArrayList list = new ArrayList<Task>();
            switch (splitRequest[1]) {
                case "all":
                    output.println("READ COMMAND");
                    list = taskManager.getTaskList();
                    sendTasks(list);
                    break;
                case "today":
                    output.println("READ COMMAND");
                    list = taskManager.getTaskListForToday();
                    sendTasks(list);
                    break;
                case "before":
                    output.println("READ COMMAND");
                    list = taskManager.getTaskListBeforeToday();
                    sendTasks(list);
                    break;
                case "after":
                    output.println("READ COMMAND");
                    list = taskManager.getTaskListAfterToday();
                    sendTasks(list);
                    break;
                default:
                    output.println("WRONG PARAMETER");
                    output.println("To read task enter: 'r all/today/before/after'");
            }
        } else {
            output.println("WRONG PARAMETER");
            output.println("To read task enter: 'r all/today/before/after'");
        }
    }

    /**
     * Manage the answer for the quit command and end the realize method
     * 
     * @return always false 
     */
    private Boolean quitCommand() {
        output.println("QUIT COMMAND");
        return false;
    }

    /**
     * Manage the answer for the help command
     */
    private void helpCommand() {
        output.println("HELP COMMAND");
        output.println("read tasks: 'r all/today/before/after', write new task: 'w dd/mm/yyyy taskName' (wrong format of date -> default today date)");
    }

    /**
     * Sends requested data to the client. First amount of the tasks, then tasks
     * 
     * @param list list of tasks which should be send to the user
     */
    private void sendTasks(ArrayList<Task> list) {
        output.println(list.size());
        for (Task t : list) {
            output.println(t.getDateString() + " " + t.getName());
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
