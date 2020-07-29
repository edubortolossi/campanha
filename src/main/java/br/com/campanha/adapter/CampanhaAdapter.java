package br.com.campanha.adapter;

import br.com.campanha.domain.CampanhaEntity;
import br.com.campanha.dto.CampanhaDto;
import br.com.campanha.dto.CampanhaResponse;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CampanhaAdapter {

    public static CampanhaEntity requestToDomain( CampanhaDto campanhaDto, LocalDate dataFimVigencia ) throws ParseException {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern( "dd/MM/uuuu" );

        return CampanhaEntity.builder()
                .nome( campanhaDto.getNome() )
                .time( campanhaDto.getTime() )
                .dataInicioVigencia( LocalDate.parse( campanhaDto.getDataInicioVigencia(), parser ) )
                .dataFimVigencia( dataFimVigencia )
                .build();
    }

    public static CampanhaEntity requestToDomain( CampanhaDto campanhaDto ) throws ParseException {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern( "dd/MM/uuuu" );
        return CampanhaEntity.builder()
                .nome( campanhaDto.getNome() )
                .time( campanhaDto.getTime() )
                .dataInicioVigencia( LocalDate.parse( campanhaDto.getDataInicioVigencia(), parser ) )
                .dataFimVigencia( LocalDate.parse( campanhaDto.getDataFimVigencia(), parser ) )
                .build();
    }

    public static CampanhaResponse domainToResponse( CampanhaEntity campanhaEntity ) throws ParseException {
        return CampanhaResponse.builder()
                .nome( campanhaEntity.getNome() )
                .time( campanhaEntity.getTime() )
                .dataInicioVigencia( campanhaEntity.getDataInicioVigencia().toString() )
                .dataFimVigencia( campanhaEntity.getDataFimVigencia().toString() )
                .build();
    }

}
