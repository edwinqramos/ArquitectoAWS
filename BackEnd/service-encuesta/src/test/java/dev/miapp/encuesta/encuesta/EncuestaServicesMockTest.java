package dev.miapp.encuesta.encuesta;

import java.util.Date;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.miapp.encuesta.encuesta.entity.Encuesta;
import dev.miapp.encuesta.encuesta.repository.EncuestaRepository;
import dev.miapp.encuesta.encuesta.service.EncuestaService;
import dev.miapp.encuesta.encuesta.service.EncuestaServiceImpl;

@SpringBootTest
public class EncuestaServicesMockTest {

	@Mock
	private EncuestaRepository encuestaRepository;
	
	@Autowired
	private EncuestaService encuestaService;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		encuestaService = new EncuestaServiceImpl(encuestaRepository);
		
		Encuesta encuesta =  Encuesta.builder()
				.name("Edwin")
				.lastName("Quispe")
				.age(34)
				.language("Java")
				.createAt(new Date())
				.build();
		
		Mockito.when(encuestaRepository.findById(1L))
		.thenReturn(Optional.of(encuesta));
		
	}
	
	@Test
	public void whenValidGetID_ThenReturnEncuesta(){
		Encuesta found = encuestaService.getEncuesta(1L);
		
		Assertions.assertThat(found.getName()).isEqualTo("Edwin");
	}
}
