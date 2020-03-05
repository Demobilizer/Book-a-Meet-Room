<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hello User!</title>

 <jsp:include page="req_css.jsp" />
 
 <style type="text/css">
 
 	ul.messages li .message_wrapper h4.heading1 {
    font-weight: 600;
    /* margin: 0 0 10px; */
    line-height: 100%;

 </style>

</head>
<body class="nav-md">

<div class="container body">
      <div class="main_container">

												<c:choose>
														<c:when test="${userBean.userRole == 'ROLE_USER' }">
												            <jsp:include page="left-bar-user.jsp" />
												         </c:when>
												         <c:when test="${userBean.userRole == 'ROLE_T' }">
												            <jsp:include page="left-bar-tl.jsp" />
												         </c:when>

														<c:otherwise>
												            <jsp:include page="left-bar-pm.jsp" />
												         </c:otherwise>
												</c:choose>

<jsp:include page="header.jsp" /> 

<!-- page content -->
 <div class="right_col" role="main" style="min-height: 958px;">
          <div>
            <div class="page-title">
              
                <h3>${userBean.fullName}</h3>
               
            <div class="clearfix"></div>

            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <!-- <div class="x_title">
                    <h2>Plain Page</h2>
                    
                    <div class="clearfix"></div>
                  </div> -->
                  <div class="x_content">
                     
                     <!-- CONTENT GOES HERE... -->
                     
                     <div class="x_content">
                    <div class="col-md-3 col-sm-3 col-xs-12 profile_left">
                      <div class="profile_img">
                        <div id="crop-avatar">
                          <!-- Current avatar -->
                          <!-- <img class="img-responsive avatar-view" src="images/new-user.jpg" alt="Avatar" title="Change the avatar"> -->
                          
                          					<c:choose>

														<c:when test="${userBean.photoPath != null}">
												            <img alt="image not avail.." class="img-responsive" src="images/${userBean.photoPath}">
												         </c:when>

														<c:otherwise>
												            <img alt="image not avail.." class="img-responsive" src="images/new-user.jpg">
												         </c:otherwise>
												</c:choose>
                          
                        </div>
                      </div>
                      <h3>${userBean.fullName}</h3>

                      <ul class="list-unstyled user_data">
                        <li><i class="fa fa-briefcase user-profile-icon"></i> &nbsp; ${userBean.department}
                        </li>

                        <li>
                          <i class="fa fa-paper-plane user-profile-icon"></i> &nbsp; ${userBean.emailId}
                        </li>

                        <li>
                          <i class="fa fa-phone-square user-profile-icon"></i>&nbsp;&nbsp;
                          ${userBean.contactNo}
                        </li>
                      </ul>

                      <a class="btn btn-success" href="editPersonalDetails"><i class="fa fa-edit m-right-xs"></i>Edit Profile</a>
                      <br>

                    </div>
                    <div class="col-md-9 col-sm-9 col-xs-12">

                      <div class="" role="tabpanel" data-example-id="togglable-tabs">
                        <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                          <li role="presentation" class="active"><a href="#tab_content1" id="home-tab" role="tab" 
                          data-toggle="tab" aria-expanded="true">Recent Booking Activity</a>
                          </li>
                          
                          <li role="presentation" class=""><a href="#tab_content3" role="tab" id="profile-tab2" 
                          data-toggle="tab" aria-expanded="false">Profile</a>
                          </li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                          <div role="tabpanel" class="tab-pane fade active in" id="tab_content1" aria-labelledby="home-tab">

                            <!-- start recent activity -->
                            
                            	<c:if test="${urbList == '[]'}">
                            			<div style="margin-left: 150px; margin-top: 80px;">
								              <h1 class="error-number">Sorry!</h1> <br>
								              <h3>No Recent Activity found!!</h3>
								            </div>
                            	</c:if>
                            
                            <ul class="messages" style="overflow-y:scroll; overflow:auto; height:400px;">
                            
                            <c:forEach var="urbList" items="${urbList}">
                            
                            <c:choose>
                            	
                            	<c:when test="${urbList.requestStatus == 'CANCELLED'}">
                            		<li>
		                                <div class="message_date" style="margin-right: 10px;">
		                                  <h3 class="date text-info">${urbList.created.date}</h3>
		                                  <p class="month">
		                                  
		                                  <c:choose>
		                                  		<c:when test="${urbList.created.month+1 == 1}">
		                                  				January
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 2}">
		                                  				February
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 3}">
		                                  				March
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 4}">
		                                  				April
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 5}">
		                                  				May
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 6}">
		                                  				June
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 7}">
		                                  				July
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 8}">
		                                  				August
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 9}">
		                                  				September
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 10}">
		                                  				October
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 11}">
		                                  				November
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 12}">
		                                  				December
		                                  		</c:when>
		                                  </c:choose>
		                                  
		                                  </p>
		                                  
		                                </div>
		                                <div class="message_wrapper">
		                                  <h4 class="heading1 glyphicon glyphicon-home">&nbsp;Booking Cancelled</h4>
		                                  <blockquote class="message">you cancelled the room booking at ${urbList.updated}</blockquote>
		                                  
		                                  </div>
                              </li>
                            	</c:when>
                            	
                            	<c:when test="${urbList.requestStatus == 'REJECTED'}">
                            		<li>
                            		<div class="message_date" style="margin-right: 10px;">
		                                  <h3 class="date text-info">${urbList.created.date}</h3>
		                                  <p class="month">
		                                  
		                                  <c:choose>
		                                  		<c:when test="${urbList.created.month+1 == 1}">
		                                  				January
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 2}">
		                                  				February
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 3}">
		                                  				March
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 4}">
		                                  				April
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 5}">
		                                  				May
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 6}">
		                                  				June
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 7}">
		                                  				July
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 8}">
		                                  				August
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 9}">
		                                  				September
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 10}">
		                                  				October
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 11}">
		                                  				November
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 12}">
		                                  				December
		                                  		</c:when>
		                                  </c:choose>
		                                  
		                                  </p>
		                                  
		                                </div>
		                               <%--  <div class="message_date">
		                                  <h3 class="date text-info">24</h3>
		                                  <p class="month">${urbList.created}</p>
		                                </div> --%>
		                                <div class="message_wrapper">
		                                  <h4 class="heading1 glyphicon glyphicon-home">&nbsp;Booking Rejected</h4>
		                                  <blockquote class="message">your booking request was rejected at ${urbList.updated}</blockquote>
		                                  
		                                  </div>
                              		</li>
                            	</c:when>
                            	
                            	<c:when test="${urbList.requestStatus == 'APPROVED'}">
                            		<li>
                            		<div class="message_date" style="margin-right: 10px;">
		                                  <h3 class="date text-info">${urbList.created.date}</h3>
		                                  <p class="month">
		                                  
		                                  <c:choose>
		                                  		<c:when test="${urbList.created.month+1 == 1}">
		                                  				January
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 2}">
		                                  				February
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 3}">
		                                  				March
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 4}">
		                                  				April
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 5}">
		                                  				May
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 6}">
		                                  				June
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 7}">
		                                  				July
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 8}">
		                                  				August
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 9}">
		                                  				September
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 10}">
		                                  				October
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 11}">
		                                  				November
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 12}">
		                                  				December
		                                  		</c:when>
		                                  </c:choose>
		                                  
		                                  </p>
		                                  
		                                </div>
		                                <%-- <div class="message_date">
		                                  <h3 class="date text-info">24</h3>
		                                  <p class="month">${urbList.created}</p>
		                                </div> --%>
		                                <div class="message_wrapper">
		                                  <h4 class="heading1 glyphicon glyphicon-home">&nbsp;Booking Confirmed</h4>
		                                  <blockquote class="message">your booking was confirmed at ${urbList.updated}</blockquote>
		                                  
		                                  </div>
                              			</li>
                            	</c:when>
                            	
                            	<c:when test="${urbList.requestStatus == 'PENDING'}">
                            		<li>
                            		
		                                <div class="message_date" style="margin-right: 10px;">
		                                  <h3 class="date text-info">${urbList.created.date}</h3>
		                                  <p class="month">
		                                  
		                                  <c:choose>
		                                  		<c:when test="${urbList.created.month+1 == 1}">
		                                  				January
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 2}">
		                                  				February
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 3}">
		                                  				March
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 4}">
		                                  				April
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 5}">
		                                  				May
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 6}">
		                                  				June
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 7}">
		                                  				July
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 8}">
		                                  				August
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 9}">
		                                  				September
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 10}">
		                                  				October
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 11}">
		                                  				November
		                                  		</c:when>
		                                  		<c:when test="${urbList.created.month+1 == 12}">
		                                  				December
		                                  		</c:when>
		                                  </c:choose>
		                                  
		                                  </p>
		                                  
		                                </div>
		                                <div class="message_wrapper">
		                                  <h4 class="heading1 glyphicon glyphicon-home">&nbsp;Booking Confirmed</h4>
		                                  <blockquote class="message">your booking for <a>${urbList.roomBean.roomName}</a> is still pending!</blockquote>
		                                  
		                                  </div>
                              			</li>
                            	</c:when>
                            	
                            	
                            </c:choose>
                            
                              
                              
                              </c:forEach>

                            </ul>
                            <!-- end recent activity -->

                          </div>
                          <div role="tabpanel" class="tab-pane fade active in" id="tab_content2" aria-labelledby="profile-tab">

                            

                          </div>
                          <div role="tabpanel" class="tab-pane fade" id="tab_content3" aria-labelledby="profile-tab">
                            <p>xxFood truck </p>
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
<!-- /page content -->


</div>
</div>
 <jsp:include page="req_js_jQuery.jsp" />
</body>
</html>