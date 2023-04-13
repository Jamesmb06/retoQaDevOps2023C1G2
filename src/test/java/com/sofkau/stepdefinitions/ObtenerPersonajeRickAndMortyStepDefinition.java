package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

import static com.sofkau.questions.rest.ReturnUpdateJsonResponse.returnUpdateJsonResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.UrlResources.RICK_AND_MORTY_BASE_URL;
import static com.sofkau.utils.UrlResources.RICK_AND_MORTY_SELECCION_ID;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class ObtenerPersonajeRickAndMortyStepDefinition  extends ApiSetUp {


    @Given("que el usuario esta usando la API de Rick and Morty")
    public void que_el_usuario_esta_usando_la_API_de_Rick_and_Morty() {
        setUp(RICK_AND_MORTY_BASE_URL.getValue());
    }



    @When("el usuario envia una solicitud de tipo GET mandando el  {int}")
    public void el_usuario_envia_una_solicitud_de_tipo_GET_mandando_el(Integer id) {
        actor.attemptsTo(
                doGet()
                        .withTheResource((RICK_AND_MORTY_SELECCION_ID.getValue()+id))
        );
        System.out.println(SerenityRest.lastResponse().body().asString());
    }



    @Then("se deberia observar el estatus  {int}  y un  body de respuesta con la informacion del personaje")
    public void se_deberia_observar_el_estatus_y_un_body_de_respuesta_con_la_informacion_del_personaje(Integer statusCode) {
        try {

            Response respuesta = (Response) returnUpdateJsonResponse().answeredBy(actor);

            actor.should(
                    seeThatResponse("El codigo de respuesta deberia ser: " + statusCode,
                            response -> response.statusCode(statusCode)),
                    seeThat("Retorna informacion",
                            act -> respuesta, notNullValue())
            );

        } catch (Exception e){
        }
    }
}
