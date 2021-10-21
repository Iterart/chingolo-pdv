package net.iterart.chingolo.web.controller;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import net.iterart.chingolo.model.domain.Categoria;
import net.iterart.chingolo.model.domain.Producto;
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
	
	@PostMapping("/guardar")
	public ResponseEntity<?> guardar(@Valid Producto producto, BindingResult result) {

		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}

		productoService.guardar(producto);
		
		return ResponseEntity.ok().build();
	}
	
	@ModelAttribute("categorias")
	public List<Categoria> getCategorias() {
		List<Categoria> categorias = productoService.buscarCategorias();
		categorias.sort(Comparator.comparing(Categoria::getNumero));
		return categorias;
	}
}
