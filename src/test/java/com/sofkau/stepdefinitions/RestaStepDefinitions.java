    package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import java.nio.charset.StandardCharsets;

import static com.sofkau.models.soap.Headers.headers;
import static com.sofkau.questions.soap.ResponseSoap.responseSoap;
import static com.sofkau.tasks.DoPostSoap.doPostSoap;
import static com.sofkau.utils.ManageFile.readFile;
import static com.sofkau.utils.UrlResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class RestaStepDefinitions extends ApiSetUp {

    String body;
    private static final Logger LOGGER = Logger.getLogger(RestaStepDefinitions.class);
    @Given("Dado que tengo acceso al servicio SOAP calculadora")
    public void dadoQueTengoAccesoAlServicioSOAPCalculadora() {
        try {
            setUp(SOAP_CALCULADORA_BASE_URL.getValue());
            LOGGER.info("Inicio de la Automatizacion de API calculadora");

        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial de la calculadora");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Given("Tengo los numeros a restar {int}, {int}")
    public void tengoLosNumerosASumar(Integer num1, Integer num2) {
        try {
            loadBody(num1,num2);
        }catch (Exception e){
            LOGGER.info(" fallo la carga del body que contiene los numeros");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("realizo la solicitud para restar los numeros")
    public void realizoLaSolicitudParaSumarLosNumeros() {
        try {
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_CALCULADORA.getValue())
                            .withTheHeaders(headers().getHeadersCollectionCalculadoraSubstract())
                            .andTheBody(body)
            );
            LOGGER.info(body);
            LOGGER.info("Realiza la peticion de resta");
        } catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion de resta");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("obtengo un codigo de estado exitoso \"200\"")
    public void obtengoUnCodigoDeEstadoExitoso() {
        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(
                    seeThatResponse("el codigo de respuesta de la API calculadora es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(HttpStatus.SC_OK))
            );
            LOGGER.info("CUMPLE");
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion del codigo de estasdo");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("El resultado correcto de la resta {string}")
    public void elResultadoCorrectoDeLaSuma(String total) {
        try {

            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(
                    seeThat(" la resta total es",
                                    responseSoap(), containsString(total))
            );
            LOGGER.info("CUMPLE CON EL RESULTADO ESPERADO");
            LOGGER.info(responseSoap());
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion del resultado esperado");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
    private void loadBody(Integer num1, Integer num2) {
        body = readFile(BODY_RESTA_PATH.getValue());
        body = String.format(body, num1, num2);
    }
}
