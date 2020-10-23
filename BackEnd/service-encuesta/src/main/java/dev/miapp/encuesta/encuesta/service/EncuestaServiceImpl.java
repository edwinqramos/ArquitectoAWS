package dev.miapp.encuesta.encuesta.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.miapp.encuesta.encuesta.entity.Encuesta;
import dev.miapp.encuesta.encuesta.repository.EncuestaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EncuestaServiceImpl implements EncuestaService {
	
	private final EncuestaRepository encuestaRepository;

	@Override
	public Encuesta createEncuesta(Encuesta encuesta) {
		encuesta.setCreateAt(new Date());
		return encuestaRepository.save(encuesta);
	}

	@Override
	public Encuesta updateEncuesta(Encuesta encuesta) {
		Encuesta encuestaDB = getEncuesta(encuesta.getId());
		if(null == encuestaDB) {
			return null;
		}
		
		encuestaDB.setName(encuesta.getName()); 
		encuestaDB.setLastName(encuesta.getLastName());
		encuestaDB.setAge(encuesta.getAge());
		encuestaDB.setLanguage(encuesta.getLanguage());

		return encuestaRepository.save(encuestaDB);
	}


	@Override
	public Encuesta deleteEncuesta(Long id) {
		Encuesta encuestaDB = getEncuesta(id);
		if (null == encuestaDB) {
			return null;
		}
		
		return encuestaRepository.save(encuestaDB);
	}
	
	@Override
	public Encuesta getEncuesta(Long id) {
		return encuestaRepository.findById(id).orElse(null);
	}

	@Override
	public List<Encuesta> listAllEncuesta() {
		return encuestaRepository.findAll();
	}

	@Override
	public List<Encuesta> findByLanguage(String language) {
		return encuestaRepository.findByLanguage(language);
	}


}
