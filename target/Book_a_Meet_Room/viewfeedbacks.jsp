<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Users</title>
<jsp:include page="req_css.jsp" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js">
</script>
 <script src="vendors/jquery/dist/mustache.js"></script>

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
							<h3>Feedbacks</h3> 
						</div>
						<div style="float: left; padding-top: 12px; padding-left: 200px;">
							<input type="radio" name="userType" value="all" checked="checked"> All Feedbacks 
							<input type="radio" name="userType" value="active"> Unread Feedbacks
							<input type="radio" name="userType" value="active"> Read Feedbacks
							
						</div>
						
						
					</div>
					
					 <div class="clearfix">
					 				
									
					</div> 

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
                    
                    <!-- <form action="approveUser" modelAttribute=""> -->
                    
                    <table class="table table-striped projects">
                    
                    	<c:if test="${responseMailSent != null}"><h4 style="color: green;" align="center">${responseMailSent}</h4></c:if>
                    
                      <thead>
                        <tr>
                          <th style="width: 2%">No.</th>
                          <th style="width: 14%">Date</th>
                          <th style="width: 15%">From</th>
                          <th style="width: 35%">Feedback</th>
                          <th style="width: 20%">#Manage Feedbacks</th>
                        </tr>
                      </thead>
                      <tbody>
                        
                        <% int i = 1;  %>
                        
                        <c:forEach var="fb" items="${feedBacks}">
                        
                        <tr>
	                          <td><% out.println(i); %>   </td>
	                          
	                        <td>
	                             ${fb.created}
                          	</td>
                          
                          <td> ${fb.userBean.fullName}</br>
                          
                          (<c:choose>
														<c:when test="${fb.userBean.userRole == 'ROLE_USER' }">
												            User
												         </c:when>
												         <c:when test="${fb.userBean.userRole == 'ROLE_T' }">
												            TL
												         </c:when>

														<c:otherwise>
												            PM
												         </c:otherwise>
												</c:choose>)
                          
                             </td>
                          
                          <td>  ${fb.feedBack}  </td>
                          
                          <td>
                          
                             	<button class="getModal btn btn-primary btn-xs" 
																			id="reply-${fb.id}"  data-id='${fb.id}'  
																			data-toggle="modal" data-target="#myModal" >
																			<i class="fa fa-pencil"></i> Reply 
																		</button>
	                            	 
	                            	<c:if test="${fb.readStatus == false}"><a href="readFeedBack?id=${fb.id}" class="btn btn-info btn-xs" 
	                            	data-toggle="tooltip" title="Mark as read" data-placement="right">
	                            	<i class="fa fa-folder"></i> Read </a></c:if>
	                            	
	                            	<c:if test="${fb.readStatus == true}"><a href="readFeedBack?id=${fb.id}" class="btn btn-info btn-xs" 
	                            	data-toggle="tooltip" title="Mark as unread" data-placement="right">
	                            	<i class="fa fa-folder"></i> Unread </a></c:if>
                            
                          </td>
                          
                        </tr>
                        
                        <% i++; %>
                        </c:forEach>
                        
                        </tbody>
                    </table>
                    
                    <!-- </form> -->
                    
                    <!-- end project list -->
					
					<div class="col-md-12 col-sm-12 col-xs-12 text-center">
                        <ul class="pagination pagination-split">
					<c:forEach begin="1" end="${totalPages+1}" varStatus="loop">

								<li class="btn-info btn-active" data-id="${loop.index}" >
								<a href="viewFeedBacks?pageId=${loop.index}" id="btn-${loop.index}">${loop.index}</a>  </li>      

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

													
													
													
													<div class="modal" id="myModal" role="dialog">
															<div class="modal-dialog" id="modalDialog">
															
													</div>
														</div>
																							
																							<!-- Modal content-->
																							
																<template id="response-template">
																				
																				<div class="modal-content">
																					<div class="modal-header">
																						<button type="button" class="close"
																							data-dismiss="modal">&times;</button>
																						<h2 class="modal-title">Response to Feedback</h2>
																					</div>
																					
																					<div class="modal-body" style="">
									
									
									<form id="demo-form" action="submitFeedBackResponse?id={{id}}" method="post">
									
									<%-- <input type="hidden" name="userId" value="${userBean.userId}"> --%>
									
										From:
										<input type="text" id="" class="form-control" name="fromEmailId" 
										value="mehulkumar.kapadiya@neosofttech.com"  readonly  /> <br/>
										
										To:
										<input type="text" id="fullname" class="form-control" name="toEmailId" 
										value="{{userBean.emailId}}"  readonly /> <br/>
										
										<div style="margin-left: 50px;">
												Feedback:
												<textarea class="form-control" rows="3" name="feedBack" readonly >{{feedBack}}</textarea>
										</div>
										<br/>
									
										<div style="margin-right: 50px;">
										Response Message:
										<textarea class="form-control" rows="3" name="feedBackResponse" ></textarea>
										  <br/>
										  </div>
										  
																				<div class="modal-footer">
																						
																						<input type="submit" class="btn btn-primary"
																							 style="margin-right: -8px; margin-top: 5px;"
																							 data-dismiss="modal" value="Send"
																							 onclick='$("#demo-form").submit();' />
																							
																						<button  class="btn btn-primary" data-dismiss="modal" 
																							 style="margin-right: -8px; margin-left: 8px; margin-top: 5px;">Cancel</button>
																					</div>																			 
									</form>
																						
																						
																					</div>


																				</div>
																
                             									</template>


	</div>
 
 <jsp:include page="req_js_jQuery.jsp" />
 <script src="replyToFeedback.js"></script>
 <script>
		$(document).ready(function() {
			$('[data-toggle="tooltip"]').tooltip();
		});
	</script>
 
</body>
</html>