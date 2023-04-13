package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.UrlResources.JSON_PLACEHOLDER_BASE_URL;
import static com.sofkau.utils.UrlResources.TO_DO_RESOURCE;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.*;

public class VerToDoStepDef extends ApiSetUp {
    private final Logger log = LoggerFactory.getLogger(VerToDoStepDef.class);

    @Given("Tengo acceso al servidor de la API de JSONPlaceholder")
    public void tengoAccesoAlServidorDeLaAPIDeJSONPlaceholder() {
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

    @When("Reviso mis tareas TO DO con ID {int}")
    public void revisoMisTareasTODOConID(Integer id) {
        try {
            log.info("Corriendo seleccion");
            actor.attemptsTo(
                    doGet().withTheResource(String.format(TO_DO_RESOURCE.getValue(), id))
            );
        } catch (Exception e) {
            log.error("ERROR");
            log.error(e.getMessage());
            log.error(String.valueOf(e.getCause()));
            Assertions.fail();
        }
    }

    @Then("Vere el titulo {string} de la tarea")
    public void vereElTituloDeLaTarea(String title) {
        try {
            actor.should(
                    seeThatResponse("Debe mostrarse el titulo de la tarea",
                            response -> response.body("title", hasItem(title)))
            );
            log.info("Primera asercion pasada");
        } catch (Exception e) {
            log.error("Test fallido");
            log.error(e.getMessage());
            log.error(String.valueOf(e.getCause()));
            Assertions.fail();
        }
    }

    @And("Recibire un codigo de estado {int}")
    public void recibireUnCodigoDeEstado(Integer code) {
        try {
            actor.should(
                    seeThatResponse("Debe mostrarse el codigo de estado de consulta de la tarea",
                            response -> response.statusCode(code))
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