package br.com.rafaellbarros.rest.utils;

import br.com.rafaellbarros.model.entity.Pessoa;

/**
 * created by:
 *
 * @author rafael barros for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 22/09/2022
 */

public class PessoaCreator {

   public static Pessoa createPessoaToBeSaved() {
      return Pessoa.builder()
            .nome("Amanda Victoria")
            .idade(27L)
            .build();
   }

   public static Pessoa createValidPessoa() {
      return Pessoa.builder()
              .id(1L)
              .nome("Rafael Barros")
              .idade(33L)
              .build();
   }
}
