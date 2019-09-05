<%-- 
    Document   : in
    Created on : Mar 24, 2018, Mar 24, 2018 9:06:19 AM
    Author     : OLAWALE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    System.out.println("OK");
    Object userObject = (Object)request.getAttribute("user_object");
    if(userObject!=null)
    {
//        session.setAttribute("user_object", userObject);
//
//        session.setAttribute("allManuMappingList", request.getAttribute("allManuMappingList"));
//        session.setAttribute("menuChildObjectMap", request.getAttribute("menuChildObjectMap"));
//
//
//        session.setAttribute("allMenuHistoryList", request.getAttribute("allMenuHistoryList"));

//        RequestDispatcher rd = request.getRequestDispatcher("admin/home.jsp");
        RequestDispatcher rd = request.getRequestDispatcher("admin/index.jsp");
        rd.forward(request, response);
    }
    else
    {
        response.sendRedirect("index.htm");
    }

%>