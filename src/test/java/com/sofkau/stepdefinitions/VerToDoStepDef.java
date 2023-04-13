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
import static org.hamcrest.Matchers.hasItem;

public class VerToDoStepDef extends ApiSetUp {
    private final Logger log = LoggerFactory.getLogger(BorrarAlbumStepDef.class);

    @Given("Tengo acceso al servidor de la API de JSONPlaceholder")
    public void tengoAccesoAlServidorDeLaAPIDeJSONPlaceholder() {
        try {
            log.info("Init scenario");
            setUp(JSON_PLACEHOLDER_BASE_URL.getValue());
        } catch (Exception e) {
            log.error("Wrong Setup provided");
        }
    }

    @When("Reviso mis tareas TO DO con ID {int}")
    public void revisoMisTareasTODOConID(Integer id) {
        try {
            log.info("Running selection");
            actor.attemptsTo(
                    doGet().theResource(String.format(TO_DO_RESOURCE.getValue(), id))
            );
        } catch (Exception e) {
            log.error("Wrong Setup provided");
        }
    }

    @Then("Vere el titulo {string} de la tarea")
    public void vereElTituloDeLaTarea(String title) {
        try {
            actor.should(
                    seeThatResponse("Title of the to-do should be shown",
                            response -> response.body("title", hasItem(title)))
            );
            log.info("First assert passed");
        } catch (Exception e) {
            log.error("Test failed");
            Assertions.fail();
        }
    }

    @And("Recibire un codigo de estado {int}")
    public void recibireUnCodigoDeEstado(Integer code) {
        try {
            actor.should(
                    seeThatResponse("Status code of To do consulted should be shown",
                            response -> response.statusCode(code))
            );
            log.info("Second assert passed");
        } catch (Exception e) {
            log.error("Test failed");
            Assertions.fail();
        } finally {
            log.error("Test completed");
        }
    }
}