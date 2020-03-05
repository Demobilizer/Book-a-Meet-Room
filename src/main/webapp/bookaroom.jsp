<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book a Room!</title>

 <jsp:include page="req_css.jsp" />
 <jsp:include page="req_js_jQuery.jsp" />
 <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js">
</script>


    <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
   <!--  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script> -->
    
    <script src="vendors/jquery/dist/mustache.js"></script>
    

<!-- <script type="text/javascript">
	$(document).ready(function(){
		console.log("fdkjgnkdfgndfkgnkd");
		
	});

	function  getRoomDetails(room) {

			console.log(room);
		//	console.log($("#hidden-"room).val());
		 	var data=document.getElementById("hidden-"room).value;
			consoel.log(data);
			console.log("fdkjgnkdfgndfkgnkd");
}

</script> -->


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
              
                <h3>Book a Room Here!</h3>
               
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
                     
                   
                    
                     <table class="table table-striped projects">
                    
                        
                        <tr>
                        	<td>
                        		Date:
                        	</td>
                        	<td>
                        		<input id="datepicker" width="276" name="date" />
										<script>

										var d = new Date();
										d.setDate(d.getDate() - 1);
										
											$('#datepicker').datepicker({ minDate: d});
											//$( "#datepicker" ).datepicker({ minDate: new Date()});
										</script>
                        	</td>
                        </tr>
                        
                        <tr>
                        	<td>
                        		From:
                        	</td>
                        	<td>
                        		<input id="timepicker1" width="276" name="startTime"/>
										<script>
											$('#timepicker1').timepicker();
										</script>
                        	</td>
                        </tr>
                        
                        <tr>
                        	<td>
                        		To:
                        	</td>
                        	<td>
                        		<input id="timepicker2" width="276" name="endTime" />
										<script>
											$('#timepicker2').timepicker();
										</script>
                        	</td>
                        </tr>
                        
                        <tr>
                        	
                        	<td colspan="2">
                        		<br/>
                        		<div
											class="col-md-5 col-sm-5 col-xs-12 form-group pull-left" style="padding-left: 140px;">
											
												 <span
													class="input-group-btn" >
													<button class="btn btn-default btnClick"> Click here to Search for Available Rooms..!
													</button>
													
												</span>
											</div>
										
                        	</td>
                        </tr>
                        
                      
                     </table>
                     
				<!-- <form action="bookThisRoom?roomId="> -->
				
				<div id="all-rooms"></div>
				
				<template id="allRoomTemplate">
				
				<div>


						
							

								<%-- <c:forEach var="room" items="{{availableRoomList}}"	varStatus="status"> --%>
										
														<div class="col-md-4 col-sm-4 col-xs-12 profile_details">
															<div class="well profile_view">
																<div class="col-sm-12">
																	<!-- <h4 class="brief">
																		<i>Digital Strategist</i>
																	</h4> -->
																	<div class="left col-xs-8">
																		<h2>{{roomName}}</h2>
																		<%-- <input type="hidden" name="${room.roomId}"> --%>
																		<p>
																			<strong>Type: </strong> {{roomType}}
																		</p>
																		
																		<p>
																			<strong>Facilities: </strong>

																			<%-- <c:forEach var="facility"	items="${room.roomFacilities}">
																				 ${facility.facilityName} |
																			</c:forEach> --%>
																			
																			{{#roomFacilities}}
																								{{facilityName}} | {{/roomFacilities}}

																		</p>
																		
																		<p>
																			 <strong> Location: </strong> {{roomLocation}}
																		</p>
																		
																	</div>
																	<div class="right col-xs-4 text-center">
																		<img src="" alt="No Image for Now!"
																			class="img-circle img-responsive">
																	</div>
																</div>
																<div class="col-xs-12 bottom text-center">
																	<div class="col-xs-12 col-sm-5 emphasis">
																		<p class="ratings">
																			<a href="#"><span class="fa fa-star"></span></a>
																			<a href="#"><span class="fa fa-star"></span></a> <a
																				href="#"><span class="fa fa-star"></span></a> <a
																				href="#"><span class="fa fa-star"></span></a> <a
																				href="#"><span class="fa fa-star-o"></span></a>
																		</p>
																	</div>
																	<div class="col-xs-12 col-sm-7 emphasis">
																		
								<!-- -------- following commented CODE is to open a new Window on Click!! -------- -->
																		
																		
																		<%-- <button type="button" class="btn btn-primary btn-xs" id="viewRoom-btn" 
																		onclick='javascript:window.open("viewSingleAvailRoom?id=${room.roomId}", "_blank", "scrollbars=1,resizable=1,height=500,width=650");'>
																			<i class="fa fa-home"> </i> View Room
																		</button> --%>
																		
																		<%-- <input type="hidden" id="hidden-${room.roomId}" value="${room}"> --%>
																		
																		
																		<button class="getModal btn btn-primary btn-xs" 
																			id="viewRoom-{{roomId}}" 	data-id={{roomId}}  
																			data-toggle="modal" data-target="#myModal" >
																			<i class="fa fa-home"> </i> View Room
																		</button>
																		
																		
																		
																		<!-- <input type="hidden" name="roomId" value="{{roomId}}">
																		<input type="hidden" id="dateHidden" name="date" value="">
																		<input type="hidden" id="timeFromHidden" name="startTime" value="">
																		<input type="hidden" id="timeToHidden" name="endTime" value=""> -->
																		
																	<%-- <a href="bookThisRoom?roomId=${room.roomId}"> --%>
																	
																		<button class="btn btn-primary btn-xs bookRoomClick" 
																		id="bookRoom-{{roomId}}" data-id="{{roomId}}" data-user-id='${userBean.userId}'>
																		Book
																		</button>
																		
																	<!-- </a> -->
																
																		<!-- Modal -->
																		
																	
																		
																</div>
															</div>
															</div>
																		
																	</div>
															<%-- </c:forEach> --%>

												
								
												
													
												
										
															
									</div>
							</template>
							
							<div id="no-rooms"></div>
							
							<template id="noRoomTemplate">
								<div class="alert alert-success" role="alert" style="text-align: center;">
								Sorry! No Room is Available for this time duration! Please try to search with different Timings!</div>
							</template>
							
						<!-- </form> -->
													

				</div>


									</div>
                </div>
              </div>
            </div>
          </div>
        </div> 

              
            </div>
            
            
            
           																 <div class="modal" id="myModal" role="dialog" >
																					<div class="modal-dialog" id="modalDialog">
																				
																					</div>
																			</div> 
																	

																			<template id="room-template">
																			
																			<!-- Modal content-->
																				<div class="modal-content">
																					<div class="modal-header">
																						<button type="button" class="close"
																							data-dismiss="modal">&times;</button>
																						<h2 class="modal-title">{{roomName}}</h2>
																					</div>
																					
																					<div class="modal-body" style="height:310px;">
																						
																						<div class="col-xs-9" style="padding-top:20px;">
																						<!-- Tab panes -->
																						<div class="tab-content">
																							<div class="tab-pane active" id="home-r">
																								<p class="lead">Room Name</p>
																								<p>{{roomName}}</p>
																							</div>
																							<div class="tab-pane" id="profile-r">
																								<p class="lead">Room Type</p>{{roomType}}</div>
																							<div class="tab-pane" id="messages-r">
																								<p class="lead">Room Location</p>{{roomLocation}}</div>
																							<div class="tab-pane" id="settings-r">
																								<p class="lead">Facilities in the Room</p>

																								<%-- <c:forEach var="facility"
																									items="${room.roomFacilities}" varStatus="loop">
																									${facility.facilityName} <br>
																								</c:forEach> --%>
																							
																								{{#roomFacilities}}
																								{{facilityName}}</br> {{/roomFacilities}}
																							

																						</div>
																							<div class="tab-pane" id="description-r">
																								<p class="lead">Other Specifications</p>{{otherDescription}}</div>
																						</div>
																					</div>



																					<div class="col-xs-3" style="padding-top:10px;">
																						<!-- required for floating -->
																						<!-- Nav tabs -->
																						<ul class="nav nav-tabs tabs-right">
																							<li class="active"><a href="#home-r"
																								data-toggle="tab" aria-expanded="true">Name</a>
																							</li>
																							<li class=""><a href="#profile-r"
																								data-toggle="tab" aria-expanded="false">Type</a>
																							</li>
																							<li class=""><a href="#messages-r"
																								data-toggle="tab" aria-expanded="false">Location</a>
																							</li>
																							<li class=""><a href="#settings-r"
																								data-toggle="tab" aria-expanded="false">Facilities</a>
																							</li>
																							<li class=""><a href="#description-r"
																								data-toggle="tab" aria-expanded="false">Others</a>
																							</li>
																						</ul>
																					</div>
																						
																					
																						
																					</div>

																					<div class="modal-footer">
																						
																						
																						<button class="btn btn-primary bookRoomClick"
																							 style="margin-right: -8px; margin-top: 5px;"
																							 id="bookRoomBtn-{{roomId}}"
																							 data-user-id='${userBean.userId}' data-id="{{roomId}}" 
																							 data-dismiss="modal">
																							 Book This Room
																						</button>
																							
																						 
																						<button  class="btn btn-primary" data-dismiss="modal" 
																							 style="margin-right: -8px; margin-left: 8px; margin-top: 5px;">Cancel</button>
																					</div>

																				</div>
																				<!-- End of Modal content-->

																		</template>
																				
																			<!-- End of Modal -->
            
<!-- /page content -->

</div>

 
 <script src="availRoom.js"></script>
 <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
 

</body>
</html>

