$(document).ready(function(){
	var urls = "/nguoidung/";
	$('#model-body1').html(event).load(urls);
	$('#btn').on('click', function(event){
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
	
	$('.edit-record').on('click', function(event){
		event.preventDefault();
			$('input[name="nhomnguoidung"]').prop("checked", false);
			$('input[name="vaitro"]').prop("checked", false);
//			var url = $('#formEdit').attr('action');
		var uu = $(this).attr('href');
		console.log(uu);
		var id = GetURLParameter(uu, 'id');
		console.log(id);
		$.ajax({
			type: "GET",
			contentType: "application/json",
			url: "/nguoidung/nguoidung-update",
			data: {
				id: id
				
			},
			dataType: "json",
			timeout: 10000,
			success: function(data){

				console.log(data);
				$('#idUpdate').val(data.id);
				$('#mUpdate').val(data.manguoidung);
				$('#tUpdate').val(data.tennguoidung);
				$('#pUpdate').val(data.password);
				$('#eUpdate').val(data.email);
				$('#phoneUpdate').val(data.phone);
				$('input:radio[value="' + data.gender + '"]').prop('checked', true);
				var ns = getData(data.nhoms);
					if(ns != null){
						$('input[name="nhomnguoidung"]').val(ns);
					};
				var vts = getData(data.vaitros);
					if(vts != null){
						$('input[name="vaitro"]').val(vts);
					};
			}
		});
	});
	
	$('.view').on('click', function(event){
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