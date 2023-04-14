package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.UrlResources.ANIME_JINKAN_BASE_URL;
import static com.sofkau.utils.UrlResources.ANIME_JINKAN_RESOURCE;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.core.IsEqual.equalTo;

public class ObtenerAnimePorIDJinkanStepDefinition extends ApiSetUp {

    private static final Logger LOGGER = Logger.getLogger(ObtenerAnimePorIDJinkanStepDefinition.class);


    @Given("que el usuario esta usando la API de Anime Jikan")
    public void que_el_usuario_esta_usando_la_API_de_Anime_Jikan() {

        try {
            setUp(ANIME_JINKAN_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }


    @When("se envia una peticion para obtener un anime por su {string}")
    public void se_envia_una_peticion_para_obtener_un_anime_por_su(String id) {

        try {

            actor.attemptsTo(
                    doGet()
                            .withTheResource((ANIME_JINKAN_RESOURCE.getValue()+id))
            );
            LOGGER.info(SerenityRest.lastResponse().body().asString());


            LOGGER.info("Realiza la peticion");
        } catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }


    @Then("se deberia observar el {int}  junto con la  {string} del anime y el {int} del anime")
    public void se_deberia_observar_el_junto_con_la_del_anime_y_el_del_anime(Integer statusCode, String url, Integer idAnime) {

        try {

            if (statusCode == 404) {
                LOGGER.info("No se encontro la peticion ");
            }
            if (statusCode == 200) {
                assersionesStatus200 (statusCode, url, idAnime);
            }

        }catch (Exception e){
            LOGGER.warn(e.getMessage());
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }





    private void assersionesStatus200(Integer statusCode, String url, Integer idAnime) {


        try {
            actor.should(
                    seeThatResponse("El codigo de respuesta deberia ser: " + statusCode,
                            response -> response.statusCode(statusCode))
            );

            actor.should(
                    seeThatResponse("Comprobar IdAnime: ",
                            response -> response.body("data.mal_id", equalTo(idAnime)))
            );

            actor.should(
                    seeThatResponse("Comprobar url Anime: ",
                            response -> response.body("data.url", equalTo(url)))
            );
        }catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }
}
