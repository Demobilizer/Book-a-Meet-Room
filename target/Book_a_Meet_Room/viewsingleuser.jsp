<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

        

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>User Detail</title>

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
				
					<h3>${userDto.fullName}</h3>

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
                          <p class="lead">Full Name</p>
                          <p>${userDto.fullName} </p>
                        </div>
                        
                        <div class="tab-pane" id="fullname-r"><p class="lead">Full Name</p>${userDto.fullName}</div>
                        <div class="tab-pane" id="username-r"><p class="lead">User Name</p>${userDto.userName}</div>
                        <div class="tab-pane" id="email-r"><p class="lead">Email Id</p>${userDto.emailId}</div>
                        <div class="tab-pane" id="contact-r"><p class="lead">Contact No:</p>${userDto.contactNo}</div>
                        <div class="tab-pane" id="department-r"><p class="lead">Department</p>${userDto.department}</div>
                        <div class="tab-pane" id="status-r"><p class="lead">Account Status</p>${userDto.accountStatus}</div>
                        
                        <c:if test="${room.updated == null}">
												
									<div class="tab-pane" id="created-r"><p class="lead">User created</p>${userDto.created}</div>
											
							</c:if>
							<c:if test="${room.updated != null}">
												
									<div class="tab-pane" id="created-r"><p class="lead">User updated</p>${userDto.updated}</div>
											
							</c:if>
                        
                        <div class="tab-pane" id="userrole-r"><p class="lead">User Role</p>${userDto.userRole}</div>
                        
                      </div>
                    </div>



                    <div class="col-xs-3">
                      <!-- required for floating -->
                      <!-- Nav tabs -->
                      <ul class="nav nav-tabs tabs-right">
                        <li class="active"><a href="#fullname-r" data-toggle="tab" aria-expanded="true">Full Name</a>
                        </li>
                        <li class=""><a href="#username-r" data-toggle="tab" aria-expanded="false">User Name</a>
                        </li>
                        <li class=""><a href="#email-r" data-toggle="tab" aria-expanded="false">Email Id</a>
                        </li>
                        <li class=""><a href="#contact-r" data-toggle="tab" aria-expanded="false">Contact Number</a>
                        </li>
                        <li class=""><a href="#department-r" data-toggle="tab" aria-expanded="false">Department</a>
                        </li>
                        <li class=""><a href="#status-r" data-toggle="tab" aria-expanded="false">Account Status</a>
                        </li>
                        <li class=""><a href="#created-r" data-toggle="tab" aria-expanded="false">Created/ Updated</a>
                        </li>
                        <li class=""><a href="#userrole-r" data-toggle="tab" aria-expanded="false">User Role</a>
                        </li>
                        
                      </ul>
                    </div>

                  </div>
								</div>
				<div align="center">
						<a href="viewAllUsers?pageId=${pageNo}"> <input type="button" class="btn btn-primary" value="Back" /></a>
								
					<c:if test="${userDto.accountStatus != 'deleted'}">
								
						<a href="getUserToEdit?id=${userDto.userId}"> <input type="button" class="btn btn-primary" value="Edit User" /></a>
						<a href="deleteUser?id=${userDto.userId}"> <input type="button" class="btn btn-primary" value="Delete User" /></a>
	
					</c:if>
	
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