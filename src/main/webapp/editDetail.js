$(function(){
	

	$('body').on('click', '.checkAvailUserName', function(e) {

		e.preventDefault();

		/*var id = $(this).attr('data-id'); 
		console.log("user id to change dept!-----------"+id);*/

		var userName = $('#user-name').val();
		console.log("user name is-------" + userName);

		$.ajax({

			type : 'GET',
			url : '/Book_a_Meet_Room/checkAvailUserName?uName=' + userName,

			success : function(result) {

				if (result === true) {
					swal({
						title : "Congrats!",
						text : "This username is available!",
						icon : "success",
						button : "Thanks!",
					});
				} else {
					swal({
						title : "Sorry!",
						text : "This username is not available!",
						icon : "error",
						button : "Try another!",
					});
				}

			},

			error : function() {

				alert('error in comparing user name!!');

			}

		});

	});
	

	$('body').on('click', '.reqToChangeEmail', function(e) {

		e.preventDefault();

		var id = $(this).attr('data-id'); 
		console.log("user id to change dept!-----------"+id);

		$.ajax({

			type : 'GET',
			url : '/Book_a_Meet_Room/checkAlreadyRequestedMailId?userId='+id,

			success : function(result) {

				if (result === true) {
					
					$("#myModal").modal('show');
				} 
				else {
					
					swal({
						title : "Sorry!",
						text : "your request is already pending! Please wait till its responded!",
						icon : "error",
						button : "Okay! :(",
					});
				}

			},

			error : function() {

				alert('error in fetching data!!');

			}

		});

	});
	
	
	$('body').on('click', '.changeDept', function(e) {

		e.preventDefault();

		var id = $(this).attr('data-id'); 
		console.log("user id to change dept!-----------"+id);

		$.ajax({

			type : 'GET',
			url : '/Book_a_Meet_Room/checkAlreadyRequestedDept?userId='+id,

			success : function(result) {

				if (result === true) {
					
					$("#my-dept-modal").modal('show');
				} 
				else {
					
					swal({
						title : "Sorry!",
						text : "your request is already pending! Please wait till its responded!",
						icon : "error",
						button : "Okay! :(",
					});
				}

			},

			error : function() {

				alert('error in fetching data!!');

			}

		});

	});
	
	


});