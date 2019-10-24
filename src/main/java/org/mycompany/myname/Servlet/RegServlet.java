package org.mycompany.myname.Servlet;

import org.mycompany.myname.accounts.AccountService;
import org.mycompany.myname.accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/registration")
public class RegServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
         String login = req.getParameter("login");
         String pass = req.getParameter("pass");
         String email = req.getParameter("email");

         if (login==null|| pass==null || email==null){
             req.getRequestDispatcher("/registration").forward(req, resp);
             return;
         }

        AccountService.addNewUser(new UserProfile(login, pass, email));

        File file=new File("\\c:\\filemanager\\"+login);
        file.mkdirs();
        req.getRequestDispatcher("/authorization").forward(req, resp);
    }
}
