// Call the dataTables jQuery plugin
$(document).ready(function() {
    $('#tablaPacientes').DataTable({
        language: {
            processing:     "Procesando...",
            search:         "Buscar",
            lengthMenu:    "Mostrar _MENU_ elementos",
            info:           "Mostrando _START_ a _END_ de _TOTAL_ elementos",
            loadingRecords: "Cargando datos...",
            zeroRecords:    "Sin datos",
            emptyTable:     "Tabla vacía",
            paginate: {
                first:      "Primero",
                previous:   "Atras",
                next:       "Siguiente",
                last:       "Últlimo"
            }
        }
    });
    $('#tablaRecetas').DataTable({
        language: {
            processing:     "Procesando...",
            search:         "Buscar",
            lengthMenu:    "Mostrar _MENU_ elementos",
            info:           "Mostrando _START_ a _END_ de _TOTAL_ elementos",
            loadingRecords: "Cargando datos...",
            zeroRecords:    "Sin datos",
            emptyTable:     "Tabla vacía",
            paginate: {
                first:      "Primero",
                previous:   "Atras",
                next:       "Siguiente",
                last:       "Últlimo"
            }
        }
    });
    $('#tablaDetalleRecetas').DataTable({
        language: {
            processing:     "Procesando...",
            search:         "Buscar",
            lengthMenu:    "Mostrar _MENU_ elementos",
            info:           "Mostrando _START_ a _END_ de _TOTAL_ elementos",
            loadingRecords: "Cargando datos...",
            zeroRecords:    "Sin datos",
            emptyTable:     "Tabla vacía",
            paginate: {
                first:      "Primero",
                previous:   "Atras",
                next:       "Siguiente",
                last:       "Últlimo"
            }
        }
    });
    $('#tablaMedicos').DataTable({
        language: {
            processing:     "Procesando...",
            search:         "Buscar",
            lengthMenu:    "Mostrar _MENU_ elementos",
            info:           "Mostrando _START_ a _END_ de _TOTAL_ elementos",
            loadingRecords: "Cargando datos...",
            zeroRecords:    "Sin datos",
            emptyTable:     "Tabla vacía",
            paginate: {
                first:      "Primero",
                previous:   "Atras",
                next:       "Siguiente",
                last:       "Últlimo"
            }
        }
    });
    $('#tablaMedicamentos').DataTable({
        language: {
            processing:     "Procesando...",
            search:         "Buscar",
            lengthMenu:    "Mostrar _MENU_ elementos",
            info:           "Mostrando _START_ a _END_ de _TOTAL_ elementos",
            loadingRecords: "Cargando datos...",
            zeroRecords:    "Sin datos",
            emptyTable:     "Tabla vacía",
            paginate: {
                first:      "Primero",
                previous:   "Atras",
                next:       "Siguiente",
                last:       "Últlimo"
            }
        }
    });
});
