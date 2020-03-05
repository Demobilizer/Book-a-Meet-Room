$(function(){

	var $x_content = $('#x_content');
	var $tableData = $('#table-data');
	
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
					url : '/Book_a_Meet_Room/viewAllActiveRejectedDeletedUsers?type='+value+'&pageId='+pageId,
		
					success : function(map) {
		
						var tp = [];
						
						for (i = 1; i <= map.totalPages; i++) {
							  
							tp.push({
								"tpid":i
						    });
															
						}
						
						map.totalPages = tp;
						
						if(value === "active")
						{
							var newKey = "radioValue";
							var newVal = value;
		
							map[newKey] = newVal;
						}
						
						else if(value === "rejected")
						{
							var newKey = "radioValue";
							var newVal = value;
		
							map[newKey] = newVal;
						}
						
						else if(value === "deleted")
						{
							var newKey = "radioValue";
							var newVal = value;
							
							var newKey1 = "isDeleted";
							var newVal1 = true;
		
							map[newKey] = newVal;
							map[newKey1] = newVal1;
						}
						
						else if(value === "all")
						{
							var newKey = "radioValue";
							var newVal = value;
		
							map[newKey] = newVal;
						}
						
						
						//console.log("from map ------"+map.radioValue);
						
						$.each(map.userDto1, function(i, m) {
							
							var x = dateFormat(m.created);
							m.created = x;
							
									if(m.updated != null){
										
										var x = dateFormat(m.updated);
										m.updated = x;
										
									}
							
						});
						
						//$x_content.innerHTML = "";
						var userTemplate = $('#users-template').html();
						
						var html = Mustache.render(userTemplate,map);
						$x_content.html(html);
						
						/* Below is the code to send each userDto object separately and rendering json object.......................
						 * $.each(map.userDto1, function(i, m) {
		
							var html = Mustache.render(userTemplate, {m, pageNo: map.pageNo, totalPages: map.totalPages});
							$x_content.html(html);
							
						});*/
						
		
				},
		
					error : function() {
		
						alert('error in getting Users!!');
		
				}
		
			});
		
		
	}
	
	
		    $('input:radio').change(function(){
		        
		    	var value = $("input[name='userType']:checked"). val();
		    	var pageId = 1;
		    	
		    	getData(value, pageId);
						    
		    });   
    
    
			$('body').on('click','.getPaging', function(){
				var pageId = $(this).attr('data-id');
				var radioVal = $(this).attr('data-type');
					
				getData(radioVal, pageId);
				
				console.log(radioVal);
				
			});
    
    

});