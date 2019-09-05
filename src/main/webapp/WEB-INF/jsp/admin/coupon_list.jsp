<%-- 
    Document   : coupon_list
    Created on : Mar 27, 2019, Mar 27, 2019 4:19:04 PM
    Author     : OLAWALE
--%>

<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!

        public String getStatus(Object statuInt, Object id)
        {
            if(Integer.parseInt(statuInt.toString())==0)
            {
                return "<a href=\"#\" title=\""+id+"\" class=\"btn-status btn-success edit_banner_master_status\">Draft</a>";
            }
            else if(Integer.parseInt(statuInt.toString())==1)
            {
                return "<a href=\"#\" title=\""+id+"\" class=\"btn-status btn-info edit_banner_master_status\">Publish</a>";
            }
            else
            {
                return "<a href=\"#\" title=\""+id+"\" class=\"btn-status btn-danger edit_banner_master_status\">Unpublish</a>";
            }
        }

//    public String getStatus(Object statuInt)
//    {
////        return Integer.parseInt(statuInt.toString())==1?"ok":"del";
//        if(Integer.parseInt(statuInt.toString())==1)
//        {
//            return "ok";
//        }
//        else if(Integer.parseInt(statuInt.toString())==2)
//        {
//            return "del";
//        }
//        else if(Integer.parseInt(statuInt.toString())==3)
//        {
//            return "enable";
//        }
//        return null;
//    }
    
    public String getEditUrl(Object statuInt, Object id)
    {
        if(Integer.parseInt(statuInt.toString())==1)
        {
            
            return "edit_content_master_details.htm?id="+id;
//            return "ok";
        }
        else if(Integer.parseInt(statuInt.toString())==2)
        {
            return "#";
        }
        else if(Integer.parseInt(statuInt.toString())==3)
        {
            return "#";
        }
        return null;
    }
    
    public String getEditString(Object statuInt, Object id)
    {
        if(Integer.parseInt(statuInt.toString())==1)
        {
            
            return "Edit";
//            return "ok";
        }
        else if(Integer.parseInt(statuInt.toString())==2)
        {
            return "Not Editable";
        }
        else if(Integer.parseInt(statuInt.toString())==3)
        {
            return "Not Editable";
        }
        return null;
    }
    
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
    
    public String getAddAction(Object statuInt, Object id, Object typeObj)
    {
        String type = typeObj.toString();
        if(Integer.parseInt(statuInt.toString())==1)
        {
//            return "<a style=\"color: black\" href=\"mc_add_content_file.htm?id="+id + "\" class=\"ico add\" title=\""+id+"\">Add</a>";
            return "<a style=\"color: black\" href=\"mc_add_content_file.htm?id="+id + "&type="+type+"\" class=\"ico add\" title=\""+id+"\">Add</a>";
        }
        else if(Integer.parseInt(statuInt.toString())==2)
        {
            return "Not Available";
        }
        else if(Integer.parseInt(statuInt.toString())==3)
        {
//            return "<a style=\"color: black\" href=\"mc_add_content_file.htm?id="+id + "\" class=\"ico add\" title=\""+id+"\">Add</a>";
            return "<a style=\"color: black\" href=\"mc_add_content_file.htm?id="+id + "&type="+type+"\" class=\"ico add\" title=\""+id+"\">Add</a>";
            
        }
        return null;
    }
    
    public String getAddMetadataAction(Object statuInt, Object id)
    {
        if(Integer.parseInt(statuInt.toString())==1)
        {
            return "<a style=\"color: black\" href=\"mc_add_md_content.htm?id="+id + "\" class=\"ico add\" title=\""+id+"\">Add</a>";
//            return "Not Available";
//            return "<a style=\"color: black\" href=\"mc_add_content_file.htm?id="+id + " class=\"ico add\" title=\""+id+"\">Add</a>";
            
//            return "<a style=\"color: black\" href=\"edit_content_master_details.htm?id="+id + "\"  class=\"ico edit edit_user\" title=\""+id +"\">Edit</a>";
            
//            return "edit_content_master_details.htm?id="+id;
//            return "ok";
        }
        else if(Integer.parseInt(statuInt.toString())==2)
        {
            return "Not Available";
        }
        else if(Integer.parseInt(statuInt.toString())==3)
        {
            return "<a style=\"color: black\" href=\"mc_add_md_content.htm?id="+id + "\" class=\"ico add\" title=\""+id+"\">Add</a>";
            
        }
        return null;
    }
    
    public String reduceDesc(String desc)
    {
        return desc.length()<=20 ? desc : desc.substring(0, 20)+"...";
    }
    
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
    ArrayList<Object[]> allManuMappingList = (ArrayList<Object[]>)session.getAttribute("allManuMappingList");
  //      HashMap<String, ArrayList<Object[]>> menuChildObjectMap = (HashMap<String, ArrayList<Object[]>>) session.getAttribute("menuChildObjectMap");
        ArrayList<Object[]> allMenuHistoryList = (ArrayList<Object[]>)session.getAttribute("allMenuHistoryList");
//    ArrayList<Object[]> mappingbjects = (ArrayList<Object[]>) request.getAttribute("mappingbjects");
//    HashMap<String, ArrayList<Object[]>> menuChildObjectMap = (HashMap<String, ArrayList<Object[]>>) request.getAttribute("menuChildObjectMap");
//    ArrayList<Object[]> menuMasterObjectList = (ArrayList<Object[]>) request.getAttribute("menuMasterObjectList");
//      ArrayList<Object[]> userMasterObjectList =  (ArrayList<Object[]>)session.getAttribute("userMasterObjectList");
        ArrayList<Object[]> allCountryCouponList = (ArrayList<Object[]>) request.getAttribute("allCountryCouponList");
        ArrayList<Object[]> couponMasterList = (ArrayList<Object[]>) request.getAttribute("couponMasterList");
//      String feedback = (String) request.getAttribute("feedback");
//      Object bannerMasterStatus = (Object) request.getAttribute("bannerMasterStatus");
//      Object contentTypeId = (Object) request.getAttribute("contentTypeId");
//      Object userId = (Object)request.getAttribute("user_id");

  String countryId = (String) request.getAttribute("countryId");
      
     Map<String, String> portal = new HashMap<String, String>();
      int next = 0;
      while(next < allCountryCouponList.size())
      {
          System.out.println("tp: " + allCountryCouponList.get(next)[1].toString());
          portal.put(allCountryCouponList.get(next)[0].toString(), allCountryCouponList.get(next)[1].toString());
          next++;
      }
      
     Map<String, String> status = new HashMap<String, String>();
      status.put("-1", "All");
      
      status.put("0", "Disable");
      status.put("1", "Enable");

      
      String contentName = countryId!=null ? portal.get(countryId) : "Nil";
      
%>
<!DOCTYPE html>

<html lang="en">

	<!-- begin::Head -->
	<head>
		<meta charset="utf-8" />
		<title>BlueSparrow | Coupon List</title>
		<meta name="description" content="BlueSparrow Dashboard">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">

		<!--begin::Web font -->
		<jsp:include page="common/css_links.html" flush="true"/>
	</head>

	<!-- end::Head -->

	<!-- begin::Body -->
	<body class="m-page--fluid m--skin- m-content--skin-light2 m-header--fixed m-header--fixed-mobile m-aside-left--enabled m-aside-left--skin-dark m-aside-left--fixed m-aside-left--offcanvas m-footer--push m-aside--offcanvas-default">

		<!-- begin:: Page -->
		<div class="m-grid m-grid--hor m-grid--root m-page">

			<!-- BEGIN: Header -->
			<jsp:include page="common/header.html"/>
			<!-- END: Header -->

			<!-- begin::Body -->
			<div class="m-grid__item m-grid__item--fluid m-grid m-grid--ver-desktop m-grid--desktop m-body">

				<!-- BEGIN: Left Aside -->
				
				<jsp:include page="common/sidebar.html"/>
				<!-- END: Left Aside -->
				<div class="m-grid__item m-grid__item--fluid m-wrapper">

					<!-- BEGIN: Subheader -->
					<div class="m-subheader ">
						<div class="d-flex align-items-center">
							<div class="mr-auto">
								<h3 class="m-subheader__title m-subheader__title--separator">Billing</h3>
								<ul class="m-subheader__breadcrumbs m-nav m-nav--inline">
									<li class="m-nav__item m-nav__item--home">
										<a href="#" class="m-nav__link m-nav__link--icon">
											<i class="m-nav__link-icon la la-home"></i>
										</a>
									</li>
									<li class="m-nav__separator">-</li>
									<li class="m-nav__item">
										<a href="#" class="m-nav__link">
											<span class="btn btn-secondary m-btn m-btn--pill m-btn--wide btn-sm"><b>Coupon List</b></span>
										</a>
									</li>
									<li class="m-nav__separator">-</li>
								</ul>
							</div>
						
							<jsp:include page="common/quick_actions.html"/>	
											
						</div>
					</div>

					<!-- END: Subheader -->
					<div class="m-content">

						<div class="m-portlet m-portlet--mobile">
							<div class="m-portlet__head">
								<div class="m-portlet__head-caption">
									<div class="m-portlet__head-title">
										<h3 class="m-portlet__head-text">
											Coupon List
										</h3>
									</div>
								</div>
								<div class="m-portlet__head-tools">
									<ul class="m-portlet__nav">
										<li class="m-portlet__nav-item">
											<div class="m-dropdown m-dropdown--inline m-dropdown--arrow m-dropdown--align-right m-dropdown--align-push" m-dropdown-toggle="hover" aria-expanded="true">
												<a href="#" class="m-portlet__nav-link btn btn-lg btn-secondary  m-btn m-btn--icon m-btn--icon-only m-btn--pill  m-dropdown__toggle">
													<i class="la la-ellipsis-h m--font-brand"></i>
												</a>
												<div class="m-dropdown__wrapper">
													<span class="m-dropdown__arrow m-dropdown__arrow--right m-dropdown__arrow--adjust"></span>
													<div class="m-dropdown__inner">
														<div class="m-dropdown__body">
															<div class="m-dropdown__content">
																<ul class="m-nav">
																	<li class="m-nav__section m-nav__section--first">
																		<span class="m-nav__section-text">Quick Actions</span>
																	</li>
																	<li class="m-nav__item">
																		<a href="" class="m-nav__link">
																			<i class="m-nav__link-icon flaticon-share"></i>
																			<span class="m-nav__link-text">Create Post</span>
																		</a>
																	</li>
																	<li class="m-nav__item">
																		<a href="" class="m-nav__link">
																			<i class="m-nav__link-icon flaticon-chat-1"></i>
																			<span class="m-nav__link-text">Send Messages</span>
																		</a>
																	</li>
																	<li class="m-nav__item">
																		<a href="" class="m-nav__link">
																			<i class="m-nav__link-icon flaticon-multimedia-2"></i>
																			<span class="m-nav__link-text">Upload File</span>
																		</a>
																	</li>
																	<li class="m-nav__section">
																		<span class="m-nav__section-text">Useful Links</span>
																	</li>
																	<li class="m-nav__item">
																		<a href="" class="m-nav__link">
																			<i class="m-nav__link-icon flaticon-info"></i>
																			<span class="m-nav__link-text">FAQ</span>
																		</a>
																	</li>
																	<li class="m-nav__item">
																		<a href="" class="m-nav__link">
																			<i class="m-nav__link-icon flaticon-lifebuoy"></i>
																			<span class="m-nav__link-text">Support</span>
																		</a>
																	</li>
																	<li class="m-nav__separator m-nav__separator--fit m--hide">
																	</li>
																	<li class="m-nav__item m--hide">
																		<a href="#" class="btn btn-outline-danger m-btn m-btn--pill m-btn--wide btn-sm">Submit</a>
																	</li>
																</ul>
															</div>
														</div>
													</div>
												</div>
											</div>
										</li>
									</ul>
								</div>
							</div>
							<div class="m-portlet__body">

								<!--begin: Search Form -->
									<div class="m-form m-form--label-align-right m--margin-top-20 m--margin-bottom-30">
									<div class="row align-items-center">
										<div class="col-xl-8 order-2 order-xl-1">
										       <!--<div>
										        <strong>All(3) | Published(3)</strong>
										      </div>-->
                                                                                       <form  id="load_by_title_frm" method="get">
											<div class="form-group m-form__group row align-items-center">
												
												<div class="col-md-4">
													<div class="m-form__group m-form__group--inline">
														<div class="m-form__label">
															<label class="m-label m-label--single">Country:</label>
														</div>
														<div class="m-form__control">
															<select class="form-control m-bootstrap-select sch_by" id="m_form_type" name="country">
                                                                                                                               
                                                                                                                                        	
                                                                                                                             <option value="0">Nigeria</option>
																<option value="1">Ghana</option>
                                                                                                                                <option value="2">Others</option>
															</select>
														</div>
													</div>
													<div class="d-md-none m--margin-bottom-10"></div>
												</div>
												<div class="col-md-4">
													<div class="m-input-icon m-input-icon--left">
														<input type="text" class="form-control m-input" placeholder="Search..." id="generalSearch">
														<span class="m-input-icon__icon m-input-icon__icon--left">
															<span><i class="la la-search"></i></span>
														</span>
													</div>
												</div>
											</div>
                                                                                           
                                                                                   </form>          
										</div>
										<div class="col-xl-4 order-1 order-xl-2 m--align-right">
											<a href="mc_add_coupon.htm" class="btn btn-primary m-btn m-btn--custom m-btn--icon m-btn--air m-btn--pill">
												<span>
													<i class="la la-user"></i>
													<span>Add Coupon</span>
												</span>
											</a>
											<div class="m-separator m-separator--dashed d-xl-none"></div>
										</div>
									</div>
                                                                          
								</div>

								<!--end: Search Form -->

								<!--begin: Datatable -->
								<div class="table-responsive">
									<table class="m-datatable"  width="100%">
									<thead>
										<tr>
											<th title="Field #1"><center>Title</center></th>
											<th title="Field #2"><center>Code</center></th>
											<th title="Field #3"><center>Discount Type</center></th>
											<th title="Field #4"><center>Description</center></th>
											<th title="Field #5"><center>Country</center></th>
											<th title="Field #6"><center>Amount</center></th>
											<th title="Field #7"><center>Minimum Purchase Amount</center></th>
											<th title="Field #8"><center>Maximum Discount Amount</center></th>
											<th title="Field #9"><center>Start Date</center></th>
											<th title="Field #10"><center>Expiry Date</center></th>
                                                                                        <th title="Field #11" data-field="Action">Action</th>
											
										</tr>
									</thead>
									<tbody>
                                                                            
                                                                            
                                                                            <%
                                                                                for(int count=0; count<couponMasterList.size(); ++count)
                                                                                {

                                                                                %>
										<tr>
											<td style="text-align: center"><center><%= couponMasterList.get(count)[1]%></center></td>
											<td><center><%= couponMasterList.get(count)[2]%></center></td>
											<td><center><%= couponMasterList.get(count)[3]%></center></td>
                                                                                    <td><center><%= couponMasterList.get(count)[4]%></center></td>
                                                                                     <td ><center><%= couponMasterList.get(count)[5]%></center></td>                            
                                                                                       <td ><center><%= couponMasterList.get(count)[6]%><%= couponMasterList.get(count)[7]%></center></td>
                                                                                       <td ><center><%= couponMasterList.get(count)[8]%></center></td>
                                                                                       <td ><center><%= couponMasterList.get(count)[9]%></center></td>
                                                                                       <td ><center><%= couponMasterList.get(count)[10]%></center></td>
                                                                                       <td ><center><%= couponMasterList.get(count)[11]%></center></td>
                                                                                 <td><a href="coupon_map.htm?id=<%=couponMasterList.get(count)[0]%>" class="ico edit">Map</a> | <a href="#">Edit</a></td>
										</tr>
                                                                                <%
                                                                                    }
                                                                                %>
                                                                             <!--<tr>
										    <td><center>Christmas Sales</center></td>
											<td><center>57520-0405</center></td>
											<td><center>Flat Rate</center></td>
											<td><center>Audebook Flash Sales</center></td>
											<!--<td><center>AudeBook</center></td>
											<td><center>Nigeria-NGN</center></td>
											<td><center>500</center></td>
											<td><center>2000</center></td>
											<td><center>15000</center></td>
											<td><center>2019-04-23</center></td>
											<td><center>2019-05-23</center></td>
											
										</tr>	
										<tr>
										    <td><center>Eid Mubarak</center></td>
											<td><center>57520-0405</center></td>
											<td><center>Percentage Discount</center></td>
											<td><center>Playcode it</center></td>
											<!--<td><center>PlayCode</center></td>
											<td><center>Ghana-GHS</center></td>
											<td><center>500</center></td>
											<td><center>2000</center></td>
											<td><center>15000</center></td>
											<td><center>2019-04-23</center></td>
											<td><center>2019-05-23</center></td>
											
										</tr>	
										<tr>
											<td><center>New Year Sales</center></td>
											<td><center>57520-0405</center></td>
											<td><center>Flat Rate</center></td>
											<td><center>Stream Videos with discount</center></td>
											<!--<td><center>BoxAfrica</center></td>
											<td><center>Other-USD</center></td>
											<td><center>200</center></td>
											<td><center>1000</center></td>
											<td><center>5000</center></td>
											<td><center>2019-04-23</center></td>
											<td><center>2019-05-23</center></td>
											
										</tr>
										<tr>
										    <td><center>Eid Mubarak</center></td>
											<td><center>57520-0405</center></td>
											<td><center>Percentage Discount</center></td>
											<td><center>Playcode it</center></td>
											<!--<td><center>PlayCode</center></td>
											<td><center>Ghana-GHS</center></td>
											<td><center>500</center></td>
											<td><center>2000</center></td>
											<td><center>15000</center></td>
											<td><center>2019-04-23</center></td>
											<td><center>2019-05-23</center></td>
											
										</tr>-->	
									</tbody>
								</table>
													</div>
								<!--end: Datatable -->
							</div>
						</div>


					</div>
				</div>
			</div>

			<!-- end:: Body -->

			<!-- begin::Footer -->
			<jsp:include page="common/footer.html"/>
			<!-- end::Footer -->
		</div>

		<!-- end:: Page -->

		<!-- begin::Quick Sidebar -->
		<jsp:include page="common/quicksidebar.html"/>

		<!-- end::Quick Sidebar -->

		<!-- begin::Scroll Top -->
		<div id="m_scroll_top" class="m-scroll-top">
			<i class="la la-arrow-up"></i>
		</div>

		<!-- end::Scroll Top -->

		<!-- begin::Quick Nav -->
		<jsp:include page="common/quicknav.html" />
		<!-- begin::Quick Nav -->

		<!--begin::Global Theme Bundle -->
		<jsp:include page="common/scripts.html"/>

		<!--end::Page Scripts -->
                <script src="js/admin.js"></script>
	</body>

	<!-- end::Body -->
</html>
<%
    }
    %>