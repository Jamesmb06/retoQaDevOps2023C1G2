package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sofkau.tasks.DoDelete.doDelete;
import static com.sofkau.utils.UrlResources.ALBUMS_RESOURCE;
import static com.sofkau.utils.UrlResources.JSON_PLACEHOLDER_BASE_URL;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class BorrarAlbumStepDef extends ApiSetUp {
    private final Logger log = LoggerFactory.getLogger(BorrarAlbumStepDef.class);

    @Given("Tengo acceso al servidor de la API JSONPlaceholder")
    public void tengoAccesoAlServidorDeLaAPIJSONPlaceholder() {
        try {
            log.info("Iniciando escenario");
            setUp(JSON_PLACEHOLDER_BASE_URL.getValue());
        } catch (Exception e) {
            log.error("Setup erroneo");
            log.error(e.getMessage());
            log.error(String.valueOf(e.getCause()));
            Assertions.fail();
        }
    }

    @When("Trato de borrar un album con ID {int}")
    public void tratoDeBorrarUnAlbumConID(Integer id) {
        try {
            log.info("Corriendo seleccion");
            actor.attemptsTo(
                    doDelete().conElRecurso(String.format(ALBUMS_RESOURCE.getValue(), id)).yConelId("")
            );
        } catch (Exception e) {
            log.error("ERROR");
            log.error(e.getMessage());
            log.error(String.valueOf(e.getCause()));
            Assertions.fail();
        }
    }

    @Then("Vere el codigo de respuesta {int}")
    public void vereElCodigoDeRespuesta(Integer code) {
        try {
            actor.should(
                    seeThatResponse("Codigo de estado por borrar album debe ser mostrado",
                            response -> response.statusCode(code))
            );
            log.info("Test pasado");
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