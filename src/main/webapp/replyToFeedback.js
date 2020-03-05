$(function(){

var $modal = $('#modalDialog');
	
	$('body').on('click','.getModal', function(){ 
		
		var id = $(this).attr('data-id'); 
		console.log(id);
		
		$.ajax({

			type : 'GET',
			url :   '/Book_a_Meet_Room/getSingleFeedback?id='+id,
			
			success : function(feedback) {
				//console.log('success', data);
				
				var responseTemplate= $('#response-template').html();
				
				$modal.html(Mustache.render(responseTemplate, feedback));
				
			},

			error : function() {

				alert('error in getting Feedback!!');

			}
			

		});
		
	});
	
});
