package dev.miapp.encuesta.encuesta.controller.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.miapp.encuesta.encuesta.entity.Encuesta;
import dev.miapp.encuesta.encuesta.service.EncuestaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin
@RequestMapping(value="/api/v1/encuestas")
public class ApiEncuestaController {
	
	@Autowired
	private EncuestaService encuestaService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Listado de encuestas")
	@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal Error Server")	
	public ResponseEntity<List<Encuesta>> listEncuestaByLanguage(@RequestParam(name = "language", required = false) String language){
		List<Encuesta> encuestas = new ArrayList<>();
		
		if (null == language) {
			encuestas = encuestaService.listAllEncuesta();
			if(encuestas.isEmpty()) {
				return ResponseEntity.noContent().build(); 
			}
		}else {
			encuestas = encuestaService.findByLanguage(language);
			
			if(null == encuestas) {
				return ResponseEntity.notFound().build();
			}
		}
		
		return ResponseEntity.ok(encuestas);
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Retorna una encuesta en base al ID")
	@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal Error Server")
	public ResponseEntity<Encuesta> getEncuesta(
			@ApiParam(example = "1", required = true) @PathVariable("id") Long id){
		Encuesta encuesta = encuestaService.getEncuesta(id);
		
		if(null == encuesta) {
			ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(encuesta);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Registra una nueva encuesta")
	@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal Error Server", response = Encuesta.class)
	public ResponseEntity<Encuesta> createEncuesta(@RequestBody Encuesta encuesta){
		Encuesta encuestaDB = encuestaService.createEncuesta(encuesta);
		return ResponseEntity.status(HttpStatus.CREATED).body(encuestaDB);
	}

}
