<%@page import="com.neoSoft.model.RoomBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hello User!</title>

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
             
                <h3>Update a Room</h3>
               
            <div class="clearfix"></div>

            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <!-- <div class="x_title">
                    <h2>Plain Page</h2>
                    
                    <div class="clearfix"></div>
                  </div> -->
                  <div class="x_content">
                     
                     
                     	<!-- start form for validation -->
												
									<form id="demo-form" modelAttribute="roomDto" action="updateRoom" method="post">
									
									<input type="hidden" name="roomId" value="${roomToEdit.roomId}" />
									
									Type of Room :
									<select class="form-control" name="roomType">
																						
												<option value="Meeting Room">Meeting Room</option>
												<option value="Conference Room">Conference Room</option>
												
										</select>
									
									
										 <%-- <select class="form-control" name="roomType">
													
													<% String type1 = "Meeting Room";
														String type2 = "Conference Room";%>
																						
												<option value="MeetingRoom" 
												<c:if test="${roomToEdit.roomType == type1}"> 'selected="selected"' </c:if>>Meeting Room</option>
													<option value="ConferenceRoom" 
												<c:if test="${roomToEdit.roomType == type2}"> 'selected="selected"' </c:if>>Conference Room</option> 
																								
												</select>  --%>
									
										Room Name :
										<small>(like; Meeting Room 1, Meeting Room 2 or Room xyz etc...) </small>
										<input type="text" id="fullname" class="form-control" name="roomName" 
										value="${roomToEdit.roomName}"  />

										Location * :
										<small>(like; in front of Zone 1, near by Server Room etc...) </small>
										<input type="text" id="email" class="form-control" name="roomLocation" data-parsley-trigger="change" 
										value="${roomToEdit.roomLocation}" /> 
											
											
										Facilities:

										<p style="padding: 5px;">
										
										<c:forEach var = "facilityAll" items="${facilitiesAll}" varStatus="status">
										
										<c:set var="flag" value="${false}" />
										
													<c:forEach var="facility" items="${roomToEdit.roomFacilities}">
														<c:choose>
															<c:when test="${facilityAll.facilityName == facility.facilityName}">
																	
																	<input type="checkbox" name="roomFacilities[${status.index}].facilityId" value="${facilityAll.facilityId}" 
																	class="flat" checked="checked" />	${facilityAll.facilityName} &nbsp; <br />
																	<c:set var="flag" value="${true}" />
                											</c:when>
															<c:otherwise>
																
               			 									</c:otherwise>
														</c:choose>
													</c:forEach>
													
													<c:if test="${flag eq false}">
													<input type="checkbox" name="roomFacilities[${status.index}].facilityId" class="flat" value="${facilityAll.facilityId}">
																${facilityAll.facilityName}&nbsp; <br />
													</c:if>
										</c:forEach>
																						
										</p>
											
											 Other Description:
											<textarea id="message" required="required"
												class="form-control" name="otherDescription"
												data-parsley-trigger="keyup" data-parsley-minlength="20"
												data-parsley-maxlength="100"
												data-parsley-minlength-message="Come on! You need to enter at least a 20 caracters long comment.."
												data-parsley-validation-threshold="10">${roomToEdit.otherDescription}</textarea>

											<br /><input type="submit" class="btn btn-primary" value="Submit Room" />
											<a href="manageRoom"> <input type="button" class="btn btn-primary" value="Cancle" /></a>
									</form>
									<!-- end form for validations -->
                     
                     
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