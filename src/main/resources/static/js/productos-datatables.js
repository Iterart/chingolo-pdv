
let table;

$(document).ready(function() {

	$("#btn-edi").addClass("disabled");
	$("#btn-del").addClass("disabled");

	//moment.locale("es");

	table = $("#products-table").DataTable({
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
			
		],
		rowCallback: function(row, data, dataIndex) {
			if (data["activo"] == false) {
				$(row).addClass('tachado');

			}
		}
	});
});

const clearStyles = () => {
	// Limpiar form de cargas anteriores...
	$("span").closest(".error-span").remove();

	// Remover border rojos si los hay...
	$(".is-invalid").removeClass("is-invalid");
}

const clearInputs = () => {
	$("#form :input").each(function() {
		$(this).val("");
	});
}

/* 
 *
 *  Botones CRUD
 *
 */

//
//Abrir form nuevo:
//
$("#btn-new").on("click", () => {

	var link = "/img/producto-sin-imagen.png";

	clearStyles();
	clearInputs();

	//Titulo...
	$("#titleModal").text("Nuevo Producto");

	$("#descripcion").select();
	$("#linkImagen").attr("src", link);
	$("#precioCosto").val(0.00);
	$("#precioVenta").val(0.00);
	$("#precioEspecial").val(0.00);
	$("#stock").val(0);
	$("#stockCritico").val(0);

	//Abrir bs5 dialog...
	let tareaModal = new bootstrap.Modal(document.getElementById("productosModal"), { backdrop: 'static', keyboard: false });
	tareaModal.show()
});

//
//Abrir form editar:
//


//
// Hacer el submit del producto
//

$("#btnGuardar").on("click", function() {

	// Crear objeto producto:
	var producto = {};

	producto.codigoBarras = $("#codigoBarras").val();
	producto.descripcion = $("#descripcion").val();
	producto.categoria = $("#categoria").val();
	producto.stock = $("#stock").val();
	producto.stockCritico = $("#stockCritico").val();
	producto.linkImagen = $("#linkImagen").attr("src");
	producto.precioVenta = $("#precioVenta").val();
	producto.precioEspecial = $("#precioEspecial").val();
	producto.precioCosto = $("#precioCosto").val();
	producto.nota = $("#obs").val();

	if ($("#numero").val() !== "")
		producto.numero = $("#numero").val();
	else
		producto.numero = 0;

	$.ajax({
		method: "POST",
		url: "/productos/guardar",
		data: producto,
		beforeSend: function() {
			// Remover errores previos...
			clearStyles();
		},
		success: function() {

			$("#productosModal").modal("hide");
			table.ajax.reload();
			Swal.fire({
				title: 'Éxito!',
				text: 'Producto guardado.',
				icon: 'success',
				confirmButtonText: 'Aceptar'
			})
			/*.then((result) => {
				if (result.isConfirmed) {
					table.ajax.reload();
				}
			});*/
		},
		statusCode: {
			422: function(xhr) {
				console.log("Status Error: " + xhr.status);
				var errors = $.parseJSON(xhr.responseText);
				$.each(errors, function(key, val) {
					$(`#${key}`).addClass("is-invalid");
					$(`#error-${key}`).addClass("invalid-feedback").append("<span class='error-span'>" + val + "</span>");
				});

				Swal.fire({
					icon: 'error',
					title: 'Oops...',
					text: 'Datos erróneos o faltantes!',
					footer: 'Verifique los mensajes de error'
				})
			},
			404: function(xhr) {
				console.log("Status Error: " + xhr.status);
				Swal.fire({
					icon: 'error',
					title: 'Oops...',
					text: 'No se pudo comunicar con el servidor!',
					footer: 'Verifique su conexión a internet o comuníquese con el administrador'
				})
			}
		}
	});

});







