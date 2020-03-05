$(function(){
	
	var $modal = $('#modalDialog');
		
	$('#all-rooms').on('click','.getModal', function(){ 
	
			
		var id = $(this).attr('data-id'); 
		console.log(id);
		
		$.ajax({

			type : 'GET',
			url :   '/Book_a_Meet_Room/viewSingleAvailRoom?id='+id,
			
			success : function(room1) {
				//console.log('success', data);
				
				var roomTemplate= $('#room-template').html();
				
				$modal.html(Mustache.render(roomTemplate, room1));
				
			},

			error : function() {

				alert('error in getting Room!!');

			}
			

		});
		
		
		
		
	});
	
	
	
	/*$('.btnClick').click(function(){
	
		var date = document.getElementById("datepicker").value;
		console.log(date);
		document.getElementById("dateHidden").value = date;
		
		var fromTime = document.getElementById("timepicker1").value;
		console.log(fromTime);
		document.getElementById("timeFromHidden").value = fromTime;
		
		var toTime = document.getElementById("timepicker2").value;
		console.log(toTime);
		document.getElementById("timeToHidden").value = toTime;
		
        
	});*/
	

	
	
});

	
	
	
	
	
	

	
		
	//$(".close-btn1").click(function(){
		  
		/*$('.close-btn1').on(
				'click',
				function() {
		console.log("close btn clicked..");
		$modal.innerHTML = "";
		
	});*/
	



