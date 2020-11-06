$(document).ready(function(){

		var urls = "/sanpham/ #myModals";
		$('#model-body').html(event).load(urls);
		$('#bnn').on('click', function(event){
			event.preventDefault();
			$('#myModal').modal("hide");
		});
		
		
		$('.edit').on('click', async (evt) =>{
			evt.preventDefault();
			let href = evt.currentTarget.href;
			form = await new Promise((resolve)=>{
				$.ajax({ url: href }).done((resp)=>{ resolve(resp)}).fail((err)=>{'<span class="alert-danger alert">Lỗi</span>'});
			})
			console.log(form);
			$('#edit-body').html(form);
			
			// $('#lspUpdate option:selected').each(function(){
			// 	$(this).prop('selected', false);
			// });
			// var url = $(this).attr('href');
			// var id = GetURLParameter(url, 'id');
			// console.log(id);
			// $.ajax({
			// 	type: "GET",
			// 	contentType: "application/json",
			// 	url : "/sanpham/sanpham-update",
			// 	data: {
			// 		id: id
			// 	},
			// 	dataType: "json",
			// 	success: function(data){
			// 		console.log(data);
			// 		$('#iUpdate').val(data.id);
			// 		$('#mUpdate').val(data.masanpham);
			// 		$('#tUpdate').val(data.tensanpham);
			// 		var lsp = data.loaisanpham;
			// 		for (let item in lsp) {
			// 			$('#lspUpdate option[value="' + item.id + '"]').prop('selected', true);
			// 		}
			// 		$('#xUpdate').val(data.xuatxu);
			// 		$('#mtUpdate').val(data.mota);
			// 	}
			// });
		});
		
		$('.chi').on('click', function(event){
			event.preventDefault();
			$('#myChitiet').modal();
		});
		
		$('.buttonDelete').on('click', function(event){
			$('#myDelete').modal();
		});
		$('#delRef').click(function(){
			$('#formDelete').submit();
		});
		
		$('.new').on('click', function(event){
			event.preventDefault();
			var href = $(this).attr('href');
			$.get(href, function(act, st){
				$('#idsanpham').val(act.id);
			})
		});
		
		$('#fileImages').change(function(){
			showImageThumbnail(this);
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