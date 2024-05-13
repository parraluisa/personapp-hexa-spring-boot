package co.edu.javeriana.as.personapp.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import co.edu.javeriana.as.personapp.application.port.in.PersonInputPort;
import co.edu.javeriana.as.personapp.application.port.in.PhoneInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PersonOutputPort;
import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.PersonUseCase;
import co.edu.javeriana.as.personapp.application.usecase.PhoneUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.mapper.TelefonoMapperRest;
import co.edu.javeriana.as.personapp.model.request.TelefonoRequest;
import co.edu.javeriana.as.personapp.model.response.TelefonoResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
public class TelefonoInputAdapterRest {
    
    //MariaDB
    @Autowired
    @Qualifier("phoneOutputAdapterMaria")
    private PhoneOutputPort phoneOutputPortMaria;

    @Autowired
    @Qualifier("personOutputAdapterMaria")
    private PersonOutputPort personOutputPortMaria;

    //MongoDB
    @Autowired
    @Qualifier("phoneOutputAdapterMongo")
    private PhoneOutputPort phoneOutputPortMongo;

    @Autowired
    @Qualifier("personOutputAdapterMongo")
    private PersonOutputPort personOutputPortMongo;

    @Autowired
    private TelefonoMapperRest telefonoMapperRest;

    PhoneInputPort phoneInputPort;
    PersonInputPort personInputPort;

    private String setPhoneOutportInjection(String dbOption) throws InvalidOptionException{
        if(dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())){
            phoneInputPort = new PhoneUseCase(phoneOutputPortMaria);
            personInputPort = new PersonUseCase(personOutputPortMaria);
            return DatabaseOption.MARIA.toString();
        }else if(dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())){
            phoneInputPort = new PhoneUseCase(phoneOutputPortMongo);
            personInputPort = new PersonUseCase(personOutputPortMongo);
            return DatabaseOption.MONGO.toString();
        }else{
            throw new InvalidOptionException("Invalid database option: " + dbOption);
        }
    }
    
    public List<TelefonoResponse> historial(String database)
    {
        log.info("Into historial TelefonoEntity in Input Adapter");
        try{
            String db = setPhoneOutportInjection(database);
            if(db.equalsIgnoreCase(DatabaseOption.MARIA.toString())){
                return phoneInputPort.findAll().stream().map(telefonoMapperRest::fromDomainToAdapterRestMaria)
                        .collect(Collectors.toList());

        }else{
                return phoneInputPort.findAll().stream().map(telefonoMapperRest::fromDomainToAdapterRestMongo)
                        .collect(Collectors.toList());
            }
        }catch(InvalidOptionException e){
            log.warn("Invalid database option: " + database+" "+e.getMessage());
            return new ArrayList<TelefonoResponse>();
        }
    }

    public TelefonoResponse crearTelefono(TelefonoRequest request)
    {
        try {
            setPhoneOutportInjection(request.getDatabase());
            Person owner = personInputPort.findOne(Integer.parseInt(request.getIdPerson()));
            Phone phone = phoneInputPort.create(telefonoMapperRest.fromAdapterToDomain(request, owner));
            return telefonoMapperRest.fromDomainToAdapterRestMaria(phone);
        } catch (Exception e) {
            log.warn("Invalid database option: " + request.getDatabase()+" "+e.getMessage());
            //return new TelefonoResponse();
        }
        return null;
    }

    public TelefonoResponse editarTelefono(TelefonoRequest request)
    {
        try{
            setPhoneOutportInjection(request.getDatabase());
            //Get Owner
            Person owner = personInputPort.findOne(Integer.parseInt(request.getIdPerson()));
            Phone phone = phoneInputPort.edit(request.getNumber(),telefonoMapperRest.fromAdapterToDomain(request, owner));
            return telefonoMapperRest.fromDomainToAdapterRestMaria(phone);
        } catch (Exception e) {
            log.warn("Invalid database option: " + request.getDatabase()+" "+e.getMessage());
            //return new TelefonoResponse();
            return null;
        }
    }

    public TelefonoResponse eliminarTelefono(String database, String number)
    {
        try{
            setPhoneOutportInjection(database);
             phoneInputPort.drop(number);
            String msg = "DELETED";
            return new TelefonoResponse(number,msg,msg,database,msg);
        } catch (Exception e) {
            log.warn("Invalid database option: " + database+" "+e.getMessage());
            //return new TelefonoResponse();
            return null;
        }
    }

    public TelefonoResponse buscarTelefono(String database, String number)
    {
        try{
            setPhoneOutportInjection(database);
            Phone phone = phoneInputPort.findOne(number);
            return telefonoMapperRest.fromDomainToAdapterRestMaria(phone);
        } catch (Exception e) {
            log.warn("Invalid database option: " + database+" "+e.getMessage());
            //return new TelefonoResponse();
            return null;
        }
    }
}
