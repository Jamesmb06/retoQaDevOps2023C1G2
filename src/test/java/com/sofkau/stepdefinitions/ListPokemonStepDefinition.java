package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;

import static com.sofkau.questions.rest.ReturnListPokemonResponse.returnListPokemonResponse;
import static com.sofkau.tasks.DoGet.doGet;

import static com.sofkau.utils.UrlResources.BASE_POKE_URL;
import static com.sofkau.utils.UrlResources.GENERATION1_RESOURCE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class ListPokemonStepDefinition extends ApiSetUp {

    public static Logger LOGGER = Logger.getLogger(ListPokemonStepDefinition.class);
    JSONObject resBody = null;
    JSONParser parser = new JSONParser();

    @Given("Que estoy en la Poke API")
    public void queEstoyEnLaPokeAPI() {
        setUp(BASE_POKE_URL.getValue());
        LOGGER.info("Inicio de la automatizacion");
    }

    @When("Hago una peticion para listar pokes de generacion {string}")
    public void hagoUnaPeticionParaListarPokesDeGeneracion(String gen) {
        try {
            actor.attemptsTo(
                    doGet()
                            .withTheResource(GENERATION1_RESOURCE.getValue()+gen)
            );
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @Then("Debo ver un codigo de estado exitoso")
    public void deboVerUnCodigoDeEstadoExitoso() {
        try {
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(200))

            );
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("La respuesta debe contener un body con toda la info {string} y {string}")
    public void laRespuestaDebeContenerUnBodyConTodaLaInfoY(String id, String main_region_name) {
        try {
            Response actualResponse = returnListPokemonResponse().answeredBy(actor);
            resBody = (JSONObject) parser.parse(actualResponse.getBody().asString());
            JSONObject main_region = (JSONObject) resBody.get("main_region");
            actor.should(
                    seeThat("Retorna info",
                            act -> actualResponse, notNullValue()),
                    seeThat("Comparar valores",
                            ids -> resBody.get("id").toString(), equalTo(id)),
                    seeThat("name de la region",
                            name_region ->main_region.get("name"),equalTo(main_region_name))
            );
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
}
