<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
    

    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Rooms</title>
<jsp:include page="req_css.jsp" />
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
						<h3>Manage Rooms</h3>
					</div>
					
					<div style="float: right;">
						  <a href="createRoom">
	                      	<button type="button" class="btn btn-success btn-lg">Create Room</button>
	                      </a>
                      </div>
                      
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
                    <table class="table table-striped projects">
                      <thead>
                        <tr>
                          <th style="width: 5%">No.</th>
                          <th style="width: 25%">Room Name</th>
                          
                          <th style="width: 40%">#Manage Room</th>
                        </tr>
                      </thead>
                      <tbody>
                        
                        <% int i = 1;  %>
                        
                        <c:forEach var="room" items="${roomsList}">
                        
                        <tr>
                          <td><% out.println(i); %>   </td>
                          <td>
                            <a>${room.roomName}</a>
                            <br>
                            
                            <c:if test="${room.updated == null}">
												
									<small>Created ${room.created}</small>
											
							</c:if>
							<c:if test="${room.updated != null}">
												
									<small>Updated ${room.updated}</small>
											
							</c:if>
                            
                          </td>
                          
                          <td>
                            <a href="viewRoomById?id=${room.roomId}" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View </a>
                            <a href="getRoomToEdit?id=${room.roomId}" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                            <a href="deleteRoom?id=${room.roomId}" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>
                          </td>
                        </tr>
                        <% i++; %>
                        </c:forEach>
                        
                        </tbody>
                    </table>
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