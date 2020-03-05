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

<jsp:include page="left-bar.jsp" />
<jsp:include page="header.jsp" /> 


 <div class="right_col" role="main" style="min-height: 958px;">
          <div>
            <div class="page-title">
              
                <h3>Welcome User</h3>
               
            <div class="clearfix"></div>

            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                 <!--  <div class="x_title">
                    <h2>Plain Page</h2>
                    
                    <div class="clearfix"></div>
                  </div> -->
                  <div class="x_content">
                      <h3>Manage Meeting/ Conference Room(s):</h3>
                      <a href="createRoom">
                      	<button type="button" class="btn btn-success btn-lg">Create Room</button>
                      </a>
                      <a href="manageRoom">
                      	<button type="button" class="btn btn-success btn-lg">Manage Room(s)</button>
                     </a>
                     <a href="manageFacilities">
                      	<button type="button" class="btn btn-success btn-lg">Manage Facilities</button>
                     </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div> 

              
            </div>


</div>
</div>
 <jsp:include page="req_js_jQuery.jsp" />
</body>
</html>