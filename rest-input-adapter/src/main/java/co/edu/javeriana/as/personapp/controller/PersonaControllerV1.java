package co.edu.javeriana.as.personapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.as.personapp.adapter.PersonaInputAdapterRest;
import co.edu.javeriana.as.personapp.model.request.PersonaRequest;
import co.edu.javeriana.as.personapp.model.response.PersonaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/persona")
public class PersonaControllerV1 {
	
	@Autowired
	private PersonaInputAdapterRest personaInputAdapterRest;
	
	/**
	 * Método para obtener la lista de personas.
	 * 
	 * @param database El nombre de la base de datos.
	 * @return La lista de personas.
	 */
	@Operation(summary = "Obtener la lista de personas")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de personas obtenida exitosamente")
	})
	@ResponseBody
	@GetMapping(path = "/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonaResponse> personas(
		@Parameter(description = "Nombre de la base de datos", required = true, example = "Mongo OR Maria")
		@PathVariable String database) {
		return personaInputAdapterRest.historial(database.toUpperCase());
	}


	/**
	 * Método para crear una nueva persona.
	 * 
	 * @param request La información de la persona a crear.
	 * @return La persona creada.
	 */
	@Operation(summary = "Crear una nueva persona")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Persona creada exitosamente")
	})
	@ResponseBody
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public PersonaResponse crearPersona(@RequestBody PersonaRequest request) {
		
		return personaInputAdapterRest.crearPersona(request);
	}

	/**
	 * Método para actualizar la información de una persona.
	 * 
	 * @param request La información de la persona a actualizar.
	 * @return La persona actualizada.
	 */
	@Operation(summary = "Actualizar la información de una persona")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Información de la persona actualizada exitosamente")
	})
	@ResponseBody
	@PutMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public PersonaResponse updatePersonaById(@RequestBody PersonaRequest request) {

		return personaInputAdapterRest.actualizarPersona(request);
	}

	/**
	 * Método para eliminar una persona por su identificación.
	 * 
	 * @param database       El nombre de la base de datos.
	 * @param identification La identificación de la persona a eliminar.
	 * @return La persona eliminada.
	 */
	@Operation(summary = "Eliminar persona por ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Persona eliminada exitosamente"),
			@ApiResponse(responseCode = "404", description = "Persona no encontrada")
	})
	@ResponseBody
	@DeleteMapping(path = "/{database}/{identification}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonaResponse deletePersonaById(
			@Parameter(description = "Nombre de la base de datos", required = true, example = "Mongo OR Maria")
			@PathVariable String database,
			@Parameter(description = "Identificación de la persona", required = true, example = "1234567890")
			@PathVariable String identification) {
		log.info("Into deletePersonaById REST API");
		return personaInputAdapterRest.eliminarPersona(database, identification);
	}

	@Operation(summary = "Buscar persona por ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Persona encontrada exitosamente"),
			@ApiResponse(responseCode = "404", description = "Persona no encontrada")
	})

	/**
	 * Método para encontrar una persona por su identificación.
	 * 
	 * @param database       El nombre de la base de datos.
	 * @param identification La identificación de la persona a eliminar.
	 * @return La persona encontrada.
	 */
	@ResponseBody
	@GetMapping(path = "/{database}/{identification}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonaResponse findPersonById(
			@Parameter(description = "Nombre de la base de datos", required = true, example = "Mongo OR Maria")
			@PathVariable String database,
			@Parameter(description = "Identificación de la persona", required = true, example = "1234567890")
			@PathVariable String identification) {
		log.info("Into personaById REST API");
		return personaInputAdapterRest.encontrarPersonaPorId(database, identification);
	}

}
