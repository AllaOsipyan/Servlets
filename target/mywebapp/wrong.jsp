<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.ArrayList" %>

<%
    String parentPath = (String)request.getAttribute("path");
%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Error</title>
    </head>
    <body>
    <h4>Вы не можете переходить в директории не находящиеся в вашей домашней папке.</h4>
     <div>
                <%
                    out.println("<a href=\'?path=" + parentPath + "/'>" + parentPath + "</a>" + "<br>");
                %>
    </body>
</html>