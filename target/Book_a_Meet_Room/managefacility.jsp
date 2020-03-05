<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hello User!</title>

 <jsp:include page="req_css.jsp" />
 
 <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js">
</script>
  <script src="facility_main.js"></script>
  
  <style type="text/css">

ul li .edit{
	display: none;
}
ul li.edit .edit{
	display: initial;
}
ul li.edit .noedit{
	display: none;
}

</style>

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
              
                <h3>Add Facilities here...</h3>
               
            <div class="clearfix"></div>

            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <!-- <div class="x_title">
                    <h2>Plain Page</h2>
                    
                    <div class="clearfix"></div>
                  </div> -->
                  <div class="x_content">

											<form id="demo-form">

												Facility Name : <input type="text" id="facilityname"
													class="form-control" name="facilityName" /> <br />
												<input type="submit" class="btn btn-primary" value="Add"
													id="addFacility" />

											</form>

											<h2>Facility List</h2>

												<ul id="facilities">

												</ul>
							                     
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