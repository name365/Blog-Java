//[Dashboard Javascript]

//Project:	EduAdmin - Responsive Admin Template
//Primary use:   Used only for the main dashboard (index.html)


$(function () {

  'use strict';
	
	var options = {
          series: [{
          name: 'Hours spent',
          data: [8, 9, 2, 4, 7, 1, 6]
        }],
          chart: {
		  foreColor:"#bac0c7",
          type: 'bar',
          height: 200,
          stacked: true,
          toolbar: {
            show: false
          },
          zoom: {
            enabled: true
          }
        },
        responsive: [{
          breakpoint: 480,
          options: {
            legend: {
              position: 'bottom',
              offsetX: -10,
              offsetY: 0
            }
          }
        }],		
		grid: {
			show: true,
			borderColor: '#f7f7f7',      
		},
		colors:['#6993ff'],
        plotOptions: {
          bar: {
            horizontal: false,
            columnWidth: '20%',
		    colors: {
				backgroundBarColors: ['#f0f0f0'],
				backgroundBarOpacity: 1,
			},
          },
        },
        dataLabels: {
          enabled: false
        },
 
        xaxis: {
          categories: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
        },
        legend: {
          show: false,
        },
        fill: {
          opacity: 1
        }
        };

        var chart = new ApexCharts(document.querySelector("#charts_widget_1_chart"), options);
        chart.render();
	
	
	
	
		var options = {
          series: [5, 11, 3],
			labels: ['In Progress', 'Completed', 'Ye to Start'],
          chart: {
          width:328,
          type: 'donut',
        },
        dataLabels: {
          enabled: false
        },
        responsive: [{
          breakpoint: 480,
          options: {
            chart: {
              width: 200
            },
            legend: {
              show: false
            }
          }
        }],
		colors:['#04a08b', '#ec8000', '#f3f6f9'],
        legend: {
          position: 'right',
          height: 230,
        }
        };

        var chart = new ApexCharts(document.querySelector("#charts_widget_2_chart"), options);
        chart.render();
	
	
		var options = {
		  chart: {
			height: 180,
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
