<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
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
              <a href="adminDashboard" class="site_title"><i class="fa fa-eye"></i> <span>Home</span></a>
            </div>

            <div class="clearfix"></div>

<!-- menu profile quick info -->
            <div class="profile clearfix">
              <div class="profile_pic">
                <img src="images/img.jpg" alt="..." class="img-circle profile_img">
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
                  <li><a href="adminDashboard"><i class="fa fa-home"></i> Home </a></li>
                  <li><a href="manageRoom"><i class="fa fa-institution"></i> Manage Room(s) </a></li>
                  <li><a href="manageUserRequest?pageId=1"><i class="fa fa-group"></i> Manage User Requests </a></li>
                  <li><a href="viewAllUsers?pageId=1"><i class="fa fa-group"></i> Manage All Users </a></li>
                  <li><a href=""><i class="fa fa-group"></i> Report of Room Booking </a></li>
                  <li><a href="manageFacilities"><i class="fa fa-paper-plane"></i> Manage Facilities </a></li>
                  <li><a href="manageDepartments"><i class="fa fa-paper-plane"></i> Manage Departments </a></li>
                  <li><a href="manageBookingRequest?pageId=1"><i class="fa fa-institution"></i> Manage Booking Requests </a></li>
                 <!--  <li><a href=""><i class="fa fa-search"></i> View Past Request(s) </a></li> --> 
                 <!--  <li><a href=""><i class="fa fa-group"></i> Manage Team Leader(s) </a></li> --> 
                 <!--  <li><a href=""><i class="fa fa-pencil-square"></i> Manage Role(s) </a></li> -->
                 <li class="" id="liii"><a onclick="return theFunction1();"><i class="fa fa-sitemap"></i> Manage PM Req(s) <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu" id="ulll" style="display: none;" >
                        <li><a href="ViewChangeMailIdRequests">Request for Mail-id change</a>
                        </li>
                        <li><a href="ViewChangeDeptRequests">Request for Department change</a>
                        </li>
                    </ul>
                  </li>
                  <li class="" id="lii"><a onclick="return theFunction();"><i class="fa fa-sitemap"></i> Department wise Users <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu" id="ull" style="display: none;" >
                        <li><a href="">PM</a>
                        </li>
                        <li><a href="">TL</a>
                        </li>
                        <li><a href="">Developers </a>
                        </li>
                    </ul>
                  </li>
                 <li><a href="viewFeedBacks?pageId=1"><i class="fa fa-pencil-square"></i> View All Feedbacks  </a></li>  
                  <li><a href=""><i class="fa fa-pencil-square"></i> View Allocation Detail  </a></li>  
                  
                  
                </ul>
              </div>
              
            </div>
            <!-- /sidebar menu -->
            
            </div>
            </div>
       

<script type="text/javascript">
function theFunction() {

	//e.preventDefault();
	
   var element = document.getElementById("lii");
   element.classList.toggle("active");

   var ele = document.getElementById("ull");

	if(ele.style.display === 'none') {
		ele.style.display = 'block';
  } else {
	  ele.style.display = 'none';
  }
   
}
</script>
<script type="text/javascript">
function theFunction1() {

	//e.preventDefault();
	
   var element = document.getElementById("liii");
   element.classList.toggle("active");

   var ele = document.getElementById("ulll");

	if(ele.style.display === 'none') {
		ele.style.display = 'block';
  } else {
	  ele.style.display = 'none';
  }
   
}
</script>
 
</body>
</html>