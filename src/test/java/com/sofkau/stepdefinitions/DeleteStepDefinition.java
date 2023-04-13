package com.sofkau.stepdefinitions;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import static com.sofkau.tasks.DoDelete.doDelete;
import static com.sofkau.utils.UrlResources.PLACE_DELETE_POST;
import static com.sofkau.utils.UrlResources.PLACE_HOLDER_BASE_URL;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;


public class DeleteStepDefinition extends ApiSetUp {

    private static final Logger LOGGER = Logger.getLogger(DeleteStepDefinition.class);
    @Given("que el usuario ingresa a la API de PlaceHolder en la seccion de Eliminar Post")
    public void que_el_usuario_ingresa_a_la_API_de_PlaceHolder_en_la_seccion_de_Eliminar_Post() {

        try {
            setUp(PLACE_HOLDER_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");

        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();

        }
    }

    @When("el usuario ingresa  el {int} del post que desea eliminar")
    public void el_usuario_ingresa_el_del_post_que_desea_eliminar(Integer int1) {
        try {
    actor.attemptsTo(
            doDelete()
                    .conElRecurso((PLACE_DELETE_POST.getValue() + int1))
    );
    System.out.println(SerenityRest.lastResponse().body().asString());
    LOGGER.info("Realiza la peticion");


        }catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }




    @Then("el usuario debe un  {int} de la peticion")
    public void el_usuario_debe_un_de_la_peticion(Integer statusCode) {

        try {
            actor.should(
                    seeThatResponse("El codigo de respuesta deberia ser: " + statusCode,
                            response -> response.statusCode(statusCode))
            );
            LOGGER.info("Realiza la comparacion");
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();

        }
    }
}
