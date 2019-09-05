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
        session.setAttribute("user_object", userObject);

//        model.addAttribute("userMasterObjectList", userMasterObjectList);
//                model.addAttribute("menuMasterObjectList", menuMasterObjectList);
//                model.addAttribute("menuChildObjectMap", menuChildObjectMap);

//        session.setAttribute("userMasterObjectList", request.getAttribute("userMasterObjectList"));
        session.setAttribute("menuMasterObjectList", request.getAttribute("menuMasterObjectList"));
        session.setAttribute("menuChildObjectMap", request.getAttribute("menuChildObjectMap"));


        RequestDispatcher rd = request.getRequestDispatcher("admin/home.jsp");
        rd.forward(request, response);
    }
    else
    {
        response.sendRedirect("index.htm");
    }

%>