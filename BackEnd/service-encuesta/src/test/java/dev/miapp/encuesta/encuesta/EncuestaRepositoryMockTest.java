package dev.miapp.encuesta.encuesta;

import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import dev.miapp.encuesta.encuesta.entity.Encuesta;
import dev.miapp.encuesta.encuesta.repository.EncuestaRepository;

@DataJpaTest
public class EncuestaRepositoryMockTest {
	
	@Autowired
	private EncuestaRepository encuestaRepository;
	
	@Test
	public void whenFindAll_thenReturnListEncuesta(){
		
		Encuesta encuesta1 = Encuesta.builder()
				.name("Edwin")
				.lastName("Quispe")
				.age(34)
				.language("Java")
				.createAt(new Date())
				.build();
		
		encuestaRepository.save(encuesta1);
		
		List<Encuesta> founds = encuestaRepository.findByLanguage(encuesta1.getLanguage());
		
		Assertions.assertThat(founds.size()).isGreaterThanOrEqualTo(3);
		
	}

}
