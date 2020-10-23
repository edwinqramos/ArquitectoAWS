package dev.miapp.encuesta.encuesta.service;

import java.util.List;

import dev.miapp.encuesta.encuesta.entity.Encuesta;

public interface EncuestaService {

	public Encuesta createEncuesta(Encuesta encuesta);
	
	public Encuesta updateEncuesta(Encuesta encuesta);
	
	public Encuesta deleteEncuesta(Long id);
	
	public Encuesta getEncuesta(Long id);

	public List<Encuesta> listAllEncuesta();

	public List<Encuesta> findByLanguage(String language);
	
}
