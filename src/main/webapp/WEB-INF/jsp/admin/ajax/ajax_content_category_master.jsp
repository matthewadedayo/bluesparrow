<%-- 
    Document   : content_category_master
    Created on : Oct 31, 2018, Oct 31, 2018 9:55:58 PM
    Author     : OLAWALE
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    
//    ArrayList<Object[]> mappingbjects = (ArrayList<Object[]>) request.getAttribute("mappingbjects");
//    ArrayList<Object[]> menuMasterObjectList = (ArrayList<Object[]>)session.getAttribute("menuMasterObjectList");
//        HashMap<String, ArrayList<Object[]>> menuChildObjectMap = (HashMap<String, ArrayList<Object[]>>) session.getAttribute("menuChildObjectMap");
//      ArrayList<Object[]> userMasterObjectList = (ArrayList<Object[]>) request.getAttribute("userMasterObjectList");
      
//      Object[] user = (Object[]) request.getAttribute("user");
//      Object userId = (Object)request.getAttribute("user_id");
      
      
//      System.out.println("user: " + user[0]);
        
        ArrayList<Object[]> allContentCategoryMasterObjectList = (ArrayList<Object[]>) request.getAttribute("allContentCategoryMasterObjectList");
        
//        ArrayList<Object[]> allContentCategoryMasterObjectList = (ArrayList<Object[]>) request.getAttribute("allContentCategoryMasterObjectList");
        
        
//        Object[] contentCategoryMasterObject = (Object[]) request.getAttribute("contentCategoryMasterObject");
        
%>
<div id="update">

        
        <ul id="cont_cat">
        
        <%
        for(int count=0; count<allContentCategoryMasterObjectList.size(); ++count)
        {

        %>
        <li title="<%=allContentCategoryMasterObjectList.get(count)[0]%>"><%=allContentCategoryMasterObjectList.get(count)[1]%></li>
        <%
        }

        %>

</ul>
        </div>
        
        