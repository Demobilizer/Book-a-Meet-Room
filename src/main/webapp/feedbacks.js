$(function(){

	var $x_content = $('#x_content');
	
	function dateFormat(date1)
	{
		var d = new Date(date1);
		var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
		var hours = (d.getHours() < 10) ? "0" + d.getHours() : d.getHours();
		var minutes = (d.getMinutes() < 10) ? "0" + d.getMinutes() : d.getMinutes();
		var seconds = (d.getSeconds() < 10) ? "0" + d.getSeconds() : d.getSeconds();
		var formattedTime = hours + ":" + minutes + ":" + seconds;

		formattedDate = formattedDate + " " + formattedTime;
		
		return formattedDate;
	}
	
	
	function getData(value, pageId) {
		
				$.ajax({
					
					type : 'GET',
					url : '/Book_a_Meet_Room/viewAllReadUnReadFeedbacks?type='+value+'&pageId='+pageId,
		
					success : function(map) {
						
						console.log(map);
		
						var tp = [];
						
						for (i = 1; i <= map.totalPages; i++) {
							  
							tp.push({
								"tpid":i
						    });
															
						}
						
						map.totalPages = tp;
						
						if(value === "read")
						{
							var newKey = "radioValue";
							var newVal = value;
		
							map[newKey] = newVal;
						}
						
						else if(value === "unread")
						{
							var newKey = "radioValue";
							var newVal = value;
		
							map[newKey] = newVal;
						}
						
						else if(value === "all")
						{
							var newKey = "radioValue";
							var newVal = value;
		
							map[newKey] = newVal;
						}
						
						console.log("from map radio ------"+map.radioValue);
						
						$.each(map.feedBacks1, function(i, m) {
							
							var x = dateFormat(m.created);
							m.created = x;
							
							console.log("created----"+m.created);
							
						});
						
						//$x_content.innerHTML = "";
						var feedbacksTemplate = $('#feedbacks-template').html();
						
						var html = Mustache.render(feedbacksTemplate,map);
						$x_content.html(html);
						
						/* Below is the code to send each userDto object separately and rendering json object.......................
						 * $.each(map.userDto1, function(i, m) {
		
							var html = Mustache.render(userTemplate, {m, pageNo: map.pageNo, totalPages: map.totalPages});
							$x_content.html(html);
							
						});*/
						
		
				},
		
					error : function() {
		
						alert('error in getting Feedbacks!!');
		
				}
		
			});
		
		
	}
	
	
		    $('input:radio').change(function(){
		        
		    	var value = $("input[name='fbType']:checked"). val();
		    	var pageId = 1;
		    	
		    	getData(value, pageId);
						    
		    });   
    
    
			$('body').on('click','.getPaging', function(){
				var pageId = $(this).attr('data-id');
				var radioVal = $(this).attr('data-type');
					
				getData(radioVal, pageId);
				
			});
    
    

});