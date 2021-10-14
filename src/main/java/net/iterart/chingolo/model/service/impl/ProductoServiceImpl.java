package net.iterart.chingolo.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.iterart.chingolo.model.domain.Categoria;
import net.iterart.chingolo.model.domain.Producto;
import net.iterart.chingolo.model.repository.ICategoriaRepository;
import net.iterart.chingolo.model.repository.IProductoRepository;
import net.iterart.chingolo.model.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService {
	
	@Autowired
	IProductoRepository productoRepo;
	
	@Autowired
	ICategoriaRepository categoriaRepo;

	@Override
	@Transactional(readOnly = true)
	public List<Producto> buscarTodo() {
		return productoRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Producto> findAll(Pageable pageable) {
		return productoRepo.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Producto buscarPorId(Long id) {
		return productoRepo.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void guardar(Producto producto) {
		productoRepo.save(producto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Categoria> buscarCategorias() {
		return categoriaRepo.findAll();
	}

}
