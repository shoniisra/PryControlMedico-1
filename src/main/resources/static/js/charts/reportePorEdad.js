$('document').ready(function () {
	$.ajax({
		url : "/reportes/agerange",
		method : "POST",
		contentType : "application/json",
		success : function(result){
		    $("#chart-container").insertFusionCharts({
		        type: "column2d",
		        width: "100%",
		        height: "400",
		        dataFormat: "json",
		        renderAt:"chart-container",
		        dataSource: {
		            "chart": {
		                "caption": "Rango de edades de los Pacientes",
		                "subCaption": "Cantidad * Categoria",
		                "xAxisName": "Categoria",
		                "yAxisName": "Cantidad",
		                "numberSuffix": "",
		                "theme":"fusion"
		            },
		            "data": result
		        }
		    });
		},
		error : function(err) {
			console.log(err);
		}
	});

});