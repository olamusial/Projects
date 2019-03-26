/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebOrganizer.Servlets;

import WebOrganizer.DataBase.UserDataManagement;
import WebOrganizer.Exceptions.DatePriorTodayException;
import WebOrganizer.Model.TaskManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Main class of the servlet that enables adding new tasks for logged in users.
 * It lets user also to go to another servlet
 *
 * @author Aleksandra Musiał
 * @version 2.0
 */
@WebServlet(name = "OrganizerForm", urlPatterns = {"/OrganizerForm"})
public class OrganizerForm extends HttpServlet {

    /**
     * Model object
     */
    private TaskManager taskManager;

    /**
     * Field representing the class for accessing base with users' data
     */
    private UserDataManagement usersBase;

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

        Cookie[] cookies = request.getCookies();
        String cookieUser = "";
        if (cookies != null) {
            cookieUser = cookies[0].getValue();
        }
        if (cookieUser.equals("") || cookieUser == null) {
            response.sendRedirect("index.html");
        }

       HttpSession session = request.getSession(false);
        if (session != null) {
            taskManager = (TaskManager) session.getAttribute("tasks");
            usersBase = (UserDataManagement) session.getAttribute("usersBase");
        } else {
            response.sendRedirect("index.html");
        }

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet OrganizerForm</title>");
        out.println("<style>\n"
                + "body { font-family: Helvetica, Sans-Serif;}\n"
                + "p, h1 {text-align: center;}</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>" + cookieUser + "'s ORGANIZER</h1>");
        out.println("<form action=\"Logout\" method=POST>");
        out.println("<p><input type=\"submit\" name=logout value=\"LogOut\" /></p>");
        out.println("</form>");
        out.println("<form action=\"\" method=\"POST\">\n"
                + " <p><label for=\"tdate\">Enter date of task:</label>\n"
                + " <input type=\"date\" id=\"tdate\" name=\"taskDate\" required pattern=\"[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}\">\n"
                + " <span class=\"validity\"></span></p>\n"
                + " <p><label for=\"tname\">Enter name of task:</label>\n"
                + " <input type=\"text\" id=\"tname\" name=\"taskName\"  </p>"
                + " <p> <input type=\"submit\" name=\"add\" value=\"Add task\"></p>\n"
                + "</form>");
        out.println("<form action=\"OrganizerHistory\" method=POST>\n"
                + "<p> <input type=\"submit\" name=\"show\" value=\"Show tasks\"></p>\n"
                + "</form>");
        out.println("</body>");
        out.println("</html>");

        String addButton = request.getParameter("add");
        if (addButton != null) {
            String date = request.getParameter("taskDate");
            String name = request.getParameter("taskName");

            String[] dateParts = date.split("-");
            String dateTask = "";
            if (dateParts.length == 3) {
                dateTask = dateParts[2] + "/" + dateParts[1] + "/" + dateParts[0];
            }
            if (date != null && !name.equals("")) {
                try {
                    taskManager.addTask(dateTask, name);
                } catch (DatePriorTodayException ex) {
                    out.println("<p>The day you entered has already passed</p>");
                }
                usersBase.saveTasksToBase(dateTask, name);
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
