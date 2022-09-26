package br.com.rafaellbarros.rest.utils;

import br.com.rafaellbarros.model.entity.PessoaEntity;

/**
 * created by:
 *
 * @author rafael barros for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 22/09/2022
 */

public class PessoaCreator {

   public static PessoaEntity createPessoaToBeSaved() {
      return PessoaEntity.builder()
            .nome("Amanda Victoria")
            .idade(27L)
            .build();
   }

   public static PessoaEntity createValidPessoa() {
      return PessoaEntity.builder()
              .id(1L)
              .nome("Rafael Barros")
              .idade(33L)
              .build();
   }
}
