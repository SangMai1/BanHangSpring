	$(document).ready(function(){
		var urls = "/nguoidung/ #myModals";
		$('#model-body1').html(event).load(urls);
		$('#btn').click(function(event){
			event.preventDefault();
			
			var url = $("#myModals").attr("action");
			console.log(url);
			$.ajax({
				contentType: "application/json",
				url: "/login/check",
				data: {
					url : url
				},
				dataType: "json",
				timeout: 10000,
				success: function(data){
					console.log(data);
					if(data > 0){
						$('#myModal').modal("show");
						
					} else {
						$('#myModal').modal("hide");
						alert("Bạn không dùng được chức năng này")
					}
				},
			});	
		});
		$('.buttonDelete').on('click', function(event){
			event.preventDefault();
			var url = $("#formDelete").attr("action");
			console.log(url);
			$.ajax({
				type: "GET",
				contentType: "application/json",
				url: "/login/check",
				
				data : {
					url : url
				},
				dataType: "json",
				timeout: 10000,
				success: function(data){
					console.log(data);
					if(data > 0){
						$('#myDelete').modal("show");	
						
					} else {
						$('#myDelete').modal("hide");
						alert("Bạn không dùng được chức năng này");
					}
				},
			});
			$('#delRef').click(function(){
				$('#formDelete').submit();
			})
		});
		
		$('.table .edit-record').on('click', function(event){
			event.preventDefault();
//			$('input[name="chucnang"]').prop("checked", false);
//			var url = $('#formEdit').attr('action');
			var uu = $(this).attr('href');
			console.log(uu);
			var id = GetURLParameter(uu, 'id');
			console.log(id);
			$.ajax({
				type: "GET",
				contentType: "application/json",
//				url: "/login/check",
				url: "/nguoidung/nguoidung-update",
				data1: {
					id: id
					
				},
//				console.log(data1);
//				data: {
//					url: url
//				},
				dataType: "json",
				timeout: 10000,
				success: function(data){
//					if(data > 0){
//						$('#myEdit').modal("show");
//					} else {
//						$('#myEdit').modal("hide");
//						alert("Bạn không dùng được chức năng này");
//					}
					console.log(data1);
					$('#idUpdate').val(data.id);
					$('#mUpdate').val(data.manguoidung);
//					var temp = data.chucnang;
//					console.log(temp);
//					if(temp != null){
//						$('input[name="chucnang"]').val(temp);
//					}
				}
			});
		});
		
		$('.view').on('click', function(event){
			event.preventDefault();
			var url = $('.view').attr('href');
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
				}
			})
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