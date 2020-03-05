<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hello Admin!</title>

 <jsp:include page="req_css.jsp" />

</head>
<body class="nav-md">

<div class="container body">
      <div class="main_container">

<jsp:include page="left-bar.jsp" />
<jsp:include page="header.jsp" /> 


 <div class="right_col" role="main" style="min-height: 958px;">
          <div>
            <div class="page-title">
              
                <h3>Welcome Admin</h3>
               
            <div class="clearfix"></div>

            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                 <!--  <div class="x_title">
                    <h2>Plain Page</h2>
                    
                    <div class="clearfix"></div>
                  </div> -->
                  <div class="x_content">
                  
                  
                  
                  				<div class="row">
                  				
                  				 <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
			                        <div class="tile-stats">
			                          <div class="icon"><i class="fa fa-check-square-o"></i>
			                          </div>
			                          <div class="count">${pendingBookingReqCount}</div>
			
			                          <h3><a href="manageBookingRequest?pageId=1">New Booking requests</a></h3>
			                          <p>${pendingBookingReqCount} new room book request(s) pending!</p>
			                        </div>
			                      </div>
			                      
			                      <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
			                        <div class="tile-stats">
			                          <div class="icon"><i class="fa fa-caret-square-o-right"></i>
			                          </div>
			                          <div class="count">${pendingUserReqCount}</div>
			
			                          <h3><a href="manageUserRequest?pageId=1">Pending User requests</a></h3>
			                          <p>${pendingUserReqCount} new user request(s) pending!</p>
			                        </div>
			                      </div>
			                      
			                      <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
			                        <div class="tile-stats">
			                          <div class="icon"><i class="fa fa-comments-o"></i>
			                          </div>
			                          <div class="count">${unreadFeedbacksCount}</div>
			
			                          <h3><a href="viewFeedBacks?pageId=1">New Feedbacks from Users</a></h3>
			                          <p>${unreadFeedbacksCount} unread feedbacks!</p>
			                        </div>
			                      </div>
			                      
			                      <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
			                        <div class="tile-stats">
			                          <div class="icon"><i class="fa fa-sort-amount-desc"></i>
			                          </div>
			                          <div class="count">*</div>
			
			                          <h3>Latest Allocation detail</h3>
			                          <p>Lorem ipsum psdea itgum rixt.</p>
			                        </div>
			                      </div>
			                      
			                      
			                    </div>
                  
                      
                     
                     
                     
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div> 

              
            </div>


</div>
</div>
 <jsp:include page="req_js_jQuery.jsp" />
</body>
</html>