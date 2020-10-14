$(document).ready(function(){
		var urls = "/chucnang/ #myModals";
		$('#model-body').html(event).load(urls);
		$('#bnn').on('click',function(event){
			event.preventDefault();
			$('#myModal').modal("hide");
		});
		
		$('.buttonDelete').on('click', function(event){
			event.preventDefault();
			$('#myDelete').modal();
			$('#delRef').click(function(){
				$('#formDelete').submit();
			});
			
		});
		
		$('.edit').on('click', function(event){
			event.preventDefault();
			$('#myEdit').modal("hide");
			
		});
		
		$('.chi').on('click', function(event){
			event.preventDefault();
			$('#myChitiet').modal("hide");
			
			});
		});

	