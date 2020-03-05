<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
    

    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Pending User Requests</title>
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
						<h3>Manage User Requests</h3>
					</div>
					<div align="right">
						<h3><a href="viewAllUsers?pageId=1">View All Users</a></h3>
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
                    
                    <%-- <form action="approveUser?userId=${user.userId}" modelAttribute="userDtoo"> --%>
                    
                    <table class="table table-striped projects">
                      <thead>
                        <tr>
                          <th style="width: 2%">No.</th>
                          <th style="width: 20%">User Requests</th>
                          <th style="width: 10%">Department</th>
                          <th style="width: 10%">Account Status</th>
                          <th style="width: 34%">#Manage Requests</th>
                        </tr>
                      </thead>
                      <tbody>
                        
                        <% int i = 1;  %>
                        
                        <c:forEach var="user" items="${userDto}">
                        
                        <form action="approveUser?userId=${user.userId}" modelAttribute="userDtoo">
                        
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
                          
                             <span id="approveBtn-${user.userId}" class="approve-btn btn btn-primary btn-xs" data-toggle="collapse" data-target="#role-${user.userId}">
                             <i class="fa fa-folder" ></i> Approve </span>

							<a href="rejectUser?userId=${user.userId}" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Reject </a>
                            
                          </td>
                          
                        </tr>
                        
                        <tr>

														<td></td>
														<td></td>
														<td></td>
														<!-- <td><div id="demo" class="collapse">Choose a Role for User:</div></td> -->
														<td></td>
														<td>

															<input type="hidden" name="userId" value="${user.userId}">
															<div id="role-${user.userId}" class="collapse">
															Choose a Role for User:
																<input type="radio" name="userRole" value="ROLE_USER" />User 
																<input type="radio" name="userRole" value="ROLE_T" />TL 
																<input type="radio" name="userRole" value="ROLE_P" />PM
																
																<%-- <a href="approveUser?userId=${user.userId}"> --%>
																<button type="submit" class="btn btn-primary btn-xs" ><i class="fa fa-folder" ></i> Save  </button>
																<!-- </a> -->
															</div> 
                            
                          								</td>
                        
                        </tr> 
                        <% i++; %>
                        </form>
                        </c:forEach>
                        
                        </tbody>
                    </table>
                    
                    <!-- </form> -->
                    
                    <!-- end project list -->
					
					
					
					<div class="col-md-12 col-sm-12 col-xs-12 text-center">
                        <ul class="pagination pagination-split">
                          <c:forEach begin="1" end="${totalPages+1}" varStatus="loop">

								<li class="btn-info btn-active" data-id="${loop.index}">
								<a href="manageUserRequest?pageId=${loop.index}" id="btn-${loop.index}">${loop.index}</a>  </li>   

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

	 
	/* var cl = $(this).attr('data-id'); 
	console.log("data id of clicked page----------"+cl);

	var ht = document.getElementById("btn-"+cl).innerHTML;
	console.log("inner html of clicked page----------"+ht); */
	 
	  /* if(cl == ht){
		  $('#btn-'+cl).addClass("btn-danger");
		 }  */

	  $(this).toggleClass('btn-danger');
	
	 
 });

 </script>
 
</body>
</html>