package com.sofkau.stepdefinitions;


import com.sofkau.models.rest.Photo;
import com.sofkau.models.rest.ResponseRegistro;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static com.sofkau.questions.rest.ReturnNewPhotoResponse.returnNewPhotoResponse;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.UrlResources.JSONPLACEHOLDER_BASE_URL;
import static com.sofkau.utils.UrlResources.PHOTO_POST_RESOURCE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class NewPhotoStepDefinition extends ApiSetUp {

    public static Logger LOGGER = Logger.getLogger(NewPhotoStepDefinition.class);
    private Photo photo = new Photo();

    @Given("que tengo un album con id {string}")
    public void queTengoUnAlbumConId(String album_id) {
        LOGGER.info("Inicio de la Automatizacion de JsonPlaceHolder");
        setUp(JSONPLACEHOLDER_BASE_URL.getValue());
        photo.setAlbumId(album_id);
    }
    @When("agrego una nueva foto con los siguientes datos {string}, {string},{string}")
    public void agregoUnaNuevaFotoConLosSiguientesDatos(String title, String url, String thumbnailUrl) {
        setValuesPhoto(title, url, thumbnailUrl);
        try {
            actor.attemptsTo(
                    doPost()
                            .withTheResource(PHOTO_POST_RESOURCE.getValue())
                            .andTheRequestBody(photo)
            );
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }
    @Then("la respuesta de la peticion debe tener el codigo de estado {int}")
    public void laRespuestaDeLaPeticionDebeTenerElCodigoDeEstado(Integer statusCode) {
        try {
            actor.should(
                    seeThatResponse("El codigo de respuesta de JsonPlaceholder es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(statusCode))
            );
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }

    }
    @Then("la respuesta debe incluir los datos de la nueva foto:")
    public void laRespuestaDebeIncluirLosDatosDeLaNuevaFoto() {
        try {
            ResponseRegistro actualResponse = returnNewPhotoResponse().answeredBy(actor);
            actor.should(
                    seeThat("Retorna info",
                            act -> actualResponse, notNullValue()),
                    seeThat("id auto asignado",
                            ids -> actualResponse.getId(), equalTo("5001")),
                    seeThat("titulo de la foto",
                            name_region ->actualResponse.getTitle(),equalTo(photo.getTitle()))
            );
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
    private void setValuesPhoto(String title, String url, String thumbnailUrl) {
        photo.setTitle(title);
        photo.setUrl(url);
        photo.setThumbnailUrl(thumbnailUrl);
    }


}
