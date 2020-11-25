	$(document).ready(function(){
		var urls = "/nhom/";
		$('#model-body').html(event).load(urls);
		$('#btn').on('click',function(event){
			event.preventDefault();
			$('#myModal').modal("hide");
		
		});
		
		$('.buttonDelete').on('click', function(event){
			event.preventDefault();
			$('#myDelete').modal("show");
			$('#delRef').click(function(){
				$('#formDelete').submit();
			});
		});

		$('.edit').on('click', function(event){
			event.preventDefault();
			$('input[name="chucnang"]').prop("checked", false);
			var uu = $(this).attr('href');
			var id = GetURLParameter(uu, 'id');
			$.ajax({
				contentType: "application/json",
				//url: "/login/check",
				url : "/nhom/nhom-update",
				data: {
					id: id
				},
				dataType: "json",
				timeout: 10000,
				success: function(data){
					console.log(data);
					$('#idUpdate').val(data.id);
					$('#mUpdate').val(data.manhom);
					$('#tUpdate').val(data.tennhom);
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
		
		$('#search').on('click', function(event){
			event.preventDefault();
			$('#search').show();

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