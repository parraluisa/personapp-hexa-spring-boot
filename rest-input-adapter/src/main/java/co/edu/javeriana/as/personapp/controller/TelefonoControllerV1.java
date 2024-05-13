package co.edu.javeriana.as.personapp.controller;

import co.edu.javeriana.as.personapp.adapter.TelefonoInputAdapterRest;
import co.edu.javeriana.as.personapp.model.request.TelefonoRequest;
import co.edu.javeriana.as.personapp.model.response.TelefonoResponse;
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

    //Get All Phones
    @ResponseBody
    @GetMapping(path="/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TelefonoResponse> telefonos(@PathVariable String database){
        log.info("Into telefonos REST API");
        return telefonoInputAdapterRest.historial(database.toUpperCase());
    }

    //Post Phone
    @ResponseBody
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public TelefonoResponse crearTelefono(@RequestBody TelefonoRequest request) {
        log.info("esta en el metodo crearTelefono en el controller del api");
        return telefonoInputAdapterRest.crearTelefono(request);
    }

}
