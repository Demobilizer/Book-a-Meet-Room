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

														<c:otherwise>
												            <jsp:include page="left-bar-pm.jsp" />
												         </c:otherwise>
												</c:choose>

<jsp:include page="header.jsp" /> 

<!-- page content -->
 <div class="right_col" role="main" style="min-height: 958px;">
          <div>
            <div class="page-title">
              
                <h3>Feedback</h3>
               
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
                     
                     			<form id="demo-form" action="submitFeedBack" method="post">
									
									<input type="hidden" name="userId" value="${userBean.userId}">
									
										<c:if test="${msg != null}"><p style="color: green;" align="center">${msg} </p></c:if>
									
										Full Name:
										<input type="text" id="fullname" class="form-control" name="fullName" 
										value="${userBean.fullName}"  readonly /> <br/>
										
										Email Id:
										<input type="text" id="" class="form-control" name="emailId" 
										value="${userBean.emailId}"  readonly  /> <br/>

										Contact Number:
										<input type="text" id="" class="form-control" name="contactNo"  
										value="${userBean.contactNo}" readonly />  <br/>
										
										Feedback:
										<textarea class="form-control" rows="3" name="feedBack" ></textarea>
										
											<br /><input type="submit" class="btn btn-primary" value="Submit" />
											
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