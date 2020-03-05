<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
    

    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Users</title>
<jsp:include page="req_css.jsp" />

<script src="https://twitter.github.io/typeahead.js/js/handlebars.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js">
</script>
<script src="vendors/jquery/dist/mustache.js"></script>



<!-- <script type="text/javascript">

function myFunction() {

	var status = ${user.accountStatus};
	  if(status == "deleted")
		  {
		  	$("#delete-btn1").style.display = "none";
		  }
	}

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
							<h3>User Details</h3> 
						</div>
						<div style="float: left; padding-top: 12px; padding-left: 100px;">
							<input type="radio" name="userType" value="all" checked="checked"> All Users
							<input type="radio" name="userType" value="active"> Active Users
							<input type="radio" name="userType" value="rejected"> Rejected Users
							<input type="radio" name="userType" value="deleted"> Deleted Users
						</div>
						<div style="padding-left: 170px; float: left;">
							<h3><a href="manageUserRequest?pageId=1">Manage Pending Requests</a></h3> 
						</div>
						
					</div>
					
					 <div class="clearfix">
					 				<c:if test="${msg != null}">
												
											<div class="alert-success" role="alert">${msg}</div>
												
									</c:if>
									
					</div> 

					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<!-- <div class="x_title">
									<h2>Plain Page</h2>

									<div class="clearfix">
									</div>
								</div> -->
								<div class="x_content" id="x_content">
									
									<!-- YOUR CONTENT GOES HERE  in this div tag-->

                    <!-- start project list -->
                    
                    <table class="table table-striped projects" id="">
                      <thead>
                        <tr>
                          <th style="width: 2%">No.</th>
                          <th style="width: 20%">User Requests</th>
                          <th style="width: 10%">Department</th>
                          <th style="width: 10%">Account Status</th>
                          <th style="width: 34%">#Manage Users</th>
                        </tr>
                      </thead>
                      <tbody id="table-data">
                        
                        <% int i = 1;  %>
                        
                        <c:forEach var="user" items="${userDto}">
                        
                        <tr>
	                          <td><% out.println(i); %>   </td>
	                          
	                          <td>
	                            <a>${user.fullName}</a>
	                            <br>
	                            
	                            	<small>Email Id: ${user.emailId}</small>
	                            
	                            <br>
	                            <c:if test="${user.updated == null}">
													
										<small>Created: ${user.created}</small>
												
								</c:if>
								<c:if test="${user.updated != null}">
													
										<small>Updated: ${user.updated}</small>
												
								</c:if>
								
                            	
                          </td>
                          
                          <td> ${user.department}   </td>
                          
                          <td> ${user.accountStatus}   </td>
                          
                          <td>
                          
                             <a href="viewUserById?userId=${user.userId}&pageNo=${pageNo}" class="btn btn-primary btn-xs">
                             <i class="fa fa-folder"></i> View </a>
                            
                            <c:if test="${user.accountStatus != 'deleted'}">
                            
	                            <a href="getUserToEdit?userId=${user.userId}&pageNo=${pageNo}" class="btn btn-info btn-xs">
	                            <i class="fa fa-pencil"></i> Edit </a>
	                            <a href="deleteUser?userId=${user.userId}&pageNo=${pageNo}" class="btn btn-danger btn-xs">
	                            <i class="fa fa-trash-o"></i> Delete </a>
	                            
                            </c:if>
                            
                            
                          </td>
                          
                        </tr>
                        
                        <% i++; %>
                        </c:forEach>
                        
                        </tbody>
                    </table>
                    
                    
                    <!-- end project list -->

					<div class="col-md-12 col-sm-12 col-xs-12 text-center">
                        <ul class="pagination pagination-split">
					<c:forEach begin="1" end="${totalPages}" varStatus="loop">

								<li class="btn-info btn-active" data-id="${loop.index}" >
								<a href="viewAllUsers?pageId=${loop.index}" id="btn-${loop.index}">${loop.index}</a>  </li>      

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
 
 																			
 													
 													<template id="users-template">
 																	<table class="table table-striped projects">
												                      
												                      <thead>
												                        <tr>
												                          <th style="width: 2%">No.</th>
												                          <th style="width: 20%">User Requests</th>
												                          <th style="width: 10%">Department</th>
												                          <th style="width: 10%">Account Status</th>
												                          <th style="width: 34%">#Manage Users</th>
												                        </tr>
												                      </thead>
												                      <% int j = 1;  %>
												                      
												                      
												                      <tbody>
												                      
												                      {{#userDto1}}
												                      <!-- {{#.}} -->
												                        <tr>
													                          <td><% out.println(j++); %>   </td>
													                          
													                          <td>
													                            <a>{{fullName}}</a>
													                            <br>
													                            
													                            	<small>Email Id: {{emailId}}</small>
													                            
													                            <br>
													                            <%-- <c:if test="{{updated == null}}">
																									
																						<small>Created: {{created}}</small>
																								
																				</c:if>
																				<c:if test="{{updated != null}}">
																									
																						<small>Updated: {{updated}}</small>
																								
																				</c:if> --%>
																				
																				{{^updated}}
																				<small> Created: {{created}} 
																				
																				</small> {{/updated}}
																				{{#updated}}
																				<small>Updated: {{updated}}</small>
																				{{/updated}}
																				
												                            	
												                          </td>
												                          
												                          <td> {{department}}   </td>
												                          
												                          <td> {{accountStatus}}   </td>
												                          
												                          <td>
												                          
												                             <a href="viewUserById?userId={{userId}}&pageNo={{pageNo}}" 
												                             class="btn btn-primary btn-xs">
												                             <i class="fa fa-folder"></i> View </a>
												                            
												                            <%-- <c:if test="{{accountStatus != 'deleted'}}"> --%>
												                            
												                            {{^isDeleted}}
												                            
													                           <a href="getUserToEdit?userId={{userId}}&pageNo={{pageNo}}" class="btn btn-info btn-xs">
													                           <i class="fa fa-pencil"></i> Edit </a>
													                           <a href="deleteUser?userId={{userId}}&pageNo={{pageNo}}" class="btn btn-danger btn-xs">
													                           <i class="fa fa-trash-o"></i> Delete </a>
													                            
												                           <%--  </c:if> --%>
												                          {{/isDeleted}}
												                            
												                          </td>
												                          
												                        </tr>
												                       <!--  {{/.}} -->
												                   
												                       <!-- {{/userDto1}}  -->
												                        
												                        </tbody>
												                        
												                    </table>
												                    
												                    
												                    <div class="col-md-12 col-sm-12 col-xs-12 text-center">
												                        <ul class="pagination pagination-split">
												                        <!-- {{totalPages}} -->
												                    <%-- <c:set var = "totalPages1" value ="{{totalPages}}"/> --%>
												                    
												                    
												                        
																	<%-- <c:forEach begin="1" end="${totalPages1+1}" varStatus="loop"> --%>
												
																		{{#totalPages}}
																		
																				<li class="btn-info btn-active getPaging" data-id="{{tpid}}" data-type="{{radioValue}}">
																				 <!-- onclick="getPaging({{tpid}}, {{radioValue}} )"-->
																				<a  id="btn-{{tpid}}">{{tpid}}</a>  
																				</li>      
																		{{/totalPages}}
																		
																	<%-- </c:forEach> --%>
																	</ul>
												                      </div>
												                    
																		</template>                    
												                    
												                    <!-- end project list -->
												
 
 
 
 
 <jsp:include page="req_js_jQuery.jsp" />
 <script src="viewusers.js"></script>
</body>
</html>