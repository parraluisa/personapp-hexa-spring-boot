package co.edu.javeriana.as.personapp.application.port.in;

import co.edu.javeriana.as.personapp.common.annotations.Port;

import java.util.List;

import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Study;

@Port
public interface StudyInputPort {
    
    public void setPersistence(StudyOutputPort studyPersistence);

    public Study create(Study study);

    public Study edit(Integer identification, Study study) throws NoExistException;

    public Boolean drop(Integer identification) throws NoExistException;

    public List<Study> findAll();

    public Study findOne(Integer identification) throws NoExistException;

    public Integer count();

}
