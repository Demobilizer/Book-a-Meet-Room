<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
    

    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mail change Requests</title>
<jsp:include page="req_css.jsp" />

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>


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
												         <c:when test="${userBean.userRole == 'ROLE_P' }">
												            <jsp:include page="left-bar-pm.jsp" />
												         </c:when>

														<c:otherwise>
												            <jsp:include page="left-bar.jsp" />
												         </c:otherwise>
												</c:choose>      

<jsp:include page="header.jsp" /> 

<!-- page content -->
	<div class="right_col" role="main" style="min-height: 958px;">
		<div>
		
			<div class="page-title">
					
					<div>
					<div style="float: left;">
						<h3>Manage change Mail-Id Requests</h3>
					</div>
					<!-- <div align="right">
						<h3><a href="viewAllUsers?pageId=1">View All Users</a></h3>
					</div> -->
					</div>
					

					 <div class="clearfix">
					 	<c:if test="${approveSuccess != null}">
												
							<div class="alert alert-success" role="alert">${approveSuccess}</div>
												
						</c:if>
						<c:if test="${rejectSuccess != null}">
												
							<div class="alert alert-success" role="alert">${rejectSuccess}</div>
												
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
								<div class="x_content">
									
									<!-- YOUR CONTENT GOES HERE  in this div tag-->

                    <!-- start project list -->
                    
                    <table class="table table-striped projects">
                      <thead>
                        <tr>
                          <th style="width: 2%">No.</th>
                          <th style="width: 10%">From</th>
                          <th style="width: 10%">Current Email Id</th>
                          <th style="width: 10%">Requested Email Id</th>
                          <th style="width: 15%">Reason to change</th>
                          <th style="width: 29%">#Manage Requests</th>
                        </tr>
                      </thead>
                      <tbody>
                        
                        <% int i = 1;  %>
                        
                        <c:forEach var="pendingMailReqList" items="${pendingMailReqList}">
                        
                        <tr>
	                          <td><% out.println(i); %>   </td>
	                          
	                          <td>
	                            <a>${pendingMailReqList.fromUser.fullName}</a>
                            	
                          </td>
                          
                          <td> ${pendingMailReqList.tempOldData}  </td>
                          
                          <td> ${pendingMailReqList.tempNewData}  </td>
                          
							<td> ${pendingMailReqList.reason}  </td>
                          
                          <td>
                          
                          
                             <a href="approveMailChangeRequest?reqManId=${pendingMailReqList.req_id}" class="approve-btn btn btn-primary btn-xs">
                             <i class="fa fa-folder" ></i> Approve </a>
						 
						 
							<span id="rejectBtn-${pendingMailReqList.req_id}" class="btn btn-danger btn-xs"
							data-toggle="collapse" data-target="#remark-${pendingMailReqList.req_id}">
							<i class="fa fa-trash-o"></i> Reject </span>
                            
                          </td>
                          
                        </tr>
                        
                        
                        <tr>
	                          <td></td>
	                          
	                          <td>
	                            
                            	
                          </td>
                          
                          <td> </td>
                          
                          <td>   </td>
                          
							
                          
                          <td colspan="2">
                          <form action="rejectMailChangeRequest" method="post">
		                          						<input type="hidden" name="req_id" value="${pendingMailReqList.req_id}">
															<div id="remark-${pendingMailReqList.req_id}" class="collapse" align="center">
															Reason to Reject:
															<textarea class="" rows="2" name="remark" ></textarea>
																
																<button type="submit" class="btn btn-primary btn-xs" ><i class="fa fa-folder" ></i> Save  </button>
															
															</div> 
                            </form>
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