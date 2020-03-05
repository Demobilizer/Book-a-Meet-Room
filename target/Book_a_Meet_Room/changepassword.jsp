<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hello User!</title>

 <jsp:include page="req_css.jsp" />
 
 <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js">
</script>
 <script type="text/javascript">
    $(function () {
        $("#btnSubmit").click(function () {
            var password = $("#newPwd").val();
            var confirmPassword = $("#confirmPwd").val();
			 if (password != confirmPassword) {
                alert("Passwords & Confirm Password do not match!!");
                return false;
            }
            return true;
        });
    });
</script>

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
              
                <h3>Change your Password</h3>
               
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
                     
                     
                     <form id="demo-form" action="setPassword" method="post">
                     
                      Old Password* :
                      <input type="password" id="oldPwd" class="form-control" name="oldPassword" data-parsley-id="20" required>
                      			

					 New Password* :
                      <input type="password" id="newPwd" class="form-control" name="newPassword" data-parsley-id="20" required>
                      			
                      			
                     Confirm Password* :
                      <input type="password" id="confirmPwd" class="form-control" name="confirmPwd" data-parsley-id="20" required>

                          	<br>
                          
                          				<c:if test = "${passwordChanged != null}">
											<p style="color: green;">${passwordChanged}</p>
										</c:if>
										
										<c:if test = "${wrongOldPwd != null}">
											<p style="color: red;">${wrongOldPwd}</p>
										</c:if>
										
							<br/>
                          
                          <input type="submit" class="btn btn-primary" value="Submit" id="btnSubmit">
                          <input type="button" class="btn btn-primary" onclick="myFunction()" value="Reset">
                          
                          						<c:choose>
														<c:when test="${userBean.userRole == 'ROLE_USER' }">
															<a href="userDashboard">  <span class="btn btn-primary">Cancel</span> </a>
												         </c:when>
												         <c:when test="${userBean.userRole == 'ROLE_T' }">
												            <a href="tlDashboard">  <span class="btn btn-primary">Cancel</span> </a>
												         </c:when>

														<c:otherwise>
												            <a href="pmDashboard">  <span class="btn btn-primary">Cancel</span> </a>
												         </c:otherwise>
												</c:choose>
                          
                    </form>
                     
                     
                     
                     
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

<script>
function myFunction() {
  document.getElementById("demo-form").reset();
}
</script>

 <jsp:include page="req_js_jQuery.jsp" />
</body>
</html>