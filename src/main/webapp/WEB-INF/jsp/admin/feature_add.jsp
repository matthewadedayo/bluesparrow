<%-- 
    Document   : feature_add
    Created on : Apr 30, 2019, Apr 30, 2019 10:28:05 AM
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
//    ArrayList<String> allManuChildMappingList = (ArrayList<String>) request.getAttribute("allManuChildMappingList");
    
//    HashMap<String, ArrayList<Object[]>> menuChildObjectMap = (HashMap<String, ArrayList<Object[]>>) request.getAttribute("menuChildObjectMap");
//    ArrayList<Object[]> menuMasterObjectList = (ArrayList<Object[]>) request.getAttribute("menuMasterObjectList");
//      ArrayList<Object[]> userMasterObjectList = (ArrayList<Object[]>) request.getAttribute("userMasterObjectList");
      
//      Object userId = (Object)request.getAttribute("user_id");
      
      
%>
<!DOCTYPE html>

<html lang="en" >
	<!-- begin::Head -->
	<head>
		<meta charset="utf-8" />
		<title>
			BlueSparrow | Add Feature
		</title>
		<meta name="description" content="Wysiwyg editor for bootstrap">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
        <!--begin::Base Styles -->
		<link href="assets/vendors/base/vendors.bundle.css" rel="stylesheet" type="text/css" />
		<link href="assets/default/base/style.bundle.css" rel="stylesheet" type="text/css" />
		<!--end::Base Styles -->
		<link rel="shortcut icon" href="assets/demo/default/media/img/logo/favicon.ico" />
	</head>
	<!-- end::Head -->
    <!-- end::Body -->
	<body class="m-page--fluid m--skin- m-content--skin-light2 m-header--fixed m-header--fixed-mobile m-aside-left--enabled m-aside-left--skin-dark m-aside-left--fixed m-aside-left--offcanvas m-footer--push m-aside--offcanvas-default">

		<!-- begin:: Page -->
		<div class="m-grid m-grid--hor m-grid--root m-page">

			<!-- BEGIN: Header -->
			<header id="m_header" class="m-grid__item    m-header " m-minimize-offset="200" m-minimize-mobile-offset="200">
				<div class="m-container m-container--fluid m-container--full-height">
					<div class="m-stack m-stack--ver m-stack--desktop">

						<!-- BEGIN: Brand -->
						<div class="m-stack__item m-brand  m-brand--skin-dark ">
							<div class="m-stack m-stack--ver m-stack--general">
								<div class="m-stack__item m-stack__item--middle m-brand__logo">
									<a href="index.html" class="m-brand__logo-wrapper">
										<img alt="" src="assets/default/media/img/logo/logo-bs-2.png" />
									</a>
								</div>
								<div class="m-stack__item m-stack__item--middle m-brand__tools">

									<!-- BEGIN: Left Aside Minimize Toggle -->
									<a href="javascript:;" id="m_aside_left_minimize_toggle" class="m-brand__icon m-brand__toggler m-brand__toggler--left m--visible-desktop-inline-block  ">
										<span></span>
									</a>

									<!-- END -->

									<!-- BEGIN: Responsive Aside Left Menu Toggler -->
									<a href="javascript:;" id="m_aside_left_offcanvas_toggle" class="m-brand__icon m-brand__toggler m-brand__toggler--left m--visible-tablet-and-mobile-inline-block">
										<span></span>
									</a>

									<!-- END -->

									<!-- BEGIN: Responsive Header Menu Toggler -->
									<a id="m_aside_header_menu_mobile_toggle" href="javascript:;" class="m-brand__icon m-brand__toggler m--visible-tablet-and-mobile-inline-block">
										<span></span>
									</a>

									<!-- END -->

									<!-- BEGIN: Topbar Toggler -->
									<a id="m_aside_header_topbar_mobile_toggle" href="javascript:;" class="m-brand__icon m--visible-tablet-and-mobile-inline-block">
										<i class="flaticon-more"></i>
									</a>

									<!-- BEGIN: Topbar Toggler -->
								</div>
							</div>
						</div>

						<!-- END: Brand -->
						<div class="m-stack__item m-stack__item--fluid m-header-head" id="m_header_nav">

							<!-- BEGIN: Horizontal Menu -->
							<button class="m-aside-header-menu-mobile-close  m-aside-header-menu-mobile-close--skin-dark " id="m_aside_header_menu_mobile_close_btn"><i class="la la-close"></i></button>
							<div id="m_header_menu" class="m-header-menu m-aside-header-menu-mobile m-aside-header-menu-mobile--offcanvas  m-header-menu--skin-light m-header-menu--submenu-skin-light m-aside-header-menu-mobile--skin-dark m-aside-header-menu-mobile--submenu-skin-dark ">
								<ul class="m-menu__nav  m-menu__nav--submenu-arrow ">
									<li class="m-menu__item  m-menu__item--submenu m-menu__item--rel" m-menu-submenu-toggle="click" m-menu-link-redirect="1" aria-haspopup="true"><a href="javascript:;" class="m-menu__link m-menu__toggle" title="Non functional dummy link"><i
											 class="m-menu__link-icon flaticon-add"></i><span class="m-menu__link-text">Actions</span><i class="m-menu__hor-arrow la la-angle-down"></i><i class="m-menu__ver-arrow la la-angle-right"></i></a>
										<div class="m-menu__submenu m-menu__submenu--classic m-menu__submenu--left"><span class="m-menu__arrow m-menu__arrow--adjust"></span>
											<ul class="m-menu__subnav">
												<li class="m-menu__item " aria-haspopup="true"><a href="content_master_add.html" class="m-menu__link "><i class="m-menu__link-icon flaticon-file"></i><span class="m-menu__link-text">Add Content</span></a></li>
												<li class="m-menu__item " aria-haspopup="true"><a href="#" class="m-menu__link "><i class="m-menu__link-icon flaticon-file"></i><span class="m-menu__link-text">Create Campaign</span></a></li>
									</li>
												<li class="m-menu__item " m-menu-link-redirect="1" aria-haspopup="true"><a href="header/actions.html" class="m-menu__link "><i class="m-menu__link-icon flaticon-users"></i><span class="m-menu__link-text">Add User</span></a></li>
											</ul>
										</div>
									</li>
									<li class="m-menu__item  m-menu__item--submenu m-menu__item--rel" m-menu-submenu-toggle="click" m-menu-link-redirect="1" aria-haspopup="true"><a href="javascript:;" class="m-menu__link m-menu__toggle" title="Non functional dummy link"><i
											 class="m-menu__link-icon flaticon-line-graph"></i><span class="m-menu__link-text">Reports</span><i class="m-menu__hor-arrow la la-angle-down"></i><i class="m-menu__ver-arrow la la-angle-right"></i></a>
																			<div class="m-menu__submenu m-menu__submenu--classic m-menu__submenu--left"><span class="m-menu__arrow m-menu__arrow--adjust"></span>
											<ul class="m-menu__subnav">
												<li class="m-menu__item " m-menu-link-redirect="1" aria-haspopup="true"><a href="header/actions.html" class="m-menu__link "><i class="m-menu__link-icon flaticon-business"></i><span class="m-menu__link-text">Subscription Report</span></a></li>
												<li class="m-menu__item " m-menu-link-redirect="1" aria-haspopup="true"><a href="header/actions.html" class="m-menu__link "><i class="m-menu__link-icon flaticon-map"></i><span class="m-menu__link-text">Service Revenue Report</span></a></li>
												<li class="m-menu__item " m-menu-link-redirect="1" aria-haspopup="true"><a href="header/actions.html" class="m-menu__link "><i class="m-menu__link-icon flaticon-map"></i><span class="m-menu__link-text">Variable Billing Report</span></a></li>
												<li class="m-menu__item " m-menu-link-redirect="1" aria-haspopup="true"><a href="header/actions.html" class="m-menu__link "><i class="m-menu__link-icon flaticon-signs-1"></i><span class="m-menu__link-text">Content Delivery Report</span></a></li>
												<li class="m-menu__item " m-menu-link-redirect="1" aria-haspopup="true"><a href="header/actions.html" class="m-menu__link "><i class="m-menu__link-icon flaticon-folder"></i><span class="m-menu__link-text">Service Summary Report</span></a></li>
												<li class="m-menu__item " m-menu-link-redirect="1" aria-haspopup="true"><a href="header/actions.html" class="m-menu__link "><i class="m-menu__link-icon flaticon-cogwheel-2"></i><span class="m-menu__link-text">Service Detail Report</span></a></li>
														
												</li>
											</ul>
										</div>
									</li>
									<li class="m-menu__item  m-menu__item--submenu m-menu__item--rel" m-menu-submenu-toggle="click" m-menu-link-redirect="1" aria-haspopup="true"><a href="javascript:;" class="m-menu__link m-menu__toggle" title="Non functional dummy link"><i
											 class="m-menu__link-icon flaticon-paper-plane"></i><span class="m-menu__link-title"> <span class="m-menu__link-wrap"> <span class="m-menu__link-text">Apps</span> <span class="m-menu__link-badge"></span>
												</span></span><i class="m-menu__hor-arrow la la-angle-down"></i><i class="m-menu__ver-arrow la la-angle-right"></i></a>
										<div class="m-menu__submenu m-menu__submenu--classic m-menu__submenu--left"><span class="m-menu__arrow m-menu__arrow--adjust"></span>
											<ul class="m-menu__subnav">
												<li class="m-menu__item " m-menu-link-redirect="1" aria-haspopup="true"><a href="#" class="m-menu__link "><i class="m-menu__link-icon flaticon-business"></i><span class="m-menu__link-text">Application1</span></a></li>
												<li class="m-menu__item " m-menu-link-redirect="1" aria-haspopup="true"><a href="#" class="m-menu__link "><i class="m-menu__link-icon flaticon-map"></i><span class="m-menu__link-text">Application2</span></a></li>
												<li class="m-menu__item " m-menu-link-redirect="1" aria-haspopup="true"><a href="#" class="m-menu__link "><i class="m-menu__link-icon flaticon-map"></i><span class="m-menu__link-text">Application3</span></a></li>
												<li class="m-menu__item " m-menu-link-redirect="1" aria-haspopup="true"><a href="#" class="m-menu__link "><i class="m-menu__link-icon flaticon-map"></i><span class="m-menu__link-text">Application4</span></a></li>

											</ul>
										</div>
									</li>
								</ul>
							</div>

							<!-- END: Horizontal Menu -->

							<!-- BEGIN: Topbar -->
							<div id="m_header_topbar" class="m-topbar  m-stack m-stack--ver m-stack--general m-stack--fluid">
								<div class="m-stack__item m-topbar__nav-wrapper">
									<ul class="m-topbar__nav m-nav m-nav--inline">
										<li class="m-nav__item m-dropdown m-dropdown--large m-dropdown--arrow m-dropdown--align-center m-dropdown--mobile-full-width m-dropdown--skin-light	m-list-search m-list-search--skin-light" m-dropdown-toggle="click" id="m_quicksearch"
										 m-quicksearch-mode="dropdown" m-dropdown-persistent="1">
											<a href="#" class="m-nav__link m-dropdown__toggle">
												<span class="m-nav__link-icon"><i class="flaticon-search-1"></i></span>
											</a>
											<div class="m-dropdown__wrapper">
												<span class="m-dropdown__arrow m-dropdown__arrow--center"></span>
												<div class="m-dropdown__inner ">
													<div class="m-dropdown__header">
														<form class="m-list-search__form">
															<div class="m-list-search__form-wrapper">
																<span class="m-list-search__form-input-wrapper">
																	<input id="m_quicksearch_input" autocomplete="off" type="text" name="q" class="m-list-search__form-input" value="" placeholder="Search...">
																</span>
																<span class="m-list-search__form-icon-close" id="m_quicksearch_close">
																	<i class="la la-remove"></i>
																</span>
															</div>
														</form>
													</div>
													<div class="m-dropdown__body">
														<div class="m-dropdown__scrollable m-scrollable" data-scrollable="true" data-height="300" data-mobile-height="200">
															<div class="m-dropdown__content">
															</div>
														</div>
													</div>
												</div>
											</div>
										</li>
										<li class="m-nav__item m-topbar__notifications m-topbar__notifications--img m-dropdown m-dropdown--large m-dropdown--header-bg-fill m-dropdown--arrow m-dropdown--align-center 	m-dropdown--mobile-full-width" m-dropdown-toggle="click"
										 m-dropdown-persistent="1">
											<a href="#" class="m-nav__link m-dropdown__toggle" id="m_topbar_notification_icon">
												<span class="m-nav__link-badge m-badge m-badge--dot m-badge--dot-small m-badge--danger"></span>
												<span class="m-nav__link-icon"><i class="flaticon-alarm"></i></span>
											</a>
											<div class="m-dropdown__wrapper">
												<span class="m-dropdown__arrow m-dropdown__arrow--center"></span>
												<div class="m-dropdown__inner">
													<div class="m-dropdown__header m--align-center" style="background: url(assets/app/media/img/misc/notification_bg.jpg); background-size: cover;">
														<span class="m-dropdown__header-title">9 New</span>
														<span class="m-dropdown__header-subtitle">User Notifications</span>
													</div>
													<div class="m-dropdown__body">
														<div class="m-dropdown__content">
															<ul class="nav nav-tabs m-tabs m-tabs-line m-tabs-line--brand" role="tablist">
																<li class="nav-item m-tabs__item">
																	<a class="nav-link m-tabs__link active" data-toggle="tab" href="#topbar_notifications_notifications" role="tab">
																		Alerts
																	</a>
																</li>
																<li class="nav-item m-tabs__item">
																	<a class="nav-link m-tabs__link" data-toggle="tab" href="#topbar_notifications_events" role="tab">Events</a>
																</li>
																<li class="nav-item m-tabs__item">
																	<a class="nav-link m-tabs__link" data-toggle="tab" href="#topbar_notifications_logs" role="tab">Logs</a>
																</li>
															</ul>
															<div class="tab-content">
																<div class="tab-pane active" id="topbar_notifications_notifications" role="tabpanel">
																	<div class="m-scrollable" data-scrollable="true" data-height="250" data-mobile-height="200">
																		<div class="m-list-timeline m-list-timeline--skin-light">
																			<div class="m-list-timeline__items">
																				<div class="m-list-timeline__item">
																					<span class="m-list-timeline__badge -m-list-timeline__badge--state-success"></span>
																					<span class="m-list-timeline__text">12 new users registered</span>
																					<span class="m-list-timeline__time">Just now</span>
																				</div>
																				<div class="m-list-timeline__item">
																					<span class="m-list-timeline__badge"></span>
																					<span class="m-list-timeline__text">System shutdown <span class="m-badge m-badge--success m-badge--wide">pending</span></span>
																					<span class="m-list-timeline__time">14 mins</span>
																				</div>
																				<div class="m-list-timeline__item">
																					<span class="m-list-timeline__badge"></span>
																					<span class="m-list-timeline__text">New invoice received</span>
																					<span class="m-list-timeline__time">20 mins</span>
																				</div>
																				<div class="m-list-timeline__item">
																					<span class="m-list-timeline__badge"></span>
																					<span class="m-list-timeline__text">DB overloaded 80% <span class="m-badge m-badge--info m-badge--wide">settled</span></span>
																					<span class="m-list-timeline__time">1 hr</span>
																				</div>
																				<div class="m-list-timeline__item">
																					<span class="m-list-timeline__badge"></span>
																					<span class="m-list-timeline__text">System error - <a href="#" class="m-link">Check</a></span>
																					<span class="m-list-timeline__time">2 hrs</span>
																				</div>
																				<div class="m-list-timeline__item m-list-timeline__item--read">
																					<span class="m-list-timeline__badge"></span>
																					<span href="" class="m-list-timeline__text">New order received <span class="m-badge m-badge--danger m-badge--wide">urgent</span></span>
																					<span class="m-list-timeline__time">7 hrs</span>
																				</div>
																				<div class="m-list-timeline__item m-list-timeline__item--read">
																					<span class="m-list-timeline__badge"></span>
																					<span class="m-list-timeline__text">Production server down</span>
																					<span class="m-list-timeline__time">3 hrs</span>
																				</div>
																				<div class="m-list-timeline__item">
																					<span class="m-list-timeline__badge"></span>
																					<span class="m-list-timeline__text">Production server up</span>
																					<span class="m-list-timeline__time">5 hrs</span>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
																<div class="tab-pane" id="topbar_notifications_events" role="tabpanel">
																	<div class="m-scrollable" data-scrollable="true" data-height="250" data-mobile-height="200">
																		<div class="m-list-timeline m-list-timeline--skin-light">
																			<div class="m-list-timeline__items">
																				<div class="m-list-timeline__item">
																					<span class="m-list-timeline__badge m-list-timeline__badge--state1-success"></span>
																					<a href="" class="m-list-timeline__text">New order received</a>
																					<span class="m-list-timeline__time">Just now</span>
																				</div>
																				<div class="m-list-timeline__item">
																					<span class="m-list-timeline__badge m-list-timeline__badge--state1-danger"></span>
																					<a href="" class="m-list-timeline__text">New invoice received</a>
																					<span class="m-list-timeline__time">20 mins</span>
																				</div>
																				<div class="m-list-timeline__item">
																					<span class="m-list-timeline__badge m-list-timeline__badge--state1-success"></span>
																					<a href="" class="m-list-timeline__text">Production server up</a>
																					<span class="m-list-timeline__time">5 hrs</span>
																				</div>
																				<div class="m-list-timeline__item">
																					<span class="m-list-timeline__badge m-list-timeline__badge--state1-info"></span>
																					<a href="" class="m-list-timeline__text">New order received</a>
																					<span class="m-list-timeline__time">7 hrs</span>
																				</div>
																				<div class="m-list-timeline__item">
																					<span class="m-list-timeline__badge m-list-timeline__badge--state1-info"></span>
																					<a href="" class="m-list-timeline__text">System shutdown</a>
																					<span class="m-list-timeline__time">11 mins</span>
																				</div>
																				<div class="m-list-timeline__item">
																					<span class="m-list-timeline__badge m-list-timeline__badge--state1-info"></span>
																					<a href="" class="m-list-timeline__text">Production server down</a>
																					<span class="m-list-timeline__time">3 hrs</span>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
																<div class="tab-pane" id="topbar_notifications_logs" role="tabpanel">
																	<div class="m-stack m-stack--ver m-stack--general" style="min-height: 180px;">
																		<div class="m-stack__item m-stack__item--center m-stack__item--middle">
																			<span class="">All caught up!<br>No new logs.</span>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</li>
										<li class="m-nav__item m-topbar__quick-actions m-topbar__quick-actions--img m-dropdown m-dropdown--large m-dropdown--header-bg-fill m-dropdown--arrow m-dropdown--align-right m-dropdown--align-push m-dropdown--mobile-full-width m-dropdown--skin-light"
										 m-dropdown-toggle="click">
											<a href="#" class="m-nav__link m-dropdown__toggle">
												<span class="m-nav__link-badge m-badge m-badge--dot m-badge--info m--hide"></span>
												<span class="m-nav__link-icon"><i class="flaticon-share"></i></span>
											</a>
											<div class="m-dropdown__wrapper">
												<span class="m-dropdown__arrow m-dropdown__arrow--right m-dropdown__arrow--adjust"></span>
												<div class="m-dropdown__inner">
													<div class="m-dropdown__header m--align-center" style="background: url(assets/app/media/img/misc/quick_actions_bg.jpg); background-size: cover;">
														<span class="m-dropdown__header-title">Quick Actions</span>
														<span class="m-dropdown__header-subtitle">Shortcuts</span>
													</div>
													<div class="m-dropdown__body m-dropdown__body--paddingless">
														<div class="m-dropdown__content">
															<div class="data" data="false" data-height="380" data-mobile-height="200">
																<div class="m-nav-grid m-nav-grid--skin-light">
																	<div class="m-nav-grid__row">
																		<a href="#" class="m-nav-grid__item">
																			<i class="m-nav-grid__icon flaticon-file"></i>
																			<span class="m-nav-grid__text">Generate Report</span>
																		</a>
																		<a href="#" class="m-nav-grid__item">
																			<i class="m-nav-grid__icon flaticon-time"></i>
																			<span class="m-nav-grid__text">Add New Event</span>
																		</a>
																	</div>
																	<div class="m-nav-grid__row">
																		<a href="#" class="m-nav-grid__item">
																			<i class="m-nav-grid__icon flaticon-folder"></i>
																			<span class="m-nav-grid__text">Create New Task</span>
																		</a>
																		<a href="#" class="m-nav-grid__item">
																			<i class="m-nav-grid__icon flaticon-clipboard"></i>
																			<span class="m-nav-grid__text">Completed Tasks</span>
																		</a>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</li>
										<li class="m-nav__item m-topbar__languages m-dropdown m-dropdown--small m-dropdown--header-bg-fill m-dropdown--arrow m-dropdown--align-right m-dropdown--mobile-full-width" m-dropdown-toggle="click">
											<a href="#" class="m-nav__link m-dropdown__toggle">
												<span class="m-nav__link-text">
													<img class="m-topbar__language-selected-img" src="assets/app/media/img/flags/020-flag.svg">
												</span>
											</a>
											<div class="m-dropdown__wrapper">
												<span class="m-dropdown__arrow m-dropdown__arrow--right m-dropdown__arrow--adjust"></span>
												<div class="m-dropdown__inner">
													<div class="m-dropdown__header m--align-center" style="background: url(assets/app/media/img/misc/quick_actions_bg.jpg); background-size: cover;">
														<span class="m-dropdown__header-subtitle">Select your language</span>
													</div>
													<div class="m-dropdown__body">
														<div class="m-dropdown__content">
															<ul class="m-nav m-nav--skin-light">
																<li class="m-nav__item m-nav__item--active">
																	<a href="#" class="m-nav__link m-nav__link--active">
																		<span class="m-nav__link-icon"><img class="m-topbar__language-img" src="assets/app/media/img/flags/020-flag.svg"></span>
																		<span class="m-nav__link-title m-topbar__language-text m-nav__link-text">USA</span>
																	</a>
																</li>
																<li class="m-nav__item">
																	<a href="#" class="m-nav__link">
																		<span class="m-nav__link-icon"><img class="m-topbar__language-img" src="assets/app/media/img/flags/015-china.svg"></span>
																		<span class="m-nav__link-title m-topbar__language-text m-nav__link-text">China</span>
																	</a>
																</li>
																<li class="m-nav__item">
																	<a href="#" class="m-nav__link">
																		<span class="m-nav__link-icon"><img class="m-topbar__language-img" src="assets/app/media/img/flags/016-spain.svg"></span>
																		<span class="m-nav__link-title m-topbar__language-text m-nav__link-text">Spain</span>
																	</a>
																</li>
																<li class="m-nav__item">
																	<a href="#" class="m-nav__link">
																		<span class="m-nav__link-icon"><img class="m-topbar__language-img" src="assets/app/media/img/flags/014-japan.svg"></span>
																		<span class="m-nav__link-title m-topbar__language-text m-nav__link-text">Japan</span>
																	</a>
																</li>
																<li class="m-nav__item">
																	<a href="#" class="m-nav__link">
																		<span class="m-nav__link-icon"><img class="m-topbar__language-img" src="assets/app/media/img/flags/017-germany.svg"></span>
																		<span class="m-nav__link-title m-topbar__language-text m-nav__link-text">Germany</span>
																	</a>
																</li>
															</ul>
														</div>
													</div>
												</div>
											</div>
										</li>
										<li class="m-nav__item m-topbar__user-profile m-topbar__user-profile--img  m-dropdown m-dropdown--medium m-dropdown--arrow m-dropdown--header-bg-fill m-dropdown--align-right m-dropdown--mobile-full-width m-dropdown--skin-light"
										 m-dropdown-toggle="click">
											<a href="#" class="m-nav__link m-dropdown__toggle">
												<span class="m-topbar__userpic">
													<img src="assets/app/media/img/users/user4.jpg" class="m--img-rounded m--marginless" alt="" />
												</span>
												<span class="m-topbar__username m--hide">Nick</span>
											</a>
											<div class="m-dropdown__wrapper">
												<span class="m-dropdown__arrow m-dropdown__arrow--right m-dropdown__arrow--adjust"></span>
												<div class="m-dropdown__inner">
													<div class="m-dropdown__header m--align-center" style="background: url(assets/app/media/img/misc/user_profile_bg.jpg); background-size: cover;">
														<div class="m-card-user m-card-user--skin-dark">
															<div class="m-card-user__pic">
																<img src="assets/app/media/img/users/user4.jpg" class="m--img-rounded m--marginless" alt="" />

																<!--
						<span class="m-type m-type--lg m--bg-danger"><span class="m--font-light">S<span><span>
						-->
															</div>
															<div class="m-card-user__details">
																<span class="m-card-user__name m--font-weight-500">Mark Andre</span>
																<a href="" class="m-card-user__email m--font-weight-300 m-link">mark.andre@gmail.com</a>
															</div>
														</div>
													</div>
													<div class="m-dropdown__body">
														<div class="m-dropdown__content">
															<ul class="m-nav m-nav--skin-light">
																<li class="m-nav__section m--hide">
																	<span class="m-nav__section-text">Section</span>
																</li>
																<li class="m-nav__item">
																	<a href="header/profile.html" class="m-nav__link">
																		<i class="m-nav__link-icon flaticon-profile-1"></i>
																		<span class="m-nav__link-title">
																			<span class="m-nav__link-wrap">
																				<span class="m-nav__link-text">My Profile</span>
																				<span class="m-nav__link-badge"><span class="m-badge m-badge--success">2</span></span>
																			</span>
																		</span>
																	</a>
																</li>
																<li class="m-nav__item">
																	<a href="header/profile.html" class="m-nav__link">
																		<i class="m-nav__link-icon flaticon-share"></i>
																		<span class="m-nav__link-text">Activity</span>
																	</a>
																</li>
																<li class="m-nav__item">
																	<a href="header/profile.html" class="m-nav__link">
																		<i class="m-nav__link-icon flaticon-chat-1"></i>
																		<span class="m-nav__link-text">Messages</span>
																	</a>
																</li>
																<li class="m-nav__separator m-nav__separator--fit">
																</li>
																<li class="m-nav__item">
																	<a href="header/profile.html" class="m-nav__link">
																		<i class="m-nav__link-icon flaticon-info"></i>
																		<span class="m-nav__link-text">FAQ</span>
																	</a>
																</li>
																<li class="m-nav__item">
																	<a href="header/profile.html" class="m-nav__link">
																		<i class="m-nav__link-icon flaticon-lifebuoy"></i>
																		<span class="m-nav__link-text">Support</span>
																	</a>
																</li>
																<li class="m-nav__separator m-nav__separator--fit">
																</li>
																<li class="m-nav__item">
																	<a href="snippets/pages/user/login-1.html" class="btn m-btn--pill    btn-secondary m-btn m-btn--custom m-btn--label-brand m-btn--bolder">Logout</a>
																</li>
															</ul>
														</div>
													</div>
												</div>
											</div>
										</li>
										<li id="m_quick_sidebar_toggle" class="m-nav__item">
											<a href="#" class="m-nav__link m-dropdown__toggle">
												<span class="m-nav__link-icon"><i class="flaticon-grid-menu"></i></span>
											</a>
										</li>
									</ul>
								</div>
							</div>

							<!-- END: Topbar -->
						</div>
					</div>
				</div>
			</header>

			<!-- END: Header -->

			<!-- begin::Body -->
			<div class="m-grid__item m-grid__item--fluid m-grid m-grid--ver-desktop m-grid--desktop m-body">

				<!-- BEGIN: Left Aside -->
				<button class="m-aside-left-close  m-aside-left-close--skin-dark " id="m_aside_left_close_btn"><i class="la la-close"></i></button>
				<div id="m_aside_left" class="m-grid__item	m-aside-left  m-aside-left--skin-dark ">

					<!-- BEGIN: Aside Menu -->
					<div id="m_ver_menu" class="m-aside-menu  m-aside-menu--skin-dark m-aside-menu--submenu-skin-dark " m-menu-vertical="1" m-menu-scrollable="1" m-menu-dropdown-timeout="500" style="position: relative;">
						<ul class="m-menu__nav  m-menu__nav--dropdown-submenu-arrow ">
						  <li class="m-menu__item " aria-haspopup="true"><a href="index.html" class="m-menu__link "><em class="m-menu__link-icon flaticon-line-graph"></em><span class="m-menu__link-title"> <span class="m-menu__link-wrap"> <span class="m-menu__link-text">Dashboard</span> <span class="m-menu__link-badge"></span></span></span></a></li>
						  <li class="m-menu__section ">
								<h4 class="m-menu__section-text">MAIN</h4>
								<i class="m-menu__section-icon flaticon-more-v2"></i>
							</li>
							<li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" m-menu-submenu-toggle="hover"><a href="javascript:;" class="m-menu__link m-menu__toggle"><i class="m-menu__link-icon flaticon-layers"></i><span class="m-menu__link-text">Products</span><i
									 class="m-menu__ver-arrow la la-angle-right"></i></a>
								<div class="m-menu__submenu "><span class="m-menu__arrow"></span>
									<ul class="m-menu__subnav">
										<li class="m-menu__item  m-menu__item--parent" aria-haspopup="true"><span class="m-menu__link"><span class="m-menu__link-text">Products</span></span></li>
										<li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" m-menu-submenu-toggle="hover"><a href="javascript:;" class="m-menu__link m-menu__toggle"><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span
												 class="m-menu__link-text">Product List</span><i class="m-menu__ver-arrow la la-angle-right"></i></a>
											<div class="m-menu__submenu "><span class="m-menu__arrow"></span>
												<ul class="m-menu__subnav">
													<li class="m-menu__item " aria-haspopup="true"><a href="#" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">EBook/Audiobook</span></a></li>
													<li class="m-menu__item " aria-haspopup="true"><a href="#" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">Videos</span></a></li>
													<li class="m-menu__item " aria-haspopup="true"><a href="#" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">Games</span></a></li>
													<li class="m-menu__item " aria-haspopup="true"><a href="#" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">HTML5</span></a></li>
													<li class="m-menu__item " aria-haspopup="true"><a href="#" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">Applications</span></a></li>
												</ul>
											</div>
										</li>
										<li class="m-menu__item " aria-haspopup="true"><a href="add_service.html" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">Add New Product</span></a></li>
									</ul>
								</div>
							</li>
							<li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" m-menu-submenu-toggle="hover"><a href="javascript:;" class="m-menu__link m-menu__toggle"><i class="m-menu__link-icon flaticon-share"></i><span class="m-menu__link-text">Content</span><i
									 class="m-menu__ver-arrow la la-angle-right"></i></a>
								<div class="m-menu__submenu "><span class="m-menu__arrow"></span>
									<ul class="m-menu__subnav">
										<li class="m-menu__item " aria-haspopup="true"><a href="content_master_add.html" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">Add Content</span></a></li>
										<li class="m-menu__item " aria-haspopup="true"><a href="content_category_master.html" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">Add Category</span></a></li>
										<li class="m-menu__item " aria-haspopup="true"><a href="content_master.html" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">List Content</span></a></li>
										<li class="m-menu__item " aria-haspopup="true"><a href="review_content.html" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">Review</span></a></li>
										<li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" m-menu-submenu-toggle="hover"><a href="javascript:;" class="m-menu__link m-menu__toggle"><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span
												 class="m-menu__link-text">Banner</span><i class="m-menu__ver-arrow la la-angle-right"></i></a>
											<div class="m-menu__submenu "><span class="m-menu__arrow"></span>
												<ul class="m-menu__subnav">
													<li class="m-menu__item" aria-haspopup="true"><a href="add_banner.html" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">Add Banner</span></a></li>
													<li class="m-menu__item" aria-haspopup="true"><a href="list_banner.html" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">List Banner</span></a></li>
												</ul>
											</div>
										</li>									
									</ul>
								</div>
							</li>
							<li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" m-menu-submenu-toggle="hover"><a href="javascript:;" class="m-menu__link m-menu__toggle"><i class="m-menu__link-icon flaticon-web"></i><span class="m-menu__link-text">Campaigns</span><i
									 class="m-menu__ver-arrow la la-angle-right"></i></a>
								<div class="m-menu__submenu "><span class="m-menu__arrow"></span>
									<ul class="m-menu__subnav">
										<li class="m-menu__item " aria-haspopup="true"><a href="#" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">Create Campaign</span></a></li>
										<li class="m-menu__item " aria-haspopup="true"><a href="#" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">View Campaigns</span></a></li>
									</ul>
								</div>
							</li>
							<li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" m-menu-submenu-toggle="hover"><a href="javascript:;" class="m-menu__link m-menu__toggle"><i class="m-menu__link-icon flaticon-interface-1"></i><span class="m-menu__link-text">Subscriber</span><i
									 class="m-menu__ver-arrow la la-angle-right"></i></a>
								<div class="m-menu__submenu "><span class="m-menu__arrow"></span>
									<ul class="m-menu__subnav">
										<li class="m-menu__item " aria-haspopup="true"><a href="#" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">Manage Subcriber</span></a></li>
										<li class="m-menu__item " aria-haspopup="true"><a href="#" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">Blacklist Subcriber</span></a></li>
									</ul>
								</div>
							</li>
							<li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" m-menu-submenu-toggle="hover"><a href="javascript:;" class="m-menu__link m-menu__toggle"><i class="m-menu__link-icon flaticon-interface-6"></i><span class="m-menu__link-text">Billing</span><i
									 class="m-menu__ver-arrow la la-angle-right"></i></a>
								<div class="m-menu__submenu "><span class="m-menu__arrow"></span>
									<ul class="m-menu__subnav">
										<li class="m-menu__item " aria-haspopup="true"><a href="#" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">Subscription Plan</span></a></li>
									</ul>
								</div>
							</li>
							<li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" m-menu-submenu-toggle="hover"><a href="javascript:;" class="m-menu__link m-menu__toggle"><i class="m-menu__link-icon flaticon-users"></i><span class="m-menu__link-text">User</span><i
									 class="m-menu__ver-arrow la la-angle-right"></i></a>
								<div class="m-menu__submenu "><span class="m-menu__arrow"></span>
									<ul class="m-menu__subnav">
										<li class="m-menu__item  m-menu__item--parent" aria-haspopup="true"><span class="m-menu__link"><span class="m-menu__link-text">User</span></span></li>
										<li class="m-menu__item " aria-haspopup="true"><a href="add_user.html" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">Add User</span></a></li>
										<li class="m-menu__item " aria-haspopup="true"><a href="list_user.html" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">List User</span></a></li>
										<li class="m-menu__item " aria-haspopup="true"><a href="user_access.html" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">User Access</span></a></li>
										<li class="m-menu__item " aria-haspopup="true"><a href="add_user_menu_mapping.html" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">Privileges</span></a></li>
										<li class="m-menu__item " aria-haspopup="true"><a href="#" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">View User Activity</span></a></li>
									</ul>
								</div>
							</li>
							<li class="m-menu__section ">
								<h4 class="m-menu__section-text">SETTINGS</h4>
								<i class="m-menu__section-icon flaticon-more-v2"></i>
							</li>
							<li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" m-menu-submenu-toggle="hover"><a href="javascript:;" class="m-menu__link m-menu__toggle"><i class="m-menu__link-icon flaticon-interface-7"></i><span class="m-menu__link-text">Customize </span><i class="m-menu__ver-arrow la la-angle-right"></i></a>
								<div class="m-menu__submenu "><span class="m-menu__arrow"></span>
									<ul class="m-menu__subnav">
										<li class="m-menu__item  m-menu__item--submenu m-menu__item--open m-menu__item--expanded" aria-haspopup="true" m-menu-submenu-toggle="hover"><a href="javascript:;" class="m-menu__link m-menu__toggle"><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span
												 class="m-menu__link-text">Menu</span><i class="m-menu__ver-arrow la la-angle-right"></i></a>
											<div class="m-menu__submenu "><span class="m-menu__arrow"></span>
												<ul class="m-menu__subnav">
													<li class="m-menu__item " aria-haspopup="true"><a href="list_menu.html" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">Menu Master</span></a></li>
													<li class="m-menu__item" aria-haspopup="true"><a href="add_menu.html" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">Add Menu Master</span></a></li>
													<li class="m-menu__item " aria-haspopup="true"><a href="list_menu_child.html" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">Menu Child</span></a></li>
													<li class="m-menu__item " aria-haspopup="true"><a href="add_menu_child.html" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">Add Menu Child</span></a></li>
												</ul>
											</div>
										</li>
										<li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" m-menu-submenu-toggle="hover"><a href="javascript:;" class="m-menu__link m-menu__toggle"><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span
												 class="m-menu__link-text">Portals</span><i class="m-menu__ver-arrow la la-angle-right"></i></a>
											<div class="m-menu__submenu "><span class="m-menu__arrow"></span>
												<ul class="m-menu__subnav">
													<li class="m-menu__item " aria-haspopup="true"><a href="add_list_content_portal.html" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">Add Content Portal</span></a></li>
													<li class="m-menu__item " aria-haspopup="true"><a href="portal_category_mapp.html" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">View Content Portal</span></a></li>
												</ul>
											</div>
										</li>
										<li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" m-menu-submenu-toggle="hover"><a href="javascript:;" class="m-menu__link m-menu__toggle"><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span
												 class="m-menu__link-text">Handset Profile</span><i class="m-menu__ver-arrow la la-angle-right"></i></a> 		<div class="m-menu__submenu "><span class="m-menu__arrow"></span>
												<ul class="m-menu__subnav">
													<li class="m-menu__item " aria-haspopup="true"><a href="list_handset_profile.html" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">Profile List</span></a></li>
													<li class="m-menu__item " aria-haspopup="true"><a href="add_handset.html" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">Add New Profile</span></a></li>
												</ul>
											</div>
										</li>
									</ul>
								</div>
							</li>
							<li class="m-menu__section ">
								<h4 class="m-menu__section-text">SUPPORT & HELP</h4>
								<i class="m-menu__section-icon flaticon-more-v2"></i>
							</li>
							<li class="m-menu__item  m-menu__item--submenu m-menu__item--open m-menu__item--expanded" aria-haspopup="true" m-menu-submenu-toggle="hover"><a href="#" class="m-menu__link m-menu__toggle"><i class="m-menu__link-icon flaticon-interface-9"></i><span class="m-menu__link-text">FAQS</span></i></a>
                                      <div class="m-menu__submenu "><span class="m-menu__arrow"></span>
												<ul class="m-menu__subnav">
													<li class="m-menu__item " aria-haspopup="true"><a href="faq_list.html" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">Faqs</span><i class="m-menu__ver-arrow la la-angle-right"></i></a>
													 <li class="m-menu__item m-menu__item--active" aria-haspopup="true"><a href="faq_add.html" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">Add Faqs</span></a></li>
												</ul>
											</div>							
							            </li>
							<li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" m-menu-submenu-toggle="hover"><a href="#" class="m-menu__link m-menu__toggle"><i class="m-menu__link-icon flaticon-piggy-bank"></i><span class="m-menu__link-text">Support</span><i class="m-menu__ver-arrow la la-angle-right"></i></a>
							 <div class="m-menu__submenu "><span class="m-menu__arrow"></span>
												<ul class="m-menu__subnav">
													<li class="m-menu__item " aria-haspopup="true"><a href="mail_inbox.html" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">Inbox</span></a></li>
													 <li class="m-menu__item " aria-haspopup="true"><a href="email_compose.html" class="m-menu__link "><i class="m-menu__link-bullet m-menu__link-bullet--dot"><span></span></i><span class="m-menu__link-text">New Email</span></a></li>

												</ul>
											</div>	
							          </li>
							
							
						</ul>
					</div>

					<!-- END: Aside Menu -->
				</div>

				<!-- END: Left Aside -->
				
				<div class="m-grid__item m-grid__item--fluid m-wrapper">

					<!-- BEGIN: Subheader -->
					<div class="m-subheader ">
						<div class="d-flex align-items-center">
							<div class="mr-auto">
								<h3 class="m-subheader__title m-subheader__title--separator">Add Feature</h3>
								<ul class="m-subheader__breadcrumbs m-nav m-nav--inline">
									<li class="m-nav__item m-nav__item--home">
										<a href="#" class="m-nav__link m-nav__link--icon">
										
										</a>
									</li>
									
								</ul>
							</div>
							<div>
								<div class="m-dropdown m-dropdown--inline m-dropdown--arrow m-dropdown--align-right m-dropdown--align-push" m-dropdown-toggle="hover" aria-expanded="true">
									<a href="#" class="m-portlet__nav-link btn btn-lg btn-secondary  m-btn m-btn--outline-2x m-btn--air m-btn--icon m-btn--icon-only m-btn--pill  m-dropdown__toggle">
										<i class="la la-plus m--hide"></i>
										<i class="la la-ellipsis-h"></i>
									</a>
									<div class="m-dropdown__wrapper">
										<span class="m-dropdown__arrow m-dropdown__arrow--right m-dropdown__arrow--adjust"></span>
										<div class="m-dropdown__inner">
											<div class="m-dropdown__body">
												<div class="m-dropdown__content">
													<ul class="m-nav">
														<li class="m-nav__section m-nav__section--first m--hide">
															<span class="m-nav__section-text">Quick Actions</span>
														</li>
														<li class="m-nav__item">
															<a href="" class="m-nav__link">
																<i class="m-nav__link-icon flaticon-share"></i>
																<span class="m-nav__link-text">Activity</span>
															</a>
														</li>
														<li class="m-nav__item">
															<a href="" class="m-nav__link">
																<i class="m-nav__link-icon flaticon-chat-1"></i>
																<span class="m-nav__link-text">Messages</span>
															</a>
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
														<li class="m-nav__separator m-nav__separator--fit">
														</li>
														<li class="m-nav__item">
															<a href="#" class="btn btn-outline-danger m-btn m-btn--pill m-btn--wide btn-sm">Submit</a>
														</li>
													</ul>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- BEGIN: Subheader -->
					<!-- END: Subheader -->
					<div class="m-content">
						<!--begin::Portlet-->
						<div class="m-portlet">
							<!--begin::Form-->
                                                        <form class="m-form m-form--fit m-form--label-align-right" action="add_feat.htm" method="post">
								<div class="m-portlet__body">
									<div class="form-group m-form__group row">
										<label class="col-form-label col-lg-3 col-sm-12 offset-1">
										
											<div class="alert alert-info m--margin-top-10">
																 Portal
																</div>
											<div class="m-form__group form-group">
												
																
																<div class="m-radio-list">
                                                                                                                                    <%
                                                                                                                                        for(int count=0; count<allContentPortalMasterObjectList.size(); ++count)
                                                                                                                                        {

                                                                                                                                        %>
																		<label class="m-radio">
																		<input type="radio" name="portal_id" value="<%= allContentPortalMasterObjectList.get(count)[0] %>">
																		<%= allContentPortalMasterObjectList.get(count)[1] %>
																		<span></span>
																	</label>
																		<%
                                                                                                                                                
                                                                                                                                        }
                                                                                                                                        %>
<!--																		<label class="m-radio">
																		<input type="radio" name="example_1" value="1">
																		PlayCode
																		<span></span>
																	</label>
																		
																		<label class="m-radio">
																		<input type="radio" name="example_1" value="1">
																		BoxAfrica
																		<span></span>
																	</label>
																		
																		<label class="m-radio">
																		<input type="radio" name="example_1" value="1">
																		Musik365
																		<span></span>
																	</label>-->
																</div>
															</div>
															<div class="m-portlet__foot m-portlet__foot--fit">
									
								</div>
											
										</label>
										
										<div class="col-form-label col-lg-8 col-sm-12 m--margin-top-10 ">
										   
                                                                                    <input type="text" class="form-control m-input m-input--square" id="price" name="ftext" placeholder="Input Feature" required>
											<br>
											<br>
											<br>
											<br>
											<br>
									
											<div class="m-form__actions m-form__actions">
										<div class="row">
											<div class="col-lg-9 ml-lg-auto">
												<button type="submit" class="btn btn-brand">
													Create
												</button>
												<button type="reset" class="btn btn-secondary">
													Cancel
												</button>
											</div>
										</div>
									</div>
											</div>
											
											
											</label>
									</div>
									</div>
								</div>
								
							</form>
							<!--end::Form-->
						</div>
						<!--end::Portlet-->

				</div>
			</div>
			<!-- end:: Body -->
<!-- begin::Footer -->
			<footer class="m-grid__item		m-footer ">
				<div class="m-container m-container--fluid m-container--full-height m-page__container">
					<div class="m-stack m-stack--flex-tablet-and-mobile m-stack--ver m-stack--desktop">
						<div class="m-stack__item m-stack__item--left m-stack__item--middle m-stack__item--last">
							<span class="m-footer__copyright">
								2019 &copy; 
								<a href="https://buffer.media" class="m-link">
									Buffermedia
								</a>
							</span>
						</div>
						<div class="m-stack__item m-stack__item--right m-stack__item--middle m-stack__item--first">
							<ul class="m-footer__nav m-nav m-nav--inline m--pull-right">
								<li class="m-nav__item">
									<a href="#" class="m-nav__link">
										<span class="m-nav__link-text">
											About
										</span>
									</a>
								</li>
								<li class="m-nav__item">
									<a href="#"  class="m-nav__link">
										<span class="m-nav__link-text">
											Privacy
										</span>
									</a>
								</li>
								<li class="m-nav__item">
									<a href="#" class="m-nav__link">
										<span class="m-nav__link-text">
											T&C
										</span>
									</a>
								</li>
								<li class="m-nav__item">
									<a href="#" class="m-nav__link">
										<span class="m-nav__link-text">
											Purchase
										</span>
									</a>
								</li>
								<li class="m-nav__item m-nav__item">
									<a href="#" class="m-nav__link" data-toggle="m-tooltip" title="Support Center" data-placement="left">
										<i class="m-nav__link-icon flaticon-info m--icon-font-size-lg3"></i>
									</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</footer>
			<!-- end::Footer -->
		</div>
		<!-- end:: Page -->
    		        <!-- begin::Quick Sidebar -->
		<div id="m_quick_sidebar" class="m-quick-sidebar m-quick-sidebar--tabbed m-quick-sidebar--skin-light">
			<div class="m-quick-sidebar__content m--hide">
				<span id="m_quick_sidebar_close" class="m-quick-sidebar__close">
					<i class="la la-close"></i>
				</span>
				<ul id="m_quick_sidebar_tabs" class="nav nav-tabs m-tabs m-tabs-line m-tabs-line--brand" role="tablist">
					<li class="nav-item m-tabs__item">
						<a class="nav-link m-tabs__link active" data-toggle="tab" href="#m_quick_sidebar_tabs_messenger" role="tab">
							Messages
						</a>
					</li>
					<li class="nav-item m-tabs__item">
						<a class="nav-link m-tabs__link" 		data-toggle="tab" href="#m_quick_sidebar_tabs_settings" role="tab">
							Settings
						</a>
					</li>
					<li class="nav-item m-tabs__item">
						<a class="nav-link m-tabs__link" data-toggle="tab" href="#m_quick_sidebar_tabs_logs" role="tab">
							Logs
						</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active m-scrollable" id="m_quick_sidebar_tabs_messenger" role="tabpanel">
						<div class="m-messenger m-messenger--message-arrow m-messenger--skin-light">
							<div class="m-messenger__messages">
								<div class="m-messenger__wrapper">
									<div class="m-messenger__message m-messenger__message--in">
										<div class="m-messenger__message-pic">
											<img src="../../../assets/app/media/img//users/user3.jpg" alt=""/>
										</div>
										<div class="m-messenger__message-body">
											<div class="m-messenger__message-arrow"></div>
											<div class="m-messenger__message-content">
												<div class="m-messenger__message-username">
													Megan wrote
												</div>
												<div class="m-messenger__message-text">
													Hi Bob. What time will be the meeting ?
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="m-messenger__wrapper">
									<div class="m-messenger__message m-messenger__message--out">
										<div class="m-messenger__message-body">
											<div class="m-messenger__message-arrow"></div>
											<div class="m-messenger__message-content">
												<div class="m-messenger__message-text">
													Hi Megan. It's at 2.30PM
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="m-messenger__wrapper">
									<div class="m-messenger__message m-messenger__message--in">
										<div class="m-messenger__message-pic">
											<img src="../../../assets/app/media/img//users/user3.jpg" alt=""/>
										</div>
										<div class="m-messenger__message-body">
											<div class="m-messenger__message-arrow"></div>
											<div class="m-messenger__message-content">
												<div class="m-messenger__message-username">
													Megan wrote
												</div>
												<div class="m-messenger__message-text">
													Will the development team be joining ?
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="m-messenger__wrapper">
									<div class="m-messenger__message m-messenger__message--out">
										<div class="m-messenger__message-body">
											<div class="m-messenger__message-arrow"></div>
											<div class="m-messenger__message-content">
												<div class="m-messenger__message-text">
													Yes sure. I invited them as well
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="m-messenger__datetime">
									2:30PM
								</div>
								<div class="m-messenger__wrapper">
									<div class="m-messenger__message m-messenger__message--in">
										<div class="m-messenger__message-pic">
											<img src="../../../assets/app/media/img//users/user3.jpg"  alt=""/>
										</div>
										<div class="m-messenger__message-body">
											<div class="m-messenger__message-arrow"></div>
											<div class="m-messenger__message-content">
												<div class="m-messenger__message-username">
													Megan wrote
												</div>
												<div class="m-messenger__message-text">
													Noted. For the Coca-Cola Mobile App project as well ?
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="m-messenger__wrapper">
									<div class="m-messenger__message m-messenger__message--out">
										<div class="m-messenger__message-body">
											<div class="m-messenger__message-arrow"></div>
											<div class="m-messenger__message-content">
												<div class="m-messenger__message-text">
													Yes, sure.
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="m-messenger__wrapper">
									<div class="m-messenger__message m-messenger__message--out">
										<div class="m-messenger__message-body">
											<div class="m-messenger__message-arrow"></div>
											<div class="m-messenger__message-content">
												<div class="m-messenger__message-text">
													Please also prepare the quotation for the Loop CRM project as well.
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="m-messenger__datetime">
									3:15PM
								</div>
								<div class="m-messenger__wrapper">
									<div class="m-messenger__message m-messenger__message--in">
										<div class="m-messenger__message-no-pic m--bg-fill-danger">
											<span>
												M
											</span>
										</div>
										<div class="m-messenger__message-body">
											<div class="m-messenger__message-arrow"></div>
											<div class="m-messenger__message-content">
												<div class="m-messenger__message-username">
													Megan wrote
												</div>
												<div class="m-messenger__message-text">
													Noted. I will prepare it.
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="m-messenger__wrapper">
									<div class="m-messenger__message m-messenger__message--out">
										<div class="m-messenger__message-body">
											<div class="m-messenger__message-arrow"></div>
											<div class="m-messenger__message-content">
												<div class="m-messenger__message-text">
													Thanks Megan. I will see you later.
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="m-messenger__wrapper">
									<div class="m-messenger__message m-messenger__message--in">
										<div class="m-messenger__message-pic">
											<img src="../../../assets/app/media/img//users/user3.jpg"  alt=""/>
										</div>
										<div class="m-messenger__message-body">
											<div class="m-messenger__message-arrow"></div>
											<div class="m-messenger__message-content">
												<div class="m-messenger__message-username">
													Megan wrote
												</div>
												<div class="m-messenger__message-text">
													Sure. See you in the meeting soon.
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="m-messenger__seperator"></div>
							<div class="m-messenger__form">
								<div class="m-messenger__form-controls">
									<input type="text" name="" placeholder="Type here..." class="m-messenger__form-input">
								</div>
								<div class="m-messenger__form-tools">
									<a href="" class="m-messenger__form-attachment">
										<i class="la la-paperclip"></i>
									</a>
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane  m-scrollable" id="m_quick_sidebar_tabs_settings" role="tabpanel">
						<div class="m-list-settings">
							<div class="m-list-settings__group">
								<div class="m-list-settings__heading">
									General Settings
								</div>
								<div class="m-list-settings__item">
									<span class="m-list-settings__item-label">
										Email Notifications
									</span>
									<span class="m-list-settings__item-control">
										<span class="m-switch m-switch--outline m-switch--icon-check m-switch--brand">
											<label>
												<input type="checkbox" checked="checked" name="">
												<span></span>
											</label>
										</span>
									</span>
								</div>
								<div class="m-list-settings__item">
									<span class="m-list-settings__item-label">
										Site Tracking
									</span>
									<span class="m-list-settings__item-control">
										<span class="m-switch m-switch--outline m-switch--icon-check m-switch--brand">
											<label>
												<input type="checkbox" name="">
												<span></span>
											</label>
										</span>
									</span>
								</div>
								<div class="m-list-settings__item">
									<span class="m-list-settings__item-label">
										SMS Alerts
									</span>
									<span class="m-list-settings__item-control">
										<span class="m-switch m-switch--outline m-switch--icon-check m-switch--brand">
											<label>
												<input type="checkbox" name="">
												<span></span>
											</label>
										</span>
									</span>
								</div>
								<div class="m-list-settings__item">
									<span class="m-list-settings__item-label">
										Backup Storage
									</span>
									<span class="m-list-settings__item-control">
										<span class="m-switch m-switch--outline m-switch--icon-check m-switch--brand">
											<label>
												<input type="checkbox" name="">
												<span></span>
											</label>
										</span>
									</span>
								</div>
								<div class="m-list-settings__item">
									<span class="m-list-settings__item-label">
										Audit Logs
									</span>
									<span class="m-list-settings__item-control">
										<span class="m-switch m-switch--outline m-switch--icon-check m-switch--brand">
											<label>
												<input type="checkbox" checked="checked" name="">
												<span></span>
											</label>
										</span>
									</span>
								</div>
							</div>
							<div class="m-list-settings__group">
								<div class="m-list-settings__heading">
									System Settings
								</div>
								<div class="m-list-settings__item">
									<span class="m-list-settings__item-label">
										System Logs
									</span>
									<span class="m-list-settings__item-control">
										<span class="m-switch m-switch--outline m-switch--icon-check m-switch--brand">
											<label>
												<input type="checkbox" name="">
												<span></span>
											</label>
										</span>
									</span>
								</div>
								<div class="m-list-settings__item">
									<span class="m-list-settings__item-label">
										Error Reporting
									</span>
									<span class="m-list-settings__item-control">
										<span class="m-switch m-switch--outline m-switch--icon-check m-switch--brand">
											<label>
												<input type="checkbox" name="">
												<span></span>
											</label>
										</span>
									</span>
								</div>
								<div class="m-list-settings__item">
									<span class="m-list-settings__item-label">
										Applications Logs
									</span>
									<span class="m-list-settings__item-control">
										<span class="m-switch m-switch--outline m-switch--icon-check m-switch--brand">
											<label>
												<input type="checkbox" name="">
												<span></span>
											</label>
										</span>
									</span>
								</div>
								<div class="m-list-settings__item">
									<span class="m-list-settings__item-label">
										Backup Servers
									</span>
									<span class="m-list-settings__item-control">
										<span class="m-switch m-switch--outline m-switch--icon-check m-switch--brand">
											<label>
												<input type="checkbox" checked="checked" name="">
												<span></span>
											</label>
										</span>
									</span>
								</div>
								<div class="m-list-settings__item">
									<span class="m-list-settings__item-label">
										Audit Logs
									</span>
									<span class="m-list-settings__item-control">
										<span class="m-switch m-switch--outline m-switch--icon-check m-switch--brand">
											<label>
												<input type="checkbox" name="">
												<span></span>
											</label>
										</span>
									</span>
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane  m-scrollable" id="m_quick_sidebar_tabs_logs" role="tabpanel">
						<div class="m-list-timeline">
							<div class="m-list-timeline__group">
								<div class="m-list-timeline__heading">
									System Logs
								</div>
								<div class="m-list-timeline__items">
									<div class="m-list-timeline__item">
										<span class="m-list-timeline__badge m-list-timeline__badge--state-success"></span>
										<a href="" class="m-list-timeline__text">
											12 new users registered
											<span class="m-badge m-badge--warning m-badge--wide">
												important
											</span>
										</a>
										<span class="m-list-timeline__time">
											Just now
										</span>
									</div>
									<div class="m-list-timeline__item">
										<span class="m-list-timeline__badge m-list-timeline__badge--state-info"></span>
										<a href="" class="m-list-timeline__text">
											System shutdown
										</a>
										<span class="m-list-timeline__time">
											11 mins
										</span>
									</div>
									<div class="m-list-timeline__item">
										<span class="m-list-timeline__badge m-list-timeline__badge--state-danger"></span>
										<a href="" class="m-list-timeline__text">
											New invoice received
										</a>
										<span class="m-list-timeline__time">
											20 mins
										</span>
									</div>
									<div class="m-list-timeline__item">
										<span class="m-list-timeline__badge m-list-timeline__badge--state-warning"></span>
										<a href="" class="m-list-timeline__text">
											Database overloaded 89%
											<span class="m-badge m-badge--success m-badge--wide">
												resolved
											</span>
										</a>
										<span class="m-list-timeline__time">
											1 hr
										</span>
									</div>
									<div class="m-list-timeline__item">
										<span class="m-list-timeline__badge m-list-timeline__badge--state-success"></span>
										<a href="" class="m-list-timeline__text">
											System error
										</a>
										<span class="m-list-timeline__time">
											2 hrs
										</span>
									</div>
									<div class="m-list-timeline__item">
										<span class="m-list-timeline__badge m-list-timeline__badge--state-info"></span>
										<a href="" class="m-list-timeline__text">
											Production server down
											<span class="m-badge m-badge--danger m-badge--wide">
												pending
											</span>
										</a>
										<span class="m-list-timeline__time">
											3 hrs
										</span>
									</div>
									<div class="m-list-timeline__item">
										<span class="m-list-timeline__badge m-list-timeline__badge--state-success"></span>
										<a href="" class="m-list-timeline__text">
											Production server up
										</a>
										<span class="m-list-timeline__time">
											5 hrs
										</span>
									</div>
								</div>
							</div>
							<div class="m-list-timeline__group">
								<div class="m-list-timeline__heading">
									Applications Logs
								</div>
								<div class="m-list-timeline__items">
									<div class="m-list-timeline__item">
										<span class="m-list-timeline__badge m-list-timeline__badge--state-info"></span>
										<a href="" class="m-list-timeline__text">
											New order received
											<span class="m-badge m-badge--info m-badge--wide">
												urgent
											</span>
										</a>
										<span class="m-list-timeline__time">
											7 hrs
										</span>
									</div>
									<div class="m-list-timeline__item">
										<span class="m-list-timeline__badge m-list-timeline__badge--state-success"></span>
										<a href="" class="m-list-timeline__text">
											12 new users registered
										</a>
										<span class="m-list-timeline__time">
											Just now
										</span>
									</div>
									<div class="m-list-timeline__item">
										<span class="m-list-timeline__badge m-list-timeline__badge--state-info"></span>
										<a href="" class="m-list-timeline__text">
											System shutdown
										</a>
										<span class="m-list-timeline__time">
											11 mins
										</span>
									</div>
									<div class="m-list-timeline__item">
										<span class="m-list-timeline__badge m-list-timeline__badge--state-danger"></span>
										<a href="" class="m-list-timeline__text">
											New invoices received
										</a>
										<span class="m-list-timeline__time">
											20 mins
										</span>
									</div>
									<div class="m-list-timeline__item">
										<span class="m-list-timeline__badge m-list-timeline__badge--state-warning"></span>
										<a href="" class="m-list-timeline__text">
											Database overloaded 89%
										</a>
										<span class="m-list-timeline__time">
											1 hr
										</span>
									</div>
									<div class="m-list-timeline__item">
										<span class="m-list-timeline__badge m-list-timeline__badge--state-success"></span>
										<a href="" class="m-list-timeline__text">
											System error
											<span class="m-badge m-badge--info m-badge--wide">
												pending
											</span>
										</a>
										<span class="m-list-timeline__time">
											2 hrs
										</span>
									</div>
									<div class="m-list-timeline__item">
										<span class="m-list-timeline__badge m-list-timeline__badge--state-info"></span>
										<a href="" class="m-list-timeline__text">
											Production server down
										</a>
										<span class="m-list-timeline__time">
											3 hrs
										</span>
									</div>
								</div>
							</div>
							<div class="m-list-timeline__group">
								<div class="m-list-timeline__heading">
									Server Logs
								</div>
								<div class="m-list-timeline__items">
									<div class="m-list-timeline__item">
										<span class="m-list-timeline__badge m-list-timeline__badge--state-success"></span>
										<a href="" class="m-list-timeline__text">
											Production server up
										</a>
										<span class="m-list-timeline__time">
											5 hrs
										</span>
									</div>
									<div class="m-list-timeline__item">
										<span class="m-list-timeline__badge m-list-timeline__badge--state-info"></span>
										<a href="" class="m-list-timeline__text">
											New order received
										</a>
										<span class="m-list-timeline__time">
											7 hrs
										</span>
									</div>
									<div class="m-list-timeline__item">
										<span class="m-list-timeline__badge m-list-timeline__badge--state-success"></span>
										<a href="" class="m-list-timeline__text">
											12 new users registered
										</a>
										<span class="m-list-timeline__time">
											Just now
										</span>
									</div>
									<div class="m-list-timeline__item">
										<span class="m-list-timeline__badge m-list-timeline__badge--state-info"></span>
										<a href="" class="m-list-timeline__text">
											System shutdown
										</a>
										<span class="m-list-timeline__time">
											11 mins
										</span>
									</div>
									<div class="m-list-timeline__item">
										<span class="m-list-timeline__badge m-list-timeline__badge--state-danger"></span>
										<a href="" class="m-list-timeline__text">
											New invoice received
										</a>
										<span class="m-list-timeline__time">
											20 mins
										</span>
									</div>
									<div class="m-list-timeline__item">
										<span class="m-list-timeline__badge m-list-timeline__badge--state-warning"></span>
										<a href="" class="m-list-timeline__text">
											Database overloaded 89%
										</a>
										<span class="m-list-timeline__time">
											1 hr
										</span>
									</div>
									<div class="m-list-timeline__item">
										<span class="m-list-timeline__badge m-list-timeline__badge--state-success"></span>
										<a href="" class="m-list-timeline__text">
											System error
										</a>
										<span class="m-list-timeline__time">
											2 hrs
										</span>
									</div>
									<div class="m-list-timeline__item">
										<span class="m-list-timeline__badge m-list-timeline__badge--state-info"></span>
										<a href="" class="m-list-timeline__text">
											Production server down
										</a>
										<span class="m-list-timeline__time">
											3 hrs
										</span>
									</div>
									<div class="m-list-timeline__item">
										<span class="m-list-timeline__badge m-list-timeline__badge--state-success"></span>
										<a href="" class="m-list-timeline__text">
											Production server up
										</a>
										<span class="m-list-timeline__time">
											5 hrs
										</span>
									</div>
									<div class="m-list-timeline__item">
										<span class="m-list-timeline__badge m-list-timeline__badge--state-info"></span>
										<a href="" class="m-list-timeline__text">
											New order received
										</a>
										<span class="m-list-timeline__time">
											1117 hrs
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- end::Quick Sidebar -->		    
	    <!-- begin::Scroll Top -->
		<div id="m_scroll_top" class="m-scroll-top">
			<i class="la la-arrow-up"></i>
		</div>
		<!-- end::Scroll Top -->		    <!-- begin::Quick Nav -->
		<ul class="m-nav-sticky" style="margin-top: 30px;">
			<!--
			<li class="m-nav-sticky__item" data-toggle="m-tooltip" title="Showcase" data-placement="left">
				<a href="">
					<i class="la la-eye"></i>
				</a>
			</li>
			<li class="m-nav-sticky__item" data-toggle="m-tooltip" title="Pre-sale Chat" data-placement="left">
				<a href="" >
					<i class="la la-comments-o"></i>
				</a>
			</li>
			-->
			<li class="m-nav-sticky__item" data-toggle="m-tooltip" title="Purchase" data-placement="left">
				<a href="https://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes" target="_blank">
					<i class="la la-cart-arrow-down"></i>
				</a>
			</li>
			<li class="m-nav-sticky__item" data-toggle="m-tooltip" title="Documentation" data-placement="left">
				<a href="https://keenthemes.com/metronic/documentation.html" target="_blank">
					<i class="la la-code-fork"></i>
				</a>
			</li>
			<li class="m-nav-sticky__item" data-toggle="m-tooltip" title="Support" data-placement="left">
				<a href="https://keenthemes.com/forums/forum/support/metronic5/" target="_blank">
					<i class="la la-life-ring"></i>
				</a>
			</li>
		</ul>
		<!-- begin::Quick Nav -->	
    	<!--begin::Base Scripts -->
		<script src="assets/vendors/base/vendors.bundle.js" type="text/javascript"></script>
		<script src="assets/demo/default/base/scripts.bundle.js" type="text/javascript"></script>
		<!--end::Base Scripts -->   
        <!--begin::Page Resources -->
		<script src="assets/demo/default/custom/components/forms/widgets/summernote.js" type="text/javascript"></script>
		<!--end::Page Resources -->
		<script src="assets/demo/default/custom/components/forms/widgets/bootstrap-datetimepicker.js" type="text/javascript"></script>
	</body>
			<!-- end::Body -->



</html>
<%
    }
    %>