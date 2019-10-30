
package org.mycompany.myname.Servlet;

import org.mycompany.myname.accounts.AccountService;
import org.mycompany.myname.accounts.UserProfile;
import org.mycompany.myname.database.DBConnection;
import org.mycompany.myname.realization.Lists;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;

public class MyServlet extends HttpServlet {
    AccountService accountService = new AccountService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            accountService.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sessionId = req.getSession().getId();
        UserProfile profile = null;
        try {
            profile = accountService.getUserBySessionId(sessionId);

            if (profile == null){
                req.getRequestDispatcher("/authorization.html").forward(req, resp);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String path = req.getParameter("path");
        if (path.equals("")){
            req.getRequestDispatcher("/wrong.jsp").forward(req, resp);
        }
        path = new String(path.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        if(path.contains("c:\\filemanager\\"+profile.getLogin())){
            Lists lists = new Lists(path);

                req.setAttribute("path", lists.getCurDir());
                req.setAttribute("directory", lists.getDir());
                req.setAttribute("files", lists.getFiles());

                getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);

        }
        else{
            req.setAttribute("path", "c:\\filemanager\\"+profile.getLogin());
            req.getRequestDispatcher("/wrong.jsp").forward(req, resp);
        }

    }


}
