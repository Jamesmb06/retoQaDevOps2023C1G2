package com.sofkau.stepdefinitions;

import com.sofkau.models.rest.Response;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import static com.sofkau.questions.rest.ReturnPutSuccessPlaceholder.returnPutSuccessPlaceholder;
import static com.sofkau.tasks.DoPut.doPut;
import static com.sofkau.utils.UrlResources.BASE_JSON_URL;
import static com.sofkau.utils.UrlResources.PUT_POST_RESOURCE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.notNullValue;

public class UpdatePostPlaceholderStepDefinition extends ApiSetUp {

    Response put = new Response();

    private final Logger LOGGER = Logger.getLogger(UpdatePostPlaceholderStepDefinition.class);

    @Given("estoy en la pagina de actualizacion de jsonplaceholder")
    public void estoyEnLaPaginaDeActualizacionDeJsonplaceholder() {
        setUp(BASE_JSON_URL.getValue());
    }

    @When("actualizo la informacion de un post {string}, {string}, {int}")
    public void actualizoLaInformacionDeUnPost(String arg0, String arg1, Integer int1) {
        try {
            put.setTitle(arg0);
            put.setBody(arg1);
            actor.attemptsTo(
                    doPut().withTheResource(PUT_POST_RESOURCE.getValue() + int1)
                            .andTheRequestBody(put)
            );
            LOGGER.info("Response code: " + SerenityRest.lastResponse().getStatusCode());
            LOGGER.info("Response body: " + SerenityRest.lastResponse().asString());
        } catch (Exception e) {
            LOGGER.error("Error updating post: " + e.getMessage());
        }
    }

    @Then("me debe devolver el post actualizado {int}, {string}")
    public void meDebeDevolverElPostActualizado(Integer int1, String string) {
        try {
            Response actualResponse = returnPutSuccessPlaceholder().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(int1)),
                    seeThat("Retorna información",
                            act -> actualResponse, notNullValue())
            );
            actor.attemptsTo(
                    Ensure.that(actualResponse.getTitle()).isEqualTo(string)
            );
            LOGGER.info("Updated post title: " + actualResponse.getTitle());
            LOGGER.info("Updated post body: " + actualResponse.getBody());
        } catch (Exception e) {
            LOGGER.error("Error verificando la actualizacion del post: " + e.getMessage());
        }
    }
}
