$(document).ready(function() {
		$('.edit').on('click', function(event) {
			event.preventDefault();
			var href = $(this).attr('href');
			$.get(href, function(spvct, st){
				$("#id_edit").val(spvct.id);
				$("#kichthuoc_edit").val(spvct.kichthuoc);
				$("#soluong_edit").val(spvct.soluong);
				$("#giatien_edit").val(spvct.giatien);
				$("#giamgia_edit").val(spvct.giamgia);
			})
		});

		$('.buttonDelete').on('click', function(event) {
			$('#myDelete').modal();
		});
		$('#delRef').click(function() {
			$('#formDelete').submit();
		});

		$('#search_select').on('click',function(event){
			event.preventDefault();
			var x = document.getElementById("search_select").selectedIndex;
			var y = document.getElementById("search_select").options;
			window.location.href ="http://localhost:8090/sanphamchitiet/dataSearch?searchsize=" + y[x].text;
		});
	});

