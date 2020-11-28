$(document).ready(function(){
		$('.edit').on('click', function(event) {
		event.preventDefault();
		var uu = $(this).attr('href');
		console.log(uu);
		var id = GetURLParameter(uu, 'id');
		console.log(id);

		$.ajax({
			type: "GET",
			contentType: "application/json",
			url: "/sanphamdamua/sanphamdamua-chitiet",
			data: {
				id: id
				
			},
			dataType: "json",
			timeout: 10000,
			success: function(data){

				console.log(data);
				$('#id_edit').val(data.billdetail_id);
				$('#parent_edit').val(data.billdetail_status);
				
			
			}
		});

	});
		
		$('#searchtrangthaidamua_select').on('click',function(event){
			event.preventDefault();
			var x = document.getElementById("searchtrangthaidamua_select").selectedIndex;
			var y = document.getElementById("searchtrangthaidamua_select").options;
			window.location.href ="http://localhost:8090/sanphamdamua/dataSearchTrangThai?searchtrangthaidamua=" + y[x].value;
		});
})

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