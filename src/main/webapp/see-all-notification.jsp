<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Notifications</title>
<jsp:include page="req_css.jsp" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js">
</script>
 <script src="vendors/jquery/dist/mustache.js"></script>

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
												         <c:when test="${userBean.userRole == 'ROLE_P' }">
												            <jsp:include page="left-bar-pm.jsp" />
												         </c:when>

														<c:otherwise>
												            <jsp:include page="left-bar.jsp" />
												         </c:otherwise>
												</c:choose>
      
<jsp:include page="header.jsp" /> 

<!-- page content -->
	<div class="right_col" role="main" style="min-height: 958px;">
		<div>
		
			<div class="page-title">
					
					<div>
						<div style="float: left;">
							<h3>Feedbacks</h3> 
						</div>
						<div style="float: left; padding-top: 12px; padding-left: 200px;">
							<input type="radio" name="userType" value="all" > All Notifications 
							<input type="radio" name="userType" value="active" checked="checked"> Unread Notifications
							<input type="radio" name="userType" value="active"> Read Notifications
							
						</div>
						
						
					</div>
					
					 <div class="clearfix">
					 				
									
					</div> 

					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<!-- <div class="x_title">
									<h2>Plain Page</h2>

									<div class="clearfix">
									</div>
								</div> -->
								<div class="x_content">
									
									<!-- YOUR CONTENT GOES HERE  in this div tag-->

                    <!-- start project list -->
                    
                    <!-- <form action="approveUser" modelAttribute=""> -->
                    
                    <table class="table table-striped projects">
                    
                      <thead>
                        <tr>
                          <th style="width: 40%">Notifications</th>
                          <th style="width: 20%">Received at</th>
                          <th style="width: 25%">#Manage Notifications</th>
                        </tr>
                      </thead>
                      <tbody>
                        
                        <c:forEach items="${notificationList}" var="notificationList">
                        
                        <tr>
	                          
	                        <td>
	                        
	                        		<c:choose>
                    					<c:when test="${notificationList.notificationType =='ROOM_BOOK_REQUEST'}">
                    						<a href="manageBookingRequest?pageId=1">
                    							${notificationList.notificationContent}
                    						</a>
                    					</c:when>
                    					<c:when test="${notificationList.notificationType =='USER_REGISTRATION_REQUEST'}">
                    						<a href="manageUserRequest?pageId=1">
                    							${notificationList.notificationContent}
                    						</a>
                    					</c:when>
                    					<c:when test="${notificationList.notificationType =='FEEDBACK_SENT'}">
                    						<a href="viewFeedBacks?pageId=1">
                    							${notificationList.notificationContent}
                    						</a>
                    					</c:when>
                    					<c:otherwise>
                    						<a>
                    							${notificationList.notificationContent}
                    						</a>
                    					</c:otherwise>
                    				</c:choose>
	                        
                          	</td>
                          
                          <td>
	                              ${notificationList.created}
                          	</td>
                         
                          
                          <td>
                          
	                            	<c:if test="${notificationList.readNotification == false}">
	                            	<a href="readNotification?id=${notificationList.notificationId}" class="btn btn-info btn-xs" 
	                            	data-toggle="tooltip" title="Mark as read" data-placement="left">
	                            	<i class="fa fa-folder"></i> Read </a></c:if>
	                            	
	                            	<c:if test="${notificationList.readNotification == true}">
	                            	<a href="readNotification?id=${notificationList.notificationId}" class="btn btn-info btn-xs" 
	                            	data-toggle="tooltip" title="Mark as unread" data-placement="left">
	                            	<i class="fa fa-folder"></i> Unread </a></c:if>
	                            	
	                            	<a href="deleteNotification?id=${notificationList.notificationId}" class="btn btn-danger btn-xs">
	                            	<i class="fa fa-folder"></i> Delete </a>
                            
                          </td>
                          
                        </tr>
                        
                        </c:forEach>
                        
                        </tbody>
                    </table>
                    
					

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
 
 <jsp:include page="req_js_jQuery.jsp" />
 
 
</body>
</html>