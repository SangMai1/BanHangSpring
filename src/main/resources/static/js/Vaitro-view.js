	$(document).ready(function(){
		var urls = "/vaitro/ #myModals";
		$('#model-body').html(event).load(urls);
		$('#bnn').on('click',function(event){
			event.preventDefault();
			var url = $('#myModals').attr('action');
			$.ajax({
				contentType: "application/json",
				url: "/login/check",
				data: {
					url: url
				},
				dataType: "json",
				timeout: 10000,
				success: function(data){
					if(data > 0){
						$('#myModal').modal("show");
						
					} else {
						alert("Bạn không dùng được chức năng này");
						$('#myModal').modal("hide");
					}
				},
			});
		});
		
		$('.buttonDelete').on('click', function(event){
			event.preventDefault();
			var url = $('#formDelete').attr('action');
			console.log(url);
			$.ajax({
				contentType: "application/json",
				url: "/login/check",
				data: {
					url: url
				},
				dataType: "json",
				success: function(data){
					console.log(data);
					if(data > 0){
						$('#myDelete').modal("show");
					} else {
						alert("Bạn không dùng được chức năng này");
						$('#myDelete').modal("hide");
					}
				},
			});
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
//				url: "/login/check",
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
			var url = $('.chi').attr('href');
			$.ajax({
				contentType: "application/json",
				url: "/login/check",
				data: {
					url: url
				},
				dataType: "json",
				timeout: 10000,
				success: function(data){
					if(data > 0){
						$('#myChitiet').modal("show");
					} else {
						$('#myChitiet').modal("hide");
						alert("Bạn không dùng được chức năng này");
					}
				},
			});
		});
		
		$('#search').on('click', function(event){
			event.preventDefault();
			var url = $('#dataSearch').attr('action');
			$.ajax({
				contentType: "application/json",
				url: "/login/check",
				data: {
					url: url
				},
				dataType: "json",
				timeout: 10000,
				success: function(data){
					if(data > 0){
						$('#search').show();
					} else {
						$('#search').hide();
						alert("Bạn không dùng được chức năng này");
					}
				},
			});
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