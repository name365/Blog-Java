//[Dashboard Javascript]

//Project:	EduAdmin - Responsive Admin Template
//Primary use:   Used only for the main dashboard (index.html)


$(function () {

  'use strict';
	
	  	
	var options = {
		  chart: {
			height: 325,
			type: "radialBar"
		  },

		  series: [77],
			colors: ['#0052cc'],
		  plotOptions: {
			radialBar: {
			  hollow: {
				margin: 15,
				size: "70%"
			  },
			  track: {
				background: '#ff9920',
			  },

			  dataLabels: {
				showOn: "always",
				name: {
				  offsetY: -10,
				  show: false,
				  color: "#888",
				  fontSize: "13px"
				},
				value: {
				  color: "#111",
				  fontSize: "30px",
				  show: true
				}
			  }
			}
		  },

		  stroke: {
			lineCap: "round",
		  },
		  labels: ["Progress"]
		};

		var chart = new ApexCharts(document.querySelector("#revenue5"), options);

		chart.render();
	
	
}); // End of use strict
