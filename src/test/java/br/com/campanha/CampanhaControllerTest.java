package br.com.campanha;

import br.com.campanha.adapter.CampanhaAdapter;
import br.com.campanha.domain.CampanhaEntity;
import br.com.campanha.dto.CampanhaDto;
import br.com.campanha.enums.Time;
import br.com.campanha.repository.CampanhaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith( SpringRunner.class )
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles( "test" )
public class CampanhaControllerTest {

    private static final String CRIAR_CAMPANHA_PATH = "/campanhas";
    private static final String DELETAR_CAMPANHA_PATH = "/campanhas/{id}";

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    private CampanhaRepository campanhaRepository;

    DateTimeFormatter parser = DateTimeFormatter.ofPattern( "dd/MM/uuuu" );

    @BeforeTestMethod
    private void setUp() {
        campanhaRepository.deleteAll();
    }

    private CampanhaDto createRequestMockDto() {
        return CampanhaDto.builder()
                .time( Time.INTERNACIONAL )
                .nome( "INTER" )
                .dataFimVigencia( "01/01/2021" )
                .dataInicioVigencia( "01/08/2020" )
                .build();
    }

    @Test
    public void criarCampanhaSucess() throws Exception {
        mockMvc.perform( post( CRIAR_CAMPANHA_PATH )
                .contentType( MediaType.APPLICATION_JSON )
                .content( objectToJson( createRequestMockDto() ) ) )
                .andExpect( status().is2xxSuccessful() );
    }

    @Test
    public void criarCampanhaComDataExistenteSucess() throws Exception {
        campanhaRepository.deleteAll();
        campanhaRepository.save( CampanhaAdapter.requestToDomain( createRequestMockDto(), LocalDate.parse( createRequestMockDto().getDataFimVigencia(), parser ) ) );

        mockMvc.perform( post( CRIAR_CAMPANHA_PATH )
                .contentType( MediaType.APPLICATION_JSON )
                .content( objectToJson( createRequestMockDto() ) ) )
                .andExpect( status().is2xxSuccessful() );

        final Optional< CampanhaEntity > byId = campanhaRepository.findById( 2L );
        Assert.assertEquals( byId.get().getDataFimVigencia(), LocalDate.parse( createRequestMockDto().getDataFimVigencia(), parser ).plusDays( 1L ) );
    }

    @Test
    public void deletarCampanhaSucess() throws Exception {

        campanhaRepository.save( CampanhaAdapter.requestToDomain( createRequestMockDto(), LocalDate.parse( createRequestMockDto().getDataFimVigencia(), parser ) ) );

        mockMvc.perform( delete( DELETAR_CAMPANHA_PATH, 1L )
                .contentType( MediaType.APPLICATION_JSON )
                .content( objectToJson( createRequestMockDto() ) ) )
                .andExpect( status().is2xxSuccessful() );
    }

    @Test
    public void atualizarCampanhaSucess() throws Exception {

        campanhaRepository.save( CampanhaAdapter.requestToDomain( createRequestMockDto(), LocalDate.parse( createRequestMockDto().getDataFimVigencia(), parser ) ) );

        mockMvc.perform( delete( DELETAR_CAMPANHA_PATH, 1L )
                .contentType( MediaType.APPLICATION_JSON )
                .content( objectToJson( createRequestMockDto() ) ) )
                .andExpect( status().is2xxSuccessful() );
    }


    protected String objectToJson( Object object ) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString( object );
        } catch( JsonProcessingException var3 ) {
            return null;
        }
    }
}
