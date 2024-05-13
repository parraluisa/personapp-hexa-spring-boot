package co.edu.javeriana.as.personapp.model.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudioRequest {

    private String id_pro;
    private String id_cc;
    private String univer;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    private String database;
    
}
