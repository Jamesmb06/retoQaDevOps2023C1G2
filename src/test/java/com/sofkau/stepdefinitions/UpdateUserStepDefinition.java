package com.sofkau.stepdefinitions;

import com.sofkau.models.rest.ResponseUpdateUserReqres;
import com.sofkau.models.rest.UserReqresUpdate;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import java.util.logging.Logger;
import static com.sofkau.questions.rest.ReturnUpdateJsonResponseReqres.returnUpdateJsonResponseReqres;
import static com.sofkau.tasks.DoPut.doPut;
import static com.sofkau.utils.UrlResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
public class UpdateUserStepDefinition extends ApiSetUp {


    private UserReqresUpdate userReqresUpdate = new UserReqresUpdate();
    public static Logger LOGGER = Logger.getLogger(String.valueOf(UpdateUserStepDefinition.class));
    ResponseUpdateUserReqres actualResponseUpdateUserReqres =new ResponseUpdateUserReqres();

    @Given("que estoy apuntando con un endpoint a la api de reqresin")
    public void queEstoyApuntandoConUnEndpointALaApiDeReqresIn() {
        setUp(REQRES_BASE_URL.getValue());
        LOGGER.info("Se inicia la automatizacion");
    }

    @When("envio la peticion put con el {string} de el usuario el {string},{int} y {string}")
    public void envioLaPeticionPutConElDeElUsuarioElY(String id, String nombre, Integer edad, String trabajo) {
        try{
            userReqresUpdate.setNombre(nombre);
            userReqresUpdate.setEdad(edad);
            userReqresUpdate.setTrabajo(trabajo);
            actor.attemptsTo(
                    doPut()
                            .withTheResource(RESOURCES_PUT.getValue()+id)
                            .andTheRequestBody(userReqresUpdate)
            );
        }catch (Exception e){
            LOGGER.info("Fallo enviando la informacion");
            LOGGER.warning(e.getMessage());
        }
    }

    @Then("se recibe un {int} de respuesta")
    public void seRecibeUnDeRespuesta(Integer code) {
        try {
            actualResponseUpdateUserReqres =returnUpdateJsonResponseReqres().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es "+ HttpStatus.SC_OK,
                            responseUpdate-> responseUpdate.statusCode(code))
            );
        }catch (Exception e){
            LOGGER.info("Fallo comparando el codigo");
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("la informacion actualizada del usuario con la fecha de actualizacion")
    public void laInformacionActualizadaDelUsuarioConLaFechaDeActualizacion() {
        try {
            actor.should(
                    seeThat("Retorna informacion",
                            act-> actualResponseUpdateUserReqres, notNullValue()),
                    seeThat("Se recibe el nombre del usuario actualizado",
                            nom-> userReqresUpdate.getNombre(), equalTo(actualResponseUpdateUserReqres.getNombre())),
                    seeThat("Se recibe la edad del usuario actualizado",
                            ed-> userReqresUpdate.getEdad(), equalTo(actualResponseUpdateUserReqres.getEdad())),
                    seeThat("Se recibe el trabajo del usuario actualizado",
                            tra-> userReqresUpdate.getTrabajo(), equalTo(actualResponseUpdateUserReqres.getTrabajo())),
                    seeThat("La fecha de actualizacion no es vacia",
                            fecha-> actualResponseUpdateUserReqres.getUpdatedAt(),notNullValue())
            );
        }catch (Exception e){
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }
        LOGGER.info("Fin de la automatizacion");
    }
}
