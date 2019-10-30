<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.ArrayList" %>

<%
    String parentPath = (String)request.getAttribute("path");
    ArrayList<File> directories = ( ArrayList<File>) request.getAttribute("directory");
    ArrayList<File> files = ( ArrayList<File>) request.getAttribute("files");
%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8">
        <title>MyServlet</title>
    </head>
    <body>
        <form action="authorization" method="get">
            <input type="submit" value="Выход">
        </form>
        <div>
            <%
                out.println("<a href=\'?path=" + parentPath + "/'>" + parentPath + "</a>" + "<br>");
                if (directories != null) {
                    for (File el: directories) {
                        out.println("<a href=\'?path=" + el.getAbsolutePath() + "/'>" + el.getName() + "</a>" + "<br/>");
                    }
                }

                if (files != null) {
                    for (File el: files) {
                         out.println( el.getName() + "<br>");
                     }
                }
            %>
        </div>
    </body>
</html>