	$(document).ready(function(){
		var urls = "/nhom/ #myModals";
		$('#model-body').html(event).load(urls);
		$('#btn').on('click',function(event){
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
			$.ajax({
				contentType: "application/json",
				url: "/login/check",
				data: {
					url: url
				},
				dataType: "json",
				success: function(data){
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
			var url = $('#formEdit').attr('action');
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
						$('#myEdit').modal("show");
					} else {
						alert("Bạn không dùng được chức năng này");
						$('#myEdit').modal("hide");
					}
				},
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