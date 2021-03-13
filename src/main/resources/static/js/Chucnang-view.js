$(document).ready(function() {
	var urls = "/chucnang/ #myModals";
	$('#model-body').html(event).load(urls);
	$('#bnn').on('click', function(event) {
		event.preventDefault();
		$('#myModal').modal("hide");
	});

	$('.buttonDelete').on('click', function(event) {
		event.preventDefault();
		$('#myDelete').modal();
		$('#delRef').click(function() {
			$('#formDelete').submit();
		});

	});

	$('.edit').on('click', function(event) {
		event.preventDefault();
		$('#myEdit').modal("hide");
		var href = $(this).attr('href');
		$.get(href, function(cn, st) {
			$('#id_edit').val(cn.id);
			$('#ma_edit').val(cn.machucnang);
			$('#ten_edit').val(cn.tenchucnang);
			$('#maapi_edit').val(cn.maapi);
			$('#parent_edit').val(cn.parentid);
		});

	});

	$('.chi').on('click', function(event) {
		event.preventDefault();
		$('#myChitiet').modal("hide");

	});
});
