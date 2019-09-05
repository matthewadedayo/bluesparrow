<%-- 
    Document   : content_master
    Created on : Mar 16, 2018, Mar 16, 2018 3:48:29 PM
    Author     : OLAWALE
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    public String getStatus(Object statuInt)
    {
//        return Integer.parseInt(statuInt.toString())==1?"ok":"del";
        if(Integer.parseInt(statuInt.toString())==1)
        {
            return "ok";
        }
        else if(Integer.parseInt(statuInt.toString())==2)
        {
            return "del";
        }
        else if(Integer.parseInt(statuInt.toString())==3)
        {
            return "enable";
        }
        return null;
    }
    
    public String active(Object statuInt)
    {
         if(Integer.parseInt(statuInt.toString())!=1)
        {
            return "disabled style=\"color: grey\"";
        }
        return "";
    }
    
//    public String remarkDisplay(Object statuInt)
//    {
//         if(Integer.parseInt(statuInt.toString())==1)
//        {
////            return "style=\"visibility: hidden\"";
//            return "disabled=\"disabled\"";
//        }
//        return "";
//    }
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
    ArrayList<Object[]> menuMasterObjectList = (ArrayList<Object[]>)session.getAttribute("menuMasterObjectList");
        HashMap<String, ArrayList<Object[]>> menuChildObjectMap = (HashMap<String, ArrayList<Object[]>>) session.getAttribute("menuChildObjectMap");
//    ArrayList<Object[]> mappingbjects = (ArrayList<Object[]>) request.getAttribute("mappingbjects");
//    HashMap<String, ArrayList<Object[]>> menuChildObjectMap = (HashMap<String, ArrayList<Object[]>>) request.getAttribute("menuChildObjectMap");
//    ArrayList<Object[]> menuMasterObjectList = (ArrayList<Object[]>) request.getAttribute("menuMasterObjectList");
      ArrayList<Object[]> userMasterObjectList =  (ArrayList<Object[]>)session.getAttribute("userMasterObjectList");
//      ArrayList<Object[]> userMasterObjectList = (ArrayList<Object[]>) request.getAttribute("userMasterObjectList");
      ArrayList<Object[]> allContentMasterObjectList = (ArrayList<Object[]>) request.getAttribute("allContentMasterObjectList");
//      Object userId = (Object)request.getAttribute("user_id");
      
      
%>
<!DOCTYPE html>
<html lang="en" >
<tr title="<%= allContentMasterObjectList.get(count)[5]%>">
                                                                                    <td><center><%= allContentMasterObjectList.get(count)[0]%></center></td>
                                                                                        <td><center><%= allContentMasterObjectList.get(count)[14]%></td>
											<td><center><%= allContentMasterObjectList.get(count)[1]%></td>
											<td><%= allContentMasterObjectList.get(count)[2]%></td>
											<td><%= allContentMasterObjectList.get(count)[3]%></td>
											<td><%= allContentMasterObjectList.get(count)[4]%></td>
											<td><%= allContentMasterObjectList.get(count)[5]%></td>
											<td><center><%= allContentMasterObjectList.get(count)[6]%></center></td>
											<td><center><%= allContentMasterObjectList.get(count)[7]%></center></td>
											<td><center><%= allContentMasterObjectList.get(count)[8]%></center></td>
											<td>
                                                                                            <center>
                                                                                            <a href="#"><img class="thumnnail" data-toggle="modal" data-target="#m_modal_content_file" src="images/tb_large.png"  title="Thumbnail Large"><input type="hidden" value="<%= allContentMasterObjectList.get(count)[11]%>" /></a>
											<a href="#"><img class="thumnnail" data-toggle="modal" data-target="#m_modal_content_file" src="images/tb_medium.png"  title="Thumbnail Medium"><input type="hidden" value="<%= allContentMasterObjectList.get(count)[10]%>" /></a>
											<a href="#"><img class="thumnnail" data-toggle="modal" data-target="#m_modal_content_file" src="images/tb_small.png"  title="Thumbnail Small"><input type="hidden" value="<%= allContentMasterObjectList.get(count)[9]%>" /></a>

                                                                                            </center>
                                                                                        </td>

                                                                                        

                                                                                        <td>
                                                                                            
                                                                                            <center><%= getStatus2(allContentMasterObjectList.get(count)[0], allContentMasterObjectList.get(count)[12])%></center>
                                                                                            
                                                                                            
                                                                                            
                                                                                        </td>
                                                                                        
											
                                                                                        
                                                                                        
										</tr>
<head>
  <meta charset="UTF-8">
  <title>Buffermedia</title>
  
  
  
      <link rel="stylesheet" href="css/style.css">
	  <link rel='stylesheet prefetch' href='http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css'>
          
          
<!--<link href="external/jquery/jquery-ui.css" rel="stylesheet">-->
  
</head>

<body>

  <head>
	<!-- Basic Page Needs-->
	<meta charset="utf-8">
	<title>Buffermedia</title>
		
	
	<!--Mobile Specific Metas-->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	
	
	<!--[if lt IE 9]>
		<script src="https://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

	<!-- Google Fonts-->
	<link href='https://fonts.googleapis.com/css?family=Roboto&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
        
        

</head>
<body>
	<!--Header-->
		<header>
			<!--İnfo Bar-->
			<div class="info">
				<div class="informationBar">
					<div class="logout">
						<!--<a href="log_out.htm">LOGOUT</a>-->
                                                <!--<a href="#">Hi <%=userObject[1]%></a>-->
					</div>
				</div>
			</div>
				
			<!--Navigation-->
			<div class="navigation">
				<div class="navContent">
					<div class="logo">
						<a href="home.htm" title="Home">Home</a>
					</div>
					<div class="topnav">
						  <nav>
							<ul id="main">
								<li><a href="home.htm">Home</a></li>
                                                                <%
                                                                for(int count=0; count<menuMasterObjectList.size(); ++count)
                                                                {
                                                                        
                                                                %>
								<li>
								  <a href="#" class="wsubmenu mm_url"><%=menuMasterObjectList.get(count)[1]%> </a>
										<div class="submenu">
										  <ul>
                                                                        <%

//                                                                        for(int innerCounter=0; innerCounter<menuChildObjectMap.get(String.valueOf(count+1)).size(); ++innerCounter)
                                                                        for(int innerCounter=0; innerCounter<menuChildObjectMap.get(String.valueOf(menuMasterObjectList.get(count)[0])).size(); ++innerCounter)
                                                                        {
                                                                        
                                                                        %>
                                                                        <li> <a href="<%=menuChildObjectMap.get(String.valueOf(menuMasterObjectList.get(count)[0])).get(innerCounter)[2]%>" class="mc_url"><%=menuChildObjectMap.get(String.valueOf(menuMasterObjectList.get(count)[0])).get(innerCounter)[1]%></a> </li>
                                                                        
                                                                        <%
                                                                        }

                                                                        %>
<!--											<li> <a href="#">Second item </a> </li>
											<li> <a href="#">Third item </a> </li>
											<li> <a href="#">Fourth item </a> </li>
											<li> <a href="#">Fifth item </a> </li>-->
										  </ul>
										</div>
								</li>
                                                                <%
                                                                }
                                    
                                                                %>
                                                                <li>
                                                                    <a href="#">Hi <%=userObject[1]%>, ▼</a>
                                                                    <div class="submenu">
										  <ul>
                                                                    <a href="log_out.htm" class="wsubmenu mm_url">Logout</a>
                                                                                  </ul>
                                                                    </div>
                                                                </li>
<!--								<li>
								  <a href="#" class="wsubmenu">Submenu </a>
										<div class="submenu">
										  <ul>
											<li> <a href="#">First item </a> </li>
											<li> <a href="#">Second item </a> </li>
											<li> <a href="#">Third item </a> </li>
											<li> <a href="#">Fourth item </a> </li>
											<li> <a href="#">Fifth item </a> </li>
										  </ul>
										</div>
								</li><li>
								  <a href="#" class="wsubmenu">Submenu </a>
										<div class="submenu">
										  <ul>
											<li> <a href="#">First item </a> </li>
											<li> <a href="#">Second item </a> </li>
											<li> <a href="#">Third item </a> </li>
											<li> <a href="#">Fourth item </a> </li>
											<li> <a href="#">Fifth item </a> </li>
										  </ul>
										</div>
								</li><li>
								  <a href="#" class="wsubmenu">Submenu </a>
										<div class="submenu">
										  <ul>
											<li> <a href="#">First item </a> </li>
											<li> <a href="#">Second item </a> </li>
											<li> <a href="#">Third item </a> </li>
											<li> <a href="#">Fourth item </a> </li>
											<li> <a href="#">Fifth item </a> </li>
										  </ul>
										</div>
								</li><li>
								  <a href="#" class="wsubmenu">Submenu </a>
										<div class="submenu">
										  <ul>
											<li> <a href="#">First item </a> </li>
											<li> <a href="#">Second item </a> </li>
											<li> <a href="#">Third item </a> </li>
											<li> <a href="#">Fourth item </a> </li>
											<li> <a href="#">Fifth item </a> </li>
										  </ul>
										</div>
								</li>
								<li><a href="#"></a></li>-->
							</ul>
						  </nav>
					</div>
					
				</div>
			</div>
			</div>

							
		</header>
			<div class="form">
            
      <div>
        <div id="adduser">   
          <h1>Search Content</h1>
          <form action="search_content.htm" method="POST">
          <div class="field-wrap">
              <select type="select" id="search_by" name="option" required autocomplete="off">
			<option disabled selected value="">Search By*</option>
                        <option value="title">Title</option>
			<option value="desc">Description</option>
                        <option value="type">Type</option>
                        <option value="status">Status</option>
                        
		   </select>
		   </div>
              <div class="field-wrap option-div" id="title-div" style="display: none">
              <input type="text" class="option-val" maxlength="20" id="title" name="title" required autocomplete="off" placeholder="Title*" />
          </div>
          <div class="field-wrap option-div"  id="desc-div" style="display: none">
                      <!--<input type="text" name="desc" required autocomplete="off" placeholder="Description*" />-->
                      <textarea  class="option-val" maxlength="512" id="desc" name="desc" required placeholder="Description*"></textarea>
          </div>
          <div class="field-wrap option-div" id="type-div"  style="display: none">
              <select type="select"  class="option-val" id="type" name="type" required autocomplete="off">
			<option disabled selected value="">Search By*</option>
                        <option value="GAME">Game</option>
			<option value="VIDEO">Video</option>
                        <option value="MUSIC">Music</option>
                        <option value="EBOOKS">eBooks</option>
                        <option value="HTML-5">Html-5</option>
			<option value="APPLICATION">Application</option>
                        
		   </select>
              
		   </div>
              <input type="hidden" id="typeN" name="type" value="" />
          <div class="field-wrap option-div" id="status-div"  style="display: none">
              <select type="select" class="option-val" id="status" name="status" required autocomplete="off">
			<option disabled selected value="">Search By*</option>
                        <option value="1">Just Uploaded</option>
                        <option value="2">Rejected</option>
			<option value="3">Approved</option>
                        
                        
                        
		   </select>
              
		   </div>
              <input type="hidden" id="statusN" name="status" value="" />
              
<!--              <div class="field-wrap" >
                      <input type="text" name="desc" required autocomplete="off" placeholder="Description*" />
                      <textarea  maxlength="512" id="remarks" name="remarks" required placeholder="Remark*"></textarea>
          </div>-->
		        
     <button type="submit" class="button button-block"/>Find</button>
          
          </form>

        </div>
        
        
        </div>
        
      </div<!-- /form -->
      
</div> 
	<!--Container-->
		<div class="container">
			<!--Content-->
			<!--<div>-->
			<h3>List Content</h3>
					
				<table class="table">
				  
				  <tr>
					<th>S/N</th>
					<th>Title</th>
					<th>Desc</th>
					<th>Type</th>
					<th>User Ratings</th>
					<th>User Rated</th>
					<th>Thumbnail Small</th>
					<th>Thumbnail Mid</th>
					<th>Thumbnail Large</th>
					<th>Storage Type </th>
                                        
					<th>Content Category</th>
                                        <th><center>Status</center></th>
                                        <th><canter>TakeaAction</canter></th>
                                        <th><canter>Remarks</canter></th>
                                        <!--<th>Add</th>-->
				  </tr>
				   <%
                                    for(int count=0; count<allContentMasterObjectList.size(); ++count)
                                    {

                                    %>
				  <tr>
					<td><%= allContentMasterObjectList.get(count)[0]%></td>
					<td><%= allContentMasterObjectList.get(count)[1]%></td>
					<td><%= allContentMasterObjectList.get(count)[2]%></td>
					<td><%= allContentMasterObjectList.get(count)[3]%></td>
					<td style="text-align: right"><%= allContentMasterObjectList.get(count)[4]%></td>
					<td style="text-align: right"><%= allContentMasterObjectList.get(count)[5]%></td>
                                        <td><a href="#" class="thumnnail"><%= allContentMasterObjectList.get(count)[6]%></a></td>
					<td><a href="#" class="thumnnail"><%= allContentMasterObjectList.get(count)[7]%></a></td>
                                        <td><a href="#" class="thumnnail"><%= allContentMasterObjectList.get(count)[8]%></a></td>
					<td><%= allContentMasterObjectList.get(count)[9]%></td>
                                        
					<td style="text-align: right"><%= allContentMasterObjectList.get(count)[11]%></td>
                                        <td><center><a href="#" class="ico <%=getStatus(allContentMasterObjectList.get(count)[10])%> edit_content_master_status" title="<%=allContentMasterObjectList.get(count)[0]%>"></a><input type="hidden" value="<%=allContentMasterObjectList.get(count)[10]%>" class="status_hidden" /></center></td>
                        <td><center><select id="<%= allContentMasterObjectList.get(count)[0]%>" <%= active(allContentMasterObjectList.get(count)[10]) %> class="review_option"><option value="none" selected disabled>Select One</option><option value="2">Rejected</option><option value="3">Approved</option></select><input type="hidden" value="<%=allContentMasterObjectList.get(count)[0]%>" class="status_hidden" /></center></td>
                        <td><a class="view_rmk" href="#"/>Remarks</a><input type="hidden" name="remarks" class="hd_rmk" value="<%= allContentMasterObjectList.get(count)[12]%>"></td>
                                        
                                        <!--<td><center><a style="color: black" href="edit_content_master_details.htm?id=<%=allContentMasterObjectList.get(count)[0]%>" class="ico edit edit_user" title="<%=allContentMasterObjectList.get(count)[0]%>">Edit</a></center> </td>-->
                        
                        <!--<td><center><a style="color: black" href="mc_add_content_file.htm?id=<%=allContentMasterObjectList.get(count)[0]%>" class="ico add" title="<%=allContentMasterObjectList.get(count)[0]%>">Add</a></center> </td>-->
                                        
					</tr>
				  <%
                                    }
                                %>

				  
				</table>
							
			</div>
			
                <!--<h3> Activate</h3>-->
<!--<button id="myBtn" class="button btn-info">Click Here</button>-->
<!--<input type="hidden" id="myBtn" class="button btn-info">-->
<!-- The Modal -->
<div id="myModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
   
    <div class="modal-header">
        <span id="close" class="close">X</span>
    <h4><span> Remarks</span></h4>
   </div>
  <div class="modal-body">
	<div>
	<!--<form action="/" method="post">-->
          <br>
          <div>
            <div class="field-wrap">
			<p>Please enter your remarks</p>
                        
                        <textarea id="rmk_area" rows="5" cols="50"></textarea>
            </div>
          <input type="hidden" id="hide_rmk" value="" > 
          </div>
<!--          <div>
              <img id="thumb" src="" height="200" width="200" />
              <p id="pp"></p>
          </div>-->
          
          <!--<button class="button button-block"/><i class="material-icons">power_settings_new</i>Activate</button>-->
        <button type="button" id="close_btn" class="button"/>Close</button>
        <button type="button" id="proceed_btn" class="button"/>Proceed</button>
                        
		  <div class="modal-footer">

		  </div>
      		  <br>
	<!--</form>-->
	</div>

</div>



  
  </div>
  
  </div>



<div id="myModal2" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
   
    <div class="modal-header">
        <span id="close2" class="close">X</span>
    <h4><span>Thumbnail</span></h4>
   </div>
  <div class="modal-body">
	<div>
	<!--<form action="/" method="post">-->
          <br>
          
          <div>
              <img id="thumb" src="" height="200" width="200" />
              <!--<p id="pp"></p>-->
          </div>
          
          <!--<button class="button button-block"/><i class="material-icons">power_settings_new</i>Activate</button>-->
        <button type="button" id="close_btn2" class="button"/>Close</button>
        <!--<button type="button" id="proceed_btn" class="button"/>Proceed</button>-->
                        
		  <div class="modal-footer">

		  </div>
      		  <br>
	<!--</form>-->
	</div>

</div>



  
  </div>
  
  </div>

		</div>

<!--Footer-->
	<footer>
		<div class="footerContainer">
			<div class="statusColor">
				<p class="statusDesc"> Buffermedia © 2018 All Rights Reserved</p>
			</div>
		</div>
	</footer>

<!--<h2 class="demoHeaders">Dialog</h2>
<p>
	<button id="dialog-link" class="ui-button ui-corner-all ui-widget">
		<span class="ui-icon ui-icon-newwin"></span>Open Dialog
	</button>
</p>-->

        <!-- ui-dialog -->
<!--<div id="dialog" title="Dialog Title">
	<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
</div>-->




</body>
</html>
  
<script src="js/jquery-1.7.2.min.js"></script>

<!--<script src="external/jquery/jquery-ui.js"></script>
<script src="external/jquery/jquery.js"></script>-->

<script  src="js/index.js"></script>



<!--<script src="external/jquery/jquery.js"></script>-->

<script src="js/admin.js"></script>




</body>

</html>
<%
    }
    %>