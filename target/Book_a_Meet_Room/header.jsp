<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    
<!DOCTYPE html>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
   
 
  </head>
<body>
  


<div class="top_nav">
          <div class="nav_menu">
            <nav>
              
              <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
              </div>

              <ul class="nav navbar-nav navbar-right">
                <li class="">
                  <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                    <!-- <img src="images/img.jpg" alt=""> -->
                    
                    						<c:choose>

														<c:when test="${userBean.photoPath != null}">
												            <img alt="image not avail.." src="images/${userBean.photoPath}">
												         </c:when>

														<c:otherwise>
												            <img alt="image not avail.."  src="images/new-user.jpg">
												         </c:otherwise>
												</c:choose>
                    
                     Hi ${userBean.fullName}
                    <span class=" fa fa-angle-down"></span>
                  </a>
                  <ul class="dropdown-menu dropdown-usermenu pull-right">
                  
                    <c:if test="${userBean.userRole != 'ROLE_ADMIN'}">
                    		<li><a href="editPersonalDetails"> Edit Profile</a></li>
                    		<li><a href="feedBackForm">Feedback</a></li>
                   			<li><a href="<c:url value="/j_spring_security_logout" />"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                    </c:if>
                    <c:if test="${userBean.userRole == 'ROLE_ADMIN'}">
                    		<li><a href="<c:url value="/j_spring_security_logout" />"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                    </c:if>
                    
                  </ul>
                </li>

                <li role="presentation" class="dropdown">
                  <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">
                    <i class="fa fa-envelope-o"></i>
                    <span class="badge bg-green">2</span>
                  </a>
                  <ul id="menu1" class="dropdown-menu list-unstyled msg_list" role="menu">
                    <li>
                      <a>
                        <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                        <span>
                          <span>John Smith</span>
                          <span class="time">3 mins ago</span>
                        </span>
                        <span class="message">
                          Film festivals used to be do-or-die moments for movie makers. They were where...
                        </span>
                      </a>
                    </li>
                    <li>
                      <a>
                        <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                        <span>
                          <span>John Smith</span>
                          <span class="time">3 mins ago</span>
                        </span>
                        <span class="message">
                          Film festivals used to be do-or-die moments for movie makers. They were where...
                        </span>
                      </a>
                    </li>
                    
                    
                    
                  </ul>
                </li>
              </ul>
            </nav>
          </div>
        </div>




</body>
</html>