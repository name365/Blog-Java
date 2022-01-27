//[widget morris charts Javascript]

//Project:	EduAdmin - Responsive Admin Template
//Primary use:   Used only for the morris charts


$(function () {
    "use strict";

    Morris.Area({
        element: 'area-chart',
        data: [{
            period: '2010',
            data1: 55,
            data2: 85,
            data3: 25
        }, {
            period: '2011',
            data1: 135,
            data2: 105,
            data3: 85
        }, {
            period: '2012',
            data1: 85,
            data2: 65,
            data3: 75
        }, {
            period: '2013',
            data1: 75,
            data2: 205,
            data3: 145
        }, {
            period: '2014',
            data1: 185,
            data2: 155,
            data3: 145
        }, {
            period: '2015',
            data1: 110,
            data2: 105,
            data3: 85
        },
         {
            period: '2016',
            data1: 255,
            data2: 155,
            data3: 205
        }],
        xkey: 'period',
        ykeys: ['data1', 'data2', 'data3'],
        labels: ['Data 1', 'Data 2', 'Data 3'],
        pointSize: 3,
        fillOpacity: 0,
        pointStrokeColors:['#0bb2d4', '#17b3a3', '#3e8ef7'],
        behaveLikeLine: true,
        gridLineColor: '#e0e0e0',
        lineWidth: 3,
        hideHover: 'auto',
        lineColors: ['#0bb2d4', '#17b3a3', '#3e8ef7'],
        resize: true
        
    });

Morris.Area({
        element: 'area-chart2',
        data: [{
            period: '2013',
            data1: 0,
            data2: 0,
            
        }, {
            period: '2014',
            data1: 135,
            data2: 105,
            
        }, {
            period: '2015',
            data1: 85,
            data2: 65,
            
        }, {
            period: '2016',
            data1: 75,
            data2: 205,
            
        }, {
            period: '2017',
            data1: 185,
            data2: 155,
            
        }, {
            period: '2018',
            data1: 110,
            data2: 95,
            
        },
         {
            period: '2019',
            data1: 255,
            data2: 155,
           
        }],
        xkey: 'period',
        ykeys: ['data1', 'data2'],
        labels: ['Data 1', 'Data 2'],
        pointSize: 0,
        fillOpacity: 0.4,
        pointStrokeColors:['#faa700', '#ff4c52'],
        behaveLikeLine: true,
        gridLineColor: '#e0e0e0',
        lineWidth: 0,
        smooth: false,
        hideHover: 'auto',
        lineColors: ['#faa700', '#ff4c52'],
        resize: true
        
    });


// LINE CHART
        var line = new Morris.Line({
          element: 'line-chart',
          resize: true,
          data: [
            {y: '2016 Q1', data1: 2566},
            {y: '2016 Q2', data1: 2678},
            {y: '2016 Q3', data1: 4812},
            {y: '2016 Q4', data1: 3867},
            {y: '2017 Q1', data1: 6910},
            {y: '2017 Q2', data1: 5770},
            {y: '2017 Q3', data1: 4920},
            {y: '2017 Q4', data1: 15173},
            {y: '2018 Q1', data1: 11687},
            {y: '2018 Q2', data1: 8632}
          ],
          xkey: 'y',
          ykeys: ['data1'],
          labels: ['Data 1'],
          gridLineColor: '#eef0f2',
          lineColors: ['#0bb2d4'],
          lineWidth: 1,
          hideHover: 'auto'
        });
 // donut chart
        
    Morris.Donut({
        element: 'donut-chart',
        data: [{
            label: "Online Sales",
            value: 15,

        }, {
            label: "In-Store Sales",
            value: 45
        }, {
            label: "Retail Sales",
            value: 35
        }],
        resize: true,
        colors:['#17b3a3', '#3e8ef7', '#ff4c52']
    });

// bar chart
    Morris.Bar({
        element: 'bar-chart',
        data: [{
            y: '2012',
            a: 105,
            b: 95,
            c: 65
        }, {
            y: '2013',
            a: 80,
            b: 70,
            c: 45
        }, {
            y: '2014',
            a: 55,
            b: 45,
            c: 35
        }, {
            y: '2015',
            a: 80,
            b: 70,
            c: 45
        }, {
            y: '2016',
            a: 55,
            b: 45,
            c: 35
        }, {
            y: '2017',
            a: 80,
            b: 70,
            c: 45
        }, {
            y: '2018',
            a: 105,
            b: 95,
            c: 45
        }],
        xkey: 'y',
        ykeys: ['a', 'b', 'c'],
        labels: ['A', 'B', 'C'],
        barColors:['#ff4c52', '#faa700', '#0bb2d4'],
        hideHover: 'auto',
        gridLineColor: '#eef0f2',
        resize: true
    });
// area chart
 Morris.Area({
        element: 'area-chart3',
        data: [{
                    period: '2013',
                    data1: 0,
                    data2: 0,
                    data3: 0
                }, {
                    period: '2014',
                    data1: 55,
                    data2: 20,
                    data3: 10
                }, {
                    period: '2015',
                    data1: 25,
                    data2: 55,
                    data3: 70
                }, {
                    period: '2016',
                    data1: 65,
                    data2: 17,
                    data3: 12
                }, {
                    period: '2017',
                    data1: 35,
                    data2: 25,
                    data3: 125
                }, {
                    period: '2018',
                    data1: 30,
                    data2: 85,
                    data3: 45
                }, {
                    period: '2019',
                    data1: 15,
                    data2: 15,
                    data3: 15
                }


                ],
                lineColors: ['#17b3a3', '#3e8ef7', '#faa700'],
                xkey: 'period',
                ykeys: ['data1', 'data2', 'data3'],
                labels: ['Data 1', 'Data 2', 'Data 3'],
                pointSize: 0,
                lineWidth: 0,
                resize:true,
                fillOpacity: 0.8,
                behaveLikeLine: true,
                gridLineColor: '#e0e0e0',
                hideHover: 'auto'
        
    });
	
	
  });