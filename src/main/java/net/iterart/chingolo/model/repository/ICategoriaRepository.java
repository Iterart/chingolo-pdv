package net.iterart.chingolo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.iterart.chingolo.model.domain.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {

}
