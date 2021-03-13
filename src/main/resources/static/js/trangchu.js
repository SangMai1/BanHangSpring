//$(document).ready(function(){
//	var urls = "/web/login1 #myModals";
//	$("#model-body").html(event).load(urls);
//	$(".dropdown-content-li-a").on('click', function(event){
//		event.preventDefault();
//		$("#myModal").modal();
//	});
//	
//	var urls1 = "/web/checkout #myModals1";
//	$("#model-body1").html(event).load(urls1);
//	$(".dropdown-content-li-a1").on('click', function(event){
//		event.preventDefault();
//		$("#myModal1").modal();
//	});
//})

// navigation

	var navOpen = document.querySelector(".nav__hamburger");
	let navClose = document.querySelector(".close__toggle");
	let menu = document.querySelector(".nav__menu");

	navOpen.addEventListener("click", () => {
			  const navLeft = menu.getBoundingClientRect().left;
			  if(navLeft < 0){
			    menu.style.left = 0;
			    document.body.classList.add("active");
			  } else {
			    menu.style.left = "-40rem";
			    document.body.classList.remove("active");
			  }
			});

	navClose.addEventListener("click", () => {
	  const navLeft = menu.getBoundingClientRect.left;
	  if(navLeft > 0){
	    menu.style.left = 0;
	  } else {
	    menu.style.left = "-40rem";
	    document.body.classList.remove("active");
	  }
	});

// Smooth Scroll

const navBar = document.querySelector(".navigation");
const scrollLinks = document.querySelectorAll(".scroll_link");

Array.from(scrollLinks).forEach(link => {
  link.addEventListener("click", e => {

    // Prevent Default
    e.preventDefault;

    const id = e.currentTarget.getAttribute("href").slice(1);
    const element = document.getElementById(id);
    const navHeight = document.getBoundingClientRect().height;
    const fixNav = navBar.classList.contains("fix__nav");
    let position = element.offsetTop - navHeight;

    if(!fixNav) {
      position = position - navHeight;
    }

    window.scrollTo({
      left: "0",
      top: position
    });

    menu.style.left = "-40rem";
    document.body.classList.remove("active");
  });
});

//Fix Nav
window.addEventListener("scroll", () => {
  const navHeight = navBar.getBoundingClientRect().height;
  const scrollHeight = window.pageYOffset;

  if(scrollHeight > navHeight) {
    navBar.classList.add("fix__nav");
  } else {
    navBar.classList.remove("fix__nav");
  }
})
