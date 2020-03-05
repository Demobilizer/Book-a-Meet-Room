$('.saveBtn').click(function(){
	
	var imageDetail = {
			
			departmentId: $li.attr('data-id'),
			departmentName : $li.find('input.name').val(),

		}

		$.ajax({

				type : 'PUT',
				url : '/Book_a_Meet_Room/departments',
				//data: department,
				data : JSON.stringify(department),
				contentType : 'application/json',

				success : function(newDepartment) {
					
					$li.find('span.name').html(department.departmentName);
					$li.removeClass('edit');

				},

				error : function() {

					alert('error in adding Department');

				}

			});
	
});
