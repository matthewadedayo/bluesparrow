<%-- 
    Document   : in
    Created on : Mar 24, 2018, Mar 24, 2018 9:06:19 AM
    Author     : OLAWALE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    session.setAttribute("user_object", null);
    response.sendRedirect("index.htm");
//    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
//    rd.forward(request, response);

%>