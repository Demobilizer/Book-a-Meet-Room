<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Register Here </title>

    <!-- Bootstrap -->
    <link href="vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="vendors/animate.css/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="build/css/custom.min.css" rel="stylesheet">
    
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    
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
   
   <script type="text/javascript">
    $(function () {
        $("#btnSubmit").click(function () {
            var password = $("#txtPassword").val();
            var confirmPassword = $("#txtConfirmPassword").val();
			 if (password != confirmPassword) {
                alert("Passwords & Confirm Password do not match!!");
                return false;
            }
            return true;
        });
    });
</script> 
    
    
  </head>

  <body class="login">
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
          
          	<c:url value='/j_spring_security_check' var='loginVar' />
          	
            <form action="${loginVar}" method="post">
              <h1>Login Form</h1>
              
              <c:if test="${passwordResetSuccess != null}">
              		<p style="color: green;">${passwordResetSuccess}</p>
              	</c:if>
              
              <div>
                <input type="text" class="form-control" placeholder="Username" name="custom_username" required="required" />
              </div>
              <div>
                <input type="password" class="form-control" placeholder="Password" name="custom_password" required="required" />
              </div>
              <div>
              	<c:if test="${param.error != null}">
              		<p style="color: red;">Invalid username or password!</p>
              	</c:if>
              </div>
              
              <div>
                <input type="submit" class="btn btn-default submit" value="Log in" />
                <a class="reset_pass" href="requestToResetPassword">Lost your password?</a>
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">New to site?
                  <a href="#signup" class="to_register"> Create Account </a>
                </p>

                <div class="clearfix"></div>
                <br />

                <div>
                  <h1><i class="fa fa-paw"></i>  NeoSoft Technologies!</h1>
                </div>
              </div>
            </form>
          </section>
        </div>

        <div id="register" class="animate form registration_form">
          <section class="login_content">
            <form action="addUser" method="post" modelAttribute="userDto">
              <h1>Create Account</h1>
              <div>
                <input type="text" class="form-control" placeholder="Full name" required="required" name="fullName" />
              </div>
              <div>
                <input type="text" class="form-control" placeholder="Create Username" required="required" name="userName" />
              </div>
              <div>
                <input type="password" class="form-control" placeholder="Password" required="required" name="password" id="txtPassword" />
              </div>
              <div>
                <input type="password" class="form-control" placeholder="Confirm Password" required="required" id="txtConfirmPassword" />
              </div>
              <div>
                <input type="email" class="form-control" placeholder="Email" required="required" name="emailId"/>
              </div>
              <div>
                <input type="text" class="form-control" placeholder="Contact No." required="required" name="contactNo"/>
              </div>
              
              <div>
                <select id="dropdownlist" class="form-control" name="department" >   </select>
              </div><br/>
              
              <div>
                <input type="submit" class="btn btn-default submit" value="Submit" id="btnSubmit" />
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">Already a member ?
                  <a href="#signin" class="to_register"> Log in </a>
                </p>

                <div class="clearfix"> </div>
                <br />

                <div>
                  <h1><i class="fa fa-paw"></i> NeoSoft Technologies!</h1>
                  
                </div>
              </div>
            </form>
          </section>
        </div>
      </div>
    </div>
  </body>
</html>


