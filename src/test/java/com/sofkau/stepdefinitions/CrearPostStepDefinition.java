package com.sofkau.stepdefinitions;


import com.sofkau.models.rest.Posts;
import com.sofkau.models.rest.User;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;
import java.util.logging.Logger;

import static com.sofkau.questions.rest.ReturnPost.returnResponse;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.UrlResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CrearPostStepDefinition extends ApiSetUp {
    private Posts posts = new Posts();
    JSONObject responseBody = null;
    JSONParser parser = new JSONParser();

    private User user = new User();
    public static Logger LOGGER = Logger.getLogger(String.valueOf(CrearPostStepDefinition.class));


    @Given("el usuario esta en la pagina para crear post")
    public void elUsuarioEstaEnLaPaginaParaCrearPost() {

        try {
            setUp(BASE_JSON_URL.getValue());
            LOGGER.info("API disponibles para realizar la peticion");
            LOGGER.info("Inicio de automatización en API placeholder ");
        } catch (Exception e) {
            LOGGER.info(" fallo configuración inicial de API placeholder");
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }
    }

    @When("cuando el usuario envia solicitud con userId {string} titulo {string} y post  {string}")
    public void cuandoElUsuarioEnviaSolicitudConUserIdTituloYPost(String userId, String title, String body) {

        try {
            posts.setUserId(userId);
            posts.setTitle(title);
            posts.setBody(body);
            actor.attemptsTo(doPost().withTheResource(POST_JSON_URL.getValue())
                    .andTheRequestBody(posts));
            System.out.println(SerenityRest.lastResponse().body().asString());
            LOGGER.info("Los datos fueron enviados correctamente");

        } catch (Exception e) {
            LOGGER.info(" fallo al enviar los datos");
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }
    }


    @Then("la pagina retornara un estatus con codigo {int} y con userId {string} titulo {string} y post  {string}")
    public void laPaginaRetornaraUnEstatusConCodigoYConUserIdTituloYPost(Integer code, String userId, String title, String body) {
        try {
            Response actualResponse = returnResponse().answeredBy(actor);
            responseBody = (JSONObject) parser.parse(actualResponse.getBody().asString());
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(code)),
                    seeThat("Retorna información",
                            act -> actualResponse, notNullValue()),
                    seeThat("Comparar id",
                            idC -> responseBody.get("userId").toString(), equalTo(userId)),
                    seeThat("Comparar title",
                            TiC -> responseBody.get("title").toString(), equalTo(title)),
                    seeThat("Comparar body",
                            BodyC -> responseBody.get("body").toString(), equalTo(body))
            );
            LOGGER.info("Datos esperados y actuales correctos");
        } catch (Exception e) {
            LOGGER.info(" fallo la asserción en la API placeholder");
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }
    }
}
