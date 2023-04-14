package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static com.sofkau.questions.rest.ReturnResponse.returnResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.UrlResources.RESOURCE_RICKANDMORTY;
import static com.sofkau.utils.UrlResources.RICK_AND_MORTY_BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class BuscarPersonajesStepDefinitions extends ApiSetUp {
    public static final Logger LOGGER = Logger.getLogger(BuscarPersonajesStepDefinitions.class);

    @Given("el usuario esta en la pagina de busqueda")
    public void elUsuarioEstaEnLaPaginaDeBusqueda() {
        try {
            setUp(RICK_AND_MORTY_BASE_URL.getValue());
            LOGGER.info("Empezando peticion");
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @When("cuando el usuario envia solicitud de busqueda  por id {string}")
    public void cuandoElUsuarioEnviaSolicitudDeBusquedaPorId(String id) {
        try {
            actor.attemptsTo(
                    doGet()
                            .withTheResource(RESOURCE_RICKANDMORTY.getValue() + id));
            LOGGER.info("Peticion realizada");
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @Then("la pagina retornara un estatus con codigo {int} y nombre {string}")
    public void laPaginaRetornaraUnEstatusConCodigoYNombre(Integer code, String nombre) {
        try {
            Response actualResponse = returnResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(code)),
                    seeThat("Retorna información",
                            act -> actualResponse, notNullValue())
            );
            LOGGER.info("Asercion exitosa");
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

}
