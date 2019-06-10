function addDetail() {
	var detail = {
		medicamentoId : $("#medicamentoId").val(),
		cantidad : $("#cantidad").val(),
		activo:true,
		observacion:'',
		numeroTomas:0,
		posologia : $("#posologia").val(),
		frecuencia : $("#frecuencia").val(),
		tipoFrecuencia : $("#tipoFrecuencia").val()
	};

	$.ajax({
		url : "/receta/addDetail",
		method : "POST",
		contentType : "application/json",
		dataType : "json",
		data : JSON.stringify(detail),
		success : function(result) {
			$("#tblDetalleReceta tbody").html('');
			$.each(result, function(i, item) {
				var detailToAdd = $("#templateDetalleReceta").html();
				console.log(item);
				detailToAdd = detailToAdd.replace(/{NOMBRE}/, item.medicamento.nombreComercial);				
				detailToAdd = detailToAdd.replace(/{CANTIDAD}/, item.cantidad);
				detailToAdd = detailToAdd.replace(/{POSOLOGIA}/, item.posologia);
				detailToAdd = detailToAdd.replace(/{FRECUENCIA}/, item.frecuencia);
				detailToAdd = detailToAdd.replace(/{TIPOFRECUENCIA}/, item.descripcionTipoFrecuencia);
				
				$("#tblDetalleReceta tbody").append(detailToAdd);
			});
			
		},
		error : function(err) {
			console.log(err);
		}
	});

}

$(document).ready(function() {
			
			$("#criteria").autocomplete({
				source : function(request, response) {
					$.ajax({
						url : "/medicamento/find/" + request.term,
						dataType : "json",
						data : {
							term : request.term
						},
						success : function(data) {
							console.log(data);					
							response($.map(data, function(item) {
								return {
									value : item.idmedicamento,
									label : item.nombreComercial,
									componente : item.componenteActivo,
									concentracion : item.concentracion
								};
							}));
						},
					});
				},
				select : function(event, ui) {					
					$("#criteria").val(ui.item.label);			
										
					$("#medicamentoId").val(ui.item.value);
					$("#nombreMedicamento").html(ui.item.label);
					$("#nombreMedicamento").append(" - ");
					$("#nombreMedicamento").append(ui.item.concentracion);					
					$("#componenteMedicamento").html(ui.item.componente);
					
					return false;
				}
			});
			
			$('#btnAddDetail').click(function() {				
				addDetail();
			});
			
		});
