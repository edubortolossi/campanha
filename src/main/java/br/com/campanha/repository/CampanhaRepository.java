package br.com.campanha.repository;

import br.com.campanha.domain.CampanhaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CampanhaRepository extends CrudRepository< CampanhaEntity, Long> {

    List< CampanhaEntity > findByDataInicioVigenciaAndDataFimVigencia( LocalDate inicioVigencia, LocalDate fimVigencia );

}
