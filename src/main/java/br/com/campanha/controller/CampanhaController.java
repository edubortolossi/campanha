package br.com.campanha.controller;

import br.com.campanha.dto.CampanhaDto;
import br.com.campanha.dto.CampanhaResponse;
import br.com.campanha.service.CampanhaService;
import br.com.campanha.utils.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Set;

@RestController
@RequestMapping( value = "/campanhas", produces = MediaType.APPLICATION_JSON_VALUE )
public class CampanhaController {

    @Autowired
    CampanhaService campanhaService;

    @PostMapping
    public ResponseEntity< Long > salvar( @Valid @RequestBody CampanhaDto campanha ) throws Exception {
        return ResponseEntity.status( HttpStatus.CREATED ).body( campanhaService.salvar( campanha ) );
    }

    @DeleteMapping( value = "/{id}" )
    public ResponseEntity< String > deletar( @PathVariable( value = "id" ) Long id ) throws Exception {

        campanhaService.deletar( id );

        return ResponseEntity.ok( "Campanha deletada com sucesso" );
    }

    @PutMapping( value = "/{id}" )
    public ResponseEntity< CampanhaResponse > alterar( @PathVariable( value = "id" ) Long id, @Valid @RequestBody CampanhaDto campanha ) throws ParseException, BusinessException {
        return ResponseEntity.ok( campanhaService.alterar( id, campanha ) );
    }

    @GetMapping
    public ResponseEntity< Set< CampanhaResponse > > listar() {

        return ResponseEntity.ok( campanhaService.listar() );
    }
}
