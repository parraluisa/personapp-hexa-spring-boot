package co.edu.javeriana.as.personapp.controller;

import co.edu.javeriana.as.personapp.adapter.TelefonoInputAdapterRest;
import co.edu.javeriana.as.personapp.model.request.TelefonoRequest;
import co.edu.javeriana.as.personapp.model.response.TelefonoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/telefono")
public class TelefonoControllerV1 {

    @Autowired
    private TelefonoInputAdapterRest telefonoInputAdapterRest;

    /**
     * Método para obtener todos los teléfonos.
     *
     * @param database La base de datos a consultar.
     * @return La lista de teléfonos.
     */
    @Operation(summary = "Obtener todos los teléfonos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de teléfonos obtenida exitosamente")
    })
    @ResponseBody
    @GetMapping(path="/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TelefonoResponse> telefonos(@PathVariable String database){
        log.info("Into telefonos REST API");
        return telefonoInputAdapterRest.historial(database.toUpperCase());
    }

    /**
     * Método para crear un teléfono.
     *
     * @param request La solicitud de creación del teléfono.
     * @return El teléfono creado.
     */
    @Operation(summary = "Crear un teléfono")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teléfono creado exitosamente")
    })
    @ResponseBody
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public TelefonoResponse crearTelefono(@RequestBody TelefonoRequest request) {
        log.info("Into crearTelefono REST API");
        return telefonoInputAdapterRest.crearTelefono(request);
    }


    /**
     * Método para actualizar un teléfono por su ID.
     *
     * @param request La solicitud de actualización del teléfono.
     * @return El teléfono actualizado.
     */
    @Operation(summary = "Actualizar un teléfono por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teléfono actualizado exitosamente")
    })
    @ResponseBody
    @PutMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public TelefonoResponse updateTelefonoById(@RequestBody TelefonoRequest request) {
        log.info("Into updateTelefonoById REST API");
        return telefonoInputAdapterRest.actualizarTelefono(request);
    }

    /**
     * Método para obtener un teléfono por su ID.
     *
     * @param database La base de datos a consultar.
     * @param id       El ID del teléfono a buscar.
     * @return El teléfono encontrado.
     */
    @Operation(summary = "Buscar un teléfono por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teléfono encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Teléfono no encontrado")
    })
    @ResponseBody
    @GetMapping(path = "/{database}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TelefonoResponse telefonoById(@PathVariable String database, @PathVariable String id){
        log.info("Into telefonoById REST API");
        return telefonoInputAdapterRest.buscarTelefono(database, id);
    }

    /**
     * Método para eliminar un teléfono por su ID.
     *
     * @param database La base de datos a consultar.
     * @param id       El ID del teléfono a eliminar.
     * @return El teléfono eliminado.
     */
    @Operation(summary = "Eliminar un teléfono por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teléfono eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Teléfono no encontrado")
    })
    @ResponseBody
    @DeleteMapping(path = "/{database}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TelefonoResponse deleteTelefonoById(@PathVariable String database, @PathVariable String id) {
        log.info("Into deleteTelefonoById REST API");
        return telefonoInputAdapterRest.eliminarTelefono(database, id);
    }
}
