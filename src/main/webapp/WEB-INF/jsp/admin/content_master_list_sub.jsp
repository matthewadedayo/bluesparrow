<%-- 
    Document   : content_master_list
    Created on : Nov 29, 2018, Nov 29, 2018 9:17:50 PM
    Author     : OLAWALE
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    public String getStatus(Object statuInt)
    {
//        return Integer.parseInt(statuInt.toString())==1?"ok":"del";
        if(Integer.parseInt(statuInt.toString())==1)
        {
//            return "ok";
            return "<button type=\"button\" class=\"btn-status btn-warning\">Pending</button>";
        }
        else if(Integer.parseInt(statuInt.toString())==2)
        {
//            return "del";
            return "<button type=\"button\" class=\"btn-status btn-danger\">Disapproved</button>";
        }
        else if(Integer.parseInt(statuInt.toString())==3)
        {
//            return "enable";
            return "<button type=\"button\" class=\"btn-status btn-success\">Approved</button>";
        }
        return null;
    }
//    
//    public String getEditUrl(Object statuInt, Object id)
//    {
//        if(Integer.parseInt(statuInt.toString())==1)
//        {
//            
//            return "edit_content_master_details.htm?id="+id;
////            return "ok";
//        }
//        else if(Integer.parseInt(statuInt.toString())==2)
//        {
//            return "#";
//        }
//        else if(Integer.parseInt(statuInt.toString())==3)
//        {
//            return "#";
//        }
//        return null;
//    }
//    
//    public String getEditString(Object statuInt, Object id)
//    {
//        if(Integer.parseInt(statuInt.toString())==1)
//        {
//            
//            return "Edit";
////            return "ok";
//        }
//        else if(Integer.parseInt(statuInt.toString())==2)
//        {
//            return "Not Editable";
//        }
//        else if(Integer.parseInt(statuInt.toString())==3)
//        {
//            return "Not Editable";
//        }
//        return null;
//    }
//    
    public String getEditUrl2(Object statuInt, Object id)
    {
        if(Integer.parseInt(statuInt.toString())==1)
        {
            
            return "<a style=\"color: black\" href=\"edit_content_master_details.htm?id="+id + "\"  class=\"ico edit edit_user\" title=\""+id +"\">Edit</a>";
            
//            return "edit_content_master_details.htm?id="+id;
//            return "ok";
        }
        else if(Integer.parseInt(statuInt.toString())==2)
        {
            return "<a style=\"color: black\" href=\"edit_content_master_details.htm?id="+id + "\"  class=\"ico edit edit_user\" title=\""+id +"\">Edit</a><br><a href=\"#\" class=\"view_rmk\" style=\"color: RED\">Remarks</a>";
//            return "Not Available<br><a href=\"#\" class=\"view_rmk\" style=\"color: RED\">Remarks</a>";
        }
        else if(Integer.parseInt(statuInt.toString())==3)
        {
            return "Not Available<br><a href=\"#\" class=\"view_rmk\" style=\"color: RED\">Remarks</a>";
        }
        return null;
    }
//    
//    public String getAddAction(Object statuInt, Object id, Object typeObj)
//    {
//        String type = typeObj.toString();
//        if(Integer.parseInt(statuInt.toString())==1)
//        {
////            return "<a style=\"color: black\" href=\"mc_add_content_file.htm?id="+id + "\" class=\"ico add\" title=\""+id+"\">Add</a>";
//            return "<a style=\"color: black\" href=\"mc_add_content_file.htm?id="+id + "&type="+type+"\" class=\"ico add\" title=\""+id+"\">Add</a>";
//        }
//        else if(Integer.parseInt(statuInt.toString())==2)
//        {
//            return "Not Available";
//        }
//        else if(Integer.parseInt(statuInt.toString())==3)
//        {
////            return "<a style=\"color: black\" href=\"mc_add_content_file.htm?id="+id + "\" class=\"ico add\" title=\""+id+"\">Add</a>";
//            return "<a style=\"color: black\" href=\"mc_add_content_file.htm?id="+id + "&type="+type+"\" class=\"ico add\" title=\""+id+"\">Add</a>";
//            
//        }
//        return null;
//    }
//    
//    public String getAddMetadataAction(Object statuInt, Object id)
//    {
//        if(Integer.parseInt(statuInt.toString())==1)
//        {
//            return "<a style=\"color: black\" href=\"mc_add_md_content.htm?id="+id + "\" class=\"ico add\" title=\""+id+"\">Add</a>";
////            return "Not Available";
////            return "<a style=\"color: black\" href=\"mc_add_content_file.htm?id="+id + " class=\"ico add\" title=\""+id+"\">Add</a>";
//            
////            return "<a style=\"color: black\" href=\"edit_content_master_details.htm?id="+id + "\"  class=\"ico edit edit_user\" title=\""+id +"\">Edit</a>";
//            
////            return "edit_content_master_details.htm?id="+id;
////            return "ok";
//        }
//        else if(Integer.parseInt(statuInt.toString())==2)
//        {
//            return "Not Available";
//        }
//        else if(Integer.parseInt(statuInt.toString())==3)
//        {
//            return "<a style=\"color: black\" href=\"mc_add_md_content.htm?id="+id + "\" class=\"ico add\" title=\""+id+"\">Add</a>";
//            
//        }
//        return null;
//    }
//    
//    public String reduceDesc(String desc)
//    {
//        return desc.length()<=20 ? desc : desc.substring(0, 20)+"...";
//    }
//    
%>
<% 
    Object userObject[] = (Object[])session.getAttribute("user_object");
    if(userObject==null)
    {
        RequestDispatcher rd = request.getRequestDispatcher("../login.jsp");
        rd.forward(request, response);
    }
    else
    {
//    ArrayList<Object[]> menuMasterObjectList = (ArrayList<Object[]>)session.getAttribute("menuMasterObjectList");
//        HashMap<String, ArrayList<Object[]>> menuChildObjectMap = (HashMap<String, ArrayList<Object[]>>) session.getAttribute("menuChildObjectMap");
//    ArrayList<Object[]> mappingbjects = (ArrayList<Object[]>) request.getAttribute("mappingbjects");
//    HashMap<String, ArrayList<Object[]>> menuChildObjectMap = (HashMap<String, ArrayList<Object[]>>) request.getAttribute("menuChildObjectMap");
//    ArrayList<Object[]> menuMasterObjectList = (ArrayList<Object[]>) request.getAttribute("menuMasterObjectList");
//      ArrayList<Object[]> userMasterObjectList =  (ArrayList<Object[]>)session.getAttribute("userMasterObjectList");
//      ArrayList<Object[]> userMasterObjectList = (ArrayList<Object[]>) request.getAttribute("userMasterObjectList");
      ArrayList<Object[]> allContentMasterObjectList = (ArrayList<Object[]>) request.getAttribute("allContentMasterObjectList");
      ArrayList<Object[]> allContentTypeMasterObjectList = (ArrayList<Object[]>) request.getAttribute("allContentTypeMasterObjectList");
//      Object userId = (Object)request.getAttribute("user_id");
      System.out.println("Total: " + allContentMasterObjectList.size());
      
%>
<table>
<!--        <thead>
                <tr>
                        <th title="Field #1" data-field="ID">Record ID</th>
                        <th title="Field #2" data-field="content_type">Content Type</th>
                        <th title="Field #3" data-field="content_cat">Content Category</th>
                        <th title="Field #4" data-field="content_sub_cat">Content Sub Category</th>
                        <th title="Field #5" data-field="title">Title</th>
                        <th title="Field #6" data-field="desc">Description</th>
                        <th title="Field #7" data-field="user_rating">User Ratings</th>
                        <th title="Field #8" data-field="user_rated">User Rated</th>
                        <th title="Field #9" data-field="storage_type">Storage Type</th>
                        <th title="Field #10" data-field="thumbnail_small">Thumbnail</th>
											<th title="Field #11" data-field="thumbnail_mid">Thumbnail Mid</th>
                        <th title="Field #12" data-field="thumbnail_large">Thumbnail Large</th>
                        <th title="Field #13" data-field="status">Status</th>
                        <th title="Field #14" data-field="action">Action</th>								
                </tr>
        </thead>-->
        <tbody>
            <%
                for(int count=0; count<allContentMasterObjectList.size(); ++count)
                {

                %>
                <tr>
                        <td><%= allContentMasterObjectList.get(count)[0]%></td>
                        <td><%= allContentMasterObjectList.get(count)[1]%></td>
                        <td><%= allContentMasterObjectList.get(count)[2]%></td>
                        <td><%= allContentMasterObjectList.get(count)[3]%></td>
                        <td><%= allContentMasterObjectList.get(count)[4]%></td>
                        <td><%= allContentMasterObjectList.get(count)[5]%></td>
                        <td><%= allContentMasterObjectList.get(count)[6]%></td>
                        <td><%= allContentMasterObjectList.get(count)[7]%></td>
                        <td><%= allContentMasterObjectList.get(count)[8]%></td>
                        <td>
                            <button data-toggle="modal" data-target="#m_modal_content_file" type="button" title="<%= allContentMasterObjectList.get(count)[9]%>" class="thumnnail btn btn-primary btn-sm">Small</button>
                            <button data-toggle="modal" data-target="#m_modal_content_file" type="button" title="<%= allContentMasterObjectList.get(count)[10]%>" class="thumnnail btn btn-primary btn-sm">Medium</button>
                            <button data-toggle="modal" data-target="#m_modal_content_file" type="button" title="<%= allContentMasterObjectList.get(count)[11]%>" class="thumnnail btn btn-primary btn-sm">Large</button>

                        </td>
<!--											<td>/content/20180515/Usagi_Yojimbo_400x400.jpg</td>
                        <td>/content/20180515/Usagi_Yojimbo_600x600.jpg</td>-->
<!--											<td><button type="button" class="btn-status btn-success">Published</button></td>-->
                        <td><%= getStatus(allContentMasterObjectList.get(count)[12])%></td>

                        <!--<td><a href="#" class="ico edit"></a>Edit</td>-->
                        <td><%= getEditUrl2(allContentMasterObjectList.get(count)[12], allContentMasterObjectList.get(count)[0]) %><input type="hidden" name="remarks" class="hd_rmk" value="<%= allContentMasterObjectList.get(count)[12]%>"></td>
                </tr>
                <%
                    }
                %>


        </tbody>
</table>
                                                      
                                                                                <%
    }
    %>