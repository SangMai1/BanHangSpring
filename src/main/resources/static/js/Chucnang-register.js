	$(document).ready(function(){
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
					$('.them').show();
				} else {
					$('.them').hide();
					alert("Bạn không dùng được chức năng này");
				}
			},
		});
		
		var href = $('.sach').attr('href');
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
					$('.sach').show();
				} else {
					$('.sach').hide();
					alert("Bạn không dùng được chức năng này");
				}
			}
		});
	});