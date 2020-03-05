$(function(){
	
	var $date = "";
	var $startTime = "";
	var $endTime = "";
	
	var $allRooms = $('#all-rooms');
	var allRoomTemplate = $('#allRoomTemplate').html();
	
	function addRoom(userRoomBookDto)
	{
		$allRooms.append(Mustache.render(allRoomTemplate, userRoomBookDto));
	}
	
	function getRoomDetails(dateVal, startTimeVal, endTimeVal) {
		
		document.getElementById("all-rooms").innerHTML = "";
		document.getElementById("no-rooms").innerHTML = "";
		
		var userRoomBookDto = {
				
				//facilityName : $facilityName.val(),
				date: dateVal,
				startTime: startTimeVal,
				endTime: endTimeVal,
		
			};
	
		
		$.ajax({

			type : 'POST',
			dataType: "json", 
			url :   '/Book_a_Meet_Room/getAvailRooms',
			data : JSON.stringify(userRoomBookDto),
			contentType : 'application/json',
			success : function(userRoomBookDtos) {
				//console.log(Object.entries(userRoomBookDtos).length);
				if(Object.entries(userRoomBookDtos).length == 0){
					//console.log("from if block......");
					var $noRooms = $('#no-rooms');
					var noRoomTemplate = $('#noRoomTemplate').html();
					//var msg = "Sorry! No Room is Available for this time duration! Please try to search with different Timings!";
					$noRooms.html(Mustache.render(noRoomTemplate));
				}
				else {
					//console.log("from else block......");
					$.each(userRoomBookDtos, function(i, userRoomBookDto) {
						addRoom(userRoomBookDto);
					});
				}
			},
			error : function() {
				swal("Error", "Please enter the different time slot!","error");
			}
		});
	}
	
	
$('.btnClick').click(function(){
		
		// following line is useful to clear the old appended data each time when btnClicked!!!
	
		/*document.getElementById("all-rooms").innerHTML = "";
		document.getElementById("no-rooms").innerHTML = "";*/
	
	console.log("coming to js-------");
	
			 $date = $('#datepicker').val();
			 $startTime = $('#timepicker1').val();
			 $endTime = $('#timepicker2').val();
			 
			//start time
			 //var start_time = $("#start_time").val();

			 //end time
			// var end_time = $("#end_time").val();

			 //convert both time into timestamp
			 var stt = new Date("November 13, 2013 " + $startTime);
			 //var stt = new Date(+$date + $startTime);
			 stt = stt.getTime();

			 var endt = new Date("November 13, 2013 " + $endTime);
			// var endt = new Date(+$date + $endTime);
			 endt = endt.getTime();

			 //by this you can see time stamp value in console via firebug
			 console.log("Time1: "+ stt + " Time2: " + endt);

			 if(stt > endt) {
			    
			     $("#timepicker2").after(swal("Error in Entered Times!", "Starting time MUST be lower than Ending time!","error"));
			         return false;
			 }
			 
			 getRoomDetails($date, $startTime, $endTime);
	});
	
	var $modal = $('#modalDialog');
	
	$('body').on('click','.getModal', function(){ 
		
		var id = $(this).attr('data-id'); 
		console.log(id);
		
		
		
		//console.log("date before getModal success function......"+$date.val());                <----------- WORKING...
		
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
	
	
	
$('body').on('click','.bookRoomClick', function(){ 
		
		var roomId = $(this).attr('data-id'); 
		console.log("room-id from book room JS-------"+roomId);
		
		var userId = $(this).attr('data-user-id'); 
		console.log("user id from book room JS-------------"+userId);
		
		/*console.log("date from book room JS on book btn click---------"+$date.val()); 
		console.log("startTime from book room JS on book btn click---------"+$startTime.val());
		console.log("endTime from book room JS on book btn click---------"+$endTime.val());*/
		
		 var dateT = $('#datepicker').val();
		 var startTimeT = $('#timepicker1').val();
		 var endTimeT = $('#timepicker2').val();
		
		var userRoomBookDto = {
				
				date: dateT,
				startTime: startTimeT,
				endTime: endTimeT,
				roomDto: {
					roomId: roomId
				},
				userDto: {
					userId: userId
				}
				
		
			};
		
		
		$.ajax({

			type : 'POST',
			//	dataType: "json",   // if we keep this line. it'll not success.. it'll result into error, even after performing the correct operation!!!!!
			url :   '/Book_a_Meet_Room/bookThisRoom',
			data : JSON.stringify(userRoomBookDto),
			contentType : 'application/json',
			
			success : function(data) {
				
				swal("Room Booking request sent Successfully!", "You'll get notified as soon as any action is taken!","success");
				
				 getRoomDetails(dateT, startTimeT, endTimeT);
				 
			},

			error : function() {

				swal("Error", "Please enter the time slot!","error");

			}
			

		});
		
		
		
		
	});
	
	

	
	
});

	
	
	
	
	
	

	
	

