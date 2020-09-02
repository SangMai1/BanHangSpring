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
		
	
function validate(){
	var manguoidung = document.form.manguoidung.value;
	var tennguoidung = document.form.tennguoidung.value;
	var password = document.form.password.value;
	var email = document.form.email.value;
	var createday = document.form.createday.value;
	var nhomnguoidung = document.form.nhomnguoidung.value;
	var chucnang = document.form.chucnang.value;
	var vaitro = document.form.vaitro.value;
	var pattern = "^[a-zA-Z][a-zA-Z0-9._]+[@][a-z0-9A-Z]+([.][a-z0-9A-Z]+)+$";
	if(manguoidung == null || manguoidung == ""){
		alert("vui long nhap ma");
		return false;
	}
	else if(tennguoidung == null || tennguoidung == ""){
		alert("vui long nhap ten");
		return false;
	}
	else if(password == null || password == "" ){
		alert("vui long nhap password");
		return false;
	}
	else if(password.length < 6){
		alert("ki tu password phai lon hon 6");
		return false;
	}
	else if(email == null || email == ""){
		alert("vui long nhap email");
		return false;
	}
	else if(!pattern.contains(email)){
		alert("vui long nhap dung dinh dang email");
		return false;
	}
	else if(createday == null || createday == ""){
		alert("vui long nhap createday");
		return false;
	}
	else if(nhomnguoidung == null || nhomnguoidung == ""){
		alert("vui long chon nhomnguoidung");
		return false;
	}
	else if(chucnang == null || chucnang == ""){
		alert("vui long chon chuc nang");
		return false;
	}
	else if(vaitro == null || vaitro == ""){
		alert("vui long chon vai tro");
		return false;
	}
	
}