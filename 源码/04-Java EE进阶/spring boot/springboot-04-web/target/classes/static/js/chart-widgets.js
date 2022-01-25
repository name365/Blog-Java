//[custom Javascript]



// Fullscreen
$(function () {
	'use strict'
	
	var options = {
          series: [{
          name: 'PRODUCT A',
          data: [44, 55, 41, 67, 22, 43]
        }, {
          name: 'PRODUCT B',
          data: [-44, -55, -41, -67, -22, -43]
        }],
          chart: {
		  foreColor:"#bac0c7",
          type: 'bar',
          height: 350,
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
		colors:['#6993ff', '#f64e60'],
        plotOptions: {
          bar: {
            horizontal: false,
            columnWidth: '10%',
            endingShape: 'rounded'
          },
        },
        dataLabels: {
          enabled: false
        },
 
        xaxis: {
          type: 'datetime',
          categories: ['01/01/2011 GMT', '01/02/2011 GMT', '01/03/2011 GMT', '01/04/2011 GMT',
            '01/05/2011 GMT', '01/06/2011 GMT'
          ],
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
        series: [{
            name: "Profit",
            data: [0, 40, 110, 70, 100, 60, 130, 55, 140, 125]
        }],
        chart: {
			foreColor:"#bac0c7",
          height: 350,
          type: 'area',
          zoom: {
            enabled: false
          }
        },
		colors:['#ea9715'],
        dataLabels: {
          enabled: false,
        },
        stroke: {
          	show: true,
			curve: 'smooth',
			lineCap: 'butt',
			colors: undefined,
			width: 2,
			dashArray: 0, 
        },		
		markers: {
			size: 2,
			colors: '#ea9715',
			strokeColors: '#ffffff',
			strokeWidth: 2,
			strokeOpacity: 0.9,
			strokeDashArray: 0,
			fillOpacity: 1,
			discrete: [],
			shape: "circle",
			radius: 5,
			offsetX: 0,
			offsetY: 0,
			onClick: undefined,
			onDblClick: undefined,
			hover: {
			  size: undefined,
			  sizeOffset: 3
			}
		},	
        grid: {
			borderColor: '#f7f7f7', 
          row: {
            colors: ['transparent'], // takes an array which will be repeated on columns
            opacity: 0
          },			
		  yaxis: {
			lines: {
			  show: true,
			},
		  },
        },
		fill: {
			type: "gradient",
			gradient: {
			  shadeIntensity: 1,
			  opacityFrom: 0.01,
			  opacityTo: 1,
			  stops: [0, 90, 100]
			}
		  },
        xaxis: {
          categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct'],
		  labels: {
			show: true,        
          },
          axisBorder: {
            show: true
          },
          axisTicks: {
            show: true
          },
          tooltip: {
            enabled: true,        
          },
        },
        yaxis: {
          labels: {
            show: true,
            formatter: function (val) {
              return val + "K";
            }
          }
        
        },
      };
      var chart = new ApexCharts(document.querySelector("#charts_widget_2_chart"), options);
      chart.render();
	
	
	// Composite line charts, the second using values supplied via javascript
    		
		$("#linechart").sparkline([32,24,26,24,32,26,40,34,22,24], {
			type: 'line',
			width: '100',
			height: '38',
			lineColor: '#f64e60',
			fillColor: '#ffffff',
			lineWidth: 2,
			minSpotColor: '#f64e60',
			maxSpotColor: '#f64e60',
		});
		$("#linechart2").sparkline([32,24,26,24,32,26,40,34,22,24], {
			type: 'line',
			width: '100',
			height: '38',
			lineColor: '#faa700',
			fillColor: '#ffffff',
			lineWidth: 2,
			minSpotColor: '#faa700',
			maxSpotColor: '#faa700',
		});
		$("#linechart3").sparkline([32,24,26,24,32,26,40,34,22,24,22,24,34,32,38,28,36,36,40,38,30,34,38], {
			type: 'line',
			width: '100%',
			height: '45',
			lineColor: '#1bc5bd',
			fillColor: '#ffffff',
			lineWidth: 2,
			minSpotColor: '#f64e60',
			maxSpotColor: '#f64e60',
		});
		$("#linechart4").sparkline([32,24,26,24,32,26,40,34,22,24], {
			type: 'line',
			width: '50%',
			height: '100',
			lineColor: '#1bc5bd',
			fillColor: '#ffffff',
			lineWidth: 2,
			minSpotColor: '#f64e60',
			maxSpotColor: '#f64e60',
		});
	
		$("#barchart").sparkline([32,24,26,24,32,26,40,34,22,24], {
			type: 'bar',
			height: '38',
			width: '100%',
			barWidth: 6,
			barSpacing: 4,
			barColor: '#3e8ef7',
		});
		$("#barchart2").sparkline([32,24,26,24,32,26,40,34,22,24], {
			type: 'bar',
			height: '38',
			barWidth: 6,
			barSpacing: 4,
			barColor: '#3e8ef7',
		});
		$("#barchart3").sparkline([32,24,26,24,32,26,40,34,22,24,22,24,34,32,38,28,36,36,40,38,30,34,38], {
			type: 'bar',
			height: '45',
			width: '100%',
			barWidth: 6,
			barSpacing: 4,
			barColor: '#f64e60',
		});
		$("#barchart4").sparkline([32,24,26,24,32,26,40,34,22,24], {
			type: 'bar',
			height: '100',
			width: '50%',
			barWidth: 6,
			barSpacing: 4,
			barColor: '#f64e60',
		});
	
		$("#discretechart").sparkline([32,24,26,24,32,26,40,34,22,24], {
			type: 'discrete',
			width: '50',
			height: '38',
			lineColor: '#6993ff',
		});
		$("#discretechart2").sparkline([32,24,26,24,32,26,40,34,22,24], {
			type: 'discrete',
			width: '50',
			height: '38',
			lineColor: '#6993ff',
		});
		$("#discretechart3").sparkline([32,24,26,24,32,26,40,34,22,24,22,24,34,32,38,28,36,36,40,38,30,34,38], {
			type: 'discrete',
			width: '180',
			height: '45',
			lineColor: '#0bb2d4',
		});
		$("#discretechart4").sparkline([32,24,26,24,32,26,40,34,22,24], {
			type: 'discrete',
			width: '100',
			height: '100',
			lineColor: '#0bb2d4',
		});
		
		$("#linearea").sparkline([32,24,26,24,32,26,40,34,22,24], {
			type: 'line',
			width: '100%',
			height: '80',
			lineColor: '#1bc5bd',
			fillColor: '#1bc5bd',
			lineWidth: 2,
		});
		$("#linearea2").sparkline([32,24,26,24,32,26,40,34,22,24,22,24,34,32,38,28,36,36,40,38,30,34,38], {
			type: 'line',
			width: '100%',
			height: '45',
			lineColor: '#0bb2d4',
			fillColor: '#0bb2d4',
			lineWidth: 2,
		});
		$("#linearea3").sparkline([32,24,26,24,32,26,40,34,22,24], {
			type: 'line',
			width: '50%',
			height: '100',
			lineColor: '#0bb2d4',
			fillColor: '#0bb2d4',
			lineWidth: 1,
		});
		
		$("#baralc").sparkline([32,24,26,24,32,26,40,34,22,24,22,24,34,32,38,28,36,36,40,38,30,34,38], {
			type: 'bar',
			height: '80',
			barWidth: 6,
			barSpacing: 4,
			barColor: '#faa700',
		});
		
		$("#lineIncrease").sparkline([32,24,26,24,32,26,40,34,22,24], {
			type: 'line',
			width: '98%',
			height: '92',
			lineWidth: 2,
			lineColor: '#ffffff',
			fillColor: "rgba(255, 255, 255, 0)",
			spotColor: '#ffffff',
			minSpotColor: '#ffffff',
			maxSpotColor: '#ffffff',
			spotRadius: 1,
		});
		
		$("#lineToday").sparkline([32,24,26,24,32,26,40,34,22,24], {
			type: 'line',
			width: '100%',
			height: '70',
			lineColor: '#ffffff',
			fillColor: 'rgba(255, 255, 255, 0)',
			lineWidth: 2,
			spotRadius: 3,
		});
		
		$("#baranl").sparkline([32,24,26,24,32,26,40,34,22,24,22,24,34,32,38,28,36,36,40,38,30,34,38], {
			type: 'bar',
			height: '70',
			barColor: '#f64e60',
			barWidth: 7,
    		barSpacing: 5,
		});
		
		$("#lineTo").sparkline([32,24,26,24,32,26,40,34,22,24], {
			type: 'line',
			width: '100%',
			height: '70',
			lineColor: '#ffffff',
			fillColor: 'rgba(255, 255, 255, 0)',
			lineWidth: 3,
			spotColor: '#ffffff',
			highlightSpotColor: '#ffffff',
			highlightLineColor: '#ffffff',
			spotRadius: 3,
		});
		
		// donut chart
		$('.donut').peity('donut');
		
		// bar chart
		$(".bar").peity("bar");	
	
		
		$('.countnm').each(function () {
			$(this).prop('Counter',0).animate({
				Counter: $(this).text()
			}, {
				duration: 1000,
				easing: 'swing',
				step: function (now) {
					$(this).text(Math.ceil(now));
				}
			});
		});
	
}); // End of use strict
		
// easypie chart
	$(function() {
		'use strict'
		$('.easypie').easyPieChart({
			easing: 'easeOutBounce',
			onStep: function(from, to, percent) {
				$(this.el).find('.percent').text(Math.round(percent));
			}
		});
		var chart = window.chart = $('.easypie').data('easyPieChart');
		$('.js_update').on('click', function() {
			chart.update(Math.random()*200-100);
		});
	});// End of use strict

// ------------------------------

var Widgetschart = function() {

    

    // Simple bar charts
    var _barChartWidget = function(element, barQty, height, animate, easing, duration, delay, color, tooltip) {
        if (typeof d3 == 'undefined') {
            console.warn('Warning - d3.min.js is not loaded.');
            return;
        }

        // Initialize chart only if element exsists in the DOM
        if(element) {


            // Basic setup
            // ------------------------------

            // Add data set
            var bardata = [];
            for (var i=0; i < barQty; i++) {
                bardata.push(Math.round(Math.random() * 10) + 10);
            }

            // Main variables
            var d3Container = d3.select(element),
                width = d3Container.node().getBoundingClientRect().width;
            


            // Construct scales
            // ------------------------------

            // Horizontal
            var x = d3.scale.ordinal()
                .rangeBands([0, width], 0.3);

            // Vertical
            var y = d3.scale.linear()
                .range([0, height]);



            // Set input domains
            // ------------------------------

            // Horizontal
            x.domain(d3.range(0, bardata.length));

            // Vertical
            y.domain([0, d3.max(bardata)]);



            // Create chart
            // ------------------------------

            // Add svg element
            var container = d3Container.append('svg');

            // Add SVG group
            var svg = container
                .attr('width', width)
                .attr('height', height)
                .append('g');



            //
            // Append chart elements
            //

            // Bars
            var bars = svg.selectAll('rect')
                .data(bardata)
                .enter()
                .append('rect')
                    .attr('class', 'd3-random-bars')
                    .attr('width', x.rangeBand())
                    .attr('x', function(d,i) {
                        return x(i);
                    })
                    .style('fill', color);



            // Tooltip
            // ------------------------------

            // Initiate
            var tip = d3.tip()
                .attr('class', 'd3-tip')
                .offset([-10, 0]);

            // Show and hide
            if(tooltip == "hours" || tooltip == "goal" || tooltip == "members") {
                bars.call(tip)
                    .on('mouseover', tip.show)
                    .on('mouseout', tip.hide);
            }

            // Daily meetings tooltip content
            if(tooltip == "hours") {
                tip.html(function (d, i) {
                    return "<div class='text-center'>" +
                            "<h6 class='mb-0'>" + d + "</h6>" +
                            "<span class='font-size-16'>meetings</span>" +
                            "<div class='font-size-16'>" + i + ":00" + "</div>" +
                        "</div>";
                });
            }

            // Statements tooltip content
            if(tooltip == "goal") {
                tip.html(function (d, i) {
                    return "<div class='text-center'>" +
                            "<h6 class='mb-0'>" + d + "</h6>" +
                            "<span class='font-size-16'>statements</span>" +
                            "<div class='font-size-16'>" + i + ":00" + "</div>" +
                        "</div>";
                });
            }

            // Online members tooltip content
            if(tooltip == "members") {
                tip.html(function (d, i) {
                    return "<div class='text-center bg-dark p-5'>" +
                            "<h6 class='mb-0'>" + d + "0" + "</h6>" +
                            "<span class='font-size-14'>members</span>" +
                            "<div class='font-size-14'>" + i + ":00" + "</div>" +
                        "</div>";
                });
            }



            // Bar loading animation
            // ------------------------------

            // Choose between animated or static
            if(animate) {
                withAnimation();
            } else {
                withoutAnimation();
            }

            // Animate on load
            function withAnimation() {
                bars
                    .attr('height', 0)
                    .attr('y', height)
                    .transition()
                        .attr('height', function(d) {
                            return y(d);
                        })
                        .attr('y', function(d) {
                            return height - y(d);
                        })
                        .delay(function(d, i) {
                            return i * delay;
                        })
                        .duration(duration)
                        .ease(easing);
            }

            // Load without animateion
            function withoutAnimation() {
                bars
                    .attr('height', function(d) {
                        return y(d);
                    })
                    .attr('y', function(d) {
                        return height - y(d);
                    });
            }



            // Resize chart
            // ------------------------------

            // Call function on window resize
            $(window).on('resize', barsResize);

            // Call function on sidebar width change
            $(document).on('click', '.sidebar-control', barsResize);

            // Resize function
            // 
            // Since D3 doesn't support SVG resize by default,
            // we need to manually specify parts of the graph that need to 
            // be updated on window resize
            function barsResize() {

                // Layout variables
                width = d3Container.node().getBoundingClientRect().width;


                // Layout
                // -------------------------

                // Main svg width
                container.attr("width", width);

                // Width of appended group
                svg.attr("width", width);

                // Horizontal range
                x.rangeBands([0, width], 0.3);


                // Chart elements
                // -------------------------

                // Bars
                svg.selectAll('.d3-random-bars')
                    .attr('width', x.rangeBand())
                    .attr('x', function(d,i) {
                        return x(i);
                    });
            }
        }
    };

    // Simple line chart
    var _lineChartWidget = function(element, chartHeight, lineColor, pathColor, pointerLineColor, pointerBgColor) {
        if (typeof d3 == 'undefined') {
            console.warn('Warning - d3.min.js is not loaded.');
            return;
        }

        // Initialize chart only if element exsists in the DOM
        if(element) {


            // Basic setup
            // ------------------------------

            // Add data set
            var dataset = [
                {
                    "date": "04/13/14",
                    "alpha": "60"
                }, {
                    "date": "04/14/14",
                    "alpha": "35"
                }, {
                    "date": "04/15/14",
                    "alpha": "65"
                }, {
                    "date": "04/16/14",
                    "alpha": "50"
                }, {
                    "date": "04/17/14",
                    "alpha": "65"
                }, {
                    "date": "04/18/14",
                    "alpha": "20"
                }, {
                    "date": "04/19/14",
                    "alpha": "60"
                }
            ];

            // Main variables
            var d3Container = d3.select(element),
                margin = {top: 0, right: 0, bottom: 0, left: 0},
                width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right,
                height = chartHeight - margin.top - margin.bottom,
                padding = 20;

            // Format date
            var parseDate = d3.time.format("%m/%d/%y").parse,
                formatDate = d3.time.format("%a, %B %e");


            // Add tooltip
            // ------------------------------

            var tooltip = d3.tip()
                .attr('class', 'd3-tip')
                .html(function (d) {
                    return "<ul class='list-unstyled mb-1 bg-dark p-5'>" +
                        "<li>" + "<div class='my-1'><i class='fa fa-check mr-2'></i>" + formatDate(d.date) + "</div>" + "</li>" +
                        "<li>" + "Sales: &nbsp;" + "<span class='float-right'>" + d.alpha + "</span>" + "</li>" +
                        "<li>" + "Revenue: &nbsp; " + "<span class='float-right'>" + "$" + (d.alpha * 0).toFixed(2) + "</span>" + "</li>" + 
                    "</ul>";
                });


            // Create chart
            // ------------------------------

            // Add svg element
            var container = d3Container.append('svg');

            // Add SVG group
            var svg = container
                    .attr('width', width + margin.left + margin.right)
                    .attr('height', height + margin.top + margin.bottom)
                    .append("g")
                        .attr("transform", "translate(" + margin.left + "," + margin.top + ")")
                        .call(tooltip);


            // Load data
            // ------------------------------

            dataset.forEach(function (d) {
                d.date = parseDate(d.date);
                d.alpha = +d.alpha;
            });


            // Construct scales
            // ------------------------------

            // Horizontal
            var x = d3.time.scale()
                .range([padding, width - padding]);

            // Vertical
            var y = d3.scale.linear()
                .range([height, 5]);


            // Set input domains
            // ------------------------------

            // Horizontal
            x.domain(d3.extent(dataset, function (d) {
                return d.date;
            }));

            // Vertical
            y.domain([0, d3.max(dataset, function (d) {
                return Math.max(d.alpha);
            })]);


            // Construct chart layout
            // ------------------------------

            // Line
            var line = d3.svg.line()
                .x(function(d) {
                    return x(d.date);
                })
                .y(function(d) {
                    return y(d.alpha);
                });


            //
            // Append chart elements
            //

            // Add mask for animation
            // ------------------------------

            // Add clip path
            var clip = svg.append("defs")
                .append("clipPath")
                .attr("id", "clip-line-small");

            // Add clip shape
            var clipRect = clip.append("rect")
                .attr('class', 'clip')
                .attr("width", 0)
                .attr("height", height);

            // Animate mask
            clipRect
                  .transition()
                      .duration(1000)
                      .ease('linear')
                      .attr("width", width);


            // Line
            // ------------------------------

            // Path
            var path = svg.append('path')
                .attr({
                    'd': line(dataset),
                    "clip-path": "url(#clip-line-small)",
                    'class': 'd3-line d3-line-medium'
                })
                .style('stroke', lineColor);

            // Animate path
            svg.select('.line-tickets')
                .transition()
                    .duration(1000)
                    .ease('linear');


            // Add vertical guide lines
            // ------------------------------

            // Bind data
            var guide = svg.append('g')
                .selectAll('.d3-line-guides-group')
                .data(dataset);

            // Append lines
            guide
                .enter()
                .append('line')
                    .attr('class', 'd3-line-guides')
                    .attr('x1', function (d, i) {
                        return x(d.date);
                    })
                    .attr('y1', function (d, i) {
                        return height;
                    })
                    .attr('x2', function (d, i) {
                        return x(d.date);
                    })
                    .attr('y2', function (d, i) {
                        return height;
                    })
                    .style('stroke', pathColor)
                    .style('stroke-dasharray', '4,2')
                    .style('shape-rendering', 'crispEdges');

            // Animate guide lines
            guide
                .transition()
                    .duration(1000)
                    .delay(function(d, i) { return i * 150; })
                    .attr('y2', function (d, i) {
                        return y(d.alpha);
                    });


            // Alpha app points
            // ------------------------------

            // Add points
            var points = svg.insert('g')
                .selectAll('.d3-line-circle')
                .data(dataset)
                .enter()
                .append('circle')
                    .attr('class', 'd3-line-circle d3-line-circle-medium')
                    .attr("cx", line.x())
                    .attr("cy", line.y())
                    .attr("r", 3)
                    .style({
                        'stroke': pointerLineColor,
                        'fill': pointerBgColor
                    });

            // Animate points on page load
            points
                .style('opacity', 0)
                .transition()
                    .duration(250)
                    .ease('linear')
                    .delay(1000)
                    .style('opacity', 1);

            // Add user interaction
            points
                .on("mouseover", function (d) {
                    tooltip.offset([-10, 0]).show(d);

                    // Animate circle radius
                    d3.select(this).transition().duration(250).attr('r', 4);
                })

                // Hide tooltip
                .on("mouseout", function (d) {
                    tooltip.hide(d);

                    // Animate circle radius
                    d3.select(this).transition().duration(250).attr('r', 3);
                });

            // Change tooltip direction of first point
            d3.select(points[0][0])
                .on("mouseover", function (d) {
                    tooltip.offset([0, 10]).direction('e').show(d);

                    // Animate circle radius
                    d3.select(this).transition().duration(250).attr('r', 4);
                })
                .on("mouseout", function (d) {
                    tooltip.direction('n').hide(d);

                    // Animate circle radius
                    d3.select(this).transition().duration(250).attr('r', 3);
                });

            // Change tooltip direction of last point
            d3.select(points[0][points.size() - 1])
                .on("mouseover", function (d) {
                    tooltip.offset([0, -10]).direction('w').show(d);

                    // Animate circle radius
                    d3.select(this).transition().duration(250).attr('r', 4);
                })
                .on("mouseout", function (d) {
                    tooltip.direction('n').hide(d);

                    // Animate circle radius
                    d3.select(this).transition().duration(250).attr('r', 3);
                });


            // Resize chart
            // ------------------------------

            // Call function on window resize
            $(window).on('resize', lineChartResize);

            // Call function on sidebar width change
            $(document).on('click', '.sidebar-control', lineChartResize);

            // Resize function
            // 
            // Since D3 doesn't support SVG resize by default,
            // we need to manually specify parts of the graph that need to 
            // be updated on window resize
            function lineChartResize() {

                // Layout variables
                width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right;


                // Layout
                // -------------------------

                // Main svg width
                container.attr("width", width + margin.left + margin.right);

                // Width of appended group
                svg.attr("width", width + margin.left + margin.right);

                // Horizontal range
                x.range([padding, width - padding]);


                // Chart elements
                // -------------------------

                // Mask
                clipRect.attr("width", width);

                // Line path
                svg.selectAll('.d3-line').attr("d", line(dataset));

                // Circles
                svg.selectAll('.d3-line-circle').attr("cx", line.x());

                // Guide lines
                svg.selectAll('.d3-line-guides')
                    .attr('x1', function (d, i) {
                        return x(d.date);
                    })
                    .attr('x2', function (d, i) {
                        return x(d.date);
                    });
            }
        }
    };

    // Simple sparklines
    var _sparklinesWidget = function(element, chartType, qty, chartHeight, interpolation, duration, interval, color) {
        if (typeof d3 == 'undefined') {
            console.warn('Warning - d3.min.js is not loaded.');
            return;
        }

        // Initialize chart only if element exsists in the DOM
        if(element) {


            // Basic setup
            // ------------------------------

            // Define main variables
            var d3Container = d3.select(element),
                margin = {top: 0, right: 0, bottom: 0, left: 0},
                width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right,
                height = chartHeight - margin.top - margin.bottom;


            // Generate random data (for demo only)
            var data = [];
            for (var i=0; i < qty; i++) {
                data.push(Math.floor(Math.random() * qty) + 5);
            }


            // Construct scales
            // ------------------------------

            // Horizontal
            var x = d3.scale.linear().range([0, width]);

            // Vertical
            var y = d3.scale.linear().range([height - 5, 5]);


            // Set input domains
            // ------------------------------

            // Horizontal
            x.domain([1, qty - 3]);

            // Vertical
            y.domain([0, qty]);
                

            // Construct chart layout
            // ------------------------------

            // Line
            var line = d3.svg.line()
                .interpolate(interpolation)
                .x(function(d, i) { return x(i); })
                .y(function(d, i) { return y(d); });

            // Area
            var area = d3.svg.area()
                .interpolate(interpolation)
                .x(function(d,i) { 
                    return x(i); 
                })
                .y0(height)
                .y1(function(d) { 
                    return y(d); 
                });


            // Create SVG
            // ------------------------------

            // Container
            var container = d3Container.append('svg');

            // SVG element
            var svg = container
                .attr('width', width + margin.left + margin.right)
                .attr('height', height + margin.top + margin.bottom)
                .append("g")
                    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");


            // Add mask for animation
            // ------------------------------

            // Add clip path
            var clip = svg.append("defs")
                .append("clipPath")
                .attr('id', function(d, i) { return "load-clip-" + element.substring(1); });

            // Add clip shape
            var clips = clip.append("rect")
                .attr('class', 'load-clip')
                .attr("width", 0)
                .attr("height", height);

            // Animate mask
            clips
                .transition()
                    .duration(1000)
                    .ease('linear')
                    .attr("width", width);


            //
            // Append chart elements
            //

            // Main path
            var path = svg.append("g")
                .attr("clip-path", function(d, i) { return "url(#load-clip-" + element.substring(1) + ")"; })
                .append("path")
                    .datum(data)
                    .attr("transform", "translate(" + x(0) + ",0)");

            // Add path based on chart type
            if(chartType == "area") {
                path.attr("d", area).attr('class', 'd3-area').style("fill", color); // area
            }
            else {
                path.attr("d", line).attr("class", "d3-line d3-line-medium").style('stroke', color); // line
            }

            // Animate path
            path
                .style('opacity', 0)
                .transition()
                    .duration(500)
                    .style('opacity', 1);



            // Set update interval. For demo only
            // ------------------------------

            setInterval(function() {

                // push a new data point onto the back
                data.push(Math.floor(Math.random() * qty) + 5);

                // pop the old data point off the front
                data.shift();

                update();

            }, interval);



            // Update random data. For demo only
            // ------------------------------

            function update() {

                // Redraw the path and slide it to the left
                path
                    .attr("transform", null)
                    .transition()
                        .duration(duration)
                        .ease("linear")
                        .attr("transform", "translate(" + x(0) + ",0)");

                // Update path type
                if(chartType == "area") {
                    path.attr("d", area).attr('class', 'd3-area').style("fill", color);
                }
                else {
                    path.attr("d", line).attr("class", "d3-line d3-line-medium").style('stroke', color);
                }
            }



            // Resize chart
            // ------------------------------

            // Call function on window resize
            $(window).on('resize', resizeSparklines);

            // Call function on sidebar width change
            $(document).on('click', '.sidebar-control', resizeSparklines);

            // Resize function
            // 
            // Since D3 doesn't support SVG resize by default,
            // we need to manually specify parts of the graph that need to 
            // be updated on window resize
            function resizeSparklines() {

                // Layout variables
                width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right;


                // Layout
                // -------------------------

                // Main svg width
                container.attr("width", width + margin.left + margin.right);

                // Width of appended group
                svg.attr("width", width + margin.left + margin.right);

                // Horizontal range
                x.range([0, width]);


                // Chart elements
                // -------------------------

                // Clip mask
                clips.attr("width", width);

                // Line
                svg.select(".d3-line").attr("d", line);

                // Area
                svg.select(".d3-area").attr("d", area);
            }
        }
    };

    

    //
    // Return objects assigned to module
    //

    return {
        init: function() {

            _barChartWidget("#chart_bar_basic", 24, 50, true, "elastic", 1200, 50, "#3e8ef7", "members");
            _barChartWidget("#chart_bar_color", 24, 50, true, "elastic", 1200, 50, "rgba(255,255,255,0.75)", "members");

            _lineChartWidget('#line_chart_simple', 50, '#0bb2d4', '#0bb2d4', '#0bb2d4', '#fff');
            _lineChartWidget('#line_chart_color', 50, '#fff', 'rgba(255,255,255,0.5)', '#fff', '#29B6F6');

            _sparklinesWidget("#sparklines_basic", "area", 30, 50, "basis", 750, 2000, "#0bb2d4");
            _sparklinesWidget("#sparklines_color", "area", 30, 50, "basis", 750, 2000, "rgba(255,255,255,0.75)");
            
        }
    }
}();


// Initialize module
// ------------------------------

// When content loaded
document.addEventListener('DOMContentLoaded', function() {
    Widgetschart.init();
});