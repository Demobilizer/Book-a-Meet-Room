<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Personal Detail</title>

<jsp:include page="req_css.jsp" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js">
</script>

<script>
    $(function() {
     $.get("/Book_a_Meet_Room/departments", function(responseJson) {
        var $select = $("#dropdownlist");
        $select.find("option").remove();  
        $.each(responseJson, function(index, department) {
        $("<option>").val(department.departmentName).text(department.departmentName).appendTo($select);
        });                   
 
    }); 
    });
</script>

<!-- <script type="text/javascript">
    function hideValue() {
        document.getElementById('pwd').value = "";
    }
</script> -->

<style type="text/css">

	.in-file {
    display: block;
    visibility: hidden;
    width: 0;
    height: 0;
}

</style>




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
				
					<h3>${userBean.fullName}</h3>

					  <div class="clearfix"></div>

					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
							<!-- 	<div class="x_title">
									<h2>Plain Page</h2>

									<div class="clearfix"></div>
								</div> -->
								
								
                 
                  <div class="x_content">

                    <div class="left col-xs-7">
                    
                    	<c:if test="${msg != null}"><p style="color: green;" align="center">${msg }</p></c:if>
                    	<c:if test = "${successMailReqSent != null}">
											<p style="color: green;" align="center">${successMailReqSent}</p>
						</c:if>
                    
					<form id="demo-form" modelAttribute="userDtoo" action="saveEditedPersonalDetails" method="post">
									
									<input type="hidden" name="userId" value="${userBean.userId}">
									
										Full Name:
										<input type="text" id="fullname" class="form-control" name="fullName" 
										value="${userBean.fullName}"   />

										User Name:
										<input type="text" id="user-name" class="form-control" name="userName"  
										value="${userBean.userName}" data-id="${userBean.userId}" />
										<small><a href="" class="checkAvailUserName" >
										Check if Available!</a></small> <br />
										
										Email Id:
										<input type="text" id="old-email" class="form-control" name="emailId" 
										value="${userBean.emailId}" readonly  />
										<small><a href="" class="reqToChangeEmail" data-id="${userBean.userId}" >
										request to change e-mail Id!</a></small> <br />

										Contact Number:
										<input type="text" id="" class="form-control" name="contactNo"  
										value="${userBean.contactNo}"  /> 
										
										Department: 
										<input type="text" id="" class="form-control" name="department"  
										value="${userBean.department}" readonly/> 
										<small><a href="" class="changeDept" id="" data-id="${userBean.userId}" >
										request to change Department!</a></small> <br />
										
										User Role:
																				
										<c:choose>
														<c:when test="${userBean.userRole == 'ROLE_USER' }">
															<input type="text" id="" class="form-control" name="userRole"  
															value="Developer"  readonly />
												         </c:when>
												         <c:when test="${userBean.userRole == 'ROLE_T' }">
												         	<input type="text" id="" class="form-control" name="userRole"  
															value="TL"  readonly />
												         </c:when>

														<c:otherwise>
															<input type="text" id="" class="form-control" name="userRole"  
															value="PM"  readonly />
												         </c:otherwise>
												</c:choose>
										 
										
										<%-- Account Status:
										<input type="text" id="" class="form-control" name="accountStatus" 
										value="${userToEdit.accountStatus}" style="background-color:gray;" readonly /> --%>

										<!-- User Role:
										<input type="text" id="" class="form-control" name="userRole"  
										value="user role here.." readonly />  -->
											
											<%-- <c:if test="${msg != null}"><p style="color: green;">	${msg }</p></c:if> --%>
											
											<br /><input type="submit" class="btn btn-primary" value="Save" />
											
											<c:choose>
														<c:when test="${userBean.userRole == 'ROLE_USER' }">
															<a href="userDashboard">  <input type="button" class="btn btn-primary" value="Cancel" /> </a>
												         </c:when>
												         <c:when test="${userBean.userRole == 'ROLE_T' }">
												            <a href="tlDashboard">  <input type="button" class="btn btn-primary" value="Cancel" /> </a>
												         </c:when>

														<c:otherwise>
												            <a href="pmDashboard">  <input type="button" class="btn btn-primary" value="Cancel" /> </a>
												         </c:otherwise>
												</c:choose>
											
											 
									</form>
								</div>
								<div class="col-xs-1"></div>
								
								<div class="left col-xs-4 text-center" style="margin-top:10px;">
										   
										   <form method="post" action="saveFile?userId=${userBean.userId}" enctype="multipart/form-data">
										   
										   		<input type="hidden" name="${userBean.userId}">
										   
												<div id="img-div">

												<c:choose>

														<c:when test="${userBean.photoPath != null}">
												            <img alt="image not avail.." class="img-responsive" src="images/${userBean.photoPath}">
												         </c:when>

														<c:otherwise>
												            <img alt="image not avail.." class="img-responsive" src="images/new-user.jpg">
												         </c:otherwise>
												</c:choose>
													<!-- <img alt="image not avail.." class="img-responsive" src="images/new-user.jpg"> -->
												</div>
												<br/>
											
													<input type="file" name="imgFile" size="chars" class="in-file" accept=".jpg, .png, .jpeg, .JPG, .JPEG, .PNG">
													<!-- <a class="btn btn-success" href="" type=""><i class="fa fa-edit m-right-xs"></i>Upload new Photo</a> -->
													<button class="btn-file btn btn-success">
														<i class="fa fa-edit m-right-xs"></i>Upload new Photo
													</button>
												
											<input type="submit" class="btn btn-primary saveBtn" value="Save" />
											
										</form>
										
										
										<c:if test = "${fail != null}">
											<p style="color: red;">${fail}</p>
										</c:if>
										
										<c:if test = "${incorrectExt != null}">
											<p style="color: red;">${incorrectExt}</p>
										</c:if>
										
										<c:if test = "${success != null}">
											<p style="color: green;">${success}</p>
										</c:if>
										
								</div>

                    

                  </div>
								</div>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

																	<div class="modal" id="myModal" role="dialog">
																		<div class="modal-dialog" id="modalDialog">
															
																			<div class="modal-content">
																				<div class="modal-header">
																					<button type="button" class="close" data-dismiss="modal">&times;</button>
																					<h2 class="modal-title">Email change Request</h2>
																				</div>
															
																				<div class="modal-body" style="">
															
															
																					<form id="this-form" action="requestToChangeEmailId" method="post">
															
																						<input type="hidden" name="userId" value="${userBean.userId}">
															
																						Old Email Id: <input type="text" id="" class="form-control"
																							name="oldEmailId" value="${userBean.emailId}" readonly /> <br />
															
																						New Email Id: <input type="text" id="fullname"
																							class="form-control" name="newEmailId" /> <br /> Reason to
																						change:
																						<textarea class="form-control" rows="3" name="reason"></textarea>
																						<br />
															
																						<div class="modal-footer">
															
																							<input type="submit" class="btn btn-primary"
																								style="margin-right: -8px; margin-top: 5px;"
																								data-dismiss="modal" value="Send"
																								onclick='$("#this-form").submit();' />
															
																							<button class="btn btn-primary" data-dismiss="modal"
																								style="margin-right: -8px; margin-left: 8px; margin-top: 5px;">Cancel</button>
																						</div>
															
																					</form>
															
															
																				</div>
															
															
															
																			</div>
															
															
																		</div>
																	</div>
																	
																	<div class="modal" id="my-dept-modal" role="dialog">
																		<div class="modal-dialog" id="modalDeptDialog">
															
																			<div class="modal-content">
																				<div class="modal-header">
																					<button type="button" class="close" data-dismiss="modal">&times;</button>
																					<h2 class="modal-title">Department change Request</h2>
																				</div>
															
																				<div class="modal-body" style="">
															
															
																		<form id="this-dept-form" action="requestToChangeDept" method="post">
															
																						<input type="hidden" name="userId" value="${userBean.userId}">
															
																						Old Department: <input type="text" id="" class="form-control"
																							name="oldDept" value="${userBean.department}" readonly /> <br />
															
																					Select new Department: <select id="dropdownlist" class="form-control" name="newDept" >   
																						</select>
																						 <br /> 
																						
																						Reason to change:
																						<textarea class="form-control" rows="3" name="reason"></textarea>
																						<br />
															
																						<div class="modal-footer">
															
																							<input type="submit" class="btn btn-primary"
																								style="margin-right: -8px; margin-top: 5px;"
																								data-dismiss="modal" value="Send"
																								onclick='$("#this-dept-form").submit();' />
															
																							<button class="btn btn-primary" data-dismiss="modal"
																								style="margin-right: -8px; margin-left: 8px; margin-top: 5px;">Cancel</button>
																						</div>
															
																					</form>
															
															
																				</div>
															
															
															
																			</div>
															
															
																		</div>
																	</div>






	</div>
		
<script type="text/javascript">
$('.btn-file').click(function(e){
	e.preventDefault();
    $('.in-file').click();
});
</script>

	<!-- /page content -->

<jsp:include page="req_js_jQuery.jsp" />
<script src="editDetail.js"></script>
 <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

</body>
</html>