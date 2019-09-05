<%-- 
    Document   : login
    Created on : Oct 26, 2018, Oct 26, 2018 11:41:21 AM
    Author     : OLAWALE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    Object userObject[] = (Object[])session.getAttribute("user_object");
    System.out.println("OutEnter");
    if(userObject!=null)
    {
        System.out.println("Enter");
//        RequestDispatcher rd = request.getRequestDispatcher("../login.jsp");
        RequestDispatcher rd = request.getRequestDispatcher("admin/index.jsp");
        rd.forward(request, response);
    }
    else
    {
//    ArrayList<Object[]> menuMasterObjectList = (ArrayList<Object[]>)session.getAttribute("menuMasterObjectList");
//        HashMap<String, ArrayList<Object[]>> menuChildObjectMap = (HashMap<String, ArrayList<Object[]>>) session.getAttribute("menuChildObjectMap");
        
//        ArrayList<Object[]> allManuMappingList = (ArrayList<Object[]>)session.getAttribute("allManuMappingList");
//        HashMap<String, ArrayList<Object[]>> menuChildObjectMap = (HashMap<String, ArrayList<Object[]>>) session.getAttribute("menuChildObjectMap");
//    ArrayList<Object[]> allMenuHistoryList = (ArrayList<Object[]>)session.getAttribute("allMenuHistoryList");
//        
    
//    ArrayList<String> allManuMappingList = (ArrayList<String>) request.getAttribute("allManuMappingList");
//    ArrayList<String> allManuChildMappingList = (ArrayList<String>) request.getAttribute("allManuChildMappingList");
    
//    HashMap<String, ArrayList<Object[]>> menuChildObjectMap = (HashMap<String, ArrayList<Object[]>>) request.getAttribute("menuChildObjectMap");
//    ArrayList<Object[]> menuMasterObjectList = (ArrayList<Object[]>) request.getAttribute("menuMasterObjectList");
//      ArrayList<Object[]> userMasterObjectList = (ArrayList<Object[]>) request.getAttribute("userMasterObjectList");
      
//      Object userId = (Object)request.getAttribute("user_id");
      
      
%>
<!DOCTYPE html>

<html lang="en">

	<!-- begin::Head -->
	<head>
		<meta charset="utf-8" />
		<title>BlueSparrow | SignIn Page</title>
		<meta name="description" content="SignIn to BlueSparrow">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">

		<!--begin::Web font -->
		<script src="https://ajax.googleapis.com/ajax/libs/webfont/1.6.16/webfont.js"></script>
		<script>
			WebFont.load({
            google: {"families":["Poppins:300,400,500,600,700","Roboto:300,400,500,600,700"]},
            active: function() {
                sessionStorage.fonts = true;
            }
          });
        </script>

		<!--end::Web font -->

		<!--begin::Global Theme Styles -->
		<link href="assets/vendors/base/vendors.bundle.css" rel="stylesheet" type="text/css" />

		<!--RTL version:<link href="../../../assets/vendors/base/vendors.bundle.rtl.css" rel="stylesheet" type="text/css" />-->
		<link href="assets/default/base/style.bundle.css" rel="stylesheet" type="text/css" />

		<!--RTL version:<link href="../../../assets/demo/default/base/style.bundle.rtl.css" rel="stylesheet" type="text/css" />-->

		<!--end::Global Theme Styles -->
		<link rel="shortcut icon" href="assets/default/media/img/logo/favicon.ico" />
	</head>

	<!-- end::Head -->

	<!-- begin::Body -->
	<body class="m--skin- m-header--fixed m-header--fixed-mobile m-aside-left--enabled m-aside-left--skin-dark m-aside-left--fixed m-aside-left--offcanvas m-footer--push m-aside--offcanvas-default">

		<!-- begin:: Page -->
		<div class="m-grid m-grid--hor m-grid--root m-page">
			<div class="m-grid__item m-grid__item--fluid m-grid m-grid--ver-desktop m-grid--desktop m-grid--tablet-and-mobile m-grid--hor-tablet-and-mobile m-login m-login--1 m-login--signin" id="m_login">
				<div class="m-grid__item m-grid__item--order-tablet-and-mobile-2 m-login__aside">
					<div class="m-stack m-stack--hor m-stack--desktop">
						<div class="m-stack__item m-stack__item--fluid">
							<div class="m-login__wrapper">
								<div class="m-login__logo">
									<a href="#">
										<img src="assets/default/media/img/logo/logo-2.png">
									</a>
								</div>
								<div class="m-login__signin">
									<div class="m-login__head">
										<h3 class="m-login__title">Sign In</h3>
									</div>
                                                                    <form class="m-login__form m-form" action="login.htm" method="post">
										<div class="form-group m-form__group">
											<input class="form-control m-input" type="text" placeholder="Email" name="email" autocomplete="off">
										</div>
										<div class="form-group m-form__group">
											<input class="form-control m-input m-login__form-input--last" type="password" placeholder="Password" name="password">
										</div>
										<div class="row m-login__form-sub">
											<div class="col m--align-left">
												<label class="m-checkbox m-checkbox--focus">
                                                                                                    <input type="checkbox" name="remember" value="1"> Remember me
													<span></span>
												</label>
											</div>
											<div class="col m--align-right">
												<a href="javascript:;" id="m_login_forget_password" class="m-link">Forget Password ?</a>
											</div>
										</div>
										<div class="m-login__form-action">
<!--                                                                                    <button id="m_login_signin_submit" class="btn btn-focus m-btn m-btn--pill m-btn--custom m-btn--air" type="submit">Sign In</button>-->
                                                                                <button id="" class="btn btn-focus m-btn m-btn--pill m-btn--custom m-btn--air" type="submit">Sign In</button>
										</div>
									</form>
								</div>
								<div class="m-login__signup">
									<div class="m-login__head">
										<h3 class="m-login__title">Sign Up</h3>
										<div class="m-login__desc">Enter your details to create your account:</div>
									</div>
									<form class="m-login__form m-form" action="">
										<div class="form-group m-form__group">
											<input class="form-control m-input" type="text" placeholder="Fullname" name="fullname">
										</div>
										<div class="form-group m-form__group">
											<input class="form-control m-input" type="text" placeholder="Email" name="email" autocomplete="off">
										</div>
										<div class="form-group m-form__group">
											<input class="form-control m-input" type="password" placeholder="Password" name="password">
										</div>
										<div class="form-group m-form__group">
											<input class="form-control m-input m-login__form-input--last" type="password" placeholder="Confirm Password" name="rpassword">
										</div>
										<div class="row form-group m-form__group m-login__form-sub">
											<div class="col m--align-left">
												<label class="m-checkbox m-checkbox--focus">
													<input type="checkbox" name="agree"> I Agree the <a href="#" class="m-link m-link--focus">terms and conditions</a>.
													<span></span>
												</label>
												<span class="m-form__help"></span>
											</div>
										</div>
										<div class="m-login__form-action">
											<button id="m_login_signup_submit" class="btn btn-focus m-btn m-btn--pill m-btn--custom m-btn--air">Sign Up</button>
											<button id="m_login_signup_cancel" class="btn btn-outline-focus  m-btn m-btn--pill m-btn--custom">Cancel</button>
										</div>
									</form>
								</div>
								<div class="m-login__forget-password">
									<div class="m-login__head">
										<h3 class="m-login__title">Forgotten Password ?</h3>
										<div class="m-login__desc">Enter your email to reset your password:</div>
									</div>
									<form class="m-login__form m-form" action="">
										<div class="form-group m-form__group">
											<input class="form-control m-input" type="text" placeholder="Email" name="email" id="m_email" autocomplete="off">
										</div>
										<div class="m-login__form-action">
											<button id="m_login_forget_password_submit" class="btn btn-focus m-btn m-btn--pill m-btn--custom m-btn--air">Request</button>
											<button id="m_login_forget_password_cancel" class="btn btn-outline-focus m-btn m-btn--pill m-btn--custom">Cancel</button>
										</div>
									</form>
								</div>
							</div>
						</div>
						<div class="m-stack__item m-stack__item--center">
							<div class="m-login__account">
								<span class="m-login__account-msg">
									Don't have an account yet ?
								</span>&nbsp;&nbsp;
								<a href="javascript:;" id="m_login_signup" class="m-link m-link--focus m-login__account-link">Sign Up</a>
							</div>
						</div>
					</div>
				</div>
				<div class="m-grid__item m-grid__item--fluid m-grid m-grid--center m-grid--hor m-grid__item--order-tablet-and-mobile-1	m-login__content m-grid-item--center" style="background-image: url(assets/default/media/img//bg/bg-01.jpg)">
					<div class="m-grid__item">
						<h4 class="m-login__welcome">Welcome to BlueSparrow</h4>
						<p class="m-login__msg">
							Manage all your content and more with BlueSparrow
						</p>
					</div>
				</div>
			</div>
		</div>

		<!-- end:: Page -->

		<!--begin::Global Theme Bundle -->
		<script src="assets/vendors/base/vendors.bundle.js" type="text/javascript"></script>
		<script src="assets/default/base/scripts.bundle.js" type="text/javascript"></script>

		<!--end::Global Theme Bundle -->

		<!--begin::Page Scripts -->
		<script src="assets/default/base/user/login.js" type="text/javascript"></script>

		<!--end::Page Scripts -->
	</body>

	<!-- end::Body -->
</html>
<%
    }
    %>