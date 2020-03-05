<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

        

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Your Room</title>

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
				
					<h3>${roomDto.roomName}</h3>

					  <div class="clearfix"></div>

					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
							<!-- 	<div class="x_title">
									<h2>Plain Page</h2>

									<div class="clearfix"></div>
								</div> -->
								
                 
                  <div class="x_content">

                    <div class="col-xs-9">
                      <!-- Tab panes -->
                      <div class="tab-content">
                        <div class="tab-pane active" id="home-r">
                          <p class="lead">Room Name</p>
                          <p>${roomDto.roomName} </p>
                        </div>
                        <div class="tab-pane" id="profile-r"><p class="lead">Room Type</p>${roomDto.roomType}</div>
                        <div class="tab-pane" id="messages-r"><p class="lead">Room Location</p>${roomDto.roomLocation}</div>
                        <div class="tab-pane" id="settings-r"><p class="lead">Facilities in the Room</p>
                        
                        <c:forEach var="facility" items="${roomDto.roomFacilities}">
									${facility.facilityName} <br> 
								</c:forEach>
                        
                        </div>
                        <div class="tab-pane" id="description-r"><p class="lead">Other Specifications</p>${roomDto.otherDescription}</div>
                      </div>
                    </div>



                    <div class="col-xs-3">
                      <!-- required for floating -->
                      <!-- Nav tabs -->
                      <ul class="nav nav-tabs tabs-right">
                        <li class="active"><a href="#home-r" data-toggle="tab" aria-expanded="true">Name</a>
                        </li>
                        <li class=""><a href="#profile-r" data-toggle="tab" aria-expanded="false">Type</a>
                        </li>
                        <li class=""><a href="#messages-r" data-toggle="tab" aria-expanded="false">Location</a>
                        </li>
                        <li class=""><a href="#settings-r" data-toggle="tab" aria-expanded="false">Facilities</a>
                        </li>
                        <li class=""><a href="#description-r" data-toggle="tab" aria-expanded="false">Others</a>
                        </li>
                      </ul>
                    </div>

                  </div>
								</div>
								<div align="center">
								<a href="manageRoom"> <input type="button" class="btn btn-primary" value="Back" /></a>
	<a href="getRoomToEdit?id=${roomDto.roomId}"> <input type="button" class="btn btn-primary" value="Edit Room" /></a>
	<a href="deleteRoom?id=${roomDto.roomId}"> <input type="button" class="btn btn-primary" value="Delete Room" /></a>
	</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
		


	<!-- /page content -->

<jsp:include page="req_js_jQuery.jsp" />

</body>
</html>