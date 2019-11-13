
package org.mycompany.myname.Servlet;

import org.mycompany.myname.accounts.UserProfile;
import org.mycompany.myname.accounts.UsersDAO;
import org.mycompany.myname.database.DBService;
import org.mycompany.myname.realization.Lists;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class MyServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        String sessionId = req.getSession().getId();
        UserProfile profile = null;
        try {
            profile = UsersDAO.getUserBySessionId(sessionId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (profile == null){
            req.getRequestDispatcher("/authorization.html").forward(req, resp);
            return;
        }

        String path = req.getParameter("path");
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
