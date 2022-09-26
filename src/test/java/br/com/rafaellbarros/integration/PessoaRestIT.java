package br.com.rafaellbarros.integration;

import br.com.rafaellbarros.rest.PessoaRest;
import br.com.rafaellbarros.rest.utils.PessoaCreator;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

/**
 * created by:
 *
 * @author rafael barros for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 22/09/2022
 */

@QuarkusTest
@Tag("integration")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestHTTPEndpoint(PessoaRest.class)
class PessoaRestIT {

    @Test
    @Order(1)
    void deveIncluirPessoa() {

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(PessoaCreator.createPessoaToBeSaved())
                .when().post()
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode());
    }

   @Test
   @Order(2)
   void deveObterTodasPessoas() {
        given()
                .when()
                .get()
                .then()
                .body("size()", equalTo(1))
                .body("id", hasItem(1))
                .body("nome", hasItem("Amanda Victoria"))
                .body("idade", hasItem(27))
                .statusCode(Response.Status.OK.getStatusCode());
   }

    @Test
    @Order(3)
    void deveObterPorId() {
        given()
                .when()
                .get("/{id}", 1)
                .then()
                .body("id", is(1))
                .body("nome", is("Amanda Victoria"))
                .body("idade", is(27))
                .statusCode(Response.Status.OK.getStatusCode());
    }
}
