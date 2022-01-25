//[widget charts Javascript]


$(function () {
    "use strict"; 
	
		$('.owl-carousel').owlCarousel({
			loop: true,
			margin: 10,
			responsiveClass: true,
			autoplay: true,
			responsive: {
			  0: {
				items: 1,
				nav: false
			  },
			  600: {
				items: 3,
				nav: false
			  },
			  1000: {
				items: 4,
				nav: false,
				margin: 20
			  }
			}
		  });
	
}); // End of use strict
