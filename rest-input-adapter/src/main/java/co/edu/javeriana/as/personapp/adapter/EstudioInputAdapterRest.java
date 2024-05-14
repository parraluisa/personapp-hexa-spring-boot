package co.edu.javeriana.as.personapp.adapter;

import co.edu.javeriana.as.personapp.application.port.in.PersonInputPort;
import co.edu.javeriana.as.personapp.application.port.in.ProfessionInputPort;
import co.edu.javeriana.as.personapp.application.port.in.StudyInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PersonOutputPort;
import co.edu.javeriana.as.personapp.application.port.out.ProfessionOutputPort;
import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.PersonUseCase;
import co.edu.javeriana.as.personapp.application.usecase.ProfessionUseCase;
import co.edu.javeriana.as.personapp.application.usecase.StudyUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mapper.EstudioMapperRest;
import co.edu.javeriana.as.personapp.model.request.EstudioRequest;
import co.edu.javeriana.as.personapp.model.response.EstudioResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Adapter
public class EstudioInputAdapterRest {

    //MariaDB Adapters
    @Autowired
    @Qualifier("studyOutputAdapterMaria")
    private StudyOutputPort studyOutputPortMaria;

    @Autowired
    @Qualifier("personOutputAdapterMaria")
    private PersonOutputPort personOutputPortMaria;

    @Autowired
    @Qualifier("professionOutputAdapterMaria")
    private ProfessionOutputPort professionOutputPortMaria;

    //MongoDB Adapters
    @Autowired
    @Qualifier("studyOutputAdapterMongo")
    private StudyOutputPort studyOutputPortMongo;

    @Autowired
    @Qualifier("personOutputAdapterMongo")
    private PersonOutputPort personOutputPortMongo;

    @Autowired
    @Qualifier("professionOutputAdapterMongo")
    private ProfessionOutputPort professionOutputPortMongo;

    @Autowired
    private EstudioMapperRest estudioMapperRest;

    //Use Cases
    StudyInputPort studyInputPort;
    PersonInputPort personInputPort;
    ProfessionInputPort professionInputPort;

    private String setStudyOutputPortInjection(String dbOption) throws InvalidOptionException {
        if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {

            studyInputPort = new StudyUseCase(studyOutputPortMaria);
            personInputPort = new PersonUseCase(personOutputPortMaria);
            professionInputPort = new ProfessionUseCase(professionOutputPortMaria);

            return DatabaseOption.MARIA.toString();
        } else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {

            studyInputPort = new StudyUseCase(studyOutputPortMongo);
            personInputPort = new PersonUseCase(personOutputPortMongo);
            professionInputPort = new ProfessionUseCase(professionOutputPortMongo);

            return  DatabaseOption.MONGO.toString();
        } else {
            throw new InvalidOptionException("Invalid database option: " + dbOption);
        }
    }

    // View All
    public List<EstudioResponse> historial(String database){
        log.info("Into historial EstudioEntity in Input Adapter");

        try{
            String db = setStudyOutputPortInjection(database);
            if(db.equalsIgnoreCase(DatabaseOption.MARIA.toString())){
                return studyInputPort.findAll().stream().map(estudioMapperRest::fromDomainToAdapterRestMaria)
                        .collect(Collectors.toList());
        }
            else {
                return studyInputPort.findAll().stream().map(estudioMapperRest::fromDomainToAdapterRestMongo)
                        .collect(Collectors.toList());
            }
    }catch (InvalidOptionException e){
            log.error("Error: " + e.getMessage());
            return null;
        }
    }

    // Create
    public EstudioResponse crearEstudio(EstudioRequest request)
    {
        try{
            setStudyOutputPortInjection(request.getDatabase());
            Person person = personInputPort.findOne(Integer.parseInt(request.getIdPerson()));
            Profession profession = professionInputPort.findOne(Integer.parseInt(request.getIdProfession()));

            Study study = studyInputPort.create(estudioMapperRest.fromAdapterToDomain(request, profession, person));
            System.out.println(study);
            return estudioMapperRest.fromDomainToAdapterRestMaria(study);
        }catch (Exception e){
            log.warn("Error: " + e.getMessage());
            return null;
        }
    }


}