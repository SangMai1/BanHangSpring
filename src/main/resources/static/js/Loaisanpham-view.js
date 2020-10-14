$(document).ready(function(){
		var urls = "/loaisanpham/ #myModals";
		$('#model-body').html(event).load(urls);
		$('.bnn').on('click', function(event){
			event.preventDefault();
			$('#myModal').modal();
		});
		
		$('.edit').on('click', function(event){
			event.preventDefault();
			$('#myEdit').modal();
		});
		
		$('.buttonDelete').on('click', function(event){
			event.preventDefault();
			$('#myDelete').modal();
			
			$('#delRef').click(function(){
				$('#formDelete').submit();
			});
		});
	});