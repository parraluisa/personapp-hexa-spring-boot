package co.edu.javeriana.as.personapp.model.response;

import java.time.LocalDate;

import co.edu.javeriana.as.personapp.model.request.EstudioRequest;

public class EstudioResponse extends EstudioRequest{
    private String status;

    public EstudioResponse(String idProfession, String idPerson, LocalDate graduationDate, String universityName, String database, String status) {
        super(idProfession, idPerson, graduationDate, universityName, database);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
