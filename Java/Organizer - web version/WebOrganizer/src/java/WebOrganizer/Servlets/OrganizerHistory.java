/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebOrganizer.Servlets;

import WebOrganizer.DataBase.UserDataManagement;
import WebOrganizer.Model.Task;
import WebOrganizer.Model.TaskManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Main class of the servlet that enables displaying tasks for logged in users.
 * It lets user also to get to another servlet
 *
 * @author Aleksandra Musiał
 * @version 2.0
 */
@WebServlet(name = "OrganizerHistory", urlPatterns = {"/OrganizerHistory"})
public class OrganizerHistory extends HttpServlet {

    /**
     * Model object
     */
    private TaskManager taskManager;

    /**
     * Field representing the class for accessing base with users' data
     */
    private UserDataManagement usersBase;
    
    /**
     * Field for checking if this is the first request for viewing the task
     * (if true data should be taken from the base)
     */
    private Boolean first = true;

    /**
     * Method for displaying tasks
     *
     * @param taskList list of tasks to be displayed
     * @param out for printing formatted representations of objects to a
     * text-output stream
     */
    private void showTasks(ArrayList<Task> taskList, PrintWriter out) {

        if (!taskList.isEmpty()) {

            for (Task t : taskList) {
                out.println("<p>" + t.getDateString() + " " + t.getName() + "</p>");
            }
        } else {
            out.println("<p>No tasks</p>");
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
         if (session != null) {
            taskManager = (TaskManager) session.getAttribute("tasks");
            usersBase = (UserDataManagement) session.getAttribute("usersBase");
             if(first) {
            usersBase.readTaskFromBase(taskManager);
            first = false;
            }

        } else {
            response.sendRedirect("index.html");
        }

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet OrganizerHistory</title>");
        out.println("<style>\n"
                + "body { font-family: Helvetica, Sans-Serif;}\n"
                + "p, h1 {text-align: center;}</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action=\"OrganizerForm\" method=POST>\n"
                + " <p> <input type=\"submit\" name=\"return\" value=\"Return\"> <p>\n"
                + "</form>");
        out.println(" <h1>Which tasks do you want to see?</h1>");
        out.println("<form action=\"\" method=POST>\n"
                + " <p> <input type=\"radio\" name=\"period\" value=\"all\"> All</p>\n"
                + " <p> <input type=\"radio\" name=\"period\" value=\"today\"> For Today</p>\n"
                + " <p> <input type=\"radio\" name=\"period\" value=\"atfer\"> Atfer today</p>\n"
                + " <p> <input type=\"radio\" name=\"period\" value=\"before\"> Before today</p>\n"
                + " <p> <input type=\"submit\" name=\"show\" value=\"Show tasks\"></p>\n"
                + "</form>");
        out.println("</body>");
        out.println("</html>");

        String timePeriod = request.getParameter("period");
        if (timePeriod != null) {
            switch (timePeriod) {
                case "all":
                    showTasks(taskManager.getTaskList(), out);
                    break;
                case "today":
                    showTasks(taskManager.getTaskListForToday(), out);
                    break;
                case "atfer":
                    showTasks(taskManager.getTaskListAfterToday(), out);
                    break;
                case "before":
                    showTasks(taskManager.getTaskListBeforeToday(), out);
                    break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
