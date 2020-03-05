<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
    
<!DOCTYPE html>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

  </head>
<body>


        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="userDashboard" class="site_title"><i class="fa fa-eye"></i> <span>Home</span></a>
            </div>

            <div class="clearfix"></div>

<!-- menu profile quick info -->
            <div class="profile clearfix">
              <div class="profile_pic">
                <!-- <img src="images/img.jpg" alt="..." class="img-circle profile_img"> -->
                
                <c:choose>

														<c:when test="${userBean.photoPath != null}">
												            <img alt="image not avail.." class="img-circle profile_img" src="images/${userBean.photoPath}">
												         </c:when>

														<c:otherwise>
												            <img alt="image not avail.." class="img-circle profile_img" src="images/new-user.jpg">
												         </c:otherwise>
												</c:choose>
                
              </div>
              <div class="profile_info">
                <span>Welcome,</span>
                <h2>${userBean.fullName}</h2>
              </div>
            </div>
            <!-- /menu profile quick info -->

            <br />

            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                <h3>General</h3>
                <ul class="nav side-menu">
                  <li><a href="userDashboard"><i class="fa fa-home"></i> Home </a></li>
                  <li><a href="bookRoom"><i class="fa fa-group"></i> Book a Room </a></li>
                  <li><a href="onGoingRequests"><i class="fa fa-pencil-square"></i> On Going Booking Requests  </a></li> 
                  <li><a href="rejectedRequests"><i class="fa fa-pencil-square"></i> Rejected Booking Requests</a></li>  
                  <li><a href="bookingHistory"><i class="fa fa-pencil-square"></i> Booking History </a></li>  
                  <li><a href="editPersonalDetails"><i class="fa fa-pencil-square"></i>Edit Personal Details </a></li> 
                  <li><a href="changePassword"><i class="fa fa-pencil-square"></i>Change Password! </a></li>  
                  
                </ul>
              </div>
              
            </div>
            <!-- /sidebar menu -->
            
            </div>
            </div>
       

    
 
</body>
</html>