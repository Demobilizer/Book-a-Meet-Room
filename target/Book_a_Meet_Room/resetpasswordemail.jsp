<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reset password!</title>

 <jsp:include page="req_css.jsp" />

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
            
            <form action="resetPassword" method="post">
            
					Enter your registered mail ID here... <br/><br/>

						<div class="input-group">
                            <input type="text" class="form-control" name="email">
                            <span class="input-group-btn">
                                              <input type="submit" class="btn btn-primary" value="Go!">
                                          </span>
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
	                          			${mailNotSent} <br/><br/>
	                          			<a href="login">Login</a><br/>
	                          			<a href="login#signup">Register here</a>
	                          		</p>
	                          		</c:if>
	                          		
                          		</div>
                         
          </div>
        </div>
        
      </div>
    </div>

</body>
</html>