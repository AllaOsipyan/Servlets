package org.mycompany.myname.Servlet;

import org.mycompany.myname.accounts.AccountService;
import org.mycompany.myname.accounts.UserProfile;
import org.mycompany.myname.database.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/registration")
public class RegServlet extends HttpServlet {

    AccountService accountService = new AccountService();
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
         String login = req.getParameter("login");
         String pass = req.getParameter("pass");
         String email = req.getParameter("email");

         if (login.equals("")|| pass.equals("")|| email.equals("")){
             req.getRequestDispatcher("/registration.jsp").forward(req, resp);
             return;
         }
        try {
            if( accountService.addNewUser(new UserProfile(login, pass, email))){
                req.setAttribute("error", "Login already exists");
                getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
                return;
            }
            if (login.contains("/")){
                req.setAttribute("error", "Login cannot contains /");
                getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
                return;
            }

        File file=new File("\\c:\\filemanager\\"+login);
        file.mkdirs();
        req.getRequestDispatcher("/authorization.html").forward(req, resp);
        } catch (SQLException e) {
        e.printStackTrace();
    }
    }
}
