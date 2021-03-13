$(document).ready(function(){
		var urls = "/loaisanpham/ #myModals";
		$('#model-body').html(event).load(urls);
		$('#bnn').on('click', function(event){
			event.preventDefault();
		});
		
		$('.edit').on('click', function(event){
			event.preventDefault();
			$('#myEdit').modal('show');
		});
		
		$('.buttonDelete').on('click', function(event){
			event.preventDefault();
			$('#myDelete').modal('show');
			
			$('#delRef').click(function(){
				$('#formDelete').submit();
			});
		});
	});