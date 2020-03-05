$(function() {

	var $facilities = $('#facilities');

	function addFacility(facility)
	{
		$facilities.append('<li data-id='+facility.facilityId+'> <span class="noedit name">' + facility.facilityName +	 '</span>' +
		 '<input class="edit name">'+
		 ' <button class="remove" data-id='+facility.facilityId+'>Remove</button>' + 
		 '  <button class="editFacility noedit">Edit</button>' +
		 '  <button class="saveEdit edit">Save</button>' +
		 '  <button class="cancleEdit edit">Cancle</button>' + '</li>' + '<br>');
	}

	$.ajax({

		type : 'GET',
		url :   '/Book_a_Meet_Room/facilities', 
		success : function(facilities) {
			//console.log('success', data);
			$.each(facilities, function(i, facility) {

				addFacility(facility);

			});
		},

		error : function() {

			alert('error in getting Facilities');

		}

	});

	$('#addFacility').on(
			'click',
			function() {

				var $facilityName = $('#facilityname');

				var facility = {

					facilityName : $facilityName.val(),

				};

				//console.log(facility);

				$.ajax({

					type : 'POST',
					url : '/Book_a_Meet_Room/facilities',
					//data: facility,
					data : JSON.stringify(facility),
					contentType : 'application/json',

					success : function(newFacility) {

						addFacility(newFacility);

					},

					error : function() {

						alert('error in adding Facility');

					}

				});

			});

		$facilities.delegate('.remove','click', function(){

			var $li = $(this).closest('li');
			
			$.ajax({

				type: 'DELETE',
				url: '/Book_a_Meet_Room/facilities/'+$(this).attr('data-id'),
				success: function(){
						$li.fadeOut(300, function(){
							$li.remove();
							
						})

				}

			});

		});


		$facilities.delegate('.editFacility','click', function(){

			var $li = $(this).closest('li');
			
			$li.find('input.name').val($li.find('span.name').html());
			
			$li.addClass('edit');

		});

		$facilities.delegate('.cancleEdit','click', function(){

			$(this).closest('li').removeClass('edit');
			
		});

		$facilities.delegate('.saveEdit','click', function(){

			var $li = $(this).closest('li');
			
			var facility = {
				
				facilityId: $li.attr('data-id'),
				facilityName : $li.find('input.name').val(),

			}

			$.ajax({

					type : 'PUT',
					url : '/Book_a_Meet_Room/facilities',
					//data: facility,
					data : JSON.stringify(facility),
					contentType : 'application/json',

					success : function(newFacility) {
						
						$li.find('span.name').html(facility.facilityName);
						$li.removeClass('edit');

					},

					error : function() {

						alert('error in adding Facility');

					}

				});
			
		});



});
