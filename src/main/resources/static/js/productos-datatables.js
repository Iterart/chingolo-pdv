
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
			url: `${rootUrl}productos/datatable/list`,
			data: "data"
		},
		columns: [
			{
				data: "linkImagen",
				"render": function(data, type, row) {
					return '<img src="' + (data !== "" ? data : "../img/producto-sin-imagen.png") + '" width="100px" />';
				}
			},
			{ data: "codigoBarras" },
			{ data: "descripcion" },
			{ data: "categoria.nombre" },
			{
				data: "precioVenta",
				render: (precio) => {
					return "$" + parseFloat(precio).toFixed(2);
				}
			},
			{ data: "stock" }

		],
		rowCallback: function(row, data, dataIndex) {
			if (data["activo"] == false) {
				$(row).addClass('tachado');

			}
		}
	});

	// Marcar/desmarcar buttons al clikear en ordenacion...
	$("#products-table thead").on("click", "tr", function() {
		$("#btn-edi").addClass("disabled");
		$("#btn-del").addClass("disabled");
	});

	//...y también para el buscar...
	$(".dataTables_filter").on("click", () => {
		$("#btn-edi").addClass("disabled");
		$("#btn-del").addClass("disabled");
		$("#products-table tbody tr").removeClass("selected");
	});
	
	//...y también para la paginación...
	$(".dataTables_paginate").on("click", () => {
		$("#btn-edi").addClass("disabled");
		$("#btn-del").addClass("disabled");
		$("#products-table tbody tr").removeClass("selected");
	});

	// Marcar/desmarcar lineas
	$("#products-table tbody").on("click", "tr", function() {
		if ($(this).hasClass("selected")) {
			$(this).removeClass("selected");
			$("#btn-edi").addClass("disabled");
			$("#btn-del").addClass("disabled");
		} else {
			$("tr.selected").removeClass("selected");
			$(this).addClass("selected");
			$("#btn-edi").removeClass("disabled");
			$("#btn-del").removeClass("disabled");
		}

		if ($(this).hasClass("tachado")) {
			$("#btn-del").html("Habilitar");
		} else {
			$("#btn-del").html("Quitar");
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
	//$("#edt_img").attr("src", "");
}

const showImage = () => {
	
	/*if (link.length > 255) {
		Swal.fire({
			icon: 'error',
			title: 'Oops...',
			text: 'El link es demasiado largo...',
			footer: 'Busque otro enlace'
		});
		//return;
	}*/
	
	let link = $("#linkImagen").val();

	$("#edt_img").attr("src", link);
}

function getProdId() {
	return table.row(table.$("tr.selected")).data().numero;
}

function getProdDescrip() {
	return table.row(table.$("tr.selected")).data().descripcion;
}

function isSelectedRow() {
	trow = table.row(table.$("tr.selected"));
	return trow.data() !== undefined;
}

function getEstado() {
	return table.row(table.$("tr.selected")).data().activo;
}

// Alterar la imagen en el <img> del modal
$("#linkImagen").on("keyup", function() {
	showImage();
});

$("#linkImagen").on("change", function() {
	showImage();
});

/* 
 *
 *  Botones CRUD
 *
 */

//
//Abrir form nuevo:
//
$("#btn-new").on("click", () => {

	let link = "/img/producto-sin-imagen.png";

	clearStyles();
	clearInputs();

	//Titulo...
	$("#titleModal").text("Nuevo Producto");

	$("#descripcion").select();
	$("#linkImagen").val("");
	$("#edt_img").attr("src", link);
	$("#precioCosto").val(0.00);
	$("#precioVenta").val(0.00);
	$("#precioEspecial").val(0.00);
	$("#stock").val(0);
	$("#stockCritico").val(0);

	//Abrir bs5 dialog...
	let tareaModal = new bootstrap.Modal(document.getElementById("productosModal"), { backdrop: 'static', keyboard: false });
	tareaModal.show();
});

//
//Abrir form editar:
//

$("#btn-edi").on("click", () => {
	if (isSelectedRow()) {
		var id = getProdId();

		$.ajax({
			method: "GET",
			url: `${rootUrl}productos/form/${id}`,
			beforeSend: function() {

				clearStyles();
				clearInputs();

				//Titulo...
				$("#titleModal").text("Ver o Modificar Producto");

				//Abrir bs5 dialog...
				let tareaModal = new bootstrap.Modal(document.getElementById("productosModal"), { backdrop: 'static', keyboard: false });
				tareaModal.show();
			},
			success: function(data) {
				$("#numero").val(data.numero);
				$("#codigoBarras").val(data.codigoBarras);
				$("#descripcion").val(data.descripcion);
				$("#categoria").val(data.categoria.numero);
				$("#stock").val(data.stock);
				$("#stockCritico").val(data.stockCritico);
				$("#linkImagen").val(data.linkImagen);
				$("#edt_img").attr("src", (data.linkImagen !== "" ? data.linkImagen : "../img/producto-sin-imagen.png"));
				$("#precioVenta").val(data.precioVenta.toLocaleString("es-AR", {
					minimumFranctionDigits: 2,
					maximumFranctionDigits: 2
				}));
				$("#precioEspecial").val(data.precioEspecial.toLocaleString("es-AR", {
					minimumFranctionDigits: 2,
					maximumFranctionDigits: 2
				}));
				$("#precioCosto").val(data.precioCosto.toLocaleString("es-AR", {
					minimumFranctionDigits: 2,
					maximumFranctionDigits: 2
				}));
			},
			error: function(xhr) {
				//alert("Ocurrió un error..." + xhr.status.text);
				Swal.fire({
					icon: 'error',
					title: 'Oops...',
					text: 'No se pudo comunicar con el servidor!',
					footer: 'Verifique su conexión a internet o comuníquese con el administrador'
				});
			}
		});

	}
});

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
	producto.linkImagen = $("#linkImagen").val();
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
		url: `${rootUrl}productos/guardar`,
		data: producto,
		beforeSend: function() {
			// Remover errores previos...
			clearStyles();
		},
		success: function() {

			$("#productosModal").modal("hide");
			table.ajax.reload(null, false); // user paging is not reset on reload
			Swal.fire({
				title: 'Éxito!',
				text: 'Producto guardado.',
				icon: 'success',
				confirmButtonText: 'Aceptar'
			});
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
				});
			},
			404: function(xhr) {
				console.log("Status Error: " + xhr.status);
				Swal.fire({
					icon: 'error',
					title: 'Oops...',
					text: 'No se pudo comunicar con el servidor!',
					footer: 'Verifique su conexión a internet o comuníquese con el administrador'
				});
			}
		}
	});

	$("#btn-edi").addClass("disabled");
	$("#btn-del").addClass("disabled");

});

// Botón de borrar:
//
$("#btn-del").on("click", function() {

	var accion = getEstado() == true ? "DESHABILITAR" : "HABILITAR";
	var estado = getEstado() == true ? "DESHABILITADO" : "HABILITADO";
	var prod = getProdDescrip();

	Swal.fire({
		icon: 'warning',
		title: '¡Atención!',
		text: getEstado() ?
			"El producto '" + prod + "' no estará disponible para las ventas. ¿Desea continuar?"
			: "Está por " + accion + " el producto '" + prod + "'. ¿Desea continuar?",
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Confirmar'
	}).then((result) => {
		if (result.value) {
			$.ajax({
				url: `${rootUrl}productos/cambiar-estado/${getProdId()}`,
				type: 'GET',
			}).done(function(resp) {
				table.ajax.reload(null, false); // user paging is not reset on reload
			});
			Swal.fire(
				'¡Confirmado!',
				'Producto ' + estado + ".",
				'success'
			)
		}
	});

	$("#btn-edi").addClass("disabled");
	$("#btn-del").addClass("disabled");
});







