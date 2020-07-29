package br.com.campanha.dto;

import br.com.campanha.enums.Time;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CampanhaResponse {

    private String nome;

    private Time time;

    private String dataInicioVigencia;

    private String dataFimVigencia;
}
