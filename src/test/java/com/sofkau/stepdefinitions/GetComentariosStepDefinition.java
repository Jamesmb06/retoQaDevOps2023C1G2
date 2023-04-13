package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import com.sofkau.tasks.DoGet;
import com.sofkau.utils.UrlResources;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import java.util.List;
import java.util.Map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetComentariosStepDefinition extends ApiSetUp {
    private Response response;
    private Actor actor;

    @Given("que el usuario tiene acceso a los servicios API REST")
    public void queElUsuarioTieneAccesoALosServiciosAPIREST() {
        try {
            actor = Actor.named("usuario")
                    .whoCan(CallAnApi.at(UrlResources.DUMMYJSON_BASE_URL_GET.getValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("el usuario envia una solicitud GET para ver todos los comentarios")
    public void elUsuarioEnviaUnaSolicitudGETParaVerTodosLosComentarios() {
        try {
            actor.attemptsTo(DoGet.doGet().withTheResource(UrlResources.RESOURCES_GET_DUMMYJSON.getValue()));
            response = SerenityRest.lastResponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("debe recibir un codigo de estado {int}")
    public void debeRecibirUnCodigoDeEstado(Integer code) {
        try {
            response.then().statusCode(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("la respuesta JSON debe mostrar los comentarios realizados por los usuarios")
    public void laRespuestaJSONDebeMostrarLosComentariosRealizadosPorLosUsuarios() {
        try {
            String responseBody = response.getBody().asString();
            JsonPath jsonPath = new JsonPath(responseBody);

            List<Map<String, Object>> comments = jsonPath.getList("comments");

            assertThat(comments, is(not(empty())));
            assertThat(comments.size(), is(greaterThan(0)));

            for (Map<String, Object> comment : comments) {
                assertThat(comment.get("body"), is(notNullValue()));
                assertThat(comment.get("postId"), is(notNullValue()));
                assertThat(comment.get("user"), is(notNullValue()));

                Map<String, Object> user = (Map<String, Object>) comment.get("user");
                assertThat(user.get("id"), is(notNullValue()));
                assertThat(user.get("username"), is(notNullValue()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

