package br.com.rafaellbarros.service;

import br.com.rafaellbarros.model.entity.Pessoa;
import br.com.rafaellbarros.repository.PessoaRepository;
import br.com.rafaellbarros.rest.utils.PessoaCreator;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;

/**
 * created by:
 *
 * @author rafael barros for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 22/09/2022
 */

@QuarkusTest
class PessoaServiceTest {

    @InjectMocks
    PessoaService pessoaService;

    @Mock
    PessoaRepository pessoaRepositoryMock;

    private final Pessoa pessoaMock = PessoaCreator.createValidPessoa();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        BDDMockito.when(pessoaRepositoryMock.findAll()).thenReturn(singletonList(pessoaMock));

        BDDMockito.when(pessoaRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(pessoaMock));

        BDDMockito.when(pessoaRepositoryMock.save(ArgumentMatchers.any(Pessoa.class))).thenReturn(pessoaMock);

    }


    @Test
    void deveObterTodasPessoas() {

        final Long exptectedId = PessoaCreator.createValidPessoa().getId();
        final List<Pessoa> pessoaEntities = pessoaService.listarTodas();

        Assertions.assertThat(pessoaEntities)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(pessoaEntities.get(0).getId()).isEqualTo(exptectedId);
    }

    @Test
    void deveObterPorId() {

        final Long expectedId = pessoaMock.getId();

        final Pessoa pessoa = pessoaService.obterPorId(1L);

        Assertions.assertThat(pessoa).isNotNull();

        Assertions.assertThat(pessoa.getId()).isNotNull().isEqualTo(expectedId);

    }

    @Test
    void deveIncluirPessoa() {

        final Pessoa pessaoSave = pessoaService.inserir(PessoaCreator.createPessoaToBeSaved());

        Assertions.assertThat(pessaoSave).isNotNull().isEqualTo(pessoaMock);

    }

}