<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
    

    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Pending Booking Requests</title>
<jsp:include page="req_css.jsp" />

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- <script type="text/javascript">

$(document).ready(function() {
    $(".approve-btn").click(function(){
      //apprive-432
        var userId = this.id.split("-")[1];
        alert(userId);
    }); 
});

</script> -->

</head>



<body class="nav-md">

<div class="container body">
      <div class="main_container">
      
      <jsp:include page="left-bar.jsp" />
<jsp:include page="header.jsp" /> 

<!-- page content -->
	<div class="right_col" role="main" style="min-height: 958px;">
		<div>
		
			<div class="page-title">
					
					<div>
					<div style="float: left;">
						<h3>Manage Room Booking Requests</h3>
					</div>
					<!-- <div align="right">
						<h3><a href="viewAllUsers?pageId=1">View All Users</a></h3>
					</div> -->
					</div>
					

					 <div class="clearfix"><c:if test="${msg != null}">
												
											<div class="alert alert-success" role="alert">${msg}</div>
												
									</c:if></div> 

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
                    
                    <form action="" modelAttribute="">
                    
                    <table class="table table-striped projects">
                      <thead>
                        <tr>
                          <th style="width: 2%">No.</th>
                          <th style="width: 20%">Booking Requests</th>
                          <th style="width: 10%">User Name</th>
                          <th style="width: 10%">Date & Time</th>
                          <th style="width: 34%">#Manage Requests</th>
                        </tr>
                      </thead>
                      <tbody>
                        
                        <% int i = 1;  %>
                        
                        <c:forEach var="userRoomBook" items="${userRoomBookDtos}">
                        
                        <tr>
	                          <td><% out.println(i); %>   </td>
	                          
	                          <td>
	                            <a>${userRoomBook.roomDto.roomName}</a>
	                            <br>
	                            
	                            	<small><strong>Room Type:</strong> ${userRoomBook.roomDto.roomType}</small>
	                            
                            	
                          </td>
                          
                          <td> ${userRoomBook.userDto.fullName}   </td>
                          
                          <td><strong>${userRoomBook.startTime}</strong> to <strong>${userRoomBook.endTime}</strong> on<br/>   
                          
                          				 <strong>${userRoomBook.date}</strong>
                          
                          </td>
                          
                          <td>
                          
                          
                             <a href="approveBookingRequest?urBookId=${userRoomBook.urBookId}" class="approve-btn btn btn-primary btn-xs">
                             <i class="fa fa-folder" ></i> Approve </a>
						 
						<a href="rejectBookingRequest?urBookId=${userRoomBook.urBookId}" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Reject </a>
                            
                          </td>
                          
                        </tr>
                        
                       
                        <% i++; %>
                        </c:forEach>
                        
                        </tbody>
                    </table>
                    
                    </form>
                    
                    <!-- end project list -->
					
					
					
					<div class="col-md-12 col-sm-12 col-xs-12 text-center">
                        <ul class="pagination pagination-split">
                          <c:forEach begin="1" end="${totalPages+1}" varStatus="loop">

								<li class="btn-info btn-active" data-id="${loop.index}" >
								<a href="manageBookingRequest?pageId=${loop.index}" id="btn-${loop.index}">${loop.index}</a>  </li>   

					</c:forEach>
                        </ul>
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
 
 <jsp:include page="req_js_jQuery.jsp" />
 
 <script type="text/javascript">

 $('.btn-active').click(function(){

			  $(this).toggleClass('btn-danger');
			
 });

 </script>
 
</body>
</html>