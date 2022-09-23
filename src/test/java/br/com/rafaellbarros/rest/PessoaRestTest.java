package br.com.rafaellbarros.rest;

import br.com.rafaellbarros.model.entity.Pessoa;
import br.com.rafaellbarros.rest.utils.PessoaCreator;
import br.com.rafaellbarros.service.PessoaService;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;
import java.util.List;

import static java.util.Collections.singletonList;

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

        BDDMockito.when(pessoaServiceMock.listarTodas()).thenReturn(singletonList(pessoaMock));

        BDDMockito.when(pessoaServiceMock.inserir(ArgumentMatchers.any(Pessoa.class))).thenReturn(pessoaMock);

    }


    @Test
    void listarTodasTest() {

        final Long exptectedId = PessoaCreator.createValidPessoa().getId();
        Response response = pessoaRest.listarTodas();
        List<Pessoa> pessoas = (List<Pessoa>) response.getEntity();

        Assertions.assertThat(pessoas)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(pessoas.get(0).getId()).isEqualTo(exptectedId);
        Assertions.assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());

    }

    @Test
    void inserirTest() {

        final Response response = pessoaRest.inserir(PessoaCreator.createPessoaToBeSaved());

        final Pessoa pessaoSave = (Pessoa) response.getEntity();

                Assertions.assertThat(pessaoSave).isNotNull().isEqualTo(pessoaMock);
        Assertions.assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());

    }
}