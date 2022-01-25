//[Javascript]



$(function () {
    "use strict";   

		$('#slimtest1').slimScroll({
            height: '300px'
        });
        $('#slimtest2').slimScroll({
            height: '300px'
        });
        $('#slimtest3').slimScroll({
            position: 'left'
            , height: '300px'
            , railVisible: true
            , alwaysVisible: true
        });
        $('#slimtest4').slimScroll({
            color: '#0bb2d4'
            , size: '10px'
            , height: '300px'
            , alwaysVisible: true
        });
	
  }); // End of use strict