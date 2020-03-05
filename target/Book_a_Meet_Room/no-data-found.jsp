<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hello User!</title>

 <jsp:include page="req_css.jsp" />

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
												         <c:when test="${userBean.userRole == 'ROLE_ADMIN' }">
												            <jsp:include page="left-bar.jsp" />
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
              
                <h3>Plain Page</h3>
               
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
                    
                    <div class="clearfix"></div>
                  </div> -->
                  <div class="x_content">
                     
                     
                     <!-- CONTENT GOES HERE... -->
                     
					                     
								            <div class="text-center text-center">
								              <h1 class="error-number">Sorry!</h1> <br>
								              <h3>No Data Found!!</h3>
								              
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
</div>
 <jsp:include page="req_js_jQuery.jsp" />
</body>
</html>


