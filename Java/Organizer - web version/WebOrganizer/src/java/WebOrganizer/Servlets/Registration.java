/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebOrganizer.Servlets;

import WebOrganizer.DataBase.UserDataManagement;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet responsible for adding a new account and checking if the account can
 * be added
 *
 * @author Aleksandra Musiał
 * @version 2.0
 */
@WebServlet(name = "Registration", urlPatterns = {"/Registration"})
public class Registration extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Registration</title>");
            out.println("<style>\n"
                    + "body { font-family: Helvetica, Sans-Serif;}\n"
                    + "p, h1 {text-align: center;}</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Create new account</h1>");
            out.println(" <form action=\"Registration\" method=\"POST\">\n"
                    + "<p>User: <input type=\"text\" name=user placeholder=\"nick\"/></p>\n"
                    + "<p>Password: <input type=\"password\" name=password1 placeholder=\"password\"/></p> \n"
                    + "<p>Password: <input type=\"password\" name=password2 placeholder=\"repeat password\"/></p> \n"
                    + "<p><input type=\"submit\" name=create value=\"Create account\" /></p> \n"
                    + "</form>");
            out.println("</body>");
            out.println("</html>");

            String nick = request.getParameter("user");
            String password1 = request.getParameter("password1");
            String password2 = request.getParameter("password2");

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

            if (password2.equals(password1)) {

                if (!usersBase.checkUser(nick, password1) && !nick.equals("")) {
                    usersBase.addUser(nick, password1);
                    response.sendRedirect("index.html");
                } else {
                    out.println("<h1>Fail</h1>");
                    out.println("<p>The user has beed already registered. Adding acount failed!</p>\n");
                }

            } else {
                out.println("<h1>Fail</h1>");
                out.println("<p>Repeated password is different then the first one. Adding acount failed!</p>\n");
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
