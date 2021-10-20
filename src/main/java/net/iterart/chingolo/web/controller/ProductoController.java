package net.iterart.chingolo.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import net.iterart.chingolo.model.domain.Categoria;
import net.iterart.chingolo.model.repository.IProductoRepository;
import net.iterart.chingolo.model.service.IProductoService;
import net.iterart.chingolo.model.service.datatable.impl.ProductoDatatableServiceImpl;

@Controller
@RequestMapping("/productos")
@SessionAttributes({"producto"})
public class ProductoController {
	
	@Autowired
	IProductoRepository productoRepository;
	
	@Autowired
	IProductoService productoService;

	@GetMapping("/listado")
	public String listar(Model model) {
		
		return "productos/list";
	}
	
	@GetMapping("/datatable/list")
	public ResponseEntity<?> datatable(HttpServletRequest request) {
		
		Map<String, Object> data = new ProductoDatatableServiceImpl().execute(productoRepository, request);

		return ResponseEntity.ok(data);
	}
	
	@ModelAttribute("categorias")
	public List<Categoria> getCategorias() {
		return productoService.buscarCategorias();
	}
}
