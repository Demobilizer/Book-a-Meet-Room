<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
    

    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Booking History</title>
<jsp:include page="req_css.jsp" />

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

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
					
					<div>
						<div style="float: left;">
						<h3>View Room Booking History</h3>
					</div>
						<div style="float: left; padding-top: 12px; padding-left: 150px;">
							<input type="radio" name="bookingType" value="all" checked="checked"> All
							<input type="radio" name="bookingType" value="completed"> Completed
							<input type="radio" name="bookingType" value="cancelled"> Cancelled
							<input type="radio" name="bookingType" value="rejected"> Rejected
						</div>
						
					</div>
					
					 <div class="clearfix">
					 
					 </div> 

					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								
								<div class="x_content">
									
									<!-- YOUR CONTENT GOES HERE  in this div tag-->

                    <!-- start project list -->
                    
                    <form action="" modelAttribute="">
                    
                    <table class="table table-striped projects">
                      <thead>
                        <tr>
                          <th style="width: 2%">No.</th>
                          <th style="width: 15%">Room</th>
                          <th style="width: 15%">Booking Date </th>
                          <th style="width: 10%">Time</th>
                          <th style="width: 10%">Status</th>
                          <th style="width: 14%">Action Time</th>
                        </tr>
                      </thead>
                      <tbody>
                        
                        <% int i = 1;  %>
                        
                        <c:forEach var="roomBookingHistoryList" items="${roomBookingHistoryList}">
                        
                        <tr>
	                          <td><% out.println(i); %>   </td>
	                          
	                          <td>
	                            <a>${roomBookingHistoryList.roomDto.roomName}</a>
	                            
	                            <br>
	                            
	                            	<small><strong>Room Type:</strong> ${roomBookingHistoryList.roomDto.roomType}</small>
	                            
                            	
                          </td>
                          
                          <td> ${roomBookingHistoryList.date}  </td>
                          
                          <td> <strong>${roomBookingHistoryList.startTime}</strong> to 
                          <strong>${roomBookingHistoryList.endTime}</strong>
                          
                          </td>
                          
                          <td>
                          
						 ${roomBookingHistoryList.requestStatus}
						
                          </td>
                          <td>
                          
						 ${roomBookingHistoryList.updated}
						
                          </td>
                          
                        </tr>
                        
                       
                        <% i++; %>
                        </c:forEach>
                        
                        </tbody>
                    </table>
                    
                    </form>
                    
                    <!-- end project list -->
					
					
					
					
					
					
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