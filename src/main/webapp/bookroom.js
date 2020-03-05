$(function(){
	
	//var $modal = $('#modalDialog');
	//var date = "";
	
	var $date = "";
	var $startTime = "";
	var $endTime = "";
	 $date = $('#datepicker');
	 $startTime = $('#timepicker1');
	 $endTime = $('#timepicker2');
	
	$('body').on('click','.bookRoomClick', function(){ 
		
		var id = $(this).attr('data-id'); 
		console.log("room-id from book room JS-------"+id);
		
		//date = document.getElementById("datepicker").value;
		
		console.log("date from book room JS on book btn click---------"+$date.val()); 
		console.log("startTime from book room JS on book btn click---------"+$startTime.val());
		console.log("endTime from book room JS on book btn click---------"+$endTime.val());
		
		/*$.ajax({

			type : 'GET',
			url :   '/Book_a_Meet_Room/bookThisRoom?roomId='+id,
			
			success : function(room1) {
				//console.log('success', data);
				
				var roomTemplate= $('#room-template').html();
				
				$modal.html(Mustache.render(roomTemplate, room1));
				
			},

			error : function() {

				//alert('error in getting Room!!');

			}
			

		});*/
		
		
		
		
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