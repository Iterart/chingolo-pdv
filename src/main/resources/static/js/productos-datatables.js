$(document).ready(function() {

	//$("#btn-edi").addClass("disabled");
	//$("#btn-del").addClass("disabled");

	//moment.locale("es");

	var table = $("#products-table").DataTable({
		processing: true,
		serverSide: true,
		responsive: true,
		select: true,
		infoFiltered: true,
		bPaginate: true,
		bLengthChange: true,
		bFilter: true,
		bInfo: true,
		bAutoWidth: true,
		lengthMenu: [5, 10, 15, 20, 25, 30],
		language: {
			"search": "Buscar:",
			"processing": "Recuperando información...",
			"lengthMenu": "Mostrar _MENU_ registros",
			"zeroRecords": "No se encontraron registros coincidentes...",
			"emptyTable": "No hay datos en la tabla",
			"info": "Mostrando de _START_ a _END_ de _TOTAL_ registros",
			"infoEmpty": "Mostrando 0 de 0 de 0 registros",
			"infoFiltered": "(Encontrados _MAX_ total registros)",
			"paginate": {
				"first": "Primero",
				"last": "Último",
				"next": "Siguiente",
				"previous": "Anterior"
			}
		},
		ajax: {
			url: "/productos/datatable/list",
			data: "data"
		},
		columns: [
			{
				data: "linkImagen",
				"render": function(data, type, row) {
					return '<img src="' + data + '" width="100px" />';
				}
			},
			{ data: "codigoBarras" },
			{ data: "descripcion" },
			{ data: "categoria.nombre" },
			{ data: "precioVenta" },
			{ data: "stock" }
			/*{ data: "numero" },
			{
				data: "linkImagen",
				"render": function(data, type, row) {
					return '<img src="' + data + '" width="100px" />';
				}
			},
			{ data: "codigoBarras" },
			{ data: "descripcion" },
			{ data: "categoria.nombre" },
			{ data: "stock" },
			{ data: "stockCritico" },
			{ data: "nota" },
			{ data: "precioCosto" },
			{ data: "precioVenta" },
			{ data: "precioEspecial" }*/
			/*{data: 
					"precios",
					render : function(precios) {
						var elemento;
						$.each(precios, function (ind, elem) { 
							  if(ind === 0)
								  elemento = elem;
							}); 
						  return "$" + parseFloat(elemento.valor).toFixed(2);
					}
			},
			{data: 
					"precios",
					render : function(precios) {
						var elemento;
						$.each(precios, function (ind, elem) { 
							if(ind === 1)
								elemento = elem;
						}); 
						return "$" + parseFloat(elemento.valor).toFixed(2);
					}
			},
			{data: 
					"precios",
					render : function(precios) {
						var elemento;
						$.each(precios, function (ind, elem) { 
							if(ind === 2)
								elemento = elem;
							}); 
						return "$" + parseFloat(elemento.valor).toFixed(2);
					}
			},
			{data:
					"precios",
					render : function(precios) {
						var elemento;
						$.each(precios, function (ind, elem) { 
							if(ind === 3)
								elemento = elem;
							}); 
						return "$" + parseFloat(elemento.valor).toFixed(2);
					}
			}*/
		],
		rowCallback: function(row, data, dataIndex) {
			if (data["activo"] == false) {
				$(row).addClass('tachado');

			}
		}
	});
});

/* 
 *
 *  Botones CRUD
 *
 */ 

$("#btn-new").on("click", () => {
	let tareaModal = new bootstrap.Modal(document.getElementById("productosModal"), {backdrop: 'static', keyboard: false});
    tareaModal.show()
});