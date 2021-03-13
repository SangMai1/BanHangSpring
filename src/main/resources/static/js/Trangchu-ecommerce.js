$(document).ready(function() {
  $("#sidebarCollapse").on("click", function(){
    $("#sidebar").addClass("active");
  });

  $("#sidebarCollapseX").on("click", function(){
    $("#sidebar").removeClass("active")
  });

  $("#sidebarCollapse").on("click", function(){
    if($("#sidebar").hasClass("active")){
      $(".overplay").addClass("visible");
      console.log("it's working");
    }
  });

  $("#sidebarCollapseX").on("click", function(){
    $(".overplay").removeClass("visible");
  })
})