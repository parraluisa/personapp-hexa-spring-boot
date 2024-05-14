package co.edu.javeriana.as.personapp.controller;

import co.edu.javeriana.as.personapp.adapter.EstudioInputAdapterRest;
import co.edu.javeriana.as.personapp.model.request.EstudioRequest;
import co.edu.javeriana.as.personapp.model.response.EstudioResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/estudio")
public class EstudioControllerV1 {

    @Autowired
    private EstudioInputAdapterRest estudioInputAdapterRest;

    
    @ResponseBody
    @GetMapping(path = "/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EstudioResponse> estudios(@PathVariable String database) {
        log.info("Into estudios REST API");
        return estudioInputAdapterRest.historial(database.toUpperCase());
    }

    
    @ResponseBody
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EstudioResponse crearEstudio(@RequestBody EstudioRequest request) {
        log.info("esta en el metodo crearEstudio en el controller del api");
        return estudioInputAdapterRest.crearEstudio(request);
    }
}