	$(document).ready(function(){
		var urls = "/vaitro/";
	
	
		
		$('#model-body').html(event).load(urls);
		$('#bnn').on('click',function(event){
			event.preventDefault();
			
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
			$('#myEdit').modal('hide');
			$('input[name="chucnang"]').prop("checked", false);
			var uu = $(this).attr('href');
			var id = GetURLParameter(uu, 'id');
			$.ajax({
				contentType: "application/json",

				url : "/vaitro/update",
				data: {
					id: id
				},
				dataType: "json",
				timeout: 10000,
				success: function(data){

					console.log(data);
					$('#idUpdate').val(data.id);
					$('#mUpdate').val(data.mavaitro);
					$('#tUpdate').val(data.tenvaitro);
					var cns = getData(data.chucnangs);
					if(cns != null){
						$('input[name="chucnang"]').val(cns);
					};
				}
			});
		});
		
		$('.chi').on('click', function(event){
			event.preventDefault();
			$('#myChitiet').modal("hide");
			
		});
		
	});
	
	function GetURLParameter(sPageURL, sParam){
		var sURLVariable = sPageURL.split('?');
		var sParameterName = sURLVariable[1].split('=');
			if(sParameterName[0] == sParam){
				return sParameterName[1];
			}
	}
	
	function getData(data){
		if(data == "[]"){
			return null;
		} else {
			var temp = data.substring(1, data.length-1);
			console.log(temp);
			return temp.split(", ");
		}
	}