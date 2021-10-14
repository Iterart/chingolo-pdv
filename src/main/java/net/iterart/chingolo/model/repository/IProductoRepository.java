package net.iterart.chingolo.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.iterart.chingolo.model.domain.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Long> {

	@Query("select p from Producto p where p.codigoBarras like %:criterio% or p.descripcion like %:criterio%"
			+ " or p.categoria.nombre like %:criterio%")
	Page<Producto> findByCodBarrasOrDescripcionOrCategoria(@Param("criterio") String criterio, Pageable pageable);

	@Query("select p from Producto p" + " where p.codigoBarras like %:criterio% or p.descripcion like %:criterio%"
			+ " or p.categoria.nombre like %:criterio%")
	List<Producto> findByCriteria(@Param("criterio") String criterio);

	@Query("select p from Producto p" + " where p.codigoBarras like %:criterio% or p.descripcion like %:criterio%"
			+ " or p.categoria.nombre like %:criterio%")
	List<Producto> findByCriteriaWhereActivo(@Param("criterio") String criterio);
}
