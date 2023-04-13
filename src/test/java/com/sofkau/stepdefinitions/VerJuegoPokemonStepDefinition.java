package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;

import java.util.logging.Logger;

import static com.sofkau.questions.rest.ReturnResponse.returnResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.UrlResources.BASE_POKE_URL;
import static com.sofkau.utils.UrlResources.RESOURCE_JUEGO;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;


public class VerJuegoPokemonStepDefinition extends ApiSetUp {
    public static String idJ;
    public static Logger LOGGER = Logger.getLogger(String.valueOf(VerJuegoPokemonStepDefinition.class));
    JSONObject resBody = null;
    JSONParser parser = new JSONParser();

    public static Response actualResponse;

    @Given("que estoy apuntando con un endpoint a la pokeapi")
    public void queEstoyApuntandoConUnEndpointALaPokeapi() {
        setUp(BASE_POKE_URL.getValue());
        LOGGER.info("Inicio de la automatizacion");
    }

    @When("envio la peticion get con el {string} del juego")
    public void envioLaPeticionGetConElDelJuego(String id) {
        try {
            idJ=id;
            actor.attemptsTo(
                    doGet()
                            .withTheResource(RESOURCE_JUEGO.getValue()+id)
            );
        }catch (Exception e){
            LOGGER.warning(e.getMessage());
        }

    }

    @Then("recibo {int} de codigo de respuesta y la informacion del juego")
    public void reciboDeCodigoDeRespuestaYLaInformacionDelJuego(Integer codigo) {
        actualResponse= returnResponse().answeredBy(actor);
        if(codigo==200){
            try{
                resBody = (JSONObject) parser.parse(actualResponse.getBody().asString());
                JSONArray names=(JSONArray) resBody.get("names");
                actor.should(
                        seeThatResponse("El codigo de respuesta es: "+ resBody,
                                responseCreate-> responseCreate.statusCode(codigo)),
                        seeThat("Retorna informacion",
                                act-> actualResponse, notNullValue()),
                        seeThat("El id recibido es: ",
                                ids -> resBody.get("id").toString(), equalTo(idJ)),
                        seeThat("El tamanio de flavors es",
                                nams-> names.size(),equalTo(9))
                );
                LOGGER.info("Se finaliza el step de la pokeApi");
            }catch (Exception e){
                LOGGER.warning(e.getMessage());
                Assertions.fail();
            }
        }else if(codigo==404){
            try{
                LOGGER.info("Se revisa la respuesta de los codigos 404");
                actor.should(
                        seeThat("Retorna informacion",
                                info-> actualResponse.getBody().asString(),equalTo("Not Found"))
                );

            }catch (Exception e){

                LOGGER.warning(e.getMessage());
                Assertions.fail();
            }
        }

    }
}
