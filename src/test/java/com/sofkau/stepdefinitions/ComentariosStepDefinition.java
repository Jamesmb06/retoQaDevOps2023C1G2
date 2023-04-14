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

import static com.sofkau.questions.rest.ReturnResponse.returnResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.UrlResources.COMMENTS_BY_POST;
import static com.sofkau.utils.UrlResources.JSONPLACE_BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class ComentariosStepDefinition extends ApiSetUp {
    public static Logger LOGGER= Logger.getLogger(ComentariosStepDefinition.class);
    JSONArray responseBody = null;
    JSONObject firstResponse = null;
    JSONParser parser = new JSONParser();
    String id;

    @Given("El usuario se encuentra en la web de JsonPlaceHolder para realizar la consulta")
    public void elUsuarioSeEncuentraEnLaWebDeJsonPlaceHolderParaRealizarLaConsulta() {
        try {
            setUp(JSONPLACE_BASE_URL.getValue());
            LOGGER.info("Empezando peticion");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @When("El usuario envia una solicitud con el {string} del post del cual se quieren los comentarios")
    public void elUsuarioEnviaUnaSolicitudConElDelPostDelCualSeQuierenLosComentarios(String idPost) {
        try {
            id = idPost;
            actor.attemptsTo(
                    doGet()
                            .withTheResource(COMMENTS_BY_POST.getValue() + idPost)
            );
            LOGGER.info("Peticion realizada");
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }


    @Then("El usuario debe recibir un respuesta de status {int} y los comentarios del post")
    public void elUsuarioDebeRecibirUnRespuestaDeStatusYLosComentariosDelPost(Integer code) {
        try {
            Response actualResponse = returnResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(code)),
                    seeThat("Retorna información",
                            act -> actualResponse, notNullValue())
            );
            responseBody = (JSONArray) parser.parse(actualResponse.getBody().asString());
            firstResponse = (JSONObject) responseBody.get(1);
            actor.should(
                    seeThat("Comparar id del post",
                            idC -> firstResponse.get("postId").toString(), equalTo(id))
            );
            LOGGER.info("Asercion exitosa");
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }
}
