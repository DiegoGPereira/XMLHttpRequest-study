package com.diego.ajax.Control;

import com.diego.ajax.DAO.UsersDAO;
import com.diego.ajax.Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Diego
 */
@WebServlet(name = "Servlet", urlPatterns = {"/Login", "/Register", "/UsersList", "/Delete"})
@MultipartConfig
public class Servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        try {
            if (uri.equals(request.getContextPath() + "/UsersList")) {
                listAll(request, response);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listAll(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {

        UsersDAO dao = new UsersDAO();

        List<Users> list = dao.listAll();

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            for (Users user : list) {
                out.println("<tr>");
                out.println("<td><ul class=\"uk-iconnav\">\n"
                        + "    <li><a href=\"#\" uk-icon=\"icon: file-edit\"></a></li>\n"
                        + "    <li><a href=\"#\" onClick=\"remove("+ user.getId() +");\" uk-icon=\"icon: trash\"></a></li>\n"
                        + "</ul></td>");
                out.println("<td>" + user.getId() + "</td>");
                out.println("<td>" + user.getName() + "</td>");
                out.println("<td>" + user.getPassword() + "</td>");
                out.println("</tr>");
            }
        }
    }
    
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        try {
            if (uri.equals(request.getContextPath() + "/Register")) {
                create(request, response);
            } else if (uri.equals(request.getContextPath() + "/Login")) {
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println("Bem vindo!");
                    out.println("<br>");
                    out.println(request.getParameter("username"));
                }
            } else if (uri.equals(request.getContextPath() + "/Delete")) {
                delete(request, response);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void create(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
        Users user = new Users();
        user.setName(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));

        UsersDAO dao = new UsersDAO();
        dao.create(user);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("User " + user.getName() + " was succesfull registered!");
        }

    }
    
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
        Users user = new Users();
        user.setId(Integer.parseInt(request.getParameter("id")));

        UsersDAO dao = new UsersDAO();
        dao.delete(user);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("User " + user.getId() + " was succesfull deleted!");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
