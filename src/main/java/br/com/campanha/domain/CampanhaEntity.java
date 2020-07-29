package br.com.campanha.domain;

import br.com.campanha.enums.Time;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@Entity
public class CampanhaEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private Time time;

    private LocalDate dataInicioVigencia;

    private LocalDate dataFimVigencia;

    public CampanhaEntity( Long id, String nome, Time time, LocalDate dataInicioVigencia, LocalDate dataFimVigencia ) {
        this.id = id;
        this.nome = nome;
        this.time = time;
        this.dataInicioVigencia = dataInicioVigencia;
        this.dataFimVigencia = dataFimVigencia;
    }

    public CampanhaEntity() {}
}
