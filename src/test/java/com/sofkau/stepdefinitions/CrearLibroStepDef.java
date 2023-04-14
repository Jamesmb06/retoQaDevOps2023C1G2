package com.sofkau.stepdefinitions;

import com.sofkau.models.rest.Book;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sofkau.tasks.DoPut.doPut;
import static com.sofkau.utils.UrlResources.BOOKS_RESOURCE;
import static com.sofkau.utils.UrlResources.FAKE_REST_API_BASE_URL;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;

public class CrearLibroStepDef extends ApiSetUp {
    private final Logger log = LoggerFactory.getLogger(CrearLibroStepDef.class);
    private final Book book = new Book();

    @Given("Tengo acceso al servidor de la API de Fake REST")
    public void tengoAccesoAlServidorDeLaAPIDeFakeREST() {
        try {
            log.info("Iniciando escenario");
            setUp(FAKE_REST_API_BASE_URL.getValue());
        } catch (Exception e) {
            log.error("Setup erroneo");
            log.error(e.getMessage());
            log.error(String.valueOf(e.getCause()));
            Assertions.fail();
        }
    }

    @When("Trato de crear un libro con ID {int}, titulo {string} y numero de paginas {int}")
    public void tratoDeCrearUnLibroConIDTituloYNumeroDePaginas(Integer id, String title, Integer pageCount) {
        try {
            log.info("Corriendo seleccion");
            book.setId(id);
            book.setTitle(title);
            book.setPageCount(pageCount);
            log.info(book.toString());
            actor.attemptsTo(
                    doPut()
                            .withTheResource(String.format(BOOKS_RESOURCE.getValue(), id))
                            .andTheRequestBody(book)
            );
        } catch (Exception e) {
            log.error("ERROR");
            log.error(e.getMessage());
            log.error(String.valueOf(e.getCause()));
            Assertions.fail();
        }
    }

    @Then("Vere un codigo de respuesta {int}")
    public void vereUnCodigoDeRespuesta(Integer code) {
        try {
            actor.should(
                    seeThatResponse("Codigo de estado de libro creado se debe mostrar",
                            response -> response.statusCode(code))
            );
            log.info("Primera asercion pasada");
        } catch (Exception e) {
            log.error("Test fallido");
            log.error(e.getMessage());
            log.error(String.valueOf(e.getCause()));
            Assertions.fail();
        }
    }

    @And("Recibire la informacion del libro de vuelta")
    public void recibireLaInformacionDelLibroDeVuelta() {
        try {
            actor.should(
                    seeThatResponse("Se debe mostrar el libro creado",
                            response -> response
                                    .body("id", equalTo(book.getId()))
                                    .body("title", equalTo(book.getTitle()))
                                    .body("description", equalTo(book.getDescription()))
                                    .body("pageCount", equalTo(book.getPageCount()))
                                    .body("excerpt", equalTo(book.getExcerpt()))
                    )
            );
            log.info("Segunda asercion pasada");
        } catch (Exception e) {
            log.error("Test fallido");
            log.error(e.getMessage());
            log.error(String.valueOf(e.getCause()));
            Assertions.fail();
        } finally {
            log.info("Test completado");
        }
    }
}