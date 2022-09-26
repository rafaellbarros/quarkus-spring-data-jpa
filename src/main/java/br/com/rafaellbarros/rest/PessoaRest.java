package br.com.rafaellbarros.rest;

import br.com.rafaellbarros.model.entity.PessoaEntity;
import br.com.rafaellbarros.service.PessoaService;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * created by:
 *
 * @author rafael barros for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 21/09/2022
 */

@RequiredArgsConstructor
@Path("/pessoas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaRest {

   private final PessoaService pessoaService;

   @GET
   public Response listarTodas() {
       return Response.ok(pessoaService.listarTodas()).build();
   }

   @POST
   public Response inserir(final PessoaEntity pessoaEntity) {
        final PessoaEntity pessoaEntitySave = pessoaService.inserir(pessoaEntity);
        return Response.status(Response.Status.CREATED).entity(pessoaEntitySave).build();
   }

}
