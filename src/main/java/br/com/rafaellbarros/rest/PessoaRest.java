package br.com.rafaellbarros.rest;

import br.com.rafaellbarros.model.entity.Pessoa;
import br.com.rafaellbarros.service.PessoaService;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

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
   public Response inserir(final Pessoa pessoa) {
        final Pessoa pessoaSave = pessoaService.inserir(pessoa);
        // return Response.created(URI.create("/pessoas/" + pessoaSave.getId())).build();
        return Response.status(Response.Status.CREATED).entity(pessoaSave).build();
   }

}
