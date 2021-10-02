$(function(){
	new Swiper('.swiper-container',{
		autoplay:5000,
		loop:true,
		paginationClickable :true,
		lazyLoading : true,
		pagination : '.swiper-pagination',
		paginationBulletRender: function (swiper, index, className) {
	      	return '<div class="' + className + '">'
	      	+'<img class="img-rounded" width="100px" height="62px" src="img/swiperImg/show0'+(index+1)+'.jpeg"/>'
	      	+'</div>';
	  	}
	});
});
