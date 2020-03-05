<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
    

    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Rejected Requests</title>
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
						<h3>Rejected Booking Requests</h3>
					</div>
					<!-- <div align="right">
						<h3><a href="viewAllUsers?pageId=1">View All Users</a></h3>
					</div> -->
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
                          <th style="width: 15%">Booking Date & Time</th>
                          <th style="width: 14%">Rejection Date & Time</th>
                          <th style="width: 30%">Reason</th>
                        </tr>
                      </thead>
                      <tbody>
                        
                        <% int i = 1;  %>
                        
                        <c:forEach var="rejectedRequestRoomsList" items="${rejectedRequestRoomsList}">
                        
                        <tr>
	                          <td><% out.println(i); %>   </td>
	                          
	                          <td>
	                            <a>${rejectedRequestRoomsList.roomDto.roomName}</a>
	                            
	                            <br/>
	                            
	                            	<small><strong>Room Type:</strong> ${rejectedRequestRoomsList.roomDto.roomType}</small>
                            	
                          </td>
                          
                          <td> <strong>${rejectedRequestRoomsList.startTime}</strong> to 
                          <strong>${rejectedRequestRoomsList.endTime}</strong> on 
                          <br/>
                           ${rejectedRequestRoomsList.date}   </td>
                          
                          <td> <strong>${rejectedRequestRoomsList.updated}</strong> on 
                          <%-- <br/>
                           ${rejectedRequestRoomsList.date} --%> 
                          
                          </td>
                          
                          <td>
                          
                            <%--  <a href="approveBookingRequest?urBookId=${userRoomBook.urBookId}" class="approve-btn btn btn-primary btn-xs">
                             <i class="fa fa-folder" ></i> Approve </a> --%>
						 
						 Rejection Reason<br/>
						<a href="bookRoom" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> Make New Request </a>
                            
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