package net.iterart.chingolo.model.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.iterart.chingolo.model.domain.Categoria;
import net.iterart.chingolo.model.domain.Producto;

public interface IProductoService {

public List<Producto> buscarTodo();
	
	public Page<Producto> findAll(Pageable pageable);
	
	public Producto buscarPorId(Long id);
	
	public void guardar(Producto producto);
		
	//==========================================================================//
	//============================== Categorías ====================================//
	//==========================================================================//
	
	public List<Categoria> buscarCategorias();
}
