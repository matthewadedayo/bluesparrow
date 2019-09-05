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
  // ArrayList<Object[]> subscriptionMasterObjectList = (ArrayList<Object[]>) request.getAttribute("subscriptionMasterObjectList");
    
//    HashMap<String, ArrayList<Object[]>> menuChildObjectMap = (HashMap<String, ArrayList<Object[]>>) request.getAttribute("menuChildObjectMap");
//    ArrayList<Object[]> menuMasterObjectList = (ArrayList<Object[]>) request.getAttribute("menuMasterObjectList");
//      ArrayList<Object[]> userMasterObjectList = (ArrayList<Object[]>) request.getAttribute("userMasterObjectList");
      
     String feedback = (String) request.getAttribute("feedback");
      System.out.println("fe: " + feedback);
//      Object userId = (Object)request.getAttribute("user_id");
      
      
%>
<!DOCTYPE html>

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
							<form class="m-form m-form--label-align-left- m-form--state-" id="m_form" action="add_coupon.htm" method="POST">
								<!--begin: Form Body -->
								<div class="m-portlet__body m-portlet__body--no-padding">
									<!--begin: Form Wizard Step 1-->
									<div class="m-wizard__form-step m-wizard__form-step--current" id="m_wizard_form_step_1">
										<div class="m-form__heading"  style="padding: 30px;padding-bottom: 0px;padding-left: 60px">
										  <h3 class="m-form__heading-title"> Coupon Details </h3>
										</div><hr/>
										<div class="m-demo" style="width: 90%; margin: 30px auto; clear: both;">
									
											<div class="m-demo__preview">
									
												<div class="col-lg-12 col-md-12 col-sm-12">
													<label for="example-text-input" >
													* Select Coupon Country
													</label>
													<select name="country" class="form-control m-input m-input--air" id="currencySelector">
                                                                                                            <option value="">
                                                                                                                Select Coupon Country
                                                                                                            </option>
                                                                                                            <option value="Nigeria">
															Nigeria
														</option>
														<option value="Ghana">
															Ghana 
														</option>
														<option value="Others">
															Other 
														</option>
														
														
													</select>
													<span class="m-form__help">
														Please select country you want to apply coupon
													</span>
												</div>
									
												<div class="col-lg-12 col-md-12 col-sm-12 m--margin-bottom-10">
													<label for="example-text-input" >
													* Coupon Title
													</label>
													
													<input type="text" name="title" class="form-control m-input m-input--air"  placeholder="Coupon Title">
													<span class="m-form__help">
														Please enter your preferred coupon title
													</span>
												</div>
									
												<div class="col-lg-12 col-md-12 col-sm-12">
													<label for="example-text-input" >
															* Coupon Code
													</label>
													<input type="text" name="code" class="form-control m-input m-input--air"  placeholder="Coupon Code">
													<span class="m-form__help">
														Please enter your coupon code
													</span>
												</div>
												<div class="col-lg-12 col-md-12 m--margin-top-10">
								   					<label for="example-text-input" >
													* Coupon Description
													</label>
									   				<textarea name="desc" class="form-control m-input m-input--air" id="exampleTextarea" rows="4" placeholder="Coupon Description"></textarea>	
									   				<span class="m-form__help">
														Type your coupon description
													</span>							   
									  			</div>
									  
									
												<div class="m-separator m-separator--dashed m-separator--lg"></div>
												
												<div class="col-lg-12 col-md-12 col-sm-12">
													<label for="example-text-input" >
													* count Type
													</label>
													<select name="discount" class="form-control m-input m-input--air" id="discountSelector">
														<option>Select Coupon Discount type</option>
														<option value="PERCENTAGE DISCOUNT">
															Percentage Discount
														</option>
														<option value="FLAT RATE DISCOUNT">
															Flat Rate Discount
														</option>
													</select>
													<span class="m-form__help">
														Select your coupon discount type
													</span>
												</div>

												<div style="margin: 10px 15px 10px 15px; overflow: auto;">
													<div style="width: 50%;  float: left;">
											
														<div style="margin-right: 10px;">
															<label for="example-text-input" >
															* Coupon Amount
															</label>
												
															<input type="text" name="amount" class="form-control m-input--air" id="priceorpercent"  placeholder="Price">
											
														</div>
									    			</div>
													<div style="width: 50%; float: left;">
														<label for="example-text-input" id="currency-label">
															Coupon Currency
														</label>
														<div class="m--margin-top-10">
                                                                                                                    <input type="hidden" name="currency" id="coupon_currency"/>
															<div class="currency"><span><h4>NGN</h4></span></div>
														</div>
													</div>
                                                                                                        
                                                                                                    
												</div>
												<div style="margin: 10px 15px 10px 15px; overflow: auto;">
													<div style="width: 50%;  float: left;">
											
														<div style="margin-right: 10px;">
															<label for="example-text-input" >
															* Minimum Purchase Amount
															</label>
															<input type="text" name="minAmt" class="form-control m-input--air"  placeholder="Price">
														</div>
									  				</div>
													<div style="width: 50%; float: left;">
														<label for="example-text-input" >
															* Maximum Discount Amount
														</label>
														<div>
															<input type="text" name="maxAmt" class="form-control m-input--air"  placeholder="Price">	
														</div>
													</div>
												</div>
												
									
												<div style="margin: 10px 15px 20px 15px; overflow: auto;">
													<div style="width: 50%;  float: left; margin: 10px 0 10px 0; padding-right: 10px">
														<label for="example-text-input" >
														* Starting Date/Time
														</label>
														<div class="input-group date">
															<input type="date" name="startDate" class="form-control m-input m-input--air" placeholder="2017-11-29 00:30" id="m_datetimepicker_3"/>
															<div class="input-group-append">
																<span class="input-group-text">
																	<i class="la la-calendar glyphicon-th"></i>
																</span>
															</div>
														</div>
														<span class="m-form__help">
															Please enter preferred start date
														</span>
									  				</div>
													<div style="width: 50%; float: left; margin: 10px 0 10px 0;">
														<label for="example-text-input" >
															* Expire Date/Time
														</label>
														<div class="input-group date">
															<input type="date" name="expiryDate" class="form-control m-input m-input--air" placeholder="2018-11-29 00:30" id="m_datetimepicker_2"/>
															<div class="input-group-append">
																<span class="input-group-text">
																	<i class="la la-calendar glyphicon-th"></i>
																</span>
															</div>
														</div>
														<span class="m-form__help">
															Please enter preferred end date
														</span>
													</div>
												</div>
											</div>
										</div>	
									</div>
									<!--end: Form Wizard Step 1-->
									
								<!--begin: Form Wizard Step 3-->
														
							        <!--end: Form Wizard Step 3-->
								
								</div>
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
                                                        
                                                            <%
                                                                            if(feedback!=null)
                                                                            {
                                                                                %>
                                                                                                                        
                                                                                    <input type="hidden" id="msg" value="<%=feedback%>" />
                                                                                <%
                                                                                }
                                                                                else
                                                                                {

                                                                                %>
                                                                                    <input type="hidden" id="msg" value="" />
                                                                                <%
                                                                                }
                                                                                %>
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

	$("#discountSelector").change(function(){
		var v = this.value;
		if(v == "PERCENTAGE DISCOUNT"){
			$(".currency").html("<span><h4>%</h4></span>");
			$("#currency-label").html(" ");
			$("#priceorpercent").attr("placeholder", "percent like 55");
		}else{
                   // console.log($("#currencySelector"));
			$(".currency").html("<span><h4>"+$("#currencySelector").val().toUpperCase()+"</h4></span>");
			$("#currency-label").html("Coupon Currency");
			$("#priceorpercent").attr("placeholder", "amount");
		}
	})

	selector.onchange = function () {
		if($("#discountSelector").val() == "PERCENTAGE DISCOUNT"){
                    //$("#coupon_currency").val('');
                    return false;
                }
			
		$("#discountSelector").trigger("change");
                $("#coupon_currency").val(this.value)
	};
	</script>

   <script>
		function userMulti() {
			var uRole = document.getElementById('couponMapp');
			var uInput = uRole.options[uRole.selectedIndex].value;
			if (uInput == 'ZY') {
				document.getElementById('hideMulti').style.visibility = 'visible';
				document.getElementById('hideCheck').style.visibility = 'hidden';
				document.getElementById('hidePro').style.visibility = 'hidden';
			} else if (uInput == 'YY') {
				document.getElementById('hideCheck').style.visibility = 'visible';
				document.getElementById('hideMulti').style.visibility = 'hidden';
				document.getElementById('hidePro').style.visibility = 'hidden';
			}
			 else if (uInput == 'MM') {
				document.getElementById('hidePro').style.visibility = 'visible';
				document.getElementById('hideMulti').style.visibility = 'hidden';
				document.getElementById('hideCheck').style.visibility = 'hidden';
			}
			else {
				document.getElementById('hideMulti').style.visibility = 'hidden';
			}
			return false;
		}
	</script>
	</body>

	<!-- end::Body -->
</html>
<%
    }
    %>	
