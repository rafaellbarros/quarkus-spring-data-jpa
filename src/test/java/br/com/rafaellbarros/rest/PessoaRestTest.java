package br.com.rafaellbarros.rest;

import br.com.rafaellbarros.model.entity.Pessoa;
import br.com.rafaellbarros.rest.utils.PessoaCreator;
import br.com.rafaellbarros.service.PessoaService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * created by:
 *
 * @author rafael barros for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 22/09/2022
 */

@QuarkusTest
class PessoaRestTest {

    @InjectMocks
    PessoaRest pessoaRest;

    @Mock
    PessoaService pessoaServiceMock;

    private final Pessoa pessoaMock = PessoaCreator.createValidPessoa();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        BDDMockito.when(pessoaServiceMock.inserir(ArgumentMatchers.any(Pessoa.class))).thenReturn(pessoaMock);

    }


    @Test
    void listarTodasTest() {
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(PessoaCreator.createValidPessoa());

        Mockito.when(pessoaServiceMock.listarTodas()).thenReturn(pessoas);
        Response response = pessoaRest.listarTodas();
        assertNotNull(response);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
        List<Pessoa> entity = (List<Pessoa>) response.getEntity();
        assertFalse(entity.isEmpty());
        assertEquals(1L, entity.get(0).getId());
        assertEquals("Rafael Barros", entity.get(0).getNome());
        assertEquals(33L, entity.get(0).getIdade());
    }

    @Test
    void inserirTest() {

        Response response = pessoaRest.inserir(PessoaCreator.createPessoaToBeSaved());

        assertNotNull(response);
        assertNotNull(response.getEntity());
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());

        final Pessoa entity = (Pessoa) response.getEntity();
        assertEquals(1L, entity.getId());
        assertEquals("Rafael Barros", entity.getNome());
        assertEquals(33L, entity.getIdade());

    }
}