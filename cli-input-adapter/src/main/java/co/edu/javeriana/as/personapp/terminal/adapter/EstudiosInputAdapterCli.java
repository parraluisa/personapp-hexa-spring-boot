package co.edu.javeriana.as.personapp.terminal.adapter;

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
import co.edu.javeriana.as.personapp.terminal.mapper.EstudiosMapperCli;
import co.edu.javeriana.as.personapp.terminal.model.EstudiosModelCli;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Slf4j
@Adapter
public class EstudiosInputAdapterCli {

    //MariaDB
    @Autowired
    @Qualifier("studyOutputAdapterMaria")
    private StudyOutputPort studyOutputPortMaria;

    @Autowired
    @Qualifier("personOutputAdapterMaria")
    private PersonOutputPort personOutputPortMaria;

    @Autowired
    @Qualifier("professionOutputAdapterMaria")
    private ProfessionOutputPort professionOutputPortMaria;

    //MongoDB
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
    private EstudiosMapperCli EstudiosMapperCli;

    //Puertos de entrada a la aplicación
    StudyInputPort studyInputPort;
    ProfessionInputPort professionInputPort;
    PersonInputPort personInputPort;

    public void setStudyOutputPortInjection(String dbOption) throws InvalidOptionException {
        if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {

            studyInputPort = new StudyUseCase(studyOutputPortMaria);
            professionInputPort = new ProfessionUseCase(professionOutputPortMaria);
            personInputPort = new PersonUseCase(personOutputPortMaria);

        } else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {

            studyInputPort = new StudyUseCase(studyOutputPortMongo);
            professionInputPort = new ProfessionUseCase(professionOutputPortMongo);
            personInputPort = new PersonUseCase(personOutputPortMongo);

        } else {
            throw new InvalidOptionException("Invalid database option: " + dbOption);
        }
    }

    public void historial(){
        log.info("Into historial StudyEntity in Input Adapter");
        studyInputPort.findAll().stream()
            .map(EstudiosMapperCli::fromDomainToAdapterCli)
            .forEach(System.out::println);
    }

    public void crearEstudios(EstudiosModelCli Estudios, String dbOption){
        log.info("Into crearEstudios StudyEntity in Input Adapter");
        try{
            setStudyOutputPortInjection(dbOption);
            //Get person by id
            Person person = personInputPort.findOne(Integer.parseInt(Estudios.getIdPerson()));
            //Get profession by id
            Profession profession = professionInputPort.findOne(Integer.parseInt(Estudios.getIdProfession()));
            studyInputPort.create(EstudiosMapperCli.fromAdapterCliToDomain(Estudios, profession, person));
            System.out.println("Estudios creado correctamente "+Estudios.toString());
        }catch (Exception e){
            log.warn(e.getMessage());
            System.out.println("Error al crear el Estudios");
        }
    }
}