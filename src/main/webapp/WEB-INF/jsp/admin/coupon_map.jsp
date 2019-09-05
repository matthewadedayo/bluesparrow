<%-- 
    Document   : coupon_add
    Created on : Mar 20, 2019, Mar 20, 2019 10:28:29 AM
    Author     : OLAWALE
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



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
        
        ArrayList<Object[]> allManuMappingList = (ArrayList<Object[]>)session.getAttribute("allManuMappingList");
        HashMap<String, ArrayList<Object[]>> menuChildObjectMap = (HashMap<String, ArrayList<Object[]>>) session.getAttribute("menuChildObjectMap");
    ArrayList<Object[]> allMenuHistoryList = (ArrayList<Object[]>)session.getAttribute("allMenuHistoryList");
        
    
    ArrayList<Object[]> allContentPortalMasterObjectList = (ArrayList<Object[]>) request.getAttribute("allContentPortalMasterObjectList");
    Object[] couponMasterObject = (Object[]) request.getAttribute("couponMasterObject");
    ArrayList<Object[]> subscriptionMasterObjectList = (ArrayList<Object[]>) request.getAttribute("subscriptionMasterObjectList");
    Object[] subscriptionMasterObject = (Object[]) request.getAttribute("subscriptionMasterObject");
    
//    HashMap<String, ArrayList<Object[]>> menuChildObjectMap = (HashMap<String, ArrayList<Object[]>>) request.getAttribute("menuChildObjectMap");
//    ArrayList<Object[]> menuMasterObjectList = (ArrayList<Object[]>) request.getAttribute("menuMasterObjectList");
//      ArrayList<Object[]> userMasterObjectList = (ArrayList<Object[]>) request.getAttribute("userMasterObjectList");
      
     String feedback = (String) request.getAttribute("feedback");
      System.out.println("fe: " + feedback);
//      Object userId = (Object)request.getAttribute("user_id");
      
      
%>
<html lang="en">

	<!-- begin::Head -->
	<head>
		<meta charset="utf-8" />
		<title>BlueSparrow | Add Coupon</title>
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
										<a href="" class="m-nav__link">
											<span class="m-nav__link-text">Create Coupon</span>
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

						<!--begin::Portlet-->
						<div class="m-portlet m-portlet--full-height">
							<form class="m-form m-form--label-align-left- m-form--state-" id="m_form"  action="map_coupon.htm" method="POST">
                                                            <input type="hidden" name="cp_id" id="cp_id" value="<%=couponMasterObject[0]%>" />
								<!--begin: Form Body -->
								<div class="m-portlet__body m-portlet__body--no-padding">
									
									<!--begin: Form Wizard Step 2-->
									<div class="m-wizard__form-step" id="m_wizard_form_step_2">
														
										<div class="m-form__section m-form__section--first">
											<div class="m-form__heading" style="padding: 30px;padding-bottom: 0px;padding-left: 60px">
												<h3 class="m-form__heading-title">
													Coupon Mapping
												</h3>
											</div><hr>
										</div>
														
										<div class="m-demo" style="width: 90%; margin: 30px auto; clear: both;">
									
											<div class="m-demo__preview">
									
												<div class="col-lg-12 col-md-12 col-sm-12">
													<label for="example-text-input" >
                                                                                                            Coupon Code &raquo;&raquo; <big><%=couponMasterObject[2]%></big>
													</label><br/>
                                                                                                        <label for="example-text-input" >
                                                                                                            Coupon  Title &raquo;&raquo; <big><%=couponMasterObject[1]%></big>
													</label><br/>
										
													<input type="hidden" name="couponId" value="<%=couponMasterObject[0]%>" class="form-control m-input m-input--air">
													
												</div>
										<br/>
												<div class="col-lg-12 col-md-12 col-sm-12">
													<label for="example-text-input" >
													* Select Portal
													</label>
										
													<select class="form-control m-input m-input--air" name="portal" id="currencySelector">
														<option>
															Select Coupon
														</option>
														<option value="Audebook">
															AudeBook
														</option>
														<option value="Playcode">
															PlayCode
														</option>
														<option value="Boxafrica">
															BoxAfrica
														</option>
														<option value="Musik365">
															Musik365
														</option>
														
														
														
													</select>
													<span class="m-form__help">
														Select portal you want the coupon to appear
													</span>
												</div><br/>
												
									
												<div class="col-lg-12 col-md-12 col-sm-12">
													<label for="example-text-input" >
															* Mapping Type
													</label>
													<select class="form-control m-input m-input--air" name="mapType" id="couponMapp">
														
														<option value="ZY_null">
															Select mapping type
														</option>
														<option value="Products Category">
															Products Category
														</option>
														<option value="Subscription Plans">
															Subscription Plans
														</option>
														<option value="Products">
															Products
														</option>

													</select>
													<span class="m-form__help">
														Select mapping category
													</span>
														
												</div>	
												<div class="col-lg-12 col-md-12">
							   						<div class="col-sm-11 hideMe" id="productCat">
														<div class="col-xl-12 col-lg-8 col-sm-12 col-md-12">
															<div class="m-checkbox-inline">
                                                                                                                            
																<label class="m-checkbox">
																	<input type="checkbox">
																	Game
																	<span></span>
																</label>
																<label class="m-checkbox">
																	<input type="checkbox">
																	Movie
																	<span></span>
																</label>
																<label class="m-checkbox">
																	<input type="checkbox">
																	Ebook
																	<span></span>
																</label>
															</div>
														</div>
													</div>
							   						<div class="col-sm-11 hideMe" id="subscriptionPlans">
												        <div class="col-xl-12 col-lg-8 col-sm-12 col-md-12">
															 <div class="m-checkbox-inline">
                                                                                                                  <%
                                                                                                                   for(int count=0; count<subscriptionMasterObjectList.size(); ++count)
                                                                                                                      {

                                                                                                                         %>
                                                                                                         <label class="m-checkbox">
                                                                                                             <input type="checkbox" name="mapId" value="<%=subscriptionMasterObjectList.get(count)[0]%>"/>
                                                                                                               <%=subscriptionMasterObjectList.get(count)[1]%>
                                                                                                               <span></span>
                                                                                                                       </label>
                                                                                                               <%

                                                                                                                     }
                                                                                                                 %>
                                                                                                                </div>
                                                                                                            </div>
													</div>
													<div class="col-sm-11 hideMe" id="products">
														<div class="col-xl-12 col-lg-8 col-sm-12 col-md-12">
															<div class="m-checkbox-inline">
																<label class="m-checkbox">
																	<input type="checkbox">
																	Avenger Endgame(movie)
																	<span></span>
																</label>
																<label class="m-checkbox">
																	<input type="checkbox">
																	Things Fall Apart(ebook)
																	<span></span>
																</label>
																<label class="m-checkbox">
																	<input type="checkbox">
																	Super Mario(game)
																	<span></span>
																</label>
															</div>
														</div>
													</div>
										  		</div>
												
											</div>
										</div>	
									</div>
														<!--end: Form Wizard Step 2-->
								<!--begin: Form Wizard Step 3-->
														
														<!--end: Form Wizard Step 3-->
								<!--begin: Form Wizard Step 4-->
														
														<!--end: Form Wizard Step 4-->
							</div>
													<!--end: Form Body -->
							<!--begin: Form Actions -->
							<div class="m-portlet__foot m-portlet__foot--fit">
									<div class="m-form__actions m-form__actions">
										<div class="row">
											<div class="col-lg-9 ml-lg-auto">
                                                                                            <button id="sub_faq" type="submit" class="btn btn-brand">
													Submit
												</button>
												<button type="reset" class="btn btn-secondary">
													Cancel
												</button>
											</div>
										</div>
									</div>
								</div>
							<!--end: Form Actions -->
						</form>
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
		<script>
		var selector = document.getElementById("currencySelector");

		var currencyElements = document.getElementsByClassName("currency");

		var usdChangeRate = {
		  GHS: 1, // 1AUD = 1.0490 USD
		  USD: 1, // 1EUR = 1.440 
		  NGN: 1
		};

	selector.onchange = function () {
		var toCurrency = selector.value.toUpperCase();

		for (var i=0,l=currencyElements.length; i<l; ++i) {

			var el = currencyElements[i];

			var fromCurrency = el.getAttribute("data-currencyName").toUpperCase();

		  if (fromCurrency in usdChangeRate) {
			  var 
				  // currency change to usd
				  fromCurrencyToUsdAmount = parseFloat(el.innerHTML) * usdChangeRate[fromCurrency];


			  el.innerHTML = "<span><h4>" + toCurrency.toUpperCase() + "</h4></span>";
			  el.setAttribute("data-currencyName",toCurrency);
		  }
		}
	};
	</script>

   <script>
		(function(){
			$("#couponMapp").change(function(){
				var area = this.value;
				$(".hideMe").fadeOut(30, function(){
					switch(area.toLowerCase()){
                                            case 'products category':
                                                $("#productCat").show();
                                                break;
                                            case 'subscription plans':
                                                $("#subscriptionPlans").show();
                                                break;
                                            case 'products':
                                                $("#products").show();
                                                break;
                                        }
				});
				
			})
		})()
	</script>
	</body>

	<!-- end::Body -->
</html>
<%
    }
    %>	
