$('document').ready(function () {
    $.ajax({
        url : "/reportes/srdosis",
        method : "POST",
        contentType : "application/json",
        success : function(result){
        	var data = JSON.stringify(result);
            $("#chart-container2").insertFusionCharts({
                type: "scrollcolumn2d",
                width: "100%",
                height: "400",
                dataFormat: "json",
                renderAt:"chart-container",
                dataSource: {
                    "chart": {
                        "caption": "Dosis suministradas o rechazadas por día",
                        "subCaption": "Cantidad * Día",
                        "xAxisName": "Día de la semana",
                        "yAxisName": "Cantidad",
                        "numberSuffix": "",
                        "theme":"fusion"
                    },
                    categories: [
                        {
                            category: result.category
                        }
                    ],
                    dataset: [
                        {
                            seriesname: result.dataset[0].seriesname,
                            data: [
                                {
                                    value: result.dataset[0].data[0]
                                },
                                {
                                    value: result.dataset[0].data[1]
                                },
                                {
                                    value: result.dataset[0].data[2]
                                },
                                {
                                    value: result.dataset[0].data[3]
                                },
                                {
                                    value: result.dataset[0].data[4]
                                },
                                {
                                    value: result.dataset[0].data[5]
                                },
                                {
                                    value: result.dataset[0].data[6]
                                }
                            ]
                        },
                        {
                            seriesname: result.dataset[1].seriesname,
                            data: [
                                {
                                    value: result.dataset[1].data[0]
                                },
                                {
                                    value: result.dataset[1].data[1]
                                },
                                {
                                    value:result.dataset[1].data[2]
                                },
                                {
                                    value: result.dataset[1].data[3]
                                },
                                {
                                    value: result.dataset[1].data[4]
                                },
                                {
                                    value: result.dataset[1].data[5]
                                },
                                {
                                    value: result.dataset[1].data[6]
                                }
                            ]
                        }
                    ]
                }
            });
        },
        error : function(err) {
            console.log(err);
        }
    });
});