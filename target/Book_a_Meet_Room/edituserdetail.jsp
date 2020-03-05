<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

        

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>User Detail</title>

<jsp:include page="req_css.jsp" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js">
</script>

<script type="text/javascript">
    function setValue() {
        
        var showPwdBtnVal = document.getElementById('showPwd').value;
        if(showPwdBtnVal == "Show")
            {
            	document.getElementById('pwd').value = '${userToEdit.password}';
            	document.getElementById('showPwd').value = "Hide";
            }
	if(showPwdBtnVal == "Hide"){
        /* document.getElementById('showPwd').value = "Hide"; */
         document.getElementById('pwd').value = ""; 
		document.getElementById('showPwd').value = "Show";
	}
   }
</script>
<!-- <script type="text/javascript">
    function hideValue() {
        document.getElementById('pwd').value = "";
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
				
					<h3>${userToEdit.fullName}</h3>

					  <div class="clearfix"></div>

					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
							<!-- 	<div class="x_title">
									<h2>Plain Page</h2>

									<div class="clearfix"></div>
								</div> -->
								
                 
                  <div class="x_content">

                    
					<form id="demo-form" modelAttribute="userDtoo" action="updateUser" method="post">
									
									<input type="hidden" name="userId" value="${userToEdit.userId}" />
									
									
										Full Name:
										<input type="text" id="fullname" class="form-control" name="fullName" 
										value="${userToEdit.fullName}"   />

										User Name:
										<input type="text" id="email" class="form-control" name="userName"  
										value="${userToEdit.userName}" style="background-color:gray;" readonly /> 
										
										Email Id:
										<input type="text" id="" class="form-control" name="emailId" 
										value="${userToEdit.emailId}" style="background-color:gray;" readonly  />

										Contact Number:
										<input type="text" id="" class="form-control" name="contactNo"  
										value="${userToEdit.contactNo}"  /> 
										
										Password:
										
										<input type="text" id="pwd" class="form-control" name="password" 
										<%-- value="${userToEdit.password}" --%> style="background-color:gray;" readonly />
										<input id="showPwd" type="button" value="Show" onclick="setValue()">
										<br/>

										Department:
										<input type="text" id="" class="form-control" name="department"  
										value="${userToEdit.department}" /> 
										
										Account Status:
										<input type="text" id="" class="form-control" name="accountStatus" 
										value="${userToEdit.accountStatus}" style="background-color:gray;" readonly />

										User Role:
										<input type="text" id="" class="form-control" name="userRole"  
										value="${userToEdit.userRole}" /> 
											

											<br /><input type="submit" class="btn btn-primary" value="Save" />
											<a href="viewAllUsers?pageId=${pageNo}"> <input type="button" class="btn btn-primary" value="Cancle" /></a>
									</form>


                    

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

<jsp:include page="req_js_jQuery.jsp" />

</body>
</html>