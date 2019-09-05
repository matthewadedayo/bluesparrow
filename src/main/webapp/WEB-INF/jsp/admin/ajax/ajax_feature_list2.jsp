<%-- 
    Document   : sub_add
    Created on : Apr 20, 2019, Apr 20, 2019 8:13:17 AM
    Author     : OLAWALE
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!

    public boolean alreadyChecked(String feature, ArrayList<Object[]> allSubscriptionFeatureMappingObjectList)
    {
        boolean checked = false;
    
        for(int count=0; count<allSubscriptionFeatureMappingObjectList.size(); ++count)
        {
            if(allSubscriptionFeatureMappingObjectList.get(count)[2].toString().equalsIgnoreCase(feature))
            {
                checked=true;
            }
        }
        return checked;
    }

%>
<% 
       
    
    ArrayList<Object[]> allContentPortalMasterObjectList = (ArrayList<Object[]>) request.getAttribute("allContentPortalMasterObjectList");
    ArrayList<Object[]> allFeatureMasterObject = (ArrayList<Object[]>) request.getAttribute("allFeatureMasterObject");
    
    
    ArrayList<Object[]> allSubscriptionFeatureMappingObjectList = (ArrayList<Object[]>) request.getAttribute("allSubscriptionFeatureMappingObjectList");
    
    System.out.println("isnul: " + allFeatureMasterObject);
  
      
%>

        <div id="replace">
    <label Audebook>
        <%
            if(allFeatureMasterObject.size()!=0)
            {
                
            
    %>
        <%= allFeatureMasterObject.get(0)[2] %>
        <%
            }
            else
            {
        %>
        No Feature
        <%
            }
            %>
                  </label>
            <div class="m-checkbox-inline">
                <%
            for(int count=0; count<allFeatureMasterObject.size(); ++count)
            {

            %>
                    <label class="m-checkbox">
                        <input type="checkbox" name="feature_id" value="<%= allFeatureMasterObject.get(count)[0] %>" <% if(alreadyChecked(allFeatureMasterObject.get(count)[0].toString(), allSubscriptionFeatureMappingObjectList)){ %> checked <%}%> >
                            <%= allFeatureMasterObject.get(count)[3] %>
                            <span></span>
                    </label>
                    <%

            }
            %>

            </div>
    </div>
