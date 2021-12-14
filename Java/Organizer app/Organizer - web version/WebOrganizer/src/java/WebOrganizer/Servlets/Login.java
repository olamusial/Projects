/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebOrganizer.Servlets;

import WebOrganizer.DataBase.UserDataManagement;
import WebOrganizer.Model.TaskManager;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet for management of login process. Checks if the user login and
 * password are correct. Sets the attribute of the session and prepare cookie
 * with user name
 *
 * @author Aleksandra Musiał
 * @version 2.0
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    /**
     * Field representing the class for accessing base with users' data
     */
    private UserDataManagement usersBase;
    /**
     * Model object
     */
    private TaskManager taskManager;

    /**
     * Overriden method, initializes the model object when the servlet is
     * started
     */
    @Override
    public void init() {
        taskManager = new TaskManager();
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

        String nick = request.getParameter("user");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        if (null == session.getAttribute("usersBase")) {

            String JDBCdriver = getServletContext().getInitParameter("JdbcDriver");
            String databaseName = getServletContext().getInitParameter("DatabaseName");
            String databaseUserName = getServletContext().getInitParameter("DatabaseUserName");
            String databaseUserPassword = getServletContext().getInitParameter("DatabaseUserPassword");
            usersBase = new UserDataManagement(JDBCdriver, databaseName, databaseUserName, databaseUserPassword);
            session.setAttribute("usersBase", usersBase);
            
        } else {
            usersBase = (UserDataManagement) session.getAttribute("usersBase");
        }

        if (!nick.equals("") && !password.equals("")) {
            if (usersBase.checkUser(nick, password)) {
                Cookie cookie = new Cookie("user", nick);
                response.addCookie(cookie);

                session.setAttribute("tasks", taskManager);

                response.sendRedirect(request.getContextPath() + "/OrganizerForm");

            } else {
                response.sendRedirect("index.html");
            }

        } else {
            response.sendRedirect("index.html");
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
