package dev.miapp.encuesta.encuesta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.miapp.encuesta.encuesta.entity.Encuesta;

@Repository
public interface EncuestaRepository extends JpaRepository<Encuesta, Long> {

	public List<Encuesta> findByLanguage(String language);
	
}
