package net.iterart.chingolo.model.service.datatable.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import net.iterart.chingolo.model.domain.Producto;
import net.iterart.chingolo.model.repository.IProductoRepository;
import net.iterart.chingolo.model.service.datatable.IProductoDatatableService;

public class ProductoDatatableServiceImpl implements IProductoDatatableService {

	// Crear las columnas para enviar al datatable:
	private String[] cols = { 
			"numero", 
			"codigoBarras", 
			"descripcion", 
			"categoria",  
			"stock",
			"stockCritico",
			"nota",
			"precioCosto",
			"precioVenta",
			"precioEspecial"
			};

	@Override
	public Map<String, Object> execute(IProductoRepository repo, HttpServletRequest request) {

		// Parámetros del request de datatables
		int start = Integer.parseInt(request.getParameter("start"));				
		int length = Integer.parseInt(request.getParameter("length"));				
		int draw = Integer.parseInt(request.getParameter("draw"));
		int current = currentPage(start, length);
		
		//Obtener esos datos del request:
		String column = columnName(request);
		Sort.Direction direction = orderBy(request);
		String criterio = searchBy(request);
		
		//Crear la paginación a demanda...
		Pageable pageable = PageRequest.of(current, length, direction, column);
		Page<Producto> page = queryBy(criterio, repo, pageable);
		
		//Crear Map para generar json:
		Map<String, Object> json = new LinkedHashMap<>();
		json.put("draw", draw);
		json.put("recordsTotal", page.getTotalElements());
		json.put("recordsFiltered", page.getTotalElements());
		json.put("data", page.getContent());

		return json;
	}

	//Consulta JPA
	private Page<Producto> queryBy(String criterio, IProductoRepository repo, Pageable pageable) {
		if(criterio.isEmpty())
			return repo.findAll(pageable);
		else
			return repo.findByCodBarrasOrDescripcionOrCategoria(criterio, pageable);
	}

	private String searchBy(HttpServletRequest request) {
		return request.getParameter("search[value]").isEmpty() ? "" : request.getParameter("search[value]");
	}

	private Direction orderBy(HttpServletRequest request) {
		String order = request.getParameter("order[0][dir]");
		return (order.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC);
	}

	private String columnName(HttpServletRequest request) {
		int iCol = Integer.parseInt(request.getParameter("order[0][column]"));
		return cols[iCol];
	}

	private int currentPage(int start, int length) {
		// 0   | 1     | 2
		// 0-9 | 10-19 | 20-29
		return start / length;
	}

}
