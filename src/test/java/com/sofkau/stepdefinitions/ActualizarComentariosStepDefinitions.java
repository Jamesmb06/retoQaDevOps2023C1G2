package com.sofkau.stepdefinitions;

import com.sofkau.models.rest.ResponseRegistro;
import com.sofkau.models.rest.UserComments;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;

import java.util.logging.Logger;

import static com.sofkau.questions.rest.ReturnResponse.returnResponse;
import static com.sofkau.questions.rest.ReturnUpdateJsonResponse.responseRegistro;
import static com.sofkau.tasks.DoPut.doPut;
import static com.sofkau.utils.UrlResources.BASE_JSON_URL;
import static com.sofkau.utils.UrlResources.RESOURCES_PUT_JSONPLACEHOLDER;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class ActualizarComentariosStepDefinitions extends ApiSetUp {
    private UserComments userComments = new UserComments();
    public static java.util.logging.Logger LOGGER = Logger.getLogger(String.valueOf(ActualizarComentariosStepDefinitions.class));

    @Given("que el usuario esta en la pagina de json placeholder")
    public void queElUsuarioEstaEnLaPaginaDeJsonPlaceholder() {
        try{
            setUp(BASE_JSON_URL.getValue());
            LOGGER.info("Inicia el proceso de automatizacion");
        }catch (AssertionError error){
            LOGGER.warning(error.getMessage());
            Assertions.fail("Respuesta de la petición - inválida");
        }
    }

    @When("el usuario envia una solicitud de actualizacion con el {int} el {string} el {string} y el {string}")
    public void elUsuarioEnviaUnaSolicitudDeActualizacionConElElElYEl(Integer id, String name, String body, String email) {
        try{
            userComments.setName(name);
            userComments.setBody(body);
            userComments.setEmail(email);
            actor.attemptsTo(
                    doPut()
                            .withTheResource(RESOURCES_PUT_JSONPLACEHOLDER.getValue()+id)
                            .andTheRequestBody(userComments)
            );
        }catch (Exception e){
            LOGGER.info("Fallo enviando la informacion");
            LOGGER.warning(e.getMessage());
        }
    }

    @Then("el usuario ve un codigo de respuesta de estado {int}")
    public void elUsuarioVeUnCodigoDeRespuestaDeEstado(Integer code) {
        try {

            ResponseRegistro actualResponseUpdateUser = responseRegistro().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es "+ HttpStatus.SC_OK,
                            responseUpdate-> responseUpdate.statusCode(code))
            );
        }catch (Exception e){
            LOGGER.info("Fallo comparando el codigo");
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }
    }
}
