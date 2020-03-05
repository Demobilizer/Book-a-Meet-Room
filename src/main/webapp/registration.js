$(function(){
	

		var username_state = false;
		var email_state = false;
		var contact_state = false;
		
		$('#uName').on('blur', function() {
					
			var username = $('#uName').val();
			
			$.ajax({

				type : 'GET',
				url :   '/Book_a_Meet_Room/checkAvailUserName?uName='+username,
				
				success : function(result) {

							if (result === true) {
								
								username_state = true;
								
								
							} else {

								username_state = false;
								
								
							}
					
					},
						error : function() {
								alert("error in getting user names!!");
							}
					});
					
				});
		
		
		
		$('#uEmail').on('blur', function() {
			
			var useremail = $('#uEmail').val();
			
			$.ajax({

				type : 'GET',
				url :   '/Book_a_Meet_Room/checkAvailEmail?uEmail='+useremail,
				
				success : function(result) {

							if (result === true) {
								
								email_state = true;
								
								
							} else {

								email_state = false;
								
								
							}
					
					},
						error : function() {
								alert("error in getting user email Ids!!");
							}
					});
					
				});
		

		
		
			$('#uContact').on('blur', function() {
			
			var userecontact = $('#uContact').val();
			
			$.ajax({

				type : 'GET',
				url :   '/Book_a_Meet_Room/checkAvailContactNo?uContact='+userecontact,
				
				success : function(result) {

							if (result === true) {
								
								contact_state = true;
								
								
							} else {

								contact_state = false;
								
								
							}
					
					},
						error : function() {
								alert("error in getting user contact Nos!!");
							}
					});
					
				});
		
		
		
		
				 $("#btnSubmit").on('click', function() {
					 
			            var password = $("#txtPassword").val();
			            var confirmPassword = $("#txtConfirmPassword").val();
					
			            if (password != confirmPassword) {
			                alert("Passwords & Confirm Password do not match!!");
			                return false;
			            }
			            
			            if (username_state == false) {
			                alert("User name is not availabe!! please choose another one!");
			                return false;
			            }
			            
			            if (email_state == false) {
			                alert("Email Id already exists!!");
			                return false;
			            }
			            
			            if (contact_state == false) {
			                alert("Contact number already exists!!");
			                return false;
			            }
			            
			            return true;
			            
			        });
	
	
	
});