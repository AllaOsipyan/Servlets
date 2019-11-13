package org.mycompany.myname.Servlet;

import org.mycompany.myname.accounts.UserProfile;
import org.mycompany.myname.accounts.UsersDAO;
import org.mycompany.myname.database.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@WebServlet("/authorization")
public class EnterServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        UserProfile user = null;
        try {
            user = UsersDAO.getUserByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (user == null){
            req.setAttribute("error", "Please register");
            getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
            return;
        }

        if (!pass.equals(user.getPass())){
            req.getRequestDispatcher("/authorization.html").forward(req, resp);
           return;
        }

        try {
            UsersDAO.addSessionId(req.getSession().getId(),user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String path = "http://localhost:8080/?path=c:\\filemanager\\"+login;
        resp.sendRedirect(new String(path.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            UsersDAO.deleteSessionId(req.getSession().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("/authorization.html").forward(req, resp);
    }
}