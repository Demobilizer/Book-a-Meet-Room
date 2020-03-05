<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reset password!</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 <jsp:include page="req_css.jsp" />
 
  <script type="text/javascript">
    $(function () {
        $("#btnSubmit").click(function () {
            var password = $("#newPassword").val();
            var confirmPassword = $("#confirmPassword").val();
			 if (password != confirmPassword) {
                alert("Passwords & Confirm Password do not match!!");
                return false;
            }
            return true;
        });
    });
</script>

</head>
<body>

<div class="container body">
      <div class="main_container">
        <!-- page content -->
        <div class="col-md-12">
          <div class="col-middle">
            
            						<c:if test="${wrongReq != null}">
	                          		<p style="color: red;" align="center">
	                          			${wrongReq} <br/>
	                          		</p>
	                          		</c:if>
	                          		
	                          		<c:if test="${expiredReq != null}">
	                          		<p style="color: red;" align="center">
	                          			${expiredReq} <br/>
	                          		</p>
	                          		</c:if>
	                          		
	                          		<%-- <c:if test="${usedToken != null}">
	                          		<p style="color: red;" align="center">
	                          			${usedToken} <br/>
	                          		</p>
	                          		</c:if> --%>
            
            <form action="saveNewPassword" method="post">
            
					Change your password here... <br/><br/>

						<div class="input-group" align="center">
                          	<input type="hidden" value="${userId}" name="userId">
                            <input type="password" id="newPassword" class="form-control" name="newPassword">
                            <input type="password" id="confirmPassword" class="form-control" name="confirmPassword">
                            <input type="submit" value="Submit" class="btn btn-primary" id="btnSubmit">
                         </div>
                          
			</form>

                          		<div style="margin-top: 25px;" align="center">
                          		
	                          		<c:if test="${mailSent != null}">
	                          		<p style="color: green;">
	                          			${mailSent}
	                          		</p>
	                          		</c:if>
	                          		
	                          		<c:if test="${mailNotSent != null}">
		                          		<p style="color: red;">
		                          			${mailNotSent} 
		                          		</p>
	                          		</c:if>
	                          		
	                          		
	                          		
                          		</div>
                         

          </div>
        </div>
        
      </div>
    </div>

</body>
</html>