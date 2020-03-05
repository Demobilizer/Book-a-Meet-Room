<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Room(s)</title>

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
              
                <h3>Create New Room</h3>
               
            <div class="clearfix">
            						<c:if test="${msg != null}">
												
											<div class="alert alert-success" role="alert">${msg}</div>
												
									</c:if>
				</div>

            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <!-- <div class="x_title">
                    
                    <div class="clearfix">
                    
                    </div>
                  </div> -->
                  <div class="x_content">
                     
                     
                     <!-- create form -->
												
									<form id="demo-form" modelAttribute="roomDto" action="addRoom" method="post">
									
									Type of Room :
										<select class="form-control" name="roomType">
																						
												<option value="Meeting Room">Meeting Room</option>
												<option value="Conference Room">Conference Room</option>
												
										</select>
									
										Room Name * :
										<small>(like; Meeting Room 1, Meeting Room 2 or Room xyz etc...) </small>
										<input type="text" id="fullname" class="form-control" name="roomName" required="required" />
											
											

										Location * :
										<small>(like; in front of Zone 1, near by Server Room etc...) </small>
										<input type="text" id="email" class="form-control" name="roomLocation" data-parsley-trigger="change" required="required" /> 
											
											
										Facilities:
																	
										<p style="padding: 5px;">
										
											<c:forEach var = "facility" items="${facilitiesList}"  varStatus="status">
         									<input type="checkbox" name="roomFacilities[${status.index}].facilityId" value="${facility.facilityId}" class="flat" />	${facility.facilityName} <br />
      									</c:forEach>
										
											
																						
										</p>

											
											 Other Description *:
											<textarea id="message" required="required"
												class="form-control" name="otherDescription"
												data-parsley-trigger="keyup" data-parsley-minlength="20"
												data-parsley-maxlength="100"
												data-parsley-minlength-message="Come on! You need to enter at least a 20 caracters long comment.."
												data-parsley-validation-threshold="10"></textarea>

											<br /><input type="submit" class="btn btn-primary" value="Submit Room" />
											<a href="index.jsp"> <input type="button" class="btn btn-primary" value="Cancle" /></a>
									</form>
									<!-- create form  -->
									
                    
                     
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