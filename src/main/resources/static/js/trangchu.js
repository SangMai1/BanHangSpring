$(document).ready(function(){
	var urls = "/web/login1 #myModals";
	$("#model-body").html(event).load(urls);
	$(".dropdown-coontent-li-a").on('click', function(event){
		event.preventDefault();
		$("#myModal").modal();
	});
	
	var urls1 = "/web/checkout #myModals1";
	$("#model-body1").html(event).load(urls1);
	$(".dropdown-coontent-li-a1").on('click', function(event){
		event.preventDefault();
		$("#myModal1").modal();
	});
})

