$(document).ready(function(){
	$('.buttonDelete').on('click', function(event) {
		event.preventDefault();
		$('#myDelete').modal();
		$('#delRef').click(function() {
			$('#formDelete').submit();
		});

	});
	
	$('.chi').on('click', function(event){
		event.preventDefault();
		$('#myChitiet').modal();
		
		});
})