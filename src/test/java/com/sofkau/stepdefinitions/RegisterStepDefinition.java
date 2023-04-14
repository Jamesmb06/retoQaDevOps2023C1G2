package com.sofkau.stepdefinitions;


import com.sofkau.models.rest.User;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;
import java.util.logging.Logger;

import static com.sofkau.questions.rest.ReturnResponse.returnResponse;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.UrlResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;


public class RegisterStepDefinition extends ApiSetUp {
    private User user = new User();
    JSONObject responseBody = null;
    JSONParser parser = new JSONParser();
    public static Logger LOGGER = Logger.getLogger(String.valueOf(FotosStepDefinition.class));

    @Given("El usuario esta en la pagina de registro.")
    public void elUsuarioEstaEnLaPaginaDeRegistro() {

        try {
            setUp(REQRES_BASE_URL.getValue());

            LOGGER.info("Inicio de automatización ");
        } catch (Exception e) {
            LOGGER.info(" fallo configuración inicial");
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }

    }

    @When("el usuario envia una solicitud de registro con el {string} y la {string}")
    public void elUsuarioEnviaUnaSolicitudDeRegistroConElYLa(String email, String password) {

        try {
            user.setUpdatedAt(email);
            user.setPassword(password);
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCES_POST.getValue())
                            .andTheRequestBody(user)
            );

            LOGGER.info("Credenciales ingresadas correctamente");
        } catch (Exception e) {
            LOGGER.info(" fallo al enviar credenciales");
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }


    }

    @Then("el usuario ve un codigo de respuesta de estado {int} y una identificacion con un token")
    public void elUsuarioVeUnCodigoDeRespuestaDeEstadoYUnaIdentificacionConUnToken(Integer statusCode) {
        try {
            Response actualResponse = returnResponse().answeredBy(actor);
            responseBody = (JSONObject) parser.parse(actualResponse.getBody().asString());
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(statusCode)),
                    seeThat("Retorna información",
                            act -> actualResponse, notNullValue())
            );
            LOGGER.info("Asserción realizada con exito");
        } catch (Exception e) {
            LOGGER.info(" fallo la asserción");
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }

    }

}
