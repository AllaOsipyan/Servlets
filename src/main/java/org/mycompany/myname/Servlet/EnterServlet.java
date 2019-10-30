package org.mycompany.myname.Servlet;

import org.mycompany.myname.accounts.AccountService;
import org.mycompany.myname.accounts.UserProfile;
import org.mycompany.myname.database.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/authorization")
public class EnterServlet extends HttpServlet {
    AccountService accountService = new AccountService();
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
            String login = req.getParameter("login");
            String pass = req.getParameter("pass");
        UserProfile user = null;
        try {
            user = accountService.getUserByLogin(login);

            if (user == null){
                        req.getRequestDispatcher("/registration.jsp").forward(req, resp);
                        return;
            }

            if (!pass.equals(user.getPass())){
                req.getRequestDispatcher("/authorization.html").forward(req, resp);
               return;
            }

            accountService.addSession(req.getSession().getId(),user);
            String path = "http://localhost:8080/?path=c:\\filemanager\\"+login;
            resp.sendRedirect(new String(path.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            accountService.deleteSession(req.getSession().getId());

            req.getRequestDispatcher("/authorization.html").forward(req, resp);
        } catch (SQLException e) {
        e.printStackTrace();
    }
    }
}