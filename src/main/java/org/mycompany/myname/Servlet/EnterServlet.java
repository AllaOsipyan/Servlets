package org.mycompany.myname.Servlet;

import org.mycompany.myname.accounts.AccountService;
import org.mycompany.myname.accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/authorization")
public class EnterServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
            String login = req.getParameter("login");
            String pass = req.getParameter("pass");
            UserProfile user =AccountService.getUserByLogin(login);
            if (AccountService.getUserByLogin(login) == null){
                    req.getRequestDispatcher("/registration.html").forward(req, resp);
                    return;
            }

            if (!pass.equals(user.getPass())){
                req.getRequestDispatcher("/authorization.html").forward(req, resp);
                return;
            }

            AccountService.addSession(req.getSession().getId(),user);
            String path = "http://localhost:8080/?path=c:\\filemanager\\"+login;
            resp.sendRedirect(new String(path.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
            AccountService.deleteSession(req.getSession().getId());
        req.getRequestDispatcher("/authorization.html").forward(req, resp);
    }
}