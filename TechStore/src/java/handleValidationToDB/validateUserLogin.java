/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package handleValidationToDB;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.User;
import database.userLoginHandling;
import database.MessageFromDB;
import java.sql.SQLException;

/**
 *
 * @author Omar Samir
 */
public class validateUserLogin extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        userLoginHandling ulh = new userLoginHandling();
        User account = new User();
        if (request.getParameter("singin-email") == null || request.getParameter("singin-password") == null) {
            out.println("<h1>Invalid inputs </h1>");
        } else {
            account.setEmail(request.getParameter("singin-email"));
            account.setPassword(request.getParameter("singin-password"));
        }
        try {
            MessageFromDB ms = ulh.checkLogin(account);
            if (ms.getStatus()) {
                response.sendRedirect("HomePage.html");
            } else {
                response.sendRedirect("ManageUser/userLogin.jsp");
            }
        } catch (SQLException ex) {
        }
    }
    
}
