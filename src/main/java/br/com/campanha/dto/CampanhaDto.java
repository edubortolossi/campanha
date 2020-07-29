package br.com.campanha.dto;

import br.com.campanha.enums.Time;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@ApiModel( value = "campanha", description = "Data transfer from user" )
@JsonInclude( JsonInclude.Include.NON_NULL )
public class CampanhaDto {

    @ApiModelProperty( required = true )
    @NotBlank( message = "{required.field}" )
    private String nome;

    @NotNull( message = "{required.field}" )
    @ApiModelProperty( allowableValues = "ATHLETICO_PARANAENSE,ATLETICO_GOIANIENSE, ATLETICO_MINEIRO"
            + "BAHIA, BOTAFOGO, CEARA, CORINTHIANS, CORITIBA, FLAMENGO, FLUMINENSE, FORTALEZA"
            + "GOIAS, GREMIO, INTERNACIONAL, PALMEIRAS, RED_BULL, SANTOS, SAO_PAULO, SPORT, VASCO" )
    private Time time;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    @NotBlank(message = "{required.field}")
    private String dataInicioVigencia;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    @NotBlank(message = "{required.field}")
    private String dataFimVigencia;

}
