function addDetail() {
	var detail = {
		medicamentoId : $("#medicamentoId").val(),
  	fechaInicio : $("#fechaInicio").val(),
		cantidad : $("#cantidad").val(),
		activo:true,
		observacion:'',
		numeroTomas:0,
		posologia : $("#posologia").val(),
		frecuencia : $("#frecuencia").val(),
		tipoFrecuencia : $("#tipoFrecuencia").val(),
		tipoDosis : $("#tipoDosis").val()
	};
	console.log(detail.fechaInicio);
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
				detailToAdd = detailToAdd.replace(/{FECHAINICIO}/, parse(item.fechaInicio));
				detailToAdd = detailToAdd.replace(/{CANTIDAD}/, item.cantidad);
				detailToAdd = detailToAdd.replace(/{POSOLOGIA}/, item.posologia);
				detailToAdd = detailToAdd.replace(/{FRECUENCIA}/, item.frecuencia + " " + item.descripcionTipoFrecuencia);
				detailToAdd = detailToAdd.replace(/{TIPODOSIS}/, item.descripcionTipoDosis);
				$("#tblDetalleReceta tbody").append(detailToAdd);
			});
			
		},
		error : function(err) {
			console.log(err);
		}
	});
}
function parse(dateTimeSpan){
	var date = new Date(dateTimeSpan);
	var year = date.getFullYear(),
		month = date.getMonth() + 1, // months are zero indexed
		day = date.getDate(),
		hour = date.getHours(),
		minute = date.getMinutes(),
		second = date.getSeconds(),
		hourFormatted = hour % 12 || 12, // hour returned in 24 hour format
		minuteFormatted = minute < 10 ? "0" + minute : minute,
		morning = hour < 12 ? "am" : "pm";
	return month + "/" + day + "/" + year + " " + hourFormatted + ":" +
		minuteFormatted + morning;
}

$(document).ready(function() {
			$("#fechaInicio").val(new Date().toISOString().slice(0,16));
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
			$('#tipoDosis').change(function(e){
				if($(this).val() == "1"){
					$('#cantidad').prop("disabled",true);
				}else{
					$('#cantidad').prop("disabled",false);
				}
			})
		});
