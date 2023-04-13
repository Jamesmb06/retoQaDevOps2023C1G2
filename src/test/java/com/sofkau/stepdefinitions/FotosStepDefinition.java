package com.sofkau.stepdefinitions;

import com.sofkau.models.rest.Fotos;
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

import static com.sofkau.questions.rest.ReturnPost.returnUpdateSuccessfulJsonResponse;
import static com.sofkau.tasks.DoPatch.doPatch;
import static com.sofkau.utils.UrlResources.BASE_JSON_URL;
import static com.sofkau.utils.UrlResources.PUT_JSON_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class FotosStepDefinition extends ApiSetUp {
    Fotos fotos = new Fotos();
    JSONObject responseBody = null;
    JSONParser parser = new JSONParser();
    public static Logger LOGGER = Logger.getLogger(String.valueOf(FotosStepDefinition.class));
    @Given("el administrador esta en la pagina para editar informacion de la foto")
    public void elAdministradorEstaEnLaPaginaParaEditarInformacionDeLaFoto() {

        try {
            setUp( BASE_JSON_URL.getValue());

            LOGGER.info("Inicio de automatización ");
        } catch (Exception e) {
            LOGGER.info(" fallo configuración inicial");
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }
    }

    @When("cuando envia solicitud con {string} de foto nuevo nombre de albumId {string} titulo y  {string}")
    public void cuandoEnviaSolicitudConDeFotoNuevoNombreDeAlbumIdTituloY(String id, String albumId, String titulo) {



        try {
            fotos.setTitle(titulo);
            fotos.setAlbumId(albumId);
            actor.attemptsTo(doPatch().withTheResource(PUT_JSON_URL.getValue() +id )
                    .andTheRequestBody(fotos));

            LOGGER.info("Datos de registro enviados correctamente ");
        } catch (Exception e) {
            LOGGER.info(" fallo al enviar los datos");
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }


    }
    @Then("la pagina retornara un estatus con codigo {int} y con el nuevo albumId {string} titulo y  {string}")
    public void laPaginaRetornaraUnEstatusConCodigoYConElNuevoAlbumIdTituloY(Integer code,  String albumId,String title) {
        try {
            Response actualResponse = returnUpdateSuccessfulJsonResponse().answeredBy(actor);
            responseBody = (JSONObject) parser.parse(actualResponse.getBody().asString());
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(code)),
                    seeThat("Retorna información",
                            act -> actualResponse, notNullValue()),
                    seeThat("Comparar albumId",
                            iAl -> responseBody.get("albumId").toString(), equalTo(albumId)),
                    seeThat("Comparar title",
                            iTI -> responseBody.get("title").toString(), equalTo(title))
            );
            LOGGER.info("Asserción realizada con exito");
        } catch (Exception e){
            LOGGER.info("Asserción fallida");
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }

    }
}
