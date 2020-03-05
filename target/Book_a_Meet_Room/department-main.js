$(function() {

	var $departments = $('#departments');

	var departmentTemplate= $('#department-template').html();
	
	
	function addDepartment(department)
	{
		$departments.append(Mustache.render(departmentTemplate, department));
	}

	$.ajax({

		type : 'GET',
		url :   '/Book_a_Meet_Room/departments', 
		success : function(departments) {
			//console.log('success', data);
			$.each(departments, function(i, department) {

				addDepartment(department);

			});
		},

		error : function() {

			alert('error in getting Departments');

		}

	});

	$('#addDepartment').on(
			'click',
			function() {

				var $departmentName = $('#departmentname');

				var department = {

					departmentName : $departmentName.val(),

				};

				//console.log(department);

				$.ajax({

					type : 'POST',
					url : '/Book_a_Meet_Room/departments',
					//data: department,
					data : JSON.stringify(department),
					contentType : 'application/json',

					success : function(newDepartment) {

						addDepartment(newDepartment);

					},

					error : function() {

						alert('error in adding Department');

					}

				});

			});

		$departments.delegate('.remove','click', function(){

			var $li = $(this).closest('li');
			
			$.ajax({

				type: 'DELETE',
				url: '/Book_a_Meet_Room/departments/'+$(this).attr('data-id'),
				success: function(){
						$li.fadeOut(300, function(){
							$li.remove();
							
						})

				}

			});

		});

		$departments.delegate('.editDepartment','click', function(){

			var $li = $(this).closest('li');
			
			$li.find('input.name').val($li.find('span.name').html());
			
			$li.addClass('edit');

		});

		$departments.delegate('.cancleEdit','click', function(){

			$(this).closest('li').removeClass('edit');
			
		});

		$departments.delegate('.saveEdit','click', function(){

			var $li = $(this).closest('li');
			
			var department = {
				
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



});
