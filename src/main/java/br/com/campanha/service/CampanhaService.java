package br.com.campanha.service;

import br.com.campanha.adapter.CampanhaAdapter;
import br.com.campanha.domain.CampanhaEntity;
import br.com.campanha.dto.CampanhaDto;
import br.com.campanha.dto.CampanhaResponse;
import br.com.campanha.repository.CampanhaRepository;
import br.com.campanha.utils.exception.BusinessException;
import jdk.nashorn.internal.runtime.ParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CampanhaService {

    @Autowired
    private CampanhaRepository campanhaRepository;

    DateTimeFormatter parser = DateTimeFormatter.ofPattern( "dd/MM/uuuu" );

    public Long salvar( CampanhaDto campanhaDto ) throws Exception {
        LocalDate dataInicioCampanha = LocalDate.parse( campanhaDto.getDataInicioVigencia(), parser );
        LocalDate dataFimCampanha = LocalDate.parse( campanhaDto.getDataFimVigencia(), parser );
        boolean dataExiste = false;

        if( dataInicioCampanha.isAfter( dataFimCampanha ) ) {
            throw new BusinessException( "Data inicial é maior que data final", dataInicioCampanha.toString() );
        }

        do {
            final List< CampanhaEntity > campanhasAtivas = campanhaRepository.findByDataInicioVigenciaAndDataFimVigencia( dataInicioCampanha, dataFimCampanha );
            if( campanhasAtivas.size() > 0 ) {
                dataExiste = true;
                dataFimCampanha = dataFimCampanha.plusDays( 1L );
            } else {
                dataExiste = false;
            }
        } while( dataExiste );

        final CampanhaEntity campanhaEntity = campanhaRepository.save( CampanhaAdapter.requestToDomain( campanhaDto, dataFimCampanha ) );

        return campanhaEntity.getId();
    }

    public void deletar( Long id ) throws Exception {
        final CampanhaEntity campanhaEntity = autenticar( id );
        campanhaRepository.delete( campanhaEntity );
    }

    public CampanhaResponse alterar( Long idRelogioEntity, CampanhaDto campanhaDto ) throws BusinessException, ParseException {

        autenticar( idRelogioEntity );

        final CampanhaEntity campanhaEntity = CampanhaAdapter.requestToDomain( campanhaDto );
        campanhaEntity.setId( idRelogioEntity );
        campanhaRepository.save( campanhaEntity );

        return CampanhaAdapter.domainToResponse( campanhaEntity );

    }

    public Set< CampanhaResponse > listar() {
        final Iterable< CampanhaEntity > campanhas = campanhaRepository.findAll();
        Set< CampanhaResponse > responses = new LinkedHashSet< CampanhaResponse >();
        campanhas.forEach( r -> {
            CampanhaResponse response = null;
            try {
                response = CampanhaAdapter.domainToResponse( r );
            } catch( ParseException e ) {
                throw new ParserException( "Erro ao converter datas" );
            }
            responses.add( response );
        } );
        return responses;
    }

    public CampanhaEntity autenticar( Long id ) throws BusinessException {
        Optional< CampanhaEntity > optCampanhaEntity = campanhaRepository.findById( id );
        if( !optCampanhaEntity.isPresent() ) {
            throw new BusinessException( "Campanha nao encontrada", "id" );
        }
        return optCampanhaEntity.get();
    }
}
