package net.iterart.chingolo.model.service.datatable;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.iterart.chingolo.model.repository.IProductoRepository;

public interface IProductoDatatableService {

	public Map<String, Object> execute(IProductoRepository repo, HttpServletRequest request);
	
}